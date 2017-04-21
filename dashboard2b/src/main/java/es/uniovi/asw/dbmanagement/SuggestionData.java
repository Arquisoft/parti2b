package es.uniovi.asw.dbmanagement;

import es.uniovi.asw.dbmanagement.model.Suggestion;

import java.util.List;

public interface SuggestionData {

    Suggestion getSuggestionData(Long id);

    List<Suggestion> getAllSuggestionsData();
}
