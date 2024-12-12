package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.model.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    
}
