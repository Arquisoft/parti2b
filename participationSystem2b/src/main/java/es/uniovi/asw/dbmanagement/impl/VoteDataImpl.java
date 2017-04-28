package es.uniovi.asw.dbmanagement.impl;

import es.uniovi.asw.dbmanagement.CommentData;
import es.uniovi.asw.dbmanagement.SuggestionData;
import es.uniovi.asw.dbmanagement.VoteData;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Suggestion;

public class VoteDataImpl implements VoteData {

	CommentData comentData= new CommentDataImpl();
	SuggestionData sugData= new SuggestionDataImpl();
	
	@Override
	public void votePositiveComment(Comment comment) {
		comment.increasePositiveVotes();
		comentData.updateComment(comment);
		
	}

	@Override
	public void voteNegativeComment(Comment comment) {
		comment.increaseNegativeVotes();
		comentData.updateComment(comment);
	}

	@Override
	public void votePositiveSuggestion(Suggestion suggestion) {
		suggestion.increasePositiveVotes();
		sugData.updateSuggestion(suggestion);
	}

	@Override
	public void voteNegativeSuggestion(Suggestion suggestion) {
		suggestion.increaseNegativeVotes();
		sugData.updateSuggestion(suggestion);
	}

	
	

	

}
