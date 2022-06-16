package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Duscution.
 */
@Entity
@Table(name = "duscution")
public class Duscution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "rel_duscution__messages",
        joinColumns = @JoinColumn(name = "duscution_id"),
        inverseJoinColumns = @JoinColumn(name = "messages_id")
    )

    @JsonIgnoreProperties(value = { "users", "duscutions" }, allowSetters = true)
    private Set<Message> messages = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Duscution id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Duscution messages(Set<Message> messages) {
        this.setMessages(messages);
        return this;
    }

    public Duscution addMessages(Message message) {
        this.messages.add(message);
        message.getDuscutions().add(this);
        return this;
    }

    public Duscution removeMessages(Message message) {
        this.messages.remove(message);
        message.getDuscutions().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Duscution)) {
            return false;
        }
        return id != null && id.equals(((Duscution) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Duscution{" +
            "id=" + getId() +
            "}";
    }
}
