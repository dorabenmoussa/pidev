package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.Abonnements;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Abonnements entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbonnementsRepository extends JpaRepository<Abonnements, Long> {}
