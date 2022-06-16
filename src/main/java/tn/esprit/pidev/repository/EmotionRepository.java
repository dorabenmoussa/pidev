package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Emotion;

@Repository
public interface EmotionRepository extends CrudRepository<Emotion, Long> {
}
