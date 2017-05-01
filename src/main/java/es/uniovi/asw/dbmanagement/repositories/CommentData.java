package es.uniovi.asw.dbmanagement.repositories;

import es.uniovi.asw.dbmanagement.model.Comment;

import java.util.List;

public interface CommentData {

	public void addComment(Comment comment);
	public void deleteComment(Comment comment);
	public Comment getComment(long suggId, long participId);
	public List<Comment> findAllCommentsBySuggestionId(long suggId);
	public List<Comment> findAllCommentsByParticipantId(long participantId);
	public Comment findCommentById(Long id);
	public void updateComment(Comment comment);
	
}
