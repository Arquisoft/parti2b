package es.uniovi.asw.dbmanagement.repositories;

import es.uniovi.asw.dbmanagement.model.Participant;
import es.uniovi.asw.dbmanagement.model.Suggestion;

import java.util.List;


public interface SuggestionData {

	public void addSuggestion(Suggestion suggestion);

	public void deleteSuggestion(Suggestion suggestion);

	public void updateSuggestion(Suggestion suggestion);

	public List<Suggestion> getAllSuggestions();

	public List<Suggestion> getSuggestionByParticipant(Participant participant);

	public Suggestion findSugById(Long id);
}
