package es.uniovi.asw.dbmanagement;

import java.util.List;

import es.uniovi.asw.model.Participant;
import es.uniovi.asw.model.Suggestion;

public interface SuggestionData {

	public void addSuggestion(Suggestion suggestion);

	public void deleteSuggestion(Suggestion suggestion);

	public void updateSuggestion(Suggestion suggestion);

	public List<Suggestion> getAllSuggestions();

	public List<Suggestion> getSuggestionByParticipant(Participant participant);

	public Suggestion findSugById(Long id);
}
