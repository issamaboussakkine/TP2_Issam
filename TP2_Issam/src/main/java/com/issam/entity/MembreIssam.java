package com.issam.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "MEMBRES_ISSAM")
public class MembreIssam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISSAM_ID")
    private Long id;

    @NotBlank(message = "Le prénom du membre est requis")
    @Size(min = 2, max = 50, message = "Le prénom doit faire entre 2 et 50 caractères")
    @Column(name = "ISSAM_PRENOM", length = 50, nullable = false)
    private String prenom;

    @NotBlank(message = "Le nom de famille est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit contenir 2 à 50 caractères")
    @Column(name = "ISSAM_NOM", length = 50, nullable = false)
    private String nom;

    @Email(message = "Format d'email invalide")
    @NotBlank(message = "L'adresse email est nécessaire")
    @Column(name = "ISSAM_EMAIL", length = 100, unique = true, nullable = false)
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Le téléphone doit contenir 10 chiffres")
    @Column(name = "ISSAM_TELEPHONE", length = 15)
    private String telephone;

    @Column(name = "ISSAM_ACTIF")
    private boolean actif;

    @Column(name = "ISSAM_DATE_INSCRIPTION")
    private LocalDate dateInscription;

    public MembreIssam() {}

    public MembreIssam(String prenom, String nom, String email, String telephone, boolean actif) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.actif = actif;
        this.dateInscription = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public boolean isActif() { return actif; }
    public void setActif(boolean actif) { this.actif = actif; }

    public LocalDate getDateInscription() { return dateInscription; }
    public void setDateInscription(LocalDate dateInscription) { this.dateInscription = dateInscription; }

    @Override
    public String toString() {
        return "Membre Issam [N°" + id + ", " + prenom + " " + nom + ", " + email + ", actif=" + actif + "]";
    }
}