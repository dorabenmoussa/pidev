package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Message.
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToMany
    @JoinTable(
        name = "rel_message__users",
        joinColumns = @JoinColumn(name = "message_id"),
        inverseJoinColumns = @JoinColumn(name = "users_id")
    )

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
    private Set<Utilisateur> users = new HashSet<>();

    @ManyToMany(mappedBy = "messages")
    @JsonIgnoreProperties(value = { "messages" }, allowSetters = true)
    private Set<Duscution> duscutions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Message id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public Message content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Utilisateur> getUsers() {
        return this.users;
    }

    public void setUsers(Set<Utilisateur> utilisateurs) {
        this.users = utilisateurs;
    }

    public Message users(Set<Utilisateur> utilisateurs) {
        this.setUsers(utilisateurs);
        return this;
    }

    public Message addUsers(Utilisateur utilisateur) {
        this.users.add(utilisateur);
        utilisateur.getMessages().add(this);
        return this;
    }

    public Message removeUsers(Utilisateur utilisateur) {
        this.users.remove(utilisateur);
        utilisateur.getMessages().remove(this);
        return this;
    }

    public Set<Duscution> getDuscutions() {
        return this.duscutions;
    }

    public void setDuscutions(Set<Duscution> duscutions) {
        if (this.duscutions != null) {
            this.duscutions.forEach(i -> i.removeMessages(this));
        }
        if (duscutions != null) {
            duscutions.forEach(i -> i.addMessages(this));
        }
        this.duscutions = duscutions;
    }

    public Message duscutions(Set<Duscution> duscutions) {
        this.setDuscutions(duscutions);
        return this;
    }

    public Message addDuscution(Duscution duscution) {
        this.duscutions.add(duscution);
        duscution.getMessages().add(this);
        return this;
    }

    public Message removeDuscution(Duscution duscution) {
        this.duscutions.remove(duscution);
        duscution.getMessages().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Message)) {
            return false;
        }
        return id != null && id.equals(((Message) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Message{" +
            "id=" + getId() +
            ", content='" + getContent() + "'" +
            "}";
    }
}
