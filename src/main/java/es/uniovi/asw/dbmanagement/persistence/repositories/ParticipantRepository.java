package es.uniovi.asw.dbmanagement.persistence.repositories;


import es.uniovi.asw.dbmanagement.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{

    Participant findByDni(String dni);

    @Query("select p from Participant p where p.email = ?1 and p.password = ?2")
    Participant findByLoginAndPass(String usuario, String password);
}
