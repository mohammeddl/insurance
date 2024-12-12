package com.insurance.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.UserActivity;
import com.insurance.service.ActivityLoggerService;
import com.insurance.repository.UserActivityRepository;

@Service
public class ActivityLoggerServiceImpl implements ActivityLoggerService {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Override
    public void logActivity(String username, String activity) {
        UserActivity userActivity = new UserActivity(username, activity, LocalDateTime.now());
        userActivityRepository.save(userActivity);
    }
    
}
