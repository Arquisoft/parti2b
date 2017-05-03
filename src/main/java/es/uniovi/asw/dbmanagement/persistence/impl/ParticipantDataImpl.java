package es.uniovi.asw.dbmanagement.persistence.impl;


import es.uniovi.asw.dbmanagement.model.Participant;
import es.uniovi.asw.dbmanagement.persistence.ParticipantData;
import es.uniovi.asw.dbmanagement.persistence.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParticipantDataImpl implements ParticipantData {

    @Autowired
    ParticipantRepository repository;

    @Override
    public void deleteAllParticipants() {
        repository.deleteAll();
    }

    @Override
    public Participant findParticipant(String dni) {
        return repository.findByDni(dni);
    }

    @Override
    public void updateParticipant(Participant participant) {
        repository.save(participant);
    }

    @Override
    public void addParticipant(Participant participant) {
        repository.save(participant);
    }

    @Override
    public void deleteParticipantByDni(String dni) {
        Participant p = repository.findByDni(dni);
        repository.delete(p);
    }

    @Override
    public Participant findLogableUser(String usuario, String password) {
        return repository.findByLoginAndPass(usuario, password);
    }

}
