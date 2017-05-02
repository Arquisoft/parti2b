package es.uniovi.asw.dbmanagement.finder;

import java.util.List;

import es.uniovi.asw.dbmanagement.util.Jpa;
import es.uniovi.asw.model.Category;

public class CategoryFinder {

	public static List<Category> findAll() {
		return Jpa.getManager().createNamedQuery("Category.findAll",Category.class).getResultList();
	}
	
	public static Category findByName(String name){
		List<Category> categories = Jpa.getManager().createNamedQuery("Category.findByName",Category.class).setParameter(1, name).getResultList();
		if(categories.isEmpty())return null;
		return categories.get(0);
	}

	public static Category findById(Long id) {
		List<Category> categories = Jpa.getManager().createNamedQuery("Category.findById",Category.class).setParameter(1, id).getResultList();
		if(categories.isEmpty())return null;
		return categories.get(0);
	}

}
