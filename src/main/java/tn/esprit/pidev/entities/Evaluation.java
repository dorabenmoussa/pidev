package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tn.esprit.pidev.entities.Choix;
import tn.esprit.pidev.entities.Question;
import tn.esprit.pidev.entities.Utilisateur;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Evaluation.
 */
@Entity
@Table(name = "evaluation")
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "evaluation_date")
    private Instant evaluationDate;

    @Column(name = "note_final")
    private Integer noteFinal;

    @OneToMany(mappedBy = "evaluation")
    @JsonIgnoreProperties(value = { "evaluation" }, allowSetters = true)
    private Set<Question> questions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "evaluations" }, allowSetters = true)
    private Choix choix;

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

    public Evaluation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public Evaluation description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getEvaluationDate() {
        return this.evaluationDate;
    }

    public Evaluation evaluationDate(Instant evaluationDate) {
        this.setEvaluationDate(evaluationDate);
        return this;
    }

    public void setEvaluationDate(Instant evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public Integer getNoteFinal() {
        return this.noteFinal;
    }

    public Evaluation noteFinal(Integer noteFinal) {
        this.setNoteFinal(noteFinal);
        return this;
    }

    public void setNoteFinal(Integer noteFinal) {
        this.noteFinal = noteFinal;
    }

    public Set<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(Set<Question> questions) {
        if (this.questions != null) {
            this.questions.forEach(i -> i.setEvaluation(null));
        }
        if (questions != null) {
            questions.forEach(i -> i.setEvaluation(this));
        }
        this.questions = questions;
    }

    public Evaluation questions(Set<Question> questions) {
        this.setQuestions(questions);
        return this;
    }

    public Evaluation addQuestions(Question question) {
        this.questions.add(question);
        question.setEvaluation(this);
        return this;
    }

    public Evaluation removeQuestions(Question question) {
        this.questions.remove(question);
        question.setEvaluation(null);
        return this;
    }

    public Choix getChoix() {
        return this.choix;
    }

    public void setChoix(Choix choix) {
        this.choix = choix;
    }

    public Evaluation choix(Choix choix) {
        this.setChoix(choix);
        return this;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Evaluation utilisateur(Utilisateur utilisateur) {
        this.setUtilisateur(utilisateur);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Evaluation)) {
            return false;
        }
        return id != null && id.equals(((Evaluation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + getId() +
                ", description='" + getDescription() + "'" +
                ", evaluationDate='" + getEvaluationDate() + "'" +
                ", noteFinal=" + getNoteFinal() +
                "}";
    }
}
