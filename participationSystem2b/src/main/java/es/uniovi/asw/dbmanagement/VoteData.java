package es.uniovi.asw.dbmanagement;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;

public interface VoteData {

	public void votePositiveComment(Comment comment);
	public void voteNegativeComment(Comment comment);
	public void votePositiveSuggestion(Suggestion suggestion);
	public void voteNegativeSuggestion(Suggestion suggestion);
}
