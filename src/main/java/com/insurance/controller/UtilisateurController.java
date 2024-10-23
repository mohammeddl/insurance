package com.insurance.controller;

import com.insurance.model.Utilisateur;
import com.insurance.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/insurance/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "register";  
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model) {
        utilisateurService.registerUser(utilisateur);
        model.addAttribute("message", "Registration successful!");
        return "login";  
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "login";  
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model) {
        if (utilisateurService.loginByUser(utilisateur.getEmail(), utilisateur.getPassword()) != null) {
            model.addAttribute("message", "Login successful!");
            return "index";
        } else {
            model.addAttribute("message", "Login failed!");
            return "login";
        }
    }
    
}
