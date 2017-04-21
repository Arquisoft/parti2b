package uo.asw.dbManagement.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import es.uniovi.asw.Application;
import es.uniovi.asw.dbManagement.ParticipantData;
import es.uniovi.asw.dbManagement.model.Participant;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ParticipantDataTest {

    @Autowired
    private ParticipantData participantData;

    @Test
    public void getExistingParticipant() {
	Participant andreu = participantData.getData("andreu@doe.com");
	Participant berto = participantData.getData("berto@doe.com");

	assertEquals("Andreu", andreu.getNombre());
	assertEquals("Berto", berto.getNombre());

	assertEquals("462799", andreu.getNdi());
	assertEquals("622104", berto.getNdi());

    }

    @Test
    public void getNotExistingParticipant() {
	Participant pedro = participantData.getData("pedro@doe.com");
	Participant antonio = participantData.getData("antonio@doe.com");

	assertNull(pedro);
	assertNull(antonio);

    }

    @Test
    public void okUpdateParticipant() {
	Participant andreu = participantData.getData("andreu@doe.com");
	Participant berto = participantData.getData("berto@doe.com");

	assertEquals("andreu123", andreu.getPassword());
	assertEquals("berto123", berto.getPassword());

	andreu.setPassword("andreu321");
	berto.setPassword("berto321");

	participantData.updateInfo(andreu);
	participantData.updateInfo(berto);

	andreu = participantData.getData("andreu@doe.com");
	berto = participantData.getData("berto@doe.com");

	assertEquals("andreu321", andreu.getPassword());
	assertEquals("berto321", berto.getPassword());

    }

}
