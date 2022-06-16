package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.Convention;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Convention entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConventionRepository extends JpaRepository<Convention, Long> {}
