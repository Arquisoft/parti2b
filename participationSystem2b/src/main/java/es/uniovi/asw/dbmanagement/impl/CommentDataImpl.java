package es.uniovi.asw.dbmanagement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import es.uniovi.asw.dbmanagement.CommentData;
import es.uniovi.asw.dbmanagement.finder.CommentFinder;
import es.uniovi.asw.dbmanagement.util.Jpa;
import es.uniovi.asw.model.Comment;

public class CommentDataImpl implements CommentData {

	@Override
	public void addComment(Comment comment) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Jpa.getManager().persist(comment);
		trx.commit();
	}

	@Override
	public void deleteComment(Comment comment) {

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Comment c = Jpa.getManager().find(Comment.class, comment.getId());
		Jpa.getManager().remove(c);

		trx.commit();
	}

	@Override
	public Comment getComment(long suggId, long participId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAllCommentsBySuggestionId(long suggId) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		List<Comment> comments = CommentFinder.findCommentsBySugId(suggId);
		trx.commit();
		return comments;
	}

	@Override
	public List<Comment> findAllCommentsByParticipantId(long participantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment findCommentById(Long id) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Comment comment = CommentFinder.findCommentById(id);
		trx.commit();
		return comment;
	}

	@Override
	public void updateComment(Comment comment) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Jpa.getManager().merge(comment);
		trx.commit();
	}

}
