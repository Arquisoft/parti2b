package es.uniovi.asw.dbmanagement.persistence.repositories;

import es.uniovi.asw.dbmanagement.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SuggestionRepository extends JpaRepository<Suggestion, Long>{

	Suggestion findById(Long id);
}
