package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.entities.Commentaire;
import tn.esprit.pidev.entities.Emotion;
import tn.esprit.pidev.services.api.CommentaireService;
import tn.esprit.pidev.services.api.EmotionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmotionController {
    @Autowired
    EmotionService emotionService;
    @GetMapping("/getaAllEmotions")
    @ResponseBody
    public List<Emotion> getEmotions() {

        return emotionService.retrieveAllEmotions();
    }
    @GetMapping("/retrieveEmotion/{id}")
    @ResponseBody
    public Emotion retrieveEmotion(@PathVariable("id") Long id) {
        return emotionService.retrieveEmotion(id);
    }
    @PostMapping("/addEmotion")
    @ResponseBody
    public Emotion addEmotion(@RequestBody Emotion emotion)
    {

        return  emotionService.ajouterEmotion(emotion);
    }
    @DeleteMapping("/removeEmotion/{id}")
    @ResponseBody
    public void supprimerEmotion(@PathVariable("id") Long id){
        emotionService.deleteEmotion(id);
    }
    @PutMapping("/modifyEmotion")
    @ResponseBody
    public Emotion modifierEmotion(@RequestBody Emotion emotion){
        return  emotionService.updateEmotion(emotion);
    }
}
