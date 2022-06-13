package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.entities.Emotion;
import tn.esprit.pidev.entities.Questionnaire;
import tn.esprit.pidev.services.api.EmotionService;
import tn.esprit.pidev.services.api.QuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class QuestionnaireController {
    @Autowired
    QuestionnaireService questionnaireService;
    @GetMapping("/getaAllQuestionnaires")
    @ResponseBody
    public List<Questionnaire> getQuestionnaires() {

        return questionnaireService.retrieveAllQuestionnaires();
    }
    @GetMapping("/retrieveQuestionnaire/{id}")
    @ResponseBody
    public Questionnaire retrieveQuestionnaire(@PathVariable("id") Long id) {
        return questionnaireService.retrieveQuestionnaire(id);
    }
    @PostMapping("/addQuestionnaire")
    @ResponseBody
    public Questionnaire addQuestionnaire(@RequestBody Questionnaire questionnaire)
    {

        return  questionnaireService.ajouterQuestionnaire(questionnaire);
    }
    @DeleteMapping("/removeQuestionnaire/{id}")
    @ResponseBody
    public void supprimerQuestionnaire(@PathVariable("id") Long id){
        questionnaireService.deleteQuestionnaire(id);
    }
    @PutMapping("/modifyQuestionnaire")
    @ResponseBody
    public Questionnaire modifierQuetionnaire(@RequestBody Questionnaire questionnaire){
        return  questionnaireService.updateQuestionnaire(questionnaire);
    }
}
