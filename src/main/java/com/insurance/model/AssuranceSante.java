package com.insurance.model;

import javax.persistence.*;

@Entity
public class AssuranceSante extends Devis {
    private int ageAssure;
    private String etatSante;
    private String typeCouverture;


    public AssuranceSante(int ageAssure, String etatSante, String typeCouverture, String typeAssurance, double montant) {
        super(typeAssurance, montant);
        this.ageAssure = ageAssure;
        this.etatSante = etatSante;
        this.typeCouverture = typeCouverture;
    }

    public AssuranceSante() {
    }

    public int getAgeAssure() {
        return ageAssure;
    }

    public void setAgeAssure(int ageAssure) {
        this.ageAssure = ageAssure;
    }

    public String getEtatSante() {
        return etatSante;
    }

    public void setEtatSante(String etatSante) {
        this.etatSante = etatSante;
    }

    public String getTypeCouverture() {
        return typeCouverture;
    }

    public void setTypeCouverture(String typeCouverture) {
        this.typeCouverture = typeCouverture;
    }


    @Override
    public void calculerDevis() {
        
    }


}
