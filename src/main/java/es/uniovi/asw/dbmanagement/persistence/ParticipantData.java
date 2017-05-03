package es.uniovi.asw.dbmanagement.persistence;


import es.uniovi.asw.dbmanagement.model.Participant;

public interface ParticipantData {


    void deleteAllParticipants();

    Participant findParticipant(String dni);

    void updateParticipant(Participant participant);

    void addParticipant(Participant participant);

    void deleteParticipantByDni(String dni);

    Participant findLogableUser(String usuario, String password);

}
