package com.insurance.repository;

import com.insurance.model.AssuranceSante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssuranceSanteRepository extends JpaRepository<AssuranceSante, Long> {
    public List<AssuranceSante> findByUtilisateur_Id(Long utilisateur_id);
}
