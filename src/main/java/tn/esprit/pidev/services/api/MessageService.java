package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Message}.
 */
public interface MessageService {
    /**
     * Save a message.
     *
     * @param message the entity to save.
     * @return the persisted entity.
     */
    Message save(Message message);

    /**
     * Updates a message.
     *
     * @param message the entity to update.
     * @return the persisted entity.
     */
    Message update(Message message);

    /**
     * Partially updates a message.
     *
     * @param message the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Message> partialUpdate(Message message);

    /**
     * Get all the messages.
     *
     * @return the list of entities.
     */
    List<Message> findAll();

    /**
     * Get all the messages with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Message> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" message.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Message> findOne(Long id);

    /**
     * Delete the "id" message.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
