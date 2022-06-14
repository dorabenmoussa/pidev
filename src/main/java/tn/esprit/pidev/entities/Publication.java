package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tn.esprit.pidev.entities.enumeration.LikeDislike;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Publication.
 */
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

    @Column(name = "jhi_desc")
    private String desc;

    @Column(name = "pub_date")
    private Instant pubDate;

    @Column(name = "pub_update_date")
    private Instant pubUpdateDate;

    @Column(name = "status")
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(name = "likes")
    private LikeDislike likes;

    @Enumerated(EnumType.STRING)
    @Column(name = "dislikes")
    private LikeDislike dislikes;

    @OneToMany(mappedBy = "pub")
    @JsonIgnoreProperties(value = { "pub" }, allowSetters = true)
    private Set<CommentairePub> commentairePubs = new HashSet<>();

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

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

    public LikeDislike getLikes() {
        return this.likes;
    }

    public Publication likes(LikeDislike likes) {
        this.setLikes(likes);
        return this;
    }

    public void setLikes(LikeDislike likes) {
        this.likes = likes;
    }

    public LikeDislike getDislikes() {
        return this.dislikes;
    }

    public Publication dislikes(LikeDislike dislikes) {
        this.setDislikes(dislikes);
        return this;
    }

    public void setDislikes(LikeDislike dislikes) {
        this.dislikes = dislikes;
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

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

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
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
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
