package es.uniovi.asw.dbmanagement.persistence;

import java.util.List;

import es.uniovi.asw.dbmanagement.model.Category;

public interface CategoryData {

    void addCategory(Category category);

    void deleteCateogory(Category category);

    void updateCategory(Category category);

    List<Category> findAllCategories();

    Category findById(Long categoria);
}
