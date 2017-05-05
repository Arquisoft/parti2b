package es.uniovi.asw.parser;

import es.uniovi.asw.model.Participant;

public interface Insert {
	
	public void addParticipant(Participant participant);

	public Participant findParticipant(String dni);

}
