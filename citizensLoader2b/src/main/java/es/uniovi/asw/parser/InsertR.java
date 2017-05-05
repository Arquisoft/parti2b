package es.uniovi.asw.parser;

import es.uniovi.asw.bdupdate.InsertP;
import es.uniovi.asw.model.Participant;

public class InsertR implements Insert{
	private InsertP insert = new InsertP();
	@Override
	public void addParticipant(Participant participant) {
		
		insert.addParticipant(participant);
		
	}

	@Override
	public Participant findParticipant(String dni) {
		
		return insert.findParticipant(dni);
	}

}
