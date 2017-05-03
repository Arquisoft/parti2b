package es.uniovi.asw.dbmanagement.persistence.repositories;

import es.uniovi.asw.dbmanagement.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{

    @Query("select c from Comment c where c.suggestion.id = ?1")
    List<Comment> findAllBySuggestionId(long suggId);

    @Query("select c from Comment c where c.participant.id = ?1")
    List<Comment> findAllByParticipantId(long participantId);

    Comment findById(Long id);
}
