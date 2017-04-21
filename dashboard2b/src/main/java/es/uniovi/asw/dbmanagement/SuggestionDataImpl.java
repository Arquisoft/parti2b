package es.uniovi.asw.dbmanagement;

import es.uniovi.asw.dbmanagement.model.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public class SuggestionDataImpl implements SuggestionData {

    @Autowired
    private SuggestionRepository repository;

    @Override
    public Suggestion getSuggestionData(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Suggestion> getAllSuggestionsData() {
        return repository.findAll();
    }
}
