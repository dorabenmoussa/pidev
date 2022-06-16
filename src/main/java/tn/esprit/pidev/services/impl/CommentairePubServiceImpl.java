package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.CommentairePub;
import tn.esprit.pidev.repository.CommentairePubRepository;
import tn.esprit.pidev.services.api.CommentairePubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link CommentairePub}.
 */
@Service
@Transactional
public class CommentairePubServiceImpl implements CommentairePubService {

    private final Logger log = LoggerFactory.getLogger(CommentairePubServiceImpl.class);

    private final CommentairePubRepository commentairePubRepository;

    public CommentairePubServiceImpl(CommentairePubRepository commentairePubRepository) {
        this.commentairePubRepository = commentairePubRepository;
    }

    @Override
    public CommentairePub save(CommentairePub commentairePub) {
        log.debug("Request to save CommentairePub : {}", commentairePub);
        return commentairePubRepository.save(commentairePub);
    }

    @Override
    public CommentairePub update(CommentairePub commentairePub) {
        log.debug("Request to save CommentairePub : {}", commentairePub);
        return commentairePubRepository.save(commentairePub);
    }

    @Override
    public Optional<CommentairePub> partialUpdate(CommentairePub commentairePub) {
        log.debug("Request to partially update CommentairePub : {}", commentairePub);

        return commentairePubRepository
            .findById(commentairePub.getId())
            .map(existingCommentairePub -> {
                if (commentairePub.getContenu() != null) {
                    existingCommentairePub.setContenu(commentairePub.getContenu());
                }
                if (commentairePub.getCmtDate() != null) {
                    existingCommentairePub.setCmtDate(commentairePub.getCmtDate());
                }
                if (commentairePub.getStatus() != null) {
                    existingCommentairePub.setStatus(commentairePub.getStatus());
                }

                return existingCommentairePub;
            })
            .map(commentairePubRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentairePub> findAll() {
        log.debug("Request to get all CommentairePubs");
        return commentairePubRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommentairePub> findOne(Long id) {
        log.debug("Request to get CommentairePub : {}", id);
        return commentairePubRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommentairePub : {}", id);
        commentairePubRepository.deleteById(id);
    }
}
