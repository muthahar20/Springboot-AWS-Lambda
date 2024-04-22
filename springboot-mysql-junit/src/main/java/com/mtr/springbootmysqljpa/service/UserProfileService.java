package com.mtr.springbootmysqljpa.service;

import com.mtr.springbootmysqljpa.model.UserProfile;
import com.mtr.springbootmysqljpa.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
@EnableScheduling
public class UserProfileService {


    @Autowired
    UserProfileRepository userRepository;
    private WebClient webClient;

    public UserProfile createUserProfile(UserProfile userProfile) {
        return userRepository.save(userProfile);
    }

    // @Scheduled(fixedRate = 20000)
    public List<UserProfile> getAllUsersProfiles() {
        List<UserProfile> response = userRepository.findAll();
        log.info(" getAllUserProfile : " + response);
        return response;
    }

    public UserProfile findByUserId(String userId) {
        UserProfile response = userRepository.findByUserId(userId);
        log.info(" findByUserId : " + response);
        return response;
    }

    // @Scheduled(fixedRate = 2000)
    public void addUserProfile() {
        LocalDateTime localdate = LocalDateTime.now();
        UserProfile userPro = UserProfile.builder()
                .user_id("777263")
                .first_name("mtr")
                .last_login_date(localdate)
                .last_name("4433222")
                .user_status("ACTIVE")
                .created_date(localdate)
                .build();
        userRepository.save(userPro);
    }

    //@Scheduled(fixedRate = 2000)
    public void addUserProfile1() {
        LocalDateTime localdate = LocalDateTime.now();
        for (int i = 0; i <= 5; i++) {
            UserProfile userPro = UserProfile.builder()
                    //.user_id("777263")
                    .first_name("mtr" + i)
                    .last_login_date(null)
                    .last_name("4433222" + i)
                    .user_status("ACTIVE")
                    .created_date(localdate)
                    .build();
        }
    }





    public  Mono<String> getPublicApi() {
     //   AtomicReference<Boolean> response1 = new AtomicReference<>(false);
        Mono<String> response = WebClient
                .builder()
                //  .filter("Error Message")
                .build()
                .get()
                //  .uri("https://jsonplaceholder.typicode.com/posts")
                .uri("https://api.restful-api.dev/objects")
                .retrieve()
                // extract the response body as a Mono<String>

                .bodyToMono(String.class);
        response.subscribe(
                successResponse -> {
                    System.out.println("Successful Response: " + successResponse);
                },
                error -> {
                    System.err.println("Error during request: " + error);

                },
                () -> {
                    System.out.println("Request completed successfully");

                }
        );
        return response;
    }


    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public WebClient getWebClient() {
        return webClient;
    }
}
