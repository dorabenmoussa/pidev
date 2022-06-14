package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;


/**
 * A Abonnements.
 */
@Entity
@Table(name = "abonnements")
public class Abonnements implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "abonnement_name")
    private String abonnementName;

    @Column(name = "date_deb")
    private Instant dateDeb;

    @Column(name = "date_fin")
    private Instant dateFin;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "reservations", "offres", "conventions", "abonnements", "evenements", "utilisateur" },
        allowSetters = true
    )
    private Partenaire partenaire;

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

    public Abonnements id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbonnementName() {
        return this.abonnementName;
    }

    public Abonnements abonnementName(String abonnementName) {
        this.setAbonnementName(abonnementName);
        return this;
    }

    public void setAbonnementName(String abonnementName) {
        this.abonnementName = abonnementName;
    }

    public Instant getDateDeb() {
        return this.dateDeb;
    }

    public Abonnements dateDeb(Instant dateDeb) {
        this.setDateDeb(dateDeb);
        return this;
    }

    public void setDateDeb(Instant dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Instant getDateFin() {
        return this.dateFin;
    }

    public Abonnements dateFin(Instant dateFin) {
        this.setDateFin(dateFin);
        return this;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public Partenaire getPartenaire() {
        return this.partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public Abonnements partenaire(Partenaire partenaire) {
        this.setPartenaire(partenaire);
        return this;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Abonnements utilisateur(Utilisateur utilisateur) {
        this.setUtilisateur(utilisateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Abonnements)) {
            return false;
        }
        return id != null && id.equals(((Abonnements) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Abonnements{" +
            "id=" + getId() +
            ", abonnementName='" + getAbonnementName() + "'" +
            ", dateDeb='" + getDateDeb() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            "}";
    }
}
