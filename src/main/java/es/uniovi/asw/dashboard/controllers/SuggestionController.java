package es.uniovi.asw.dashboard.controllers;

import es.uniovi.asw.dbmanagement.model.Suggestion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SuggestionController {
    ResponseEntity<Suggestion> getSuggestion(Long id);

    ResponseEntity<List<Suggestion>> getAllSuggestions();
}
