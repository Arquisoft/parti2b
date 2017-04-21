package uo.asw.participants.wrappers;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.participants.wrappers.ChangePassWrapper;

public class ChangePasswordWrapperTest {

    private ChangePassWrapper changePass;

    @Test
    public void changePasswordWrapperTest() {
	changePass = new ChangePassWrapper();
	changePass.setLogin("Paco");
	changePass.setPassword("Paco123");
	changePass.setNewPassword("Paco321");

	assertEquals("Paco", changePass.getLogin());
	assertEquals("Paco123", changePass.getPassword());
	assertEquals("Paco321", changePass.getNewPassword());

    }
}
