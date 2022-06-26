package tn.esprit.pidev.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texte;

    @ManyToOne(cascade = CascadeType.ALL)
    private Article article;
    @ManyToOne
    Commentaire commentaire;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentaire")
    private Set<Commentaire> commentaires;
    @ManyToOne
    @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;


    public Commentaire() {
    }

    public Commentaire(Long id, String texte, Article article) {
        this.id = id;
        this.texte = texte;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public String getTexte() {
        return texte;
    }

    public Article getArticle() {
        return article;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
