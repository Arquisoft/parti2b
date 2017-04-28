package es.uniovi.asw.participationSystem.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.uniovi.asw.dbmanagement.CategoryData;
import es.uniovi.asw.dbmanagement.CommentData;
import es.uniovi.asw.dbmanagement.SuggestionData;
import es.uniovi.asw.dbmanagement.VoteData;
import es.uniovi.asw.dbmanagement.impl.CategoryDataImpl;
import es.uniovi.asw.dbmanagement.impl.CommentDataImpl;
import es.uniovi.asw.dbmanagement.impl.SuggestionDataImpl;
import es.uniovi.asw.dbmanagement.impl.VoteDataImpl;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.EstadoPropuesta;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.model.Suggestion;

@Controller
@Scope("session")
public class VoteController {

	/**
	 * Atributos en sesion: user -> contiene el Particpant logueado suggestion
	 * -> la sugerencia que se selecciona para ver
	 */
	@Autowired
	private SuggestionData sugData = new SuggestionDataImpl();
	private CommentData commentData = new CommentDataImpl();
	private VoteData voteData = new VoteDataImpl();
	private CategoryData catData = new CategoryDataImpl();
	

	

	@RequestMapping("/votarPropSi")
	public String votarPropuestaSi(HttpSession sesion, Model model) {
		Suggestion s = (Suggestion) sesion.getAttribute("suggestion");

		voteData.votePositiveSuggestion(s);
		sesion.setAttribute("suggestion", s);

		return "showSuggestion";
	}

	@RequestMapping("/votarPropNo")
	public String votarPropuestaNo(HttpSession sesion, Model model) {
		Suggestion s = (Suggestion) sesion.getAttribute("suggestion");

		voteData.voteNegativeSuggestion(s);
		sesion.setAttribute("suggestion", s);

		return "showSuggestion";
	}

	

	@RequestMapping("/votaSiCom/{id}")
	public String votaSiCom(HttpSession session, @PathVariable("id") Long id, Model model) {
		Comment comment = commentData.findCommentById(id);
		voteData.votePositiveComment(comment);

		Suggestion s = (Suggestion) session.getAttribute("suggestion");
		model.addAttribute("comments", commentData.findAllCommentsBySuggestionId(s.getId()));
		return "showSuggestion";
	}

	@RequestMapping("/votaNoCom/{id}")
	public String votaNoCom(HttpSession session, @PathVariable("id") Long id, Model model) {
		Comment comment = commentData.findCommentById(id);
		
		voteData.voteNegativeComment(comment);

		Suggestion s = (Suggestion) session.getAttribute("suggestion");
		model.addAttribute("comments", commentData.findAllCommentsBySuggestionId(s.getId()));
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

	

	@RequestMapping("/modificarMinimoVotos/{id}")
	public String modificarMinimoVotos(HttpSession session, @PathVariable("id") Long id,
			@RequestParam("minVotos") int votos, Model model) {
		Suggestion suggestion = sugData.findSugById(id);
		suggestion.setMinVotos(votos);
		sugData.updateSuggestion(suggestion);
		model.addAttribute("suggestions", getSuggestions());
		model.addAttribute("suggestionsRechazadas", getSuggestionsRechazadas());
		model.addAttribute("suggestionsAceptadas", getSuggestionsAceptadas());
		return "principalAdmin";
	}
}
