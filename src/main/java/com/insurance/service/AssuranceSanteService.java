package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.AssuranceSante;
import com.insurance.repository.AssuranceSanteRepository;

@Service
public class AssuranceSanteService {

    @Autowired
    private AssuranceSanteRepository assuranceSanteRepository;
    
    public double calculerDevisSante(AssuranceSante sante) {
        double base = 150;

        if (sante.getAgeAssure() > 60) {
            base += base * 0.20;
        }

        if (sante.getEtatSante().equalsIgnoreCase("maladies chroniques")) {
            base += base * 0.30;
        }

        if (sante.getTypeCouverture().equalsIgnoreCase("premium")) {
            base += base * 0.05;
        } else {
            base -= base * 0.10;
        }

        sante.setMontant(base);

        assuranceSanteRepository.save(sante);
        
        return base; 
    }
}
