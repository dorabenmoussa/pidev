package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Partenaire.
 */
@Entity
@Table(name = "partenaire")
public class Partenaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "partenaire_name")
    private String partenaireName;

    @Column(name = "partenaire_type")
    private String partenaireType;

    @Column(name = "matriculation")
    private String matriculation;

    @OneToMany(mappedBy = "partenaire")
    @JsonIgnoreProperties(value = { "evenements", "offre", "partenaire", "utilisateur" }, allowSetters = true)
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "reservation")
    @JsonIgnoreProperties(value = { "publicites", "reservations", "reservation" }, allowSetters = true)
    private Set<Offre> offres = new HashSet<>();

    @OneToMany(mappedBy = "partenaire")
    @JsonIgnoreProperties(value = { "partenaire" }, allowSetters = true)
    private Set<Convention> conventions = new HashSet<>();

    @OneToMany(mappedBy = "partenaire")
    @JsonIgnoreProperties(value = { "partenaire", "utilisateur" }, allowSetters = true)
    private Set<Abonnements> abonnements = new HashSet<>();

    @OneToMany(mappedBy = "partenaire")
    @JsonIgnoreProperties(value = { "partenaire", "reservations" }, allowSetters = true)
    private Set<Evenement> evenements = new HashSet<>();

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

    public Partenaire id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartenaireName() {
        return this.partenaireName;
    }

    public Partenaire partenaireName(String partenaireName) {
        this.setPartenaireName(partenaireName);
        return this;
    }

    public void setPartenaireName(String partenaireName) {
        this.partenaireName = partenaireName;
    }

    public String getPartenaireType() {
        return this.partenaireType;
    }

    public Partenaire partenaireType(String partenaireType) {
        this.setPartenaireType(partenaireType);
        return this;
    }

    public void setPartenaireType(String partenaireType) {
        this.partenaireType = partenaireType;
    }

    public String getMatriculation() {
        return this.matriculation;
    }

    public Partenaire matriculation(String matriculation) {
        this.setMatriculation(matriculation);
        return this;
    }

    public void setMatriculation(String matriculation) {
        this.matriculation = matriculation;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        if (this.reservations != null) {
            this.reservations.forEach(i -> i.setPartenaire(null));
        }
        if (reservations != null) {
            reservations.forEach(i -> i.setPartenaire(this));
        }
        this.reservations = reservations;
    }

    public Partenaire reservations(Set<Reservation> reservations) {
        this.setReservations(reservations);
        return this;
    }

    public Partenaire addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setPartenaire(this);
        return this;
    }

    public Partenaire removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setPartenaire(null);
        return this;
    }

    public Set<Offre> getOffres() {
        return this.offres;
    }

    public void setOffres(Set<Offre> offres) {
        if (this.offres != null) {
            this.offres.forEach(i -> i.setReservation(null));
        }
        if (offres != null) {
            offres.forEach(i -> i.setReservation(this));
        }
        this.offres = offres;
    }

    public Partenaire offres(Set<Offre> offres) {
        this.setOffres(offres);
        return this;
    }

    public Partenaire addOffre(Offre offre) {
        this.offres.add(offre);
        offre.setReservation(this);
        return this;
    }

    public Partenaire removeOffre(Offre offre) {
        this.offres.remove(offre);
        offre.setReservation(null);
        return this;
    }

    public Set<Convention> getConventions() {
        return this.conventions;
    }

    public void setConventions(Set<Convention> conventions) {
        if (this.conventions != null) {
            this.conventions.forEach(i -> i.setPartenaire(null));
        }
        if (conventions != null) {
            conventions.forEach(i -> i.setPartenaire(this));
        }
        this.conventions = conventions;
    }

    public Partenaire conventions(Set<Convention> conventions) {
        this.setConventions(conventions);
        return this;
    }

    public Partenaire addConventions(Convention convention) {
        this.conventions.add(convention);
        convention.setPartenaire(this);
        return this;
    }

    public Partenaire removeConventions(Convention convention) {
        this.conventions.remove(convention);
        convention.setPartenaire(null);
        return this;
    }

    public Set<Abonnements> getAbonnements() {
        return this.abonnements;
    }

    public void setAbonnements(Set<Abonnements> abonnements) {
        if (this.abonnements != null) {
            this.abonnements.forEach(i -> i.setPartenaire(null));
        }
        if (abonnements != null) {
            abonnements.forEach(i -> i.setPartenaire(this));
        }
        this.abonnements = abonnements;
    }

    public Partenaire abonnements(Set<Abonnements> abonnements) {
        this.setAbonnements(abonnements);
        return this;
    }

    public Partenaire addAbonnements(Abonnements abonnements) {
        this.abonnements.add(abonnements);
        abonnements.setPartenaire(this);
        return this;
    }

    public Partenaire removeAbonnements(Abonnements abonnements) {
        this.abonnements.remove(abonnements);
        abonnements.setPartenaire(null);
        return this;
    }

    public Set<Evenement> getEvenements() {
        return this.evenements;
    }

    public void setEvenements(Set<Evenement> evenements) {
        if (this.evenements != null) {
            this.evenements.forEach(i -> i.setPartenaire(null));
        }
        if (evenements != null) {
            evenements.forEach(i -> i.setPartenaire(this));
        }
        this.evenements = evenements;
    }

    public Partenaire evenements(Set<Evenement> evenements) {
        this.setEvenements(evenements);
        return this;
    }

    public Partenaire addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
        evenement.setPartenaire(this);
        return this;
    }

    public Partenaire removeEvenement(Evenement evenement) {
        this.evenements.remove(evenement);
        evenement.setPartenaire(null);
        return this;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Partenaire utilisateur(Utilisateur utilisateur) {
        this.setUtilisateur(utilisateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Partenaire)) {
            return false;
        }
        return id != null && id.equals(((Partenaire) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Partenaire{" +
            "id=" + getId() +
            ", partenaireName='" + getPartenaireName() + "'" +
            ", partenaireType='" + getPartenaireType() + "'" +
            ", matriculation='" + getMatriculation() + "'" +
            "}";
    }
}
