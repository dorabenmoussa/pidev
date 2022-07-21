package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import tn.esprit.pidev.entities.enumeration.Role;

/**
 * A Utilisateur.
 */
@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties(value = { "utilisateur" }, allowSetters = true)
    private Set<Badges> badges = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties(value = { "questions", "choix", "utilisateur" }, allowSetters = true)
    private Set<Evaluation> evaluations = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties(value = { "commentairePubs", "user" }, allowSetters = true)
    private Set<Publication> publications = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties(value = { "utilisateur" }, allowSetters = true)
    private Set<NotificationToken> fcmTokens = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties(value = { "evenements", "offre", "partenaire", "utilisateur" }, allowSetters = true)
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties(value = { "utilisateur" }, allowSetters = true)
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties(
            value = { "reservations", "offres", "conventions", "abonnements", "evenements", "utilisateur" },
            allowSetters = true
    )
    private Set<Partenaire> partenaires = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties(value = { "utilisateur" }, allowSetters = true)
    private Set<Activite> activites = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties(value = { "partenaire", "utilisateur" }, allowSetters = true)
    private Set<Abonnements> abonnements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "utilisateurs", "entreprise" }, allowSetters = true)
    private Departement departement;

    @ManyToMany(mappedBy = "users")
    @JsonIgnoreProperties(value = { "users", "duscutions" }, allowSetters = true)
    private Set<Message> messages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Utilisateur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Utilisateur firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Utilisateur lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return this.role;
    }

    public Utilisateur role(Role role) {
        this.setRole(role);
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return this.email;
    }

    public Utilisateur email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public Utilisateur password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public Utilisateur jobTitle(String jobTitle) {
        this.setJobTitle(jobTitle);
        return this;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return this.phone;
    }

    public Utilisateur phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Badges> getBadges() {
        return this.badges;
    }

    public void setBadges(Set<Badges> badges) {
        if (this.badges != null) {
            this.badges.forEach(i -> i.setUtilisateur(null));
        }
        if (badges != null) {
            badges.forEach(i -> i.setUtilisateur(this));
        }
        this.badges = badges;
    }

    public Utilisateur badges(Set<Badges> badges) {
        this.setBadges(badges);
        return this;
    }

    public Utilisateur addBadges(Badges badges) {
        this.badges.add(badges);
        badges.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeBadges(Badges badges) {
        this.badges.remove(badges);
        badges.setUtilisateur(null);
        return this;
    }

    public Set<Evaluation> getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        if (this.evaluations != null) {
            this.evaluations.forEach(i -> i.setUtilisateur(null));
        }
        if (evaluations != null) {
            evaluations.forEach(i -> i.setUtilisateur(this));
        }
        this.evaluations = evaluations;
    }

    public Utilisateur evaluations(Set<Evaluation> evaluations) {
        this.setEvaluations(evaluations);
        return this;
    }

    public Utilisateur addEvaluations(Evaluation evaluation) {
        this.evaluations.add(evaluation);
        evaluation.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeEvaluations(Evaluation evaluation) {
        this.evaluations.remove(evaluation);
        evaluation.setUtilisateur(null);
        return this;
    }

    public Set<Publication> getPublications() {
        return this.publications;
    }

    public void setPublications(Set<Publication> publications) {
        if (this.publications != null) {
            this.publications.forEach(i -> i.setUser(null));
        }
        if (publications != null) {
            publications.forEach(i -> i.setUser(this));
        }
        this.publications = publications;
    }

    public Utilisateur publications(Set<Publication> publications) {
        this.setPublications(publications);
        return this;
    }

    public Utilisateur addPublication(Publication publication) {
        this.publications.add(publication);
        publication.setUser(this);
        return this;
    }

    public Utilisateur removePublication(Publication publication) {
        this.publications.remove(publication);
        publication.setUser(null);
        return this;
    }

    public Set<NotificationToken> getFcmTokens() {
        return this.fcmTokens;
    }

    public void setFcmTokens(Set<NotificationToken> notificationTokens) {
        if (this.fcmTokens != null) {
            this.fcmTokens.forEach(i -> i.setUtilisateur(null));
        }
        if (notificationTokens != null) {
            notificationTokens.forEach(i -> i.setUtilisateur(this));
        }
        this.fcmTokens = notificationTokens;
    }

    public Utilisateur fcmTokens(Set<NotificationToken> notificationTokens) {
        this.setFcmTokens(notificationTokens);
        return this;
    }

    public Utilisateur addFcmTokens(NotificationToken notificationToken) {
        this.fcmTokens.add(notificationToken);
        notificationToken.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeFcmTokens(NotificationToken notificationToken) {
        this.fcmTokens.remove(notificationToken);
        notificationToken.setUtilisateur(null);
        return this;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        if (this.reservations != null) {
            this.reservations.forEach(i -> i.setUtilisateur(null));
        }
        if (reservations != null) {
            reservations.forEach(i -> i.setUtilisateur(this));
        }
        this.reservations = reservations;
    }

    public Utilisateur reservations(Set<Reservation> reservations) {
        this.setReservations(reservations);
        return this;
    }

    public Utilisateur addReservations(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeReservations(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setUtilisateur(null);
        return this;
    }

    public Set<Notification> getNotifications() {
        return this.notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        if (this.notifications != null) {
            this.notifications.forEach(i -> i.setUtilisateur(null));
        }
        if (notifications != null) {
            notifications.forEach(i -> i.setUtilisateur(this));
        }
        this.notifications = notifications;
    }

    public Utilisateur notifications(Set<Notification> notifications) {
        this.setNotifications(notifications);
        return this;
    }

    public Utilisateur addNotifications(Notification notification) {
        this.notifications.add(notification);
        notification.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeNotifications(Notification notification) {
        this.notifications.remove(notification);
        notification.setUtilisateur(null);
        return this;
    }

    public Set<Partenaire> getPartenaires() {
        return this.partenaires;
    }

    public void setPartenaires(Set<Partenaire> partenaires) {
        if (this.partenaires != null) {
            this.partenaires.forEach(i -> i.setUtilisateur(null));
        }
        if (partenaires != null) {
            partenaires.forEach(i -> i.setUtilisateur(this));
        }
        this.partenaires = partenaires;
    }

    public Utilisateur partenaires(Set<Partenaire> partenaires) {
        this.setPartenaires(partenaires);
        return this;
    }

    public Utilisateur addPartenaire(Partenaire partenaire) {
        this.partenaires.add(partenaire);
        partenaire.setUtilisateur(this);
        return this;
    }

    public Utilisateur removePartenaire(Partenaire partenaire) {
        this.partenaires.remove(partenaire);
        partenaire.setUtilisateur(null);
        return this;
    }

    public Set<Activite> getActivites() {
        return this.activites;
    }

    public void setActivites(Set<Activite> activites) {
        if (this.activites != null) {
            this.activites.forEach(i -> i.setUtilisateur(null));
        }
        if (activites != null) {
            activites.forEach(i -> i.setUtilisateur(this));
        }
        this.activites = activites;
    }

    public Utilisateur activites(Set<Activite> activites) {
        this.setActivites(activites);
        return this;
    }

    public Utilisateur addActivite(Activite activite) {
        this.activites.add(activite);
        activite.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeActivite(Activite activite) {
        this.activites.remove(activite);
        activite.setUtilisateur(null);
        return this;
    }

    public Set<Abonnements> getAbonnements() {
        return this.abonnements;
    }

    public void setAbonnements(Set<Abonnements> abonnements) {
        if (this.abonnements != null) {
            this.abonnements.forEach(i -> i.setUtilisateur(null));
        }
        if (abonnements != null) {
            abonnements.forEach(i -> i.setUtilisateur(this));
        }
        this.abonnements = abonnements;
    }

    public Utilisateur abonnements(Set<Abonnements> abonnements) {
        this.setAbonnements(abonnements);
        return this;
    }

    public Utilisateur addAbonnements(Abonnements abonnements) {
        this.abonnements.add(abonnements);
        abonnements.setUtilisateur(this);
        return this;
    }

    public Utilisateur removeAbonnements(Abonnements abonnements) {
        this.abonnements.remove(abonnements);
        abonnements.setUtilisateur(null);
        return this;
    }

    public Departement getDepartement() {
        return this.departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Utilisateur departement(Departement departement) {
        this.setDepartement(departement);
        return this;
    }

    public Set<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(Set<Message> messages) {
        if (this.messages != null) {
            this.messages.forEach(i -> i.removeUsers(this));
        }
        if (messages != null) {
            messages.forEach(i -> i.addUsers(this));
        }
        this.messages = messages;
    }

    public Utilisateur messages(Set<Message> messages) {
        this.setMessages(messages);
        return this;
    }

    public Utilisateur addMessages(Message message) {
        this.messages.add(message);
        message.getUsers().add(this);
        return this;
    }

    public Utilisateur removeMessages(Message message) {
        this.messages.remove(message);
        message.getUsers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Utilisateur)) {
            return false;
        }
        return id != null && id.equals(((Utilisateur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", role='" + getRole() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", jobTitle='" + getJobTitle() + "'" +
                ", phone='" + getPhone() + "'" +
                "}";
    }
}
