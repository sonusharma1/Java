package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

}
