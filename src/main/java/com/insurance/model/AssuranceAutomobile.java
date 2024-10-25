package com.insurance.model;

import javax.persistence.*;

@Entity
public class AssuranceAutomobile extends Devis {
    private int ageConducteur;
    private String typeVehicule;
    private String utilisationVehicule;
    private String historiqueConduite;

    public AssuranceAutomobile(int ageConducteur, String typeVehicule, String utilisationVehicule, String historiqueConduite, String typeAssurance, double montant, Utilisateur utilisateur) {
        super(typeAssurance, montant, utilisateur);
        this.ageConducteur = ageConducteur;
        this.typeVehicule = typeVehicule;
        this.utilisationVehicule = utilisationVehicule;
        this.historiqueConduite = historiqueConduite;
    }

    public AssuranceAutomobile() {
    }

    public String getType() {
        return "Automobile";
    }
    @Override
    public void calculerDevis() {
        
    }
    
    // Getters and Setters

    public int getAgeConducteur() {
        return ageConducteur;
    }

    public void setAgeConducteur(int ageConducteur) {
        this.ageConducteur = ageConducteur;
    }

    public String getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(String typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public String getUtilisationVehicule() {
        return utilisationVehicule;
    }

    public void setUtilisationVehicule(String utilisationVehicule) {
        this.utilisationVehicule = utilisationVehicule;
    }

    public String getHistoriqueConduite() {
        return historiqueConduite;
    }

    public void setHistoriqueConduite(String historiqueConduite) {
        this.historiqueConduite = historiqueConduite;
    }

    
}
