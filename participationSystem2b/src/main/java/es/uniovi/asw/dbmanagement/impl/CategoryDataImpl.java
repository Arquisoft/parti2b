package es.uniovi.asw.dbmanagement.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import es.uniovi.asw.dbmanagement.CategoryData;
import es.uniovi.asw.dbmanagement.finder.CategoryFinder;
import es.uniovi.asw.dbmanagement.util.Jpa;
import es.uniovi.asw.model.Category;

public class CategoryDataImpl implements CategoryData {

	@Override
	public void addCategory(Category category) {

		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		if(CategoryFinder.findByName(category.getName())==null){
			Jpa.getManager().persist(category);
		}
		trx.commit();
	}

	@Override
	public void deleteCateogory(Category category) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Category c =Jpa.getManager().find(Category.class, category.getId());
		Jpa.getManager().remove(c);
		
		trx.commit();
	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub

	}
	@Override
	public List<Category> findAllCategories() {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		return CategoryFinder.findAll();
	}
	@Override
	public Category findById(Long categoria) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Category c = CategoryFinder.findById(categoria);
		return c;
	}

	

}
