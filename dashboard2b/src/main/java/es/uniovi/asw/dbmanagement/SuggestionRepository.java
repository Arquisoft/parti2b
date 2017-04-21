package es.uniovi.asw.dbmanagement;

import es.uniovi.asw.dbmanagement.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
}
