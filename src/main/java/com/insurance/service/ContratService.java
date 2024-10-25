package com.insurance.service;

import com.insurance.model.Contrat;
import com.insurance.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    public Contrat createContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }
}
