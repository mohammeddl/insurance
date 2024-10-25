package com.insurance.controller;

import com.insurance.model.AssuranceAutomobile;
import com.insurance.model.AssuranceHabitation;
import com.insurance.model.AssuranceSante;
import com.insurance.model.Utilisateur;
import com.insurance.service.AssuranceAutomobileService;
import com.insurance.service.AssuranceHabitationService;
import com.insurance.service.AssuranceSanteService;
import com.insurance.service.UtilisateurService;

import javax.servlet.http.HttpSession;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/contrat/create")
    public String createContrat(
            @RequestParam String insuranceType,
            HttpSession session,
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

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("loggedInUser");
        double finalPrice = 0.0;
        if (utilisateur == null) {
            return "/insurance/utilisateur/login";
        }

        if (insuranceType.equalsIgnoreCase("Automobile")) {
            AssuranceAutomobile auto = new AssuranceAutomobile(ageConducteur, typeVehicule, utilisationVehicule,
                    historiqueConduite, "Automobile", 0, utilisateur);
            finalPrice = assuranceAutomobileService.calculerDevisAutomobile(auto);
            model.addAttribute("insuranceDetails", auto);
        } else if (insuranceType.equalsIgnoreCase("Habitation")) {
            AssuranceHabitation habitation = new AssuranceHabitation(valeurBien, typeLogement, localisation,
                    systemeSecurite, "Habitation", 0, utilisateur);
            finalPrice = assuranceHabitationService.calculerDevisHabitation(habitation);
            model.addAttribute("insuranceDetails", habitation);
        } else if (insuranceType.equalsIgnoreCase("Sante")) {
            AssuranceSante sante = new AssuranceSante(ageAssure, etatSante, typeCouverture, "Sante", 0, utilisateur);
            finalPrice = assuranceSanteService.calculerDevisSante(sante);
            model.addAttribute("insuranceDetails", sante);
        }

        model.addAttribute("finalPrice", finalPrice);
        model.addAttribute("insuranceType", insuranceType);


        return "contrat";
    }

    @PostMapping("/insurance/updateInsurance")
    public String updateInsurance(
            @RequestParam("insuranceType") String insuranceType,
            @RequestParam("insuranceId") Long insuranceId,
            @RequestParam Map<String, String> formParams,
            Model model) {

        switch (insuranceType) {
            case "Automobile":
                AssuranceAutomobile automobile = assuranceAutomobileService.findById(insuranceId);
                if (automobile != null) {
                    automobile.setAgeConducteur(Integer.parseInt(formParams.get("ageConducteur")));
                    automobile.setTypeVehicule(formParams.get("typeVehicule"));
                    automobile.setUtilisationVehicule(formParams.get("utilisationVehicule"));
                    automobile.setHistoriqueConduite(formParams.get("historiqueConduite"));
                    assuranceAutomobileService.updateAssuranceAutomobile(automobile);
                }
                break;
            case "Habitation":
                AssuranceHabitation habitation = assuranceHabitationService.findById(insuranceId);
                if (habitation != null) {
                    habitation.setValeurBien(Double.parseDouble(formParams.get("valeurBien")));
                    habitation.setTypeLogement(formParams.get("typeLogement"));
                    habitation.setLocalisation(formParams.get("localisation"));
                    habitation.setSystemeSecurite(Boolean.parseBoolean(formParams.get("systemeSecurite")));
                    assuranceHabitationService.updateAssuranceHabitation(habitation);
                }
                break;
            case "Sante":
                AssuranceSante sante = assuranceSanteService.findById(insuranceId);
                if (sante != null) {
                    sante.setAgeAssure(Integer.parseInt(formParams.get("ageAssure")));
                    sante.setEtatSante(formParams.get("etatSante"));
                    sante.setTypeCouverture(formParams.get("typeCouverture"));
                    assuranceSanteService.updateAssuranceSante(sante);
                }
                break;
            default:
                model.addAttribute("error", "Unknown insurance type.");
                return "managementInsurance";
        }

        model.addAttribute("success", "Insurance updated successfully.");
        return "redirect:/contrat/all";
    }

    @GetMapping("/home")
    public String showInsuranceDetails(HttpSession session, Model model) {
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("loggedInUser");
        if (utilisateur == null) {
            return "redirect:/insurance/utilisateur/login";
        }else
        return "insurance";
    }

    @PostMapping("/deleteInsurance")
    public String deleteInsurance(
            @RequestParam("insuranceId") Long insuranceId,
            Model model) {
    
        String insuranceType = findInsuranceTypeById(insuranceId);
    
        switch (insuranceType) {
            case "Automobile":
                assuranceAutomobileService.deleteAssuranceAutomobile(insuranceId);
                break;
            case "Habitation":
                assuranceHabitationService.deleteAssuranceHabitation(insuranceId);
                break;
            case "Sante":
                assuranceSanteService.deleteAssuranceSante(insuranceId);
                break;
            default:
                model.addAttribute("error", "Unknown insurance type.");
                return "managementInsurance";
        }
    
        model.addAttribute("success", "Insurance deleted successfully.");
        return "redirect:/insurance/home";
    }
    

    public String findInsuranceTypeById(Long insuranceId) {
        if (assuranceAutomobileService.existsById(insuranceId)) {
            return "Automobile";
        } else if (assuranceHabitationService.existsById(insuranceId)) {
            return "Habitation";
        } else if (assuranceSanteService.existsById(insuranceId)) {
            return "Sante";
        } else {
            return "Unknown";
        }
    }

}
