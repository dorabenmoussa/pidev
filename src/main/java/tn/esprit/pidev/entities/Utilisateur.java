package tn.esprit.pidev.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "utilisateur")
    private List<Article> articles;
    @OneToMany(mappedBy = "utilisateur")
    private List<Commentaire> commentaires;
    @OneToMany(mappedBy = "utilisateur")
    private List<Questionnaire> questionnaires;
    @OneToMany(mappedBy = "utilisateur")
    private List<Emotion> emotions;

    public Utilisateur( ) {

    }

    public Utilisateur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Utilisateur(Long id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}

