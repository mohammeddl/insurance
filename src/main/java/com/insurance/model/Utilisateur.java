package com.insurance.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private String adresse;
    private String email;
    private String telephone;
    private String password;
    
    @OneToMany(mappedBy = "utilisateur")
    private List<Contrat> contrats;

    public Utilisateur(String nom, String adresse, String email, String telephone, String password) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }

    public Utilisateur(){}

    // Getters and Setters

    public void creerCompte() {
       
    }

    public void seConnecter() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }


    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

}
