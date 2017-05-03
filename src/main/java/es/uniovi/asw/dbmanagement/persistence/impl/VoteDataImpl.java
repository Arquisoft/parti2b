package es.uniovi.asw.dbmanagement.persistence.impl;

import es.uniovi.asw.dbmanagement.model.Comment;
import es.uniovi.asw.dbmanagement.model.Suggestion;
import es.uniovi.asw.dbmanagement.persistence.CommentData;
import es.uniovi.asw.dbmanagement.persistence.SuggestionData;
import es.uniovi.asw.dbmanagement.persistence.VoteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteDataImpl implements VoteData {

	@Autowired
	CommentData comentData;
	@Autowired
	SuggestionData sugData;
	
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
