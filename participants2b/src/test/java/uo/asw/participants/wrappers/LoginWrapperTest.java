package uo.asw.participants.wrappers;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.participants.wrappers.LoginWrapper;

public class LoginWrapperTest {

	private LoginWrapper loginWrapper;
	
	@Test
	public void testLoginWrapper() {
		loginWrapper = new LoginWrapper();
		loginWrapper.setLogin("Paco");
		loginWrapper.setPassword("Paco123");
		
		assertEquals("Paco", loginWrapper.getLogin());
		assertEquals("Paco123", loginWrapper.getPassword());
	}
}
