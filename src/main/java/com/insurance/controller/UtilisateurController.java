package com.insurance.controller;

import com.insurance.enums.Role;
import com.insurance.model.Utilisateur;
import com.insurance.service.UtilisateurService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        utilisateur.setPassword(new BCryptPasswordEncoder().encode(utilisateur.getPassword()));
        utilisateur.setRoles(Set.of(Role.ROLE_USER));
        utilisateurService.registerUser(utilisateur);
        model.addAttribute("message", "Registration successful!");
        return "login";
    }

    // @GetMapping("/login")
    // public String showLoginForm(Model model) {
    //     return "login";
    // }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; 
    }

}
