package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
/**
 * A CommentairePub.
 */
@Entity
@Table(name = "commentaire_pub")
public class CommentairePub implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "cmt_date")
    private Instant cmtDate;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JsonIgnoreProperties(value = { "commentairePubs", "user" }, allowSetters = true)
    private Publication pub;



    public Long getId() {
        return this.id;
    }

    public CommentairePub id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return this.contenu;
    }

    public CommentairePub contenu(String contenu) {
        this.setContenu(contenu);
        return this;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Instant getCmtDate() {
        return this.cmtDate;
    }

    public CommentairePub cmtDate(Instant cmtDate) {
        this.setCmtDate(cmtDate);
        return this;
    }

    public void setCmtDate(Instant cmtDate) {
        this.cmtDate = cmtDate;
    }

    public String getStatus() {
        return this.status;
    }

    public CommentairePub status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Publication getPub() {
        return this.pub;
    }

    public void setPub(Publication publication) {
        this.pub = publication;
    }

    public CommentairePub pub(Publication publication) {
        this.setPub(publication);
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommentairePub)) {
            return false;
        }
        return id != null && id.equals(((CommentairePub) o).id);
    }

    @Override
    public int hashCode() {

        return getClass().hashCode();
    }


    @Override
    public String toString() {
        return "CommentairePub{" +
            "id=" + getId() +
            ", contenu='" + getContenu() + "'" +
            ", cmtDate='" + getCmtDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
