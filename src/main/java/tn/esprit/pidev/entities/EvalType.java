package tn.esprit.pidev.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A EvalType.
 */
@Entity
@Table(name = "eval_type")
public class EvalType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "jhi_desc")
    private String desc;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EvalType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public EvalType nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesc() {
        return this.desc;
    }

    public EvalType desc(String desc) {
        this.setDesc(desc);
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvalType)) {
            return false;
        }
        return id != null && id.equals(((EvalType) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvalType{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
