package es.uniovi.asw.dbmanagement.persistence;

import es.uniovi.asw.dbmanagement.model.Suggestion;

import java.util.List;


public interface SuggestionData {

	void addSuggestion(Suggestion suggestion);

	void deleteSuggestion(Suggestion suggestion);

	void updateSuggestion(Suggestion suggestion);


	List<Suggestion> getAllSuggestions();

	Suggestion findSugById(Long id);
}
