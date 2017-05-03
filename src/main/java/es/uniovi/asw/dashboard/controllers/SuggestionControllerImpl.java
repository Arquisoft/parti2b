package es.uniovi.asw.dashboard.controllers;

import es.uniovi.asw.dashboard.pojos.SuggestionInfo;
import es.uniovi.asw.dbmanagement.model.Suggestion;
import es.uniovi.asw.dbmanagement.persistence.SuggestionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@Controller
public class SuggestionControllerImpl implements SuggestionController {
    @Autowired
    private SuggestionData data;

    @Override
    @RequestMapping(path = "/suggestion/{id}", method = RequestMethod.GET)
    public ResponseEntity<SuggestionInfo> getSuggestion(@PathVariable Long id) {
        Suggestion s = data.findSugById(id);
        if (s == null) {
            return new ResponseEntity<SuggestionInfo>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SuggestionInfo>(new SuggestionInfo(s), HttpStatus.OK);
    }

    @Override
    @RequestMapping(path = "/suggestion", method = RequestMethod.GET)
    public ResponseEntity<List<SuggestionInfo>> getAllSuggestions() {
        List<Suggestion> list = data.getAllSuggestions();
        List<SuggestionInfo> l = new ArrayList<>();
        for(Suggestion s: list)
            l.add(new SuggestionInfo(s));
        return new ResponseEntity<List<SuggestionInfo>>(l, HttpStatus.OK);
    }
}
