package es.uniovi.asw.dbmanagement.repositories.finder;

import java.util.List;

import es.uniovi.asw.dbmanagement.model.Participant;
import es.uniovi.asw.dbmanagement.model.Suggestion;
import es.uniovi.asw.dbmanagement.util.Jpa;

public class SuggestionFinder {

	public static List<Suggestion> findByParticipantId(Participant part) {
		return Jpa.getManager().createNamedQuery("Suggestion.findByPId", Suggestion.class).setParameter(1, part)
				.getResultList();
	}

	public static List<Suggestion> findAll() {
		return Jpa.getManager().createNamedQuery("Suggestion.findAll", Suggestion.class).getResultList();
	}

	public static Suggestion findById(Long id) {
		List<Suggestion> sug = Jpa.getManager().createNamedQuery("Suggestion.findById", Suggestion.class)
				.setParameter(1, id).getResultList();
		if (sug.isEmpty())
			return null;
		return sug.get(0);
	}

}
