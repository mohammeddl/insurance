package com.insurance.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.Utilisateur;
import com.insurance.repository.UtilisateurRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UtilisateurService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.err.println("Authentication attempt for email: " + email);

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null) {
            System.err.println("No user found with email: " + email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        System.out.println("Authentication Details:");
        System.out.println("Email: " + utilisateur.getEmail());
        System.out.println("Stored Password: " + utilisateur.getPassword());
        System.out.println("Roles: " + utilisateur.getRoles());

        return new org.springframework.security.core.userdetails.User(
                utilisateur.getEmail(),
                utilisateur.getPassword(),
                utilisateur.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toList()));
    }

    public Utilisateur registerUser(Utilisateur utilisateur) {
        utilisateur.setPassword(new BCryptPasswordEncoder().encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}
