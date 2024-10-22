package com.insurance.model;

import javax.persistence.*;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeDocument;
    private String chemin;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private Contrat contrat;

    public Document(String typeDocument, String chemin, Contrat contrat) {
        this.typeDocument = typeDocument;
        this.chemin = chemin;
        this.contrat = contrat;
    }

    public Document() {
    }

    public void uploaderDocument() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

}
