package es.uniovi.asw.dashboard.controllers;

import es.uniovi.asw.dashboard.pojos.SuggestionInfo;
import es.uniovi.asw.dbmanagement.model.Suggestion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SuggestionController {
    ResponseEntity<SuggestionInfo> getSuggestion(Long id);

    ResponseEntity<List<SuggestionInfo>> getAllSuggestions();
}
