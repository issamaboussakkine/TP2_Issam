package com.issam.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "ESPACES_ISSAM")
public class EspaceIssam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISSAM_ID")
    private Long id;

    @NotBlank(message = "Veuillez saisir un nom pour l'espace")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    @Column(name = "ISSAM_NOM", length = 50, nullable = false)
    private String nom;

    @Min(value = 1, message = "La capacité minimale est de 1 personne")
    @Max(value = 500, message = "La capacité ne peut pas dépasser 500 personnes")
    @Column(name = "ISSAM_CAPACITE")
    private int capacite;

    @Column(name = "ISSAM_DISPONIBLE")
    private boolean disponible;

    @Column(name = "ISSAM_LOCALISATION", length = 100)
    private String localisation;

    public EspaceIssam() {}

    public EspaceIssam(String nom, int capacite, boolean disponible) {
        this.nom = nom;
        this.capacite = capacite;
        this.disponible = disponible;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }

    @Override
    public String toString() {
        return "Espace Issam [Réf=" + id + ", Désignation=" + nom + ", Places=" + capacite +
                ", Libre=" + disponible + ", Localisation=" + localisation + "]";
    }
}