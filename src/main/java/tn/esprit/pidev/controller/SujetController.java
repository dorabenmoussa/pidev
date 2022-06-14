package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.entities.Questionnaire;
import tn.esprit.pidev.entities.Sujet;
import tn.esprit.pidev.services.api.QuestionnaireService;
import tn.esprit.pidev.services.api.SujetService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SujetController {
    @Autowired
    SujetService sujetService;
    @GetMapping("/getaAllSujet")
    @ResponseBody
    public List<Sujet> getSujets() {

        return sujetService.retrieveAllSujets();
    }
    @GetMapping("/retrieveSujet/{id}")
    @ResponseBody
    public Sujet retrieveSujet(@PathVariable("id") Long id) {
        return sujetService.retrieveSujet(id);
    }
    @PostMapping("/addSujet")
    @ResponseBody
    public Sujet addSujet(@RequestBody Sujet sujet)
    {

        return  sujetService.ajouterSujet(sujet);
    }
    @DeleteMapping("/removeSujet/{id}")
    @ResponseBody
    public void supprimerSujet(@PathVariable("id") Long id){
        sujetService.deleteSujet(id);
    }
    @PutMapping("/modifySujet")
    @ResponseBody
    public Sujet modifierSujet(@RequestBody Sujet sujet){
        return  sujetService.updateSujet(sujet);
    }
}
