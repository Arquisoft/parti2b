package uo.asw.participants.wrappers;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.dbManagement.model.Participant;
import es.uniovi.asw.participants.wrappers.ParticipantInfo;
import es.uniovi.asw.util.DateUtil;

public class ParticipantInfoTest {

    private Participant participant1;
    private Participant participant2;

    private ParticipantInfo participantInfo1Auto;
    private ParticipantInfo participantInfo1Manual;

    private ParticipantInfo participantInfo2Auto;
    private ParticipantInfo participantInfo2Manual;

    private Date fechaPruebas = DateUtil.asDate(LocalDate.now().withYear(1994));

    @Before
    public void setUp() {
	participant1 = new Participant("1");
	participant1.setNombre("Antonio");
	participant1.setApellidos("Machin");
	participant1.setFechaNacimiento(fechaPruebas);
	participant1.setEmail("antonioMachine@hotmail.com");

	participant2 = new Participant("2");
	participant2.setNombre("Luis");
	participant2.setApellidos("Aguile");
	participant2.setFechaNacimiento(fechaPruebas);
	participant2.setEmail("luisAguile@hotmail.com");

    }

    @Test
    public void constructorAutoTest() {
	participantInfo1Auto = new ParticipantInfo(participant1);
	participantInfo2Auto = new ParticipantInfo(participant2);

	assertEquals("Antonio", participantInfo1Auto.getNombre());
	assertEquals("Machin", participantInfo1Auto.getApellidos());
	assertEquals(23, participantInfo1Auto.getEdad());
	assertEquals("antonioMachine@hotmail.com",
		participantInfo1Auto.getEmail());
	assertEquals("1", participantInfo1Auto.getNdi());

	assertNotEquals(participantInfo1Auto, participantInfo2Manual);
    }

    @Test
    public void constructorManualTest() {
	participantInfo1Manual = new ParticipantInfo("Antonio", "Machin",
		fechaPruebas, "1", "antonioMachine@hotmail.com");

	participantInfo2Manual = new ParticipantInfo("Luis", "Aguile",
		fechaPruebas, "2", "luisAguile@hotmail.com");

	assertEquals("Antonio", participantInfo1Manual.getNombre());
	assertEquals("Machin", participantInfo1Manual.getApellidos());
	assertEquals(23, participantInfo1Manual.getEdad());
	assertEquals("antonioMachine@hotmail.com",
		participantInfo1Manual.getEmail());
	assertEquals("1", participantInfo1Manual.getNdi());

	assertNotEquals(participantInfo1Manual, participantInfo2Manual);
    }

    @Test
    public void constructorsTest() {
	participantInfo1Auto = new ParticipantInfo(participant1);
	participantInfo2Auto = new ParticipantInfo(participant2);

	participantInfo1Manual = new ParticipantInfo("Antonio", "Machin",
		fechaPruebas, "1", "antonioMachine@hotmail.com");

	participantInfo2Manual = new ParticipantInfo("Luis", "Aguile",
		fechaPruebas, "2", "luisAguile@hotmail.com");

	assertEquals(participantInfo1Auto, participantInfo1Manual);
	assertEquals(participantInfo2Auto, participantInfo2Manual);

	assertNotEquals(participantInfo1Auto, participantInfo2Manual);
	assertNotEquals(participantInfo1Manual, participantInfo2Auto);
    }

}
