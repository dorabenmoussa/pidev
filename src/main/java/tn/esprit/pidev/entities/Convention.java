package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;


/**
 * A Convention.
 */
@Entity
@Table(name = "convention")
public class Convention implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_deb_convention")
    private Instant dateDebConvention;

    @Column(name = "date_fin_convention")
    private Instant dateFinConvention;

    @Column(name = "renouvelable")
    private Boolean renouvelable;

    @Column(name = "duree_renouvellement")
    private Integer dureeRenouvellement;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "reservations", "offres", "conventions", "abonnements", "evenements", "utilisateur" },
        allowSetters = true
    )
    private Partenaire partenaire;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Convention id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDebConvention() {
        return this.dateDebConvention;
    }

    public Convention dateDebConvention(Instant dateDebConvention) {
        this.setDateDebConvention(dateDebConvention);
        return this;
    }

    public void setDateDebConvention(Instant dateDebConvention) {
        this.dateDebConvention = dateDebConvention;
    }

    public Instant getDateFinConvention() {
        return this.dateFinConvention;
    }

    public Convention dateFinConvention(Instant dateFinConvention) {
        this.setDateFinConvention(dateFinConvention);
        return this;
    }

    public void setDateFinConvention(Instant dateFinConvention) {
        this.dateFinConvention = dateFinConvention;
    }

    public Boolean getRenouvelable() {
        return this.renouvelable;
    }

    public Convention renouvelable(Boolean renouvelable) {
        this.setRenouvelable(renouvelable);
        return this;
    }

    public void setRenouvelable(Boolean renouvelable) {
        this.renouvelable = renouvelable;
    }

    public Integer getDureeRenouvellement() {
        return this.dureeRenouvellement;
    }

    public Convention dureeRenouvellement(Integer dureeRenouvellement) {
        this.setDureeRenouvellement(dureeRenouvellement);
        return this;
    }

    public void setDureeRenouvellement(Integer dureeRenouvellement) {
        this.dureeRenouvellement = dureeRenouvellement;
    }

    public Partenaire getPartenaire() {
        return this.partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public Convention partenaire(Partenaire partenaire) {
        this.setPartenaire(partenaire);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Convention)) {
            return false;
        }
        return id != null && id.equals(((Convention) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Convention{" +
            "id=" + getId() +
            ", dateDebConvention='" + getDateDebConvention() + "'" +
            ", dateFinConvention='" + getDateFinConvention() + "'" +
            ", renouvelable='" + getRenouvelable() + "'" +
            ", dureeRenouvellement=" + getDureeRenouvellement() +
            "}";
    }
}
