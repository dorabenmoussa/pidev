package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.CommentairePub;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CommentairePub entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentairePubRepository extends JpaRepository<CommentairePub, Long> {}
