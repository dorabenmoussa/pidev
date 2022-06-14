package tn.esprit.pidev.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Questionnaire;
import tn.esprit.pidev.repository.QuestionnaireRepository;
import tn.esprit.pidev.services.api.QuestionnaireService;

import java.util.List;
import java.util.Optional;
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Override
    public List<Questionnaire> retrieveAllQuestionnaires() {
        return (List<Questionnaire>) questionnaireRepository.findAll();
    }

    @Override
    public void deleteQuestionnaire(Long id) {
        questionnaireRepository.deleteById(id);
    }

    @Override
    public Questionnaire updateQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    public Questionnaire retrieveQuestionnaire(Long id) {
        return questionnaireRepository.findById(id).get();
    }
    @Override
    public Questionnaire ajouterQuestionnaire(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
        return questionnaire;
    }
}
