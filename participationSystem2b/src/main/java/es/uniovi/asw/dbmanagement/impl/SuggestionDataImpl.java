package es.uniovi.asw.dbmanagement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import es.uniovi.asw.dbmanagement.SuggestionData;
import es.uniovi.asw.dbmanagement.finder.SuggestionFinder;
import es.uniovi.asw.dbmanagement.util.Jpa;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.model.Suggestion;

public class SuggestionDataImpl implements SuggestionData {

	@Override
	public void addSuggestion(Suggestion suggestion) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Jpa.getManager().persist(suggestion);
		trx.commit();
	}

	@Override
	public void deleteSuggestion(Suggestion suggestion) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Suggestion s = Jpa.getManager().find(Suggestion.class, suggestion.getId());
		if (s.getComments().size() == 0) {
			Jpa.getManager().remove(s);
		}
		trx.commit();
	}

	@Override
	public void updateSuggestion(Suggestion suggestion) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		if (Jpa.getManager().find(Suggestion.class, suggestion.getId()) != null)
			Jpa.getManager().merge(suggestion);
		trx.commit();

	}

	@Override
	public List<Suggestion> getSuggestionByParticipant(Participant participant) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		return SuggestionFinder.findByParticipantId(participant);
	}

	@Override
	public List<Suggestion> getAllSuggestions() {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		return SuggestionFinder.findAll();
	}

	@Override
	public Suggestion findSugById(Long id) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		return SuggestionFinder.findById(id);
	}

}
