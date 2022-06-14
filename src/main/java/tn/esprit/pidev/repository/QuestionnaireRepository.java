package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Questionnaire;
import tn.esprit.pidev.entities.Sujet;

@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long> {
}
