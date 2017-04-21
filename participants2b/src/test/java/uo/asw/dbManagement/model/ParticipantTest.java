package uo.asw.dbManagement.model;

import static org.junit.Assert.*;


import java.util.Date;


import org.junit.Test;

import es.uniovi.asw.dbManagement.model.Participant;

public class ParticipantTest {

	@Test
	public void constructorTest() {
		Participant paco = new Participant("Paco", "Fernandez", "paco@hotmail.com");
		assertEquals("Paco", paco.getNombre());
		assertEquals("Fernandez", paco.getApellidos());
		assertEquals("paco@hotmail.com", paco.getEmail());
	}
	
	@Test
	public void settersTest() {
		Participant carmen = new Participant("1");
		Date fechaNacimiento = new Date();
		carmen.setNombre("Carmen");
		carmen.setApellidos("Lopez");
		carmen.setEmail("carmen@hotmail.com");
		carmen.setNacionalidad("Francia");
		carmen.setPassword("carmen123");
		carmen.setFechaNacimiento(fechaNacimiento);
		
		assertEquals("Carmen", carmen.getNombre());
		assertEquals("Lopez", carmen.getApellidos());
		assertEquals("carmen@hotmail.com", carmen.getEmail());
		assertEquals("Francia", carmen.getNacionalidad());
		assertEquals("carmen123", carmen.getPassword());
		assertEquals(fechaNacimiento, carmen.getFechaNacimiento());
		
	}
	
	@Test
	public void equalsTest() {
		Participant paco1 = new Participant("Paco", "Fernandez", "paco@hotmail.com");
		Participant paco2 = new Participant("Paco", "Fernandez", "paco@hotmail.com");
		
		assertTrue(paco1.equals(paco2));
	}
}
