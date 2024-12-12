package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.Devis;
import java.util.List;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {

    //find devis with all sub-entities by utilisateur id
    List<Devis> findDevisByUtilisateurId(Long id);
    
}
