package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import tn.esprit.pidev.*;

/**
 * A Publicite.
 */
@Entity
@Table(name = "publicite")
public class Publicite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_deb_publicite")
    private Instant dateDebPublicite;

    @Column(name = "date_fin_publicite")
    private Instant dateFinPublicite;

    @ManyToOne
    @JsonIgnoreProperties(value = { "publicites", "reservations", "reservation" }, allowSetters = true)
    private Offre offre;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Publicite id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDebPublicite() {
        return this.dateDebPublicite;
    }

    public Publicite dateDebPublicite(Instant dateDebPublicite) {
        this.setDateDebPublicite(dateDebPublicite);
        return this;
    }

    public void setDateDebPublicite(Instant dateDebPublicite) {
        this.dateDebPublicite = dateDebPublicite;
    }

    public Instant getDateFinPublicite() {
        return this.dateFinPublicite;
    }

    public Publicite dateFinPublicite(Instant dateFinPublicite) {
        this.setDateFinPublicite(dateFinPublicite);
        return this;
    }

    public void setDateFinPublicite(Instant dateFinPublicite) {
        this.dateFinPublicite = dateFinPublicite;
    }

    public Offre getOffre() {
        return this.offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Publicite offre(Offre offre) {
        this.setOffre(offre);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Publicite)) {
            return false;
        }
        return id != null && id.equals(((Publicite) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Publicite{" +
            "id=" + getId() +
            ", dateDebPublicite='" + getDateDebPublicite() + "'" +
            ", dateFinPublicite='" + getDateFinPublicite() + "'" +
            "}";
    }
}
