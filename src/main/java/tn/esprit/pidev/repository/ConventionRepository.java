package tn.esprit.pidev.repository;

import org.springframework.data.repository.query.Param;
import tn.esprit.pidev.entities.Convention;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Spring Data SQL repository for the Convention entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConventionRepository extends JpaRepository<Convention, Long> {

    @Query(value = "select c from Convention c where c.partenaire.id = :partenaire ")
    public List<Convention> findByPartenaire(@Param("partenaire") Long partenaire);
}
