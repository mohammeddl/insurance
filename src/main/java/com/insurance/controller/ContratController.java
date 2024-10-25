package com.insurance.controller;

import com.insurance.model.AssuranceAutomobile;
import com.insurance.model.AssuranceHabitation;
import com.insurance.model.AssuranceSante;
import com.insurance.model.Contrat;
import com.insurance.model.Devis;
import com.insurance.model.Document;
import com.insurance.model.Utilisateur;
import com.insurance.service.AssuranceAutomobileService;
import com.insurance.service.AssuranceHabitationService;
import com.insurance.service.AssuranceSanteService;
import com.insurance.service.ContratService;
import com.insurance.service.DevisService;
import com.insurance.service.DocumentService;

import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/contrat")
public class ContratController {

    @Autowired
    private ContratService contratService;

    @Autowired
    private DevisService devisService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private AssuranceAutomobileService assuranceAutomobileService;

    @Autowired
    private AssuranceHabitationService assuranceHabitationService;

    @Autowired
    private AssuranceSanteService assuranceSanteService;

    @PostMapping("/create")
    public String createContrat(
            HttpSession session,
            @RequestParam("devisId") Long devisId,
            @RequestParam("dateDebut") String startDateStr,
            @RequestParam("dateFin") String endDateStr,
            @RequestParam("document") MultipartFile documentFile,
            Model model) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDebut;
        Date dateFin;
        try {
            dateDebut = dateFormat.parse(startDateStr);
            dateFin = dateFormat.parse(endDateStr);
        } catch (Exception e) {
            model.addAttribute("error", "Invalid date format");
            return "contrat";
        }

        Devis devis = devisService.findDevisById(devisId);
        if (devis == null) {
            model.addAttribute("error", "Devis not found");
            return "insurance";
        }

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("loggedInUser");
        Contrat contrat = new Contrat(dateDebut, dateFin, devis.getMontant(), utilisateur, devis);
        contratService.createContrat(contrat);

        if (!documentFile.isEmpty()) {
            String uploadDir = "/path/to/upload/directory";
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }
            String fileName = documentFile.getOriginalFilename();
            File destFile = new File(uploadPath, fileName);

            try {
                documentFile.transferTo(destFile);
                Document document = new Document("Insurance Document", destFile.getAbsolutePath(), contrat);
                documentService.saveDocument(document);

            } catch (IOException e) {
                model.addAttribute("error", "Failed to upload document");
                return "contrat";
            }
        }

        model.addAttribute("insuranceDetails", contrat);
        return "insurance";
    }

    @GetMapping("/all")
public String showAllContracts(HttpSession session, Model model) {
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("loggedInUser");
    if (utilisateur != null) {
        List<AssuranceAutomobile> assuranceAutomobiles = assuranceAutomobileService.findAssuranceAutomobileByUtilisateur_Id(utilisateur.getId());
        List<AssuranceHabitation> assuranceHabitations = assuranceHabitationService.findAssuranceHabitationByUtilisateur_Id(utilisateur.getId());
        List<AssuranceSante> assuranceSantes = assuranceSanteService.findAssuranceSanteByUtilisateur_Id(utilisateur.getId());

        model.addAttribute("assuranceAutomobiles", assuranceAutomobiles); 
        model.addAttribute("assuranceHabitations", assuranceHabitations); 
        model.addAttribute("assuranceSantes", assuranceSantes); 
    } else {
        model.addAttribute("error", "No user logged in.");
    }
    return "managementInsurance";
}

    

    @PostMapping("/update")
    public String updateDevis(
            @RequestParam("devisId") Long devisId,
            @RequestParam Map<String, String> params,
            Model model) {

        Devis existingDevis = devisService.findDevisById(devisId);
        if (existingDevis != null) {
            // Check instance type and cast to update specific fields
            if (existingDevis instanceof AssuranceAutomobile) {
                AssuranceAutomobile automobileDevis = (AssuranceAutomobile) existingDevis;
                automobileDevis.setAgeConducteur(Integer.parseInt(params.get("ageConducteur")));
                automobileDevis.setTypeVehicule(params.get("typeVehicule"));
                automobileDevis.setUtilisationVehicule(params.get("utilisationVehicule"));
                automobileDevis.setHistoriqueConduite(params.get("historiqueConduite"));
                assuranceAutomobileService.updateAssuranceAutomobile(automobileDevis);

            } else if (existingDevis instanceof AssuranceHabitation) {
                AssuranceHabitation habitationDevis = (AssuranceHabitation) existingDevis;
                habitationDevis.setValeurBien(Double.parseDouble(params.get("valeurBien")));
                habitationDevis.setTypeLogement(params.get("typeLogement"));
                habitationDevis.setLocalisation(params.get("localisation"));
                habitationDevis.setSystemeSecurite(Boolean.parseBoolean(params.get("systemeSecurite")));
                assuranceHabitationService.updateAssuranceHabitation(habitationDevis);

            } else if (existingDevis instanceof AssuranceSante) {
                AssuranceSante santeDevis = (AssuranceSante) existingDevis;
                santeDevis.setAgeAssure(Integer.parseInt(params.get("ageAssure")));
                santeDevis.setEtatSante(params.get("etatSante"));
                santeDevis.setTypeCouverture(params.get("typeCouverture"));
                assuranceSanteService.updateAssuranceSante(santeDevis);
            }

            model.addAttribute("success", "Devis updated successfully.");
        } else {
            model.addAttribute("error", "Devis not found.");
        }
        return "redirect:/contrat/all";
    }

}
