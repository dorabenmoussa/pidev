package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.Message;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class MessageRepositoryWithBagRelationshipsImpl implements MessageRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Message> fetchBagRelationships(Optional<Message> message) {
        return message.map(this::fetchUsers);
    }

    @Override
    public Page<Message> fetchBagRelationships(Page<Message> messages) {
        return new PageImpl<>(fetchBagRelationships(messages.getContent()), messages.getPageable(), messages.getTotalElements());
    }

    @Override
    public List<Message> fetchBagRelationships(List<Message> messages) {
        return Optional.of(messages).map(this::fetchUsers).orElse(Collections.emptyList());
    }

    Message fetchUsers(Message result) {
        return entityManager
            .createQuery("select message from Message message left join fetch message.users where message is :message", Message.class)
            .setParameter("message", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Message> fetchUsers(List<Message> messages) {
        return entityManager
            .createQuery(
                "select distinct message from Message message left join fetch message.users where message in :messages",
                Message.class
            )
            .setParameter("messages", messages)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
