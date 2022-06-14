package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "evenement")
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "evenement_name")
    private String evenementName;

    @Column(name = "photo")
    private String photo;

    @Column(name = "date_deb")
    private Instant dateDeb;

    @Column(name = "date_fin")
    private Instant dateFin;

    @Column(name = "nbr_tiket")
    private Long nbrTiket;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "reservations", "offres", "conventions", "abonnements", "evenements", "utilisateur" },
        allowSetters = true
    )
    private Partenaire partenaire;

    @ManyToMany(mappedBy = "evenements")
    @JsonIgnoreProperties(value = { "evenements", "offre", "partenaire", "utilisateur" }, allowSetters = true)
    private Set<Reservation> reservations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Evenement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvenementName() {
        return this.evenementName;
    }

    public Evenement evenementName(String evenementName) {
        this.setEvenementName(evenementName);
        return this;
    }

    public void setEvenementName(String evenementName) {
        this.evenementName = evenementName;
    }

    public String getPhoto() {
        return this.photo;
    }

    public Evenement photo(String photo) {
        this.setPhoto(photo);
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Instant getDateDeb() {
        return this.dateDeb;
    }

    public Evenement dateDeb(Instant dateDeb) {
        this.setDateDeb(dateDeb);
        return this;
    }

    public void setDateDeb(Instant dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Instant getDateFin() {
        return this.dateFin;
    }

    public Evenement dateFin(Instant dateFin) {
        this.setDateFin(dateFin);
        return this;
    }

    public void setDateFin(Instant dateFin) {
        this.dateFin = dateFin;
    }

    public Long getNbrTiket() {
        return this.nbrTiket;
    }

    public Evenement nbrTiket(Long nbrTiket) {
        this.setNbrTiket(nbrTiket);
        return this;
    }

    public void setNbrTiket(Long nbrTiket) {
        this.nbrTiket = nbrTiket;
    }

    public Partenaire getPartenaire() {
        return this.partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public Evenement partenaire(Partenaire partenaire) {
        this.setPartenaire(partenaire);
        return this;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        if (this.reservations != null) {
            this.reservations.forEach(i -> i.removeEvenement(this));
        }
        if (reservations != null) {
            reservations.forEach(i -> i.addEvenement(this));
        }
        this.reservations = reservations;
    }

    public Evenement reservations(Set<Reservation> reservations) {
        this.setReservations(reservations);
        return this;
    }

    public Evenement addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.getEvenements().add(this);
        return this;
    }

    public Evenement removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.getEvenements().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Evenement)) {
            return false;
        }
        return id != null && id.equals(((Evenement) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Evenement{" +
            "id=" + getId() +
            ", evenementName='" + getEvenementName() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", dateDeb='" + getDateDeb() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", nbrTiket=" + getNbrTiket() +
            "}";
    }
}
