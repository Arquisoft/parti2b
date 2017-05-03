package es.uniovi.asw.dbmanagement.persistence;

import es.uniovi.asw.dbmanagement.model.Comment;
import es.uniovi.asw.dbmanagement.model.Suggestion;

public interface VoteData {

    void votePositiveComment(Comment comment);

    void voteNegativeComment(Comment comment);

    void votePositiveSuggestion(Suggestion suggestion);

    void voteNegativeSuggestion(Suggestion suggestion);
}
