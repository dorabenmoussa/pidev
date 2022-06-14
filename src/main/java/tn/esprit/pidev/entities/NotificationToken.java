package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A NotificationToken.
 */
@Entity
@Table(name = "notification_token")
public class NotificationToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fcm")
    private String fcm;

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

    public NotificationToken id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFcm() {
        return this.fcm;
    }

    public NotificationToken fcm(String fcm) {
        this.setFcm(fcm);
        return this;
    }

    public void setFcm(String fcm) {
        this.fcm = fcm;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public NotificationToken utilisateur(Utilisateur utilisateur) {
        this.setUtilisateur(utilisateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NotificationToken)) {
            return false;
        }
        return id != null && id.equals(((NotificationToken) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NotificationToken{" +
            "id=" + getId() +
            ", fcm='" + getFcm() + "'" +
            "}";
    }
}
