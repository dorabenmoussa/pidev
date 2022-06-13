package tn.esprit.pidev.entities;

import javax.persistence.*;

@Entity
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String icon;
    @ManyToOne
    @JoinColumn(name="utilisateur_id")
    private Utilisateur utilisateur;
    @ManyToOne(cascade = CascadeType.ALL)
    private Article article;
    public Emotion() {

    }

    public Emotion(Long id, String type, String icon) {
        this.id = id;
        this.type = type;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
