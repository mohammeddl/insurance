package com.insurance.repository;

import com.insurance.model.AssuranceHabitation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AssuranceHabitationRepository extends JpaRepository<AssuranceHabitation, Long> {

    public List<AssuranceHabitation> findByUtilisateur_Id(Long utilisateur_id);
}

