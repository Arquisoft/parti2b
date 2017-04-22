package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.model.Participant;

public class RList implements ReadList {

	@Override
	public List<Participant> leerParticipantsCsv(String ruta) {
		Csv csv = new Csv();
		return csv.leerCiudadanos( ruta);
	}

	@Override
	public List<Participant> leerParticipantsXlsx(String ruta) {
		Xlsx xl = new Xlsx();
		return xl.leerCiudadanos(ruta);
	}

}
