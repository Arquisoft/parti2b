package es.uniovi.asw.dbmanagement.repositories;

import es.uniovi.asw.dbmanagement.model.Comment;
import es.uniovi.asw.dbmanagement.model.Suggestion;

public interface VoteData {

	public void votePositiveComment(Comment comment);
	public void voteNegativeComment(Comment comment);
	public void votePositiveSuggestion(Suggestion suggestion);
	public void voteNegativeSuggestion(Suggestion suggestion);
}
