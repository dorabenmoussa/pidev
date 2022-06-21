package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tn.esprit.pidev.entities.enumeration.LikeDislike;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dislike")
public class DisLike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nb")
    private int nb;

    @Enumerated(EnumType.STRING)
    @Column(name = "react_type")
    private LikeDislike reactType;

    @ManyToOne
    @JsonIgnoreProperties(
            value = {
                    "badges",
                    "evaluations",
                    "publications",
                    "fcmTokens",
                    "reservations",
                    "notifications",
                    "partenaires",
                    "activites",
                    "abonnements",
                    "departement",
                    "messages",
            },
            allowSetters = true
    )
    private Utilisateur user;

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public DisLike id(Long id) {
        this.setId(id);
        return this;
    }

    @ManyToOne
    @JsonIgnoreProperties(value = { "commentairePubs", "user" }, allowSetters = true)
    private Publication pub;



    public Publication getPub() {
        return this.pub;
    }

    public void setPub(Publication publication) {
        this.pub = publication;
    }

    public DisLike pub(Publication publication) {
        this.setPub(publication);
        return this;
    }

    public int getNb() {
        return this.nb;
    }
    public void setNb(int nb) {
        this.nb = nb;
    }

    public DisLike nb(int nb) {
        this.setNb(nb);
        return this;
    }



    public LikeDislike getReactType() {
        return this.reactType;
    }
    public void setReactType(LikeDislike reactType) {
        this.reactType = reactType;
    }

    public DisLike reactType(LikeDislike reactType) {
        this.setReactType(reactType);
        return this;
    }

    public Utilisateur getUser() {
        return this.user;
    }

    public void setUser(Utilisateur utilisateur) {
        this.user = utilisateur;
    }

    public DisLike user(Utilisateur utilisateur) {
        this.setUser(utilisateur);
        return this;
    }




}
