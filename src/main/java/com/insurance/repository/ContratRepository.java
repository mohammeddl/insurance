package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.Contrat;
@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

    
} 
