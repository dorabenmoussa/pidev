package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import javax.persistence.*;
import tn.esprit.pidev.entities.*;
import tn.esprit.pidev.entities.enumeration.TypeOffre;

/**
 * A Offre.
 */
@Entity
@Table(name = "offre")
public class Offre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_deb_offre")
    private Instant dateDebOffre;

    @Column(name = "date_fin_offre")
    private Instant dateFinOffre;

    @Column(name = "nbre_limit")
    private Integer nbreLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_offre")
    private TypeOffre typeOffre;

    @OneToMany(mappedBy = "offre")
    @JsonIgnoreProperties(value = { "offre" }, allowSetters = true)
    private Set<Publicite> publicites;

    @OneToMany(mappedBy = "offre")
    @JsonIgnoreProperties(value = { "evenements", "offre", "partenaire", "utilisateur" }, allowSetters = true)
    private Set<Reservation> reservations;

    @ManyToOne()
    @JsonIgnoreProperties(
        value = { "reservations", "offres", "conventions", "abonnements", "evenements", "utilisateur" },
        allowSetters = true
    )
    private Partenaire reservation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Offre id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDebOffre() {
        return this.dateDebOffre;
    }

    public Offre dateDebOffre(Instant dateDebOffre) {
        this.setDateDebOffre(dateDebOffre);
        return this;
    }

    public void setDateDebOffre(Instant dateDebOffre) {
        this.dateDebOffre = dateDebOffre;
    }

    public Instant getDateFinOffre() {
        return this.dateFinOffre;
    }

    public Offre dateFinOffre(Instant dateFinOffre) {
        this.setDateFinOffre(dateFinOffre);
        return this;
    }

    public void setDateFinOffre(Instant dateFinOffre) {
        this.dateFinOffre = dateFinOffre;
    }

    public Integer getNbreLimit() {
        return this.nbreLimit;
    }

    public Offre nbreLimit(Integer nbreLimit) {
        this.setNbreLimit(nbreLimit);
        return this;
    }

    public void setNbreLimit(Integer nbreLimit) {
        this.nbreLimit = nbreLimit;
    }

    public TypeOffre getTypeOffre() {
        return this.typeOffre;
    }

    public Offre typeOffre(TypeOffre typeOffre) {
        this.setTypeOffre(typeOffre);
        return this;
    }

    public void setTypeOffre(TypeOffre typeOffre) {
        this.typeOffre = typeOffre;
    }

    public Set<Publicite> getPublicites() {
        return this.publicites;
    }

    public void setPublicites(Set<Publicite> publicites) {
        if (this.publicites != null) {
            this.publicites.forEach(i -> i.setOffre(null));
        }
        if (publicites != null) {
            publicites.forEach(i -> i.setOffre(this));
        }
        this.publicites = publicites;
    }

    public Offre publicites(Set<Publicite> publicites) {
        this.setPublicites(publicites);
        return this;
    }

    public Offre addPublicite(Publicite publicite) {
        this.publicites.add(publicite);
        publicite.setOffre(this);
        return this;
    }

    public Offre removePublicite(Publicite publicite) {
        this.publicites.remove(publicite);
        publicite.setOffre(null);
        return this;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        if (this.reservations != null) {
            this.reservations.forEach(i -> i.setOffre(null));
        }
        if (reservations != null) {
            reservations.forEach(i -> i.setOffre(this));
        }
        this.reservations = reservations;
    }

    public Offre reservations(Set<Reservation> reservations) {
        this.setReservations(reservations);
        return this;
    }

    public Offre addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setOffre(this);
        return this;
    }

    public Offre removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setOffre(null);
        return this;
    }

    public Partenaire getReservation() {
        return this.reservation;
    }

    public void setReservation(Partenaire partenaire) {
        this.reservation = partenaire;
    }

    public Offre reservation(Partenaire partenaire) {
        this.setReservation(partenaire);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Offre)) {
            return false;
        }
        return id != null && id.equals(((Offre) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Offre{" +
            "id=" + getId() +
            ", dateDebOffre='" + getDateDebOffre() + "'" +
            ", dateFinOffre='" + getDateFinOffre() + "'" +
            ", nbreLimit=" + getNbreLimit() +
            ", typeOffre='" + getTypeOffre() + "'" +
            "}";
    }
}
