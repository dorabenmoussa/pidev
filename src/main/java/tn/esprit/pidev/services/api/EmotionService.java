package tn.esprit.pidev.services.api;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Emotion;

import java.util.List;
import java.util.Optional;

@Service
public interface EmotionService {

    List<Emotion> retrieveAllEmotions();
    void deleteEmotion( Long id);
    Emotion updateEmotion(Emotion emotion);
    Emotion retrieveEmotion(Long id);

    Emotion ajouterEmotion(Emotion emotion);
}
