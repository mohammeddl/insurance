package com.insurance.repository;

import com.insurance.model.AssuranceAutomobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuranceAutomobileRepository extends JpaRepository<AssuranceAutomobile, Long> {
}

