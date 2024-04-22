package com.mtr.springbootmysqljpa;

import com.mtr.springbootmysqljpa.model.UserProfile;
import com.mtr.springbootmysqljpa.repository.UserProfileRepository;
import com.mtr.springbootmysqljpa.service.UserProfileService;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
public class UserProfileServiceTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private UserProfileService userProfileService;


    public UserProfile crateUserProfileTest(){
        LocalDateTime localdate = LocalDateTime.now();
        UserProfile userPro = new UserProfile();
        userPro.setUser_id("0ebb6438-3e89-4be5-9186-de8c15d9721d");
        userPro.setFirst_name("Mtr");
        userPro.setLast_login_date(localdate);
        userPro.setLast_name("4433222");
        userPro.setUser_status("ACTIVE");
        userPro.setCreated_date(localdate);
        return userPro;
    }

   // @Test
    public void getUserProfileByUserId(){
        UserProfile userPro = crateUserProfileTest();
        when( userProfileRepository.findByUserId("0ebb6438-3e89-4be5-9186-de8c15d9721d")).thenReturn(userPro);
        UserProfile getUserProfileFromService = userProfileService.findByUserId("0ebb6438-3e89-4be5-9186-de8c15d9721d");
       // verify( userProfileRepository, times(1)).findByUserId("0ebb6438-3e89-4be5-9186-de8c15d9721d");
        assertNotNull(userPro);
        assertEquals("0ebb6438-3e89-4be5-9186-de8c15d9721", getUserProfileFromService.getUser_id());
        assertEquals("Mtr", getUserProfileFromService.getFirst_name());
        assertEquals("ACTIVE", getUserProfileFromService.getUser_status());
    }


    @Test
    public void remoteWebclientTest(){
        WebClient webClientMock = Mockito.mock(WebClient.class);

      //  when(webClientMock.get()).thenReturn(Mockito.mock(WebClient.RequestHeadersUriSpec.class));
     //   when(webClientMock.get().uri(Mockito.anyString())).thenReturn(Mockito.mock(WebClient.RequestHeadersSpec.class));
        when(webClientMock.get().uri(Mockito.anyString()).retrieve()).thenReturn(Mockito.mock(WebClient.ResponseSpec.class));
        when(webClientMock.get().uri(Mockito.anyString()).retrieve().bodyToMono(String.class)).thenReturn(Mono.just("Mocked response"));
        userProfileService.setWebClient(webClientMock);

        Mono<String> responseMono = userProfileService.getPublicApi();


    }











    /*
    @Test
    public void saveUserProfile(){
        UserProfile userPro = crateUserProfileTest();
     //   when(userProfileRepository.save( userPro).thenReturn(userPro);
        UserProfile getUserProfile = userProfileService.createUserProfile(userPro);
        verify( userProfileRepository, times(1)).save(userPro);
        assertNotNull(getUserProfile);
        assertEquals("777263", getUserProfile.getUser_id());
        assertEquals("Mtr", getUserProfile.getFirst_name());
    }

*/


}
