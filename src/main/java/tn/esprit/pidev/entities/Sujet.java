package tn.esprit.pidev.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sujet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @OneToMany(mappedBy="sujet")
    private List<Article> articles;

    public Sujet() {
    }

    public Sujet(String label) {
        this.label = label;
    }

    public Sujet(Long id, String label) {
        this.id = id;
        this.label = label;
    }


    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
