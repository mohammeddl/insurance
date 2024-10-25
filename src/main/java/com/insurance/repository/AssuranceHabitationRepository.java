package com.insurance.repository;

import com.insurance.model.AssuranceHabitation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AssuranceHabitationRepository extends JpaRepository<AssuranceHabitation, Long> {
}

