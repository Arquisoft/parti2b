package es.uniovi.asw.bdupdate;


import es.uniovi.asw.model.Participant;

public interface BDUpdate {

	void addParticipant(Participant participant);

	void deleteParticipant(String dni);

	void updateParticipant(Participant participant);

	Participant findParticipant(String dni);

	//void guardaarPasswordUsuario(String dni, String password);

	void deleteAllParticipants();

}
