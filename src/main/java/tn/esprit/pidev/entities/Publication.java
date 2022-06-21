package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tn.esprit.pidev.entities.enumeration.LikeDislike;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "publication")
public class Publication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "pub_desc")
    private String desc;

    @Column(name = "pub_date")
    private Instant pubDate;

    @Column(name = "pub_update_date")
    private Instant pubUpdateDate;

    @Column(name = "status")
    private String status;



    @OneToMany(mappedBy = "pub")
    @JsonIgnoreProperties(value = { "pub" }, allowSetters = true)
    private Set<CommentairePub> commentairePubs = new HashSet<>();

    @OneToMany(mappedBy = "pub")
    @JsonIgnoreProperties(value = { "pub" }, allowSetters = true)
    private Set<DisLike> likes = new HashSet<>();

    @OneToMany(mappedBy = "pub")
    @JsonIgnoreProperties(value = { "pub" }, allowSetters = true)
    private Set<DisLike> dislikes = new HashSet<>();

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

    public Publication id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Publication title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return this.desc;
    }

    public Publication desc(String desc) {
        this.setDesc(desc);
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Instant getPubDate() {
        return this.pubDate;
    }

    public Publication pubDate(Instant pubDate) {
        this.setPubDate(pubDate);
        return this;
    }

    public void setPubDate(Instant pubDate) {
        this.pubDate = pubDate;
    }

    public Instant getPubUpdateDate() {
        return this.pubUpdateDate;
    }

    public Publication pubUpdateDate(Instant pubUpdateDate) {
        this.setPubUpdateDate(pubUpdateDate);
        return this;
    }

    public void setPubUpdateDate(Instant pubUpdateDate) {
        this.pubUpdateDate = pubUpdateDate;
    }

    public String getStatus() {
        return this.status;
    }

    public Publication status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Set<DisLike> getLikes() {
        return this.likes;
    }


    public void setLikes(Set<DisLike> likes) {
        if (this.likes != null) {
            this.likes.forEach(i -> i.setPub(null));
        }
        if (likes != null) {
            likes.forEach(i -> i.setPub(this));
        }
        this.likes = likes;
    }
    public Publication likes(Set<DisLike> likes) {
        this.setDislikes(likes);
        return this;
    }


    public Set<DisLike> getDislikes() {
        return this.dislikes;
    }

    public void setDislikes(DisLike dislikes) {
        this.dislikes = (Set<DisLike>) dislikes;
    }

    public void setDislikes(Set<DisLike> dislikes) {
        if (this.dislikes != null) {
            this.dislikes.forEach(i -> i.setPub(null));
        }
        if (dislikes != null) {
            dislikes.forEach(i -> i.setPub(this));
        }
        this.dislikes = dislikes;
    }

    public Publication dislikes(Set<DisLike> dislikes) {
        this.setDislikes(dislikes);
        return this;
    }

    public Set<CommentairePub> getCommentairePubs() {
        return this.commentairePubs;
    }

    public void setCommentairePubs(Set<CommentairePub> commentairePubs) {
        if (this.commentairePubs != null) {
            this.commentairePubs.forEach(i -> i.setPub(null));
        }
        if (commentairePubs != null) {
            commentairePubs.forEach(i -> i.setPub(this));
        }
        this.commentairePubs = commentairePubs;
    }

    public Publication commentairePubs(Set<CommentairePub> commentairePubs) {
        this.setCommentairePubs(commentairePubs);
        return this;
    }


/*
    public Publication addCommentairePub(CommentairePub commentairePub) {
        this.commentairePubs.add(commentairePub);
        commentairePub.setPub(this);
        return this;
    }

    public Publication removeCommentairePub(CommentairePub commentairePub) {
        this.commentairePubs.remove(commentairePub);
        commentairePub.setPub(null);
        return this;
    }
    */


    public Utilisateur getUser() {
        return this.user;
    }

    public void setUser(Utilisateur utilisateur) {
        this.user = utilisateur;
    }

    public Publication user(Utilisateur utilisateur) {
        this.setUser(utilisateur);
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Publication)) {
            return false;
        }
        return id != null && id.equals(((Publication) o).id);
    }

    @Override
    public int hashCode() {

        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Publication{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", desc='" + getDesc() + "'" +
            ", pubDate='" + getPubDate() + "'" +
            ", pubUpdateDate='" + getPubUpdateDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", likes='" + getLikes() + "'" +
            ", dislikes='" + getDislikes() + "'" +
            "}";
    }
}
