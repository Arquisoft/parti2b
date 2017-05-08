package es.uniovi.asw.participationSystem.controllers;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import es.uniovi.asw.Application;
import es.uniovi.asw.dashboard.pojos.Notification;
import es.uniovi.asw.dbmanagement.model.*;
import es.uniovi.asw.dbmanagement.persistence.CategoryData;
import es.uniovi.asw.dbmanagement.persistence.CommentData;
import es.uniovi.asw.dbmanagement.persistence.ParticipantData;
import es.uniovi.asw.dbmanagement.persistence.SuggestionData;
import es.uniovi.asw.dbmanagement.types.NotificationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SuggestionController {

    /**
     * Atributos en sesion: user -> contiene el Particpant logueado suggestion
     * -> la sugerencia que se selecciona para ver
     */
    //@Autowired
    //private KafkaProducer kafkaProducer;
    @Autowired
	private SuggestionData sugData;
    @Autowired
	private CommentData commentData;
    @Autowired
	private ParticipantData partData;
    @Autowired
	private CategoryData catData;

    private Logger log = LoggerFactory.getLogger(Application.class);

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registrar(HttpSession sesion, Model model, @RequestParam String archivo) {
        System.out.println(archivo);
        return "principalUsuario";
    }

    @RequestMapping("/principalUsuario")
    public String principalUsuario(HttpSession session, Model model) {
    	Participant p = partData.findLogableUser("lopez", "lopez");
    	session.setAttribute("user",p);
        return "principalUsuario";
    }


    @RequestMapping(value = "/principalAdmin", method = RequestMethod.GET)
    public String admin(HttpSession sesion, Model model) {
        return "principalAdmin";
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
	        //kafkaProducer.send("exampleTopic", json.toString());
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
		List<Suggestion> aux = new ArrayList<>();
		for (Suggestion suggestion : suggestions)
			//if (suggestion.getEstado().equals(EstadoPropuesta.Entramite))
				aux.add(suggestion);
		return aux;
	}

	@ModelAttribute("suggestionsRechazadas")
	public List<Suggestion> getSuggestionsRechazadas() {
		List<Suggestion> suggestions = sugData.getAllSuggestions();
		List<Suggestion> aux = new ArrayList<Suggestion>();
		for (Suggestion suggestion : suggestions)
			//if (suggestion.getEstado().equals(EstadoPropuesta.Rechazada))
				aux.add(suggestion);
		return aux;
	}

	@ModelAttribute("suggestionsAceptadas")
	public List<Suggestion> getSuggestionsAceptadas() {
		List<Suggestion> suggestions = sugData.getAllSuggestions();
		List<Suggestion> aux = new ArrayList<Suggestion>();
		for (Suggestion suggestion : suggestions)
			//if (suggestion.getEstado().equals(EstadoPropuesta.Aceptada))
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
