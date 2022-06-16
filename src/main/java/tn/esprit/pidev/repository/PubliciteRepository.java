package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.Publicite;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Publicite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PubliciteRepository extends JpaRepository<Publicite, Long> {}
