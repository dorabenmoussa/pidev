package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.Duscution;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class DuscutionRepositoryWithBagRelationshipsImpl implements DuscutionRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Duscution> fetchBagRelationships(Optional<Duscution> duscution) {
        return duscution.map(this::fetchMessages);
    }

    @Override
    public Page<Duscution> fetchBagRelationships(Page<Duscution> duscutions) {
        return new PageImpl<>(fetchBagRelationships(duscutions.getContent()), duscutions.getPageable(), duscutions.getTotalElements());
    }

    @Override
    public List<Duscution> fetchBagRelationships(List<Duscution> duscutions) {
        return Optional.of(duscutions).map(this::fetchMessages).orElse(Collections.emptyList());
    }

    Duscution fetchMessages(Duscution result) {
        return entityManager
            .createQuery(
                "select duscution from Duscution duscution left join fetch duscution.messages where duscution is :duscution",
                Duscution.class
            )
            .setParameter("duscution", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Duscution> fetchMessages(List<Duscution> duscutions) {
        return entityManager
            .createQuery(
                "select distinct duscution from Duscution duscution left join fetch duscution.messages where duscution in :duscutions",
                Duscution.class
            )
            .setParameter("duscutions", duscutions)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
