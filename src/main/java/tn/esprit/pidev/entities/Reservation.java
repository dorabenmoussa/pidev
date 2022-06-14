package tn.esprit.pidev.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.springframework.data.domain.Persistable;
import tn.esprit.pidev.entities.enumeration.Type;

/**
 * A Reservation.
 */
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "reservation_date")
    private Instant reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_type")
    private Type reservationType;

    @Transient
    private boolean isPersisted;

    @ManyToMany
    @JoinTable(
        name = "rel_reservation__evenement",
        joinColumns = @JoinColumn(name = "reservation_id"),
        inverseJoinColumns = @JoinColumn(name = "evenement_id")
    )
    @JsonIgnoreProperties(value = { "partenaire", "reservations" }, allowSetters = true)
    private Set<Evenement> evenements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "publicites", "reservations", "reservation" }, allowSetters = true)
    private Offre offre;

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

    public String getId() {
        return this.id;
    }

    public Reservation id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getReservationDate() {
        return this.reservationDate;
    }

    public Reservation reservationDate(Instant reservationDate) {
        this.setReservationDate(reservationDate);
        return this;
    }

    public void setReservationDate(Instant reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Type getReservationType() {
        return this.reservationType;
    }

    public Reservation reservationType(Type reservationType) {
        this.setReservationType(reservationType);
        return this;
    }

    public void setReservationType(Type reservationType) {
        this.reservationType = reservationType;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public Reservation setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    public Set<Evenement> getEvenements() {
        return this.evenements;
    }

    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }

    public Reservation evenements(Set<Evenement> evenements) {
        this.setEvenements(evenements);
        return this;
    }

    public Reservation addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
        evenement.getReservations().add(this);
        return this;
    }

    public Reservation removeEvenement(Evenement evenement) {
        this.evenements.remove(evenement);
        evenement.getReservations().remove(this);
        return this;
    }

    public Offre getOffre() {
        return this.offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Reservation offre(Offre offre) {
        this.setOffre(offre);
        return this;
    }

    public Partenaire getPartenaire() {
        return this.partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public Reservation partenaire(Partenaire partenaire) {
        this.setPartenaire(partenaire);
        return this;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Reservation utilisateur(Utilisateur utilisateur) {
        this.setUtilisateur(utilisateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reservation)) {
            return false;
        }
        return id != null && id.equals(((Reservation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Reservation{" +
            "id=" + getId() +
            ", reservationDate='" + getReservationDate() + "'" +
            ", reservationType='" + getReservationType() + "'" +
            "}";
    }
}
