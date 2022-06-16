package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.NotificationToken;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the NotificationToken entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationTokenRepository extends JpaRepository<NotificationToken, Long> {}
