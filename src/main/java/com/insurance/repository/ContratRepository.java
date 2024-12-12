package com.insurance.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insurance.model.Contrat;
@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {

    public List<Contrat> findByUtilisateur_Id(Long utilisateurId);
    @Query("SELECT c FROM Contrat c JOIN FETCH c.devis WHERE c.utilisateur.id = :utilisateur_id")
    List<Contrat> findContractsWithDevisByUserId(@Param("utilisateur_id") Long userId);
} 
