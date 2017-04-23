package es.uniovi.asw.parser;

import es.uniovi.asw.bdupdate.BDUpdate;
import es.uniovi.asw.bdupdate.BDUpdateImpl;
import es.uniovi.asw.model.Participant;

public class InsertR implements Insert{

	@Override
	public void addParticipant(Participant participant) {
		BDUpdate bd = new BDUpdateImpl();
		bd.addParticipant(participant);
		
	}

}
