package com.insurance.service;

import com.insurance.model.Contrat;
import com.insurance.model.Document;
import com.insurance.repository.ContratRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    public Contrat createContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public List<Contrat> getContractsByUser(Long userId) {
        List<Contrat> contrats = contratRepository.findByUtilisateur_Id(userId);
        return contrats;
    }

    public Contrat findById(Long id) {
        return contratRepository.findById(id).orElse(null);
    }

    public void updateContrat(Contrat contrat) {
        contratRepository.save(contrat);
    }

    public List<Contrat> getContractsWithDevisByUserId(Long userId) {
        return contratRepository.findContractsWithDevisByUserId(userId);
    }

}
