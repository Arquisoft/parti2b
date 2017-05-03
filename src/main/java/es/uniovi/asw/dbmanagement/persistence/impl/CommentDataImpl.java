package es.uniovi.asw.dbmanagement.persistence.impl;

import es.uniovi.asw.dbmanagement.model.Comment;
import es.uniovi.asw.dbmanagement.persistence.CommentData;
import es.uniovi.asw.dbmanagement.persistence.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentDataImpl implements CommentData {

    @Autowired
    CommentRepository repository;


    @Override
    public void addComment(Comment comment) {
        repository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        repository.delete(comment);
    }

    @Override
    public List<Comment> findAllCommentsBySuggestionId(long suggId) {
        return repository.findAllBySuggestionId(suggId);
    }

    @Override
    public List<Comment> findAllCommentsByParticipantId(long participantId) {
        return repository.findAllByParticipantId(participantId);
    }

    @Override
    public Comment findCommentById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void updateComment(Comment comment) {
        repository.save(comment);
    }
}
