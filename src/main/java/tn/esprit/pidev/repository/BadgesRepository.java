package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.Badges;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Badges entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BadgesRepository extends JpaRepository<Badges, Long> {}
