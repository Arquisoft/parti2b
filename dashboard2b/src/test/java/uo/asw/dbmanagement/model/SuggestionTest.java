package uo.asw.dbmanagement.model;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.dbmanagement.model.Suggestion;

public class SuggestionTest {

	@Test
	public void suggestionTest() {
		Suggestion suggestion1 = new Suggestion("Propuesta1");
		Suggestion suggestion2 = new Suggestion("Propuesta2");
		
		assertEquals("Propuesta1", suggestion1.getTitle());
		assertEquals("Propuesta2", suggestion2.getTitle());
		
		suggestion1.setTitle("NuevoNombrePropuesta1");
		suggestion2.setTitle("NuevoNombrePropuesta2");
		assertEquals("NuevoNombrePropuesta1", suggestion1.getTitle());
		assertEquals("NuevoNombrePropuesta2", suggestion2.getTitle());
		
		suggestion1.setNumberOfVotes(10);
		suggestion2.setNumberOfVotes(20);
		
		assertEquals(10, suggestion1.getNumberOfVotes());
		assertEquals(20, suggestion2.getNumberOfVotes());
		
		for(int i=0; i<10; i++){
			suggestion1.vote();
		}
		assertEquals(20, suggestion1.getNumberOfVotes());
		
		for(int i=0; i<5; i++){
			suggestion2.vote();
		}
		assertEquals(25, suggestion2.getNumberOfVotes());
		
		
		
	}

}
