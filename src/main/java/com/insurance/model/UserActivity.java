package com.insurance.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String activity;
    private LocalDateTime timestamp;

    public UserActivity(String username, String activity, LocalDateTime timestamp) {
        this.username = username;
        this.activity = activity;
        this.timestamp = timestamp;
    }

    public UserActivity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    
    
}
