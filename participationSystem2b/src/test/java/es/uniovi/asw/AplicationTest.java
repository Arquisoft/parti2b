package es.uniovi.asw;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Category;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.service.Service;
import es.uniovi.asw.service.impl.CategoryServiceImpl;
import es.uniovi.asw.service.impl.CommentServiceImpl;
import es.uniovi.asw.service.impl.ParticipantServiceImpl;
import es.uniovi.asw.service.impl.SuggestionServiceImpl;

public class AplicationTest {

	@Before
	public void before() {
		//Service.getParticipantService().init();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void jpaTest(){
		
		CommentServiceImpl servComment=Service.getCommentService();
		ParticipantServiceImpl servPart= Service.getParticipantService();
		SuggestionServiceImpl servSug=Service.getSuggestionService();
		CategoryServiceImpl servCat = Service.getCategoryService();
//		Participant user = new Participant("Daniel", "Orviz", "orviz@prueba", "dir", "España", "23453212Y",
//				new Date(1995 - 1900, 2, 14),"DanielOrviz");
		assertNotNull(servPart.findParticipant("23453212Y"));
//		user  = new Participant("Admin", "1", "admin@prueba", "dir", "España", "65431789T",
//				new Date(1995 - 1900, 2, 14),"Admin1");
		assertNotNull(servPart.findParticipant("65431789T"));
		// creamos un participant
		Participant c = new Participant("juan", "Garcia", "email@prueba", "dir", "España", "1564613I",
				new Date(1995 - 1900, 2, 25),"PepeGarcia");
		
		//lo metemos en la base de datos
		servPart.addParticipant(c);
		assertNotNull(Service.getParticipantService().findParticipant("1564613I"));
		// creamos una sugerencia
		
		Category categoria = new Category("Categoria p");
		servCat.addCategory(categoria);
		System.out.println(servCat.findAllCategories());
		Suggestion sug = new Suggestion(c, "prueba", "para probar", categoria);
		assertTrue(c.getSuggestions().contains(sug));
		servSug.addSuggestion(sug);
		
		Comment comment = new Comment(c, sug, "comentario");
		
		assertTrue(sug.getComments().contains(comment));
		assertTrue(c.getComments().contains(comment));
		servComment.addComment(comment);
		
		assertNotNull(servSug.getSuggestionByParticipant(c));
		
		servComment.deleteComment(comment);
		servSug.deleteSuggestion(sug);
		servCat.deleteCateogory(categoria);
		servPart.deleteParticipantByDni("1564613I");
		
	}
	
}
