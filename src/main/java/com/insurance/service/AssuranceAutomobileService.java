package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.AssuranceAutomobile;
import com.insurance.repository.AssuranceAutomobileRepository;

@Service
public class AssuranceAutomobileService {
    
    @Autowired
    private AssuranceAutomobileRepository assuranceAutomobileRepository;

    public double calculerDevisAutomobile(AssuranceAutomobile auto) {
        double base = 500;
        
        if (auto.getAgeConducteur() < 25) {
            base += base * 0.10;
        }

        if (auto.getTypeVehicule().equalsIgnoreCase("luxe")) {
            base += base * 0.15;
        }

        if (auto.getUtilisationVehicule().equalsIgnoreCase("professionnelle")) {
            base += base * 0.10;
        }

        if (auto.getHistoriqueConduite().equalsIgnoreCase("sans sinistre")) {
            base -= base * 0.20;
        } else {
            base += base * 0.10;
        }

        auto.setMontant(base);

        assuranceAutomobileRepository.save(auto);
        
        return base; 
    }
}
