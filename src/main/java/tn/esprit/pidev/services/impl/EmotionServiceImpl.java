package tn.esprit.pidev.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Emotion;
import tn.esprit.pidev.repository.EmotionRepository;
import tn.esprit.pidev.services.api.EmotionService;

import java.util.List;
import java.util.Optional;
@Service
public class EmotionServiceImpl implements EmotionService {
    @Autowired
    private EmotionRepository emotionRepository;
    @Override
    public List<Emotion> retrieveAllEmotions() {
        return (List<Emotion>) emotionRepository.findAll();
    }

    @Override
    public void deleteEmotion(Long id) {
        emotionRepository.deleteById(id);
    }

    @Override
    public Emotion updateEmotion(Emotion emotion) {
        return emotionRepository.save(emotion);

    }

    @Override
    public Emotion retrieveEmotion(Long id) {
        return emotionRepository.findById(id).get();
    }
    @Override
    public Emotion ajouterEmotion(Emotion emotion) {
        emotionRepository.save(emotion);
        return emotion;
    }
}
