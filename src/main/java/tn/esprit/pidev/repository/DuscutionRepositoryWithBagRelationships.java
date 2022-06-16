package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.Duscution;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface DuscutionRepositoryWithBagRelationships {
    Optional<Duscution> fetchBagRelationships(Optional<Duscution> duscution);

    List<Duscution> fetchBagRelationships(List<Duscution> duscutions);

    Page<Duscution> fetchBagRelationships(Page<Duscution> duscutions);
}
