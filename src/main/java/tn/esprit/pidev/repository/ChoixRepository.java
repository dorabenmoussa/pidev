package tn.esprit.pidev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Choix;

/**
 * Spring Data SQL repository for the Choix entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChoixRepository extends JpaRepository<Choix, Long> {}
