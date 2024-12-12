package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.AssuranceHabitation;
import com.insurance.repository.AssuranceHabitationRepository;
import java.util.List;

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

    public void updateAssuranceHabitation(AssuranceHabitation habitation) {
        assuranceHabitationRepository.save(habitation);
    }

    public List<AssuranceHabitation> findAssuranceHabitationByUtilisateur_Id(Long utilisateur_id) {
        return assuranceHabitationRepository.findByUtilisateur_Id(utilisateur_id);
    }

    public AssuranceHabitation findById(Long id) {
        return assuranceHabitationRepository.findById(id).orElse(null);
    }

    public void deleteAssuranceHabitation(Long id) {
        assuranceHabitationRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return assuranceHabitationRepository.existsById(id);
    }
}