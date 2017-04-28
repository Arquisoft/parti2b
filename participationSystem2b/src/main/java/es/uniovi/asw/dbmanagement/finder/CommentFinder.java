package es.uniovi.asw.dbmanagement.finder;

import java.util.List;

import es.uniovi.asw.dbmanagement.util.Jpa;
import es.uniovi.asw.model.Comment;

public class CommentFinder {

	public static List<Comment> findCommentsBySugId(Long id) {
		return Jpa.getManager().createNamedQuery("Comment.findBySugId", Comment.class).setParameter(1, id)
				.getResultList();
	}

	public static Comment findCommentById(Long id) {
		List<Comment> comments = Jpa.getManager().createNamedQuery("Comment.findById", Comment.class)
				.setParameter(1, id).getResultList();
		return comments.size() == 0 ? null : comments.get(0);
	}

}
