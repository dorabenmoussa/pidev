package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.EvalType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EvalType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvalTypeRepository extends JpaRepository<EvalType, Long> {}
