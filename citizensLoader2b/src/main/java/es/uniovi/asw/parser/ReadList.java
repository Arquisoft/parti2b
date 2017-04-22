package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.model.Participant;

public interface ReadList {
	
	public 	List<Participant> leerParticipantsCsv( String ruta);
	public 	List<Participant> leerParticipantsXlsx( String ruta);

}
