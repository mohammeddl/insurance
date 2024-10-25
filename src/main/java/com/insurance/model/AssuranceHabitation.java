package com.insurance.model;

import javax.persistence.*;

@Entity
public class AssuranceHabitation extends Devis {
    private double valeurBien;
    private String typeLogement;
    private String localisation;
    private boolean systemeSecurite;

    public AssuranceHabitation(double valeurBien, String typeLogement, String localisation, boolean systemeSecurite, String typeAssurance, double montant, Utilisateur utilisateur) {
        super(typeAssurance, montant, utilisateur);
        this.valeurBien = valeurBien;
        this.typeLogement = typeLogement;
        this.localisation = localisation;
        this.systemeSecurite = systemeSecurite;
    }

    public AssuranceHabitation() {
    }

    public String getType() {
        return "Habitation";
    }

    @Override
    public void calculerDevis() {
       
    }

    public double getValeurBien() {
        return valeurBien;
    }

    public void setValeurBien(double valeurBien) {
        this.valeurBien = valeurBien;
    }

    public String getTypeLogement() {
        return typeLogement;
    }

    public void setTypeLogement(String typeLogement) {
        this.typeLogement = typeLogement;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public boolean isSystemeSecurite() {
        return systemeSecurite;
    }

    public void setSystemeSecurite(boolean systemeSecurite) {
        this.systemeSecurite = systemeSecurite;
    }

}

