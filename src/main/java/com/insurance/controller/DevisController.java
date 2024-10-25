package com.insurance.controller;

import com.insurance.model.AssuranceAutomobile;
import com.insurance.model.AssuranceHabitation;
import com.insurance.model.AssuranceSante;
import com.insurance.service.AssuranceAutomobileService;
import com.insurance.service.AssuranceHabitationService;
import com.insurance.service.AssuranceSanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/insurance")
public class DevisController {

    @Autowired
    private AssuranceAutomobileService assuranceAutomobileService;

    @Autowired
    private AssuranceHabitationService assuranceHabitationService;

    @Autowired
    private AssuranceSanteService assuranceSanteService;

    @PostMapping("/contrat/create")
    public String createContrat(@RequestParam String insuranceType,
                                @RequestParam(required = false) Integer ageConducteur,
                                @RequestParam(required = false) String typeVehicule,
                                @RequestParam(required = false) String utilisationVehicule,
                                @RequestParam(required = false) String historiqueConduite,
                                @RequestParam(required = false) Double valeurBien,
                                @RequestParam(required = false) String typeLogement,
                                @RequestParam(required = false) String localisation,
                                @RequestParam(required = false) Boolean systemeSecurite,
                                @RequestParam(required = false) Integer ageAssure,
                                @RequestParam(required = false) String etatSante,
                                @RequestParam(required = false) String typeCouverture,
                                Model model) {
    
        double finalPrice = 0.0;
    
        if (insuranceType.equalsIgnoreCase("Automobile")) {
            AssuranceAutomobile auto = new AssuranceAutomobile(ageConducteur, typeVehicule, utilisationVehicule, historiqueConduite, "Automobile", 0);
            finalPrice = assuranceAutomobileService.calculerDevisAutomobile(auto);
        } else if (insuranceType.equalsIgnoreCase("Habitation")) {
            AssuranceHabitation habitation = new AssuranceHabitation(valeurBien, typeLogement, localisation, systemeSecurite, "Habitation", 0);
            finalPrice = assuranceHabitationService.calculerDevisHabitation(habitation);
        } else if (insuranceType.equalsIgnoreCase("Sante")) {
            AssuranceSante sante = new AssuranceSante(ageAssure, etatSante, typeCouverture, "Sante", 0);
            finalPrice = assuranceSanteService.calculerDevisSante(sante);
        }
    
        model.addAttribute("finalPrice", finalPrice);
        return "summary";  
    }
}
