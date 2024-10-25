package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.AssuranceHabitation;
import com.insurance.repository.AssuranceHabitationRepository;

@Service
public class AssuranceHabitationService {

    @Autowired
    private AssuranceHabitationRepository assuranceHabitationRepository;

    public double calculerDevisHabitation(AssuranceHabitation habitation) {
        double base = 300;

        if (habitation.getTypeLogement().equalsIgnoreCase("maison")) {
            base += base * 0.02;
        }

        if (habitation.getLocalisation().equalsIgnoreCase("zone à risque")) {
            base += base * 0.05;
        }

        if (habitation.getValeurBien() > 200000) {
            base += base * 0.10;
        }

        if (habitation.isSystemeSecurite()) {
            base -= base * 0.15;
        } else {
            base += base * 0.15;
        }

        habitation.setMontant(base);

        assuranceHabitationRepository.save(habitation);
        
        return base; 
    }
}