package es.uniovi.asw.participationSystem.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import es.uniovi.asw.dbmanagement.model.*;
import es.uniovi.asw.dbmanagement.persistence.CategoryData;
import es.uniovi.asw.dbmanagement.persistence.CommentData;
import es.uniovi.asw.dbmanagement.persistence.SuggestionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import es.uniovi.asw.dbmanagement.persistence.impl.CategoryDataImpl;
import es.uniovi.asw.dbmanagement.persistence.impl.CommentDataImpl;
import es.uniovi.asw.dbmanagement.persistence.impl.SuggestionDataImpl;


@Controller
@Scope("session")
public class CommentController {

	/**
	 * Atributos en sesion: user -> contiene el Particpant logueado suggestion
	 * -> la sugerencia que se selecciona para ver
	 */
	@Autowired
	private SuggestionData sugData;
	@Autowired
	private CommentData commentData;
	@Autowired
	private CategoryData catData;
	



	@RequestMapping("/comentar")
	public String comentar(@RequestParam String comment, HttpSession sesion, Model model) {
		Suggestion s = (Suggestion) sesion.getAttribute("suggestion");
		if (!comment.equals("")) {
			Participant p = (Participant) sesion.getAttribute("user");
			commentData.addComment(new Comment(p, s, comment));
			model.addAttribute("comments", commentData.findAllCommentsBySuggestionId(s.getId()));
			comment = "";
			
		}

		
		return "showSuggestion";
	}

	

	
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return catData.findAllCategories();
	}

	@ModelAttribute("suggestions")
	public List<Suggestion> getSuggestions() {
		List<Suggestion> suggestions = sugData.getAllSuggestions();
		List<Suggestion> aux = new ArrayList<Suggestion>();
		for (Suggestion suggestion : suggestions)
			if (suggestion.getEstado().equals(EstadoPropuesta.Entramite))
				aux.add(suggestion);
		return aux;
	}

	@ModelAttribute("suggestionsRechazadas")
	public List<Suggestion> getSuggestionsRechazadas() {
		List<Suggestion> suggestions = sugData.getAllSuggestions();
		List<Suggestion> aux = new ArrayList<Suggestion>();
		for (Suggestion suggestion : suggestions)
			if (suggestion.getEstado().equals(EstadoPropuesta.Rechazada))
				aux.add(suggestion);
		return aux;
	}

	@ModelAttribute("suggestionsAceptadas")
	public List<Suggestion> getSuggestionsAceptadas() {
		List<Suggestion> suggestions = sugData.getAllSuggestions();
		List<Suggestion> aux = new ArrayList<Suggestion>();
		for (Suggestion suggestion : suggestions)
			if (suggestion.getEstado().equals(EstadoPropuesta.Aceptada))
				aux.add(suggestion);
		return aux;
	}

	@ModelAttribute("user")
	public Participant getUser(HttpSession sesion) {
		return (Participant) sesion.getAttribute("user");
	}

	@ModelAttribute("comments")
	public List<Comment> getComments(HttpSession sesion) {
		Suggestion s = (Suggestion) sesion.getAttribute("suggestion");
		if (s != null) {
			return commentData.findAllCommentsBySuggestionId(s.getId());
		}
		return null;
	}

	
}
