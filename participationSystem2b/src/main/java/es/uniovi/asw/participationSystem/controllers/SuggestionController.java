package es.uniovi.asw.participationSystem.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.uniovi.asw.Application;
import es.uniovi.asw.dbmanagement.CategoryData;
import es.uniovi.asw.dbmanagement.CommentData;
import es.uniovi.asw.dbmanagement.ParticipantData;
import es.uniovi.asw.dbmanagement.SuggestionData;
import es.uniovi.asw.dbmanagement.impl.CategoryDataImpl;
import es.uniovi.asw.dbmanagement.impl.CommentDataImpl;
import es.uniovi.asw.dbmanagement.impl.ParticipantDataImpl;
import es.uniovi.asw.dbmanagement.impl.SuggestionDataImpl;
import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.EstadoPropuesta;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.participationSystem.pojos.Notification;
import es.uniovi.asw.participationSystem.pojos.NotificationType;
import es.uniovi.asw.participationSystem.producers.KafkaProducer;

@Controller
@Scope("session")
public class SuggestionController {

	/**
	 * Atributos en sesion: user -> contiene el Particpant logueado suggestion
	 * -> la sugerencia que se selecciona para ver
	 */
	@Autowired
	private KafkaProducer kafkaProducer;
	private SuggestionData sugData = new SuggestionDataImpl();
	private CommentData commentData = new CommentDataImpl();
	private ParticipantData partData = new ParticipantDataImpl();
	private CategoryData catData = new CategoryDataImpl();
	
	private Logger log = LoggerFactory.getLogger(Application.class);

	@RequestMapping("/")
	public String landing(Model model) {
		model.addAttribute("comment", "");
		return "login";
	}

	

	@RequestMapping(value = "/loguearse", method = RequestMethod.POST)
	public String loguearse(HttpSession sesion, Model model, @RequestParam String usuario,
			@RequestParam String password) {
		Participant user = partData.findLogableUser(usuario, password);
		String resultado = "login";
		if (user != null) {
			if (user.isAdmin())
				resultado = "principalAdmin";
			else
				resultado = "principalUsuario";

			sesion.setAttribute("user", user);
		}
		return resultado;
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public String registrar(HttpSession sesion, Model model, @RequestParam String archivo) {
		System.out.println(archivo);
		return "principalUsuario";
	}

	@RequestMapping("/mostrarPropuestas")
	public String mostrarPropuestas(HttpSession sesion, Model model) {
		// es de prueba, habria que llamr al servicefindAllSuggestions
		// model.addAttribute("suggestions",((Participant)sesion.getAttribute("user")).getSuggestions());
		return "principalUsuario";
	}

	@RequestMapping("/mostrarPropuestasAdmin")
	public String mostrarPropuestasAdmin(HttpSession sesion, Model model) {
		return "principalAdmin";
	}

	@RequestMapping("/crearPropuesta")
	public String crearPropuesta(HttpSession sesion, Model model) {
		System.out.println(sesion.getAttribute("user"));
		return "addSuggestion";
	}

	@RequestMapping("/anadirPropuesta")
	public String anadirPropuesta(HttpSession sesion, Model model, @RequestParam String titulo,
			@RequestParam Long categoria, @RequestParam String propuesta) {
		if (!titulo.equals("") && !propuesta.equals("")) {
			Category category = catData.findById(categoria);
			System.out.println(categoria);
			Suggestion sug = new Suggestion((Participant) sesion.getAttribute("user"), titulo, propuesta, category);
			sugData.addSuggestion(sug);
			model.addAttribute("suggestions", getSuggestions());
			
			log.info("Saved new suggestion - id:" + sug.getId() + ", title:" + sug
	                        .getTitle());
			Notification n = new Notification(NotificationType.VOTING, sug.getId());
			ObjectNode json = JsonNodeFactory.instance.objectNode();
	        json.put("type", n.getType().ordinal());
	        json.put("suggestionId",n.getSuggestionId());
	        kafkaProducer.send("exampleTopic", json.toString());
			return "principalUsuario";
		}
		return "addSuggestion";
	}

	@RequestMapping("/verPropuesta/{id}")
	public String verPropuesta(HttpSession sesion, Model model, @PathVariable("id") Long id) {
		model.addAttribute("comments", commentData.findAllCommentsBySuggestionId(id));
		// Cateogory cat = Service.getCategoryService().findByName();
	
		Suggestion suggestion = (Suggestion) sugData.findSugById(id);
		model.addAttribute("suggestion", suggestion);
		sesion.setAttribute("suggestion", suggestion);

		return "showSuggestion";
	}

	

	@RequestMapping("/cerrarSesion")
	public String cerrarSesion(HttpSession session) {
		session.setAttribute("user", null);
		return "login";
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

	@RequestMapping("/rechazarPropuesta/{id}")
	public String rechazarPropuesta(HttpSession session, @PathVariable("id") Long id, Model model) {
		Suggestion suggestion = sugData.findSugById(id);
		suggestion.rechazar();
		sugData.updateSuggestion(suggestion);
		model.addAttribute("suggestions", getSuggestions());
		model.addAttribute("suggestionsRechazadas", getSuggestionsRechazadas());
		model.addAttribute("suggestionsAceptadas", getSuggestionsAceptadas());
		return "principalAdmin";
	}

}
