package tn.esprit.pidev.entities;

import javax.persistence.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;


    private String nom_article;
    private String texte;

    @ManyToOne
    @JoinColumn(name="sujet_id")
    private Sujet sujet;

    @ManyToOne
    @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;

    public Article() {
    }

    public Article(Long articleId, String nom_article, String texte) {
        this.articleId = articleId;
        this.nom_article = nom_article;
        this.texte = texte;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }



    public Long getArticleId() {
        return articleId;
    }

    public String getNom_article() {
        return nom_article;
    }

    public String getTexte() {
        return texte;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
