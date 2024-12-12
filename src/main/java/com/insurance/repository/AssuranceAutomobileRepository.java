package com.insurance.repository;

import com.insurance.model.AssuranceAutomobile;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuranceAutomobileRepository extends JpaRepository<AssuranceAutomobile, Long> {

    public List<AssuranceAutomobile> findByUtilisateur_Id(Long utilisateur_id);
}

