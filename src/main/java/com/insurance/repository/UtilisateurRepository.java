package com.insurance.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}