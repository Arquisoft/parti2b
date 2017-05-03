package es.uniovi.asw.dbmanagement.persistence;

import es.uniovi.asw.dbmanagement.model.Comment;

import java.util.List;

public interface CommentData {

    void addComment(Comment comment);

    void deleteComment(Comment comment);

    List<Comment> findAllCommentsBySuggestionId(long suggId);

    List<Comment> findAllCommentsByParticipantId(long participantId);

    Comment findCommentById(Long id);

    void updateComment(Comment comment);

}
