package es.uniovi.asw.dbManagement;

import es.uniovi.asw.dbManagement.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{

    Participant findByEmail(String email);
}
