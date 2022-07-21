package tn.esprit.pidev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Choix.
 */
@Entity
@Table(name = "choix")
public class Choix implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "choix_1")
    private String choix1;

    @Column(name = "choix_2")
    private String choix2;

    @Column(name = "choix_3")
    private String choix3;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "note")
    private Integer note;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @OneToMany(mappedBy = "choix")
    @JsonIgnoreProperties(value = { "questions", "choix", "utilisateur" }, allowSetters = true)
    private Set<Evaluation> evaluations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Choix id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChoix1() {
        return this.choix1;
    }

    public Choix choix1(String choix1) {
        this.setChoix1(choix1);
        return this;
    }

    public void setChoix1(String choix1) {
        this.choix1 = choix1;
    }

    public String getChoix2() {
        return this.choix2;
    }

    public Choix choix2(String choix2) {
        this.setChoix2(choix2);
        return this;
    }

    public void setChoix2(String choix2) {
        this.choix2 = choix2;
    }

    public String getChoix3() {
        return this.choix3;
    }

    public Choix choix3(String choix3) {
        this.setChoix3(choix3);
        return this;
    }

    public void setChoix3(String choix3) {
        this.choix3 = choix3;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public Choix correctAnswer(String correctAnswer) {
        this.setCorrectAnswer(correctAnswer);
        return this;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getNote() {
        return this.note;
    }

    public Choix note(Integer note) {
        this.setNote(note);
        return this;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Boolean getIsCorrect() {
        return this.isCorrect;
    }

    public Choix isCorrect(Boolean isCorrect) {
        this.setIsCorrect(isCorrect);
        return this;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Set<Evaluation> getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        if (this.evaluations != null) {
            this.evaluations.forEach(i -> i.setChoix(null));
        }
        if (evaluations != null) {
            evaluations.forEach(i -> i.setChoix(this));
        }
        this.evaluations = evaluations;
    }

    public Choix evaluations(Set<Evaluation> evaluations) {
        this.setEvaluations(evaluations);
        return this;
    }

    public Choix addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
        evaluation.setChoix(this);
        return this;
    }

    public Choix removeEvaluation(Evaluation evaluation) {
        this.evaluations.remove(evaluation);
        evaluation.setChoix(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Choix)) {
            return false;
        }
        return id != null && id.equals(((Choix) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Choix{" +
            "id=" + getId() +
            ", choix1='" + getChoix1() + "'" +
            ", choix2='" + getChoix2() + "'" +
            ", choix3='" + getChoix3() + "'" +
            ", correctAnswer='" + getCorrectAnswer() + "'" +
            ", note=" + getNote() +
            ", isCorrect='" + getIsCorrect() + "'" +
            "}";
    }
}
