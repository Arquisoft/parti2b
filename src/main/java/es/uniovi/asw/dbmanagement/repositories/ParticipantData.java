package es.uniovi.asw.dbmanagement.repositories;


import es.uniovi.asw.dbmanagement.model.Participant;

public interface ParticipantData {
	
	
	public void deleteAllParticipants();

	public Participant findParticipant(String dni);

	public void updateParticipant(Participant participant);

	public void addParticipant(Participant participant);

	public void deleteParticipantByDni(String dni);

	public void init();
	public Participant findLogableUser(String usuario, String password);

	Participant findParticipantByIdAndPassword(String id, String password);


}
