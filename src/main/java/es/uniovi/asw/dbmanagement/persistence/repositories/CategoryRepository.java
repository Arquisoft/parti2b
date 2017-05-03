package es.uniovi.asw.dbmanagement.persistence.repositories;

import es.uniovi.asw.dbmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findById(Long categoria);
}
