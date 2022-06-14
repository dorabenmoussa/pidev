package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;


/**
 * A Activite.
 */
@Entity
@Table(name = "activite")
public class Activite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "activite_name")
    private String activiteName;

    @Column(name = "photo")
    private String photo;

    @Column(name = "date_deb")
    private Instant dateDeb;

    @Column(name = "date_fin")
    private Instant dateFin;

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
    private Utilisateur utilisateur;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Activite id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActiviteName() {
        return this.activiteName;
    }

    public Activite activiteName(String activiteName) {
        this.setActiviteName(activiteName);
        return this;
    }

    public void setActiviteName(String activiteName) {
        this.activiteName = activiteName;
    }

    public String getPhoto() {
        return this.photo;
    }

    public Activite photo(String photo) {
        this.setPhoto(photo);
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Instant getDateDeb() {
        return this.dateDeb;
    }

    public Activite dateDeb(Instant dateDeb) {
        this.setDateDeb(dateDeb);
        return this;
    }

    public void setDateDeb(Instant dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Instant getDateFin() {
        return this.dateFin;
    }

    public Activite dateFin(Instant dateFin) {
        this.setDateFin(dateFin);
        return this;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Activite utilisateur(Utilisateur utilisateur) {
        this.setUtilisateur(utilisateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Activite)) {
            return false;
        }
        return id != null && id.equals(((Activite) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Activite{" +
            "id=" + getId() +
            ", activiteName='" + getActiviteName() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", dateDeb='" + getDateDeb() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            "}";
    }
}
