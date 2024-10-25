package com.insurance.repository;

import com.insurance.model.AssuranceSante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuranceSanteRepository extends JpaRepository<AssuranceSante, Long> {
}
