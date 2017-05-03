package es.uniovi.asw.dbmanagement.persistence.impl;

import java.util.List;

import es.uniovi.asw.dbmanagement.model.Suggestion;
import es.uniovi.asw.dbmanagement.persistence.SuggestionData;
import es.uniovi.asw.dbmanagement.persistence.repositories.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SuggestionDataImpl implements SuggestionData {

	@Autowired
	SuggestionRepository repository;

	@Override
	public void addSuggestion(Suggestion suggestion) {
	repository.save(suggestion);
	}

	@Override
	public void deleteSuggestion(Suggestion suggestion) {
repository.delete(suggestion);
	}

	@Override
	public void updateSuggestion(Suggestion suggestion) {
repository.save(suggestion);
	}

	@Override
	public List<Suggestion> getAllSuggestions() {
		return repository.findAll();
	}

	@Override
	public Suggestion findSugById(Long id) {
		return repository.findById(id);
	}
}
