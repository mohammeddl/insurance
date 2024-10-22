package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.model.Contrat;

public interface ContratRepository extends JpaRepository<Contrat, Long> {

    
} 
