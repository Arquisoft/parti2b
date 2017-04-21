package es.uniovi.asw.dbManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.asw.dbManagement.model.Participant;

@Component
public class ParticipantDataImpl implements ParticipantData {

    @Autowired
    private ParticipantRepository repository;

    @Override
    public Participant getData(String login) {
	Participant p = repository.findByEmail(login);
	return p;
    }

    @Override
    public void updateInfo(Participant p) {
	repository.saveAndFlush(p);
    }

}
