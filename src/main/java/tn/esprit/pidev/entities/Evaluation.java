package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
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

    @Column(name = "questions")
    private String questions;

    @Column(name = "evaluation_date")
    private Instant evaluationDate;

    @OneToOne
    @JoinColumn(unique = true)
    private EvalType evalutionType;

    @ManyToOne
    @JsonIgnoreProperties(value = { "evaluations" }, allowSetters = true)
    private Reponse reponse;

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

    public String getQuestions() {
        return this.questions;
    }

    public Evaluation questions(String questions) {
        this.setQuestions(questions);
        return this;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
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

    public EvalType getEvalutionType() {
        return this.evalutionType;
    }

    public void setEvalutionType(EvalType evalType) {
        this.evalutionType = evalType;
    }

    public Evaluation evalutionType(EvalType evalType) {
        this.setEvalutionType(evalType);
        return this;
    }

    public Reponse getReponse() {
        return this.reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    public Evaluation reponse(Reponse reponse) {
        this.setReponse(reponse);
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
            ", questions='" + getQuestions() + "'" +
            ", evaluationDate='" + getEvaluationDate() + "'" +
            "}";
    }
}
