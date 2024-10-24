package com.insurance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insurance")
public class TestController {
    

    @GetMapping("/test")
    public String showIndex(Model model){
        return "test";
    }
}
