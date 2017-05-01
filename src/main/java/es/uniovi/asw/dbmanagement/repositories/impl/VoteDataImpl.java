package es.uniovi.asw.dbmanagement.repositories.impl;

import es.uniovi.asw.dbmanagement.model.Comment;
import es.uniovi.asw.dbmanagement.model.Suggestion;
import es.uniovi.asw.dbmanagement.repositories.CommentData;
import es.uniovi.asw.dbmanagement.repositories.SuggestionData;
import es.uniovi.asw.dbmanagement.repositories.VoteData;
import org.springframework.stereotype.Component;

@Component
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
