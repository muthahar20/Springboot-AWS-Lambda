package com.mtr.springbootmysqljpa.repository;

import com.mtr.springbootmysqljpa.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    public UserProfile save(UserProfile userProfileRequest);
    @Query(value = "select * from user_profile u where u.user_id = :userId", nativeQuery = true)
    public UserProfile findByUserId(String userId);



}
