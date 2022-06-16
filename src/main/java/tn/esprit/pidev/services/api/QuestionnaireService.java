package tn.esprit.pidev.services.api;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Questionnaire;

import java.util.List;
import java.util.Optional;

@Service
public interface QuestionnaireService {

    List<Questionnaire> retrieveAllQuestionnaires();
    void deleteQuestionnaire( Long id);
    Questionnaire updateQuestionnaire(Questionnaire questionnaire);
    Questionnaire retrieveQuestionnaire(Long id);

    Questionnaire ajouterQuestionnaire(Questionnaire questionnaire);
}
