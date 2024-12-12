package com.insurance.controller;

import com.insurance.model.UserActivity;
import com.insurance.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @GetMapping("/admin/activities")
    public String viewActivities(Model model) {
        List<UserActivity> activities = userActivityRepository.findAll();
        model.addAttribute("activities", activities);
        return "admin-activities"; 
    }
}
