package es.uniovi.asw.dbManagement;

import es.uniovi.asw.dbManagement.model.Participant;

public interface ParticipantData {

    Participant getData(String login);

    void updateInfo(Participant p);
}
