package com.insurance.service;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.Utilisateur;
import com.insurance.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur getLoggedInUser() {
        return utilisateurRepository.findById(1L).orElse(null);  
    }

    public Utilisateur getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return findByEmail(username); 
        }
        return null;
    }

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email); 
    }

    public Utilisateur registerUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur loginByUser(String email, String password) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur != null && utilisateur.getPassword().equals(password)) {
            return utilisateur;
        }
        return null;

    }
}
