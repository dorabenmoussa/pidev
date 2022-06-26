package tn.esprit.pidev.entities;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;
import java.util.List;

@Entity
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(
            name = "QUESTION",
            joinColumns = @JoinColumn(name = "QUESTIONNAIRE_ID")
    )
    private List<String> questions;
    @ElementCollection
    @CollectionTable(
            name = "REPONSE",
            joinColumns = @JoinColumn(name = "REPONSE_ID")
    )
    private List<String> Reponses;
    @ManyToOne
    @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;

    public Questionnaire( ){

    }

    public Questionnaire(Long id, List<String> questions, Utilisateur utilisateur) {
        this.id = id;
        this.questions = questions;
        this.utilisateur = utilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<String> getReponses() {
        return Reponses;
    }

    public void setReponses(List<String> reponses) {
        Reponses = reponses;
    }
}
