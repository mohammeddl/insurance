package com.insurance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insurance")
public class insuranceController {
    

    @GetMapping
    public String insurance() {
        return "insurance";
    }
}
