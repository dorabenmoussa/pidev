package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.NotificationToken;
import tn.esprit.pidev.repository.NotificationTokenRepository;
import tn.esprit.pidev.services.api.NotificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link NotificationToken}.
 */
@Service
@Transactional
public class NotificationTokenServiceImpl implements NotificationTokenService {

    private final Logger log = LoggerFactory.getLogger(NotificationTokenServiceImpl.class);

    private final NotificationTokenRepository notificationTokenRepository;

    public NotificationTokenServiceImpl(NotificationTokenRepository notificationTokenRepository) {
        this.notificationTokenRepository = notificationTokenRepository;
    }

    @Override
    public NotificationToken save(NotificationToken notificationToken) {
        log.debug("Request to save NotificationToken : {}", notificationToken);
        return notificationTokenRepository.save(notificationToken);
    }

    @Override
    public NotificationToken update(NotificationToken notificationToken) {
        log.debug("Request to save NotificationToken : {}", notificationToken);
        return notificationTokenRepository.save(notificationToken);
    }

    @Override
    public Optional<NotificationToken> partialUpdate(NotificationToken notificationToken) {
        log.debug("Request to partially update NotificationToken : {}", notificationToken);

        return notificationTokenRepository
            .findById(notificationToken.getId())
            .map(existingNotificationToken -> {
                if (notificationToken.getFcm() != null) {
                    existingNotificationToken.setFcm(notificationToken.getFcm());
                }

                return existingNotificationToken;
            })
            .map(notificationTokenRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationToken> findAll() {
        log.debug("Request to get all NotificationTokens");
        return notificationTokenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotificationToken> findOne(Long id) {
        log.debug("Request to get NotificationToken : {}", id);
        return notificationTokenRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotificationToken : {}", id);
        notificationTokenRepository.deleteById(id);
    }
}
