package es.uniovi.asw.dbmanagement.persistence.impl;

import es.uniovi.asw.dbmanagement.model.Category;
import es.uniovi.asw.dbmanagement.persistence.CategoryData;
import es.uniovi.asw.dbmanagement.persistence.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDataImpl implements CategoryData {

    @Autowired
    CategoryRepository repository;

    @Override
    public void addCategory(Category category) {
        repository.save(category);
    }

    @Override
    public void deleteCateogory(Category category) {
        repository.delete(category);
    }

    @Override
    public void updateCategory(Category category) {
        repository.save(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return repository.findAll();
    }

    @Override
    public Category findById(Long categoria) {
        return repository.findById(categoria);
    }
}
