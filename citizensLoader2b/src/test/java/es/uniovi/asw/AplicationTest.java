package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.bdupdate.BDUpdate;
import es.uniovi.asw.bdupdate.BDUpdateImpl;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.parser.RList;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.Xlsx;
import es.uniovi.asw.parser.util.CrearCorreo;
import es.uniovi.asw.parser.util.CreatePassword;

public class AplicationTest {
	private ReadList read = new RList();
	private BDUpdate bd = new BDUpdateImpl();

	@Before
	public void before() {
	}



	@Test
	public void testCargarCSS() {
		// leemos y cargamos el fichero
		bd.deleteParticipant("90500084Y");
		bd.deleteParticipant("19160962F");
		bd.deleteParticipant("09940449X");
		Xlsx cs = new Xlsx();
		List<Participant> ciudadanos = new ArrayList<Participant>();
		ciudadanos.addAll(cs.leerCiudadanos("./src/test/java/es/uniovi/asw/test.xlsx"));

		// probamos con el primer ciudadano
		Participant c = ciudadanos.get(0);
		assertEquals("Juan", c.getNombre());
		assertEquals("Torres Pardo", c.getApellidos());
		assertEquals("juan@example.com", c.getEmail());
		// assertEquals("1985-10-10", String.valueOf(c.getFecha_nacimiento()));
		assertEquals("C/ Federico García Lorca 2", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("90500084Y", c.getDni());

		// probamos con el segundo ciudadano
		c = ciudadanos.get(1);
		assertEquals("Luis", c.getNombre());
		assertEquals("López Fernando", c.getApellidos());
		assertEquals("luis@example.com", c.getEmail());
		// assertEquals("1970-03-02", String.valueOf(c.getFecha_nacimiento()));
		assertEquals("C/ Real Oviedo 2", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("19160962F", c.getDni());

		// probamos con el tercer ciudadano
		c = ciudadanos.get(2);
		assertEquals("Ana", c.getNombre());
		assertEquals("Torres Pardo", c.getApellidos());
		assertEquals("ana@example.com", c.getEmail());
		// assertEquals("1960-01-01", String.valueOf(c.getFecha_nacimiento()));
		assertEquals("Av. De la Constitución 8", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("09940449X", c.getDni());

		bd.deleteParticipant("90500084Y");
		bd.deleteParticipant("19160962F");
		bd.deleteParticipant("09940449X");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void addCiudadanoTest() {

		Participant c = new Participant("Pepe", "Garcia", "email@prueba", "dir", "España", "564613I",
				new Date(1995 - 1900, 2, 25),"PepeGarcia");
		bd.addParticipant(c);

		Participant cBD = bd.findParticipant("564613I");
		assertNotNull(cBD);
		assertEquals("Pepe", cBD.getNombre());
		assertEquals("Garcia", cBD.getApellidos());
		assertEquals("email@prueba", cBD.getEmail());
		assertEquals("dir", cBD.getDireccion());
		assertEquals("España", cBD.getNacionalidad());
		assertEquals("564613I", cBD.getDni());
		assertEquals(new Date(1995 - 1900, 2, 25), cBD.getFecha_nacimiento());

		c.setDireccion("direccion2");
		c.setEmail("otroemail@.com");
		c.setApellidos("Garcia Garcia");

		bd.updateParticipant(c);
		cBD = bd.findParticipant("564613I");
		assertNotNull(cBD);
		assertEquals("Pepe", cBD.getNombre());
		assertEquals("Garcia Garcia", cBD.getApellidos());
		assertEquals("otroemail@.com", cBD.getEmail());
		assertEquals("direccion2", cBD.getDireccion());
		assertEquals("España", cBD.getNacionalidad());
		assertEquals("564613I", cBD.getDni());
		assertEquals(new Date(1995 - 1900, 2, 25), cBD.getFecha_nacimiento());

		bd.deleteParticipant("564613I");
		cBD = bd.findParticipant("564613I");
		assertNull(cBD);
		
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testCargarCiudadanos() {
		// leemos y cargamos el fichero
		bd.deleteParticipant("90500084Y");
		bd.deleteParticipant("19160962F");
		bd.deleteParticipant("09940449X");
		List<Participant> ciudadanos = new ArrayList<Participant>();
		ciudadanos.addAll(read.leerParticipantsXlsx("./src/test/java/es/uniovi/asw/test.xlsx"));
//		for (Participant participant : ciudadanos) {
//			bd.insertarCiudadano(participant);
//		}
		
		
		// probamos con el primer ciudadano
		Participant c = ciudadanos.get(0);
		assertEquals("Juan", c.getNombre());
		assertEquals("Torres Pardo", c.getApellidos());
		assertEquals("juan@example.com", c.getEmail());
		assertEquals(new Date(1985 - 1900, 10 - 1, 10), c.getFecha_nacimiento());
		assertEquals("C/ Federico García Lorca 2", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("90500084Y", c.getDni());

		// probamos con el segundo ciudadano
		c = ciudadanos.get(1);
		assertEquals("Luis", c.getNombre());
		assertEquals("López Fernando", c.getApellidos());
		assertEquals("luis@example.com", c.getEmail());
		assertEquals(new Date(1970 - 1900, 3 - 1, 2), c.getFecha_nacimiento());
		assertEquals("C/ Real Oviedo 2", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("19160962F", c.getDni());

		// probamos con el tercer ciudadano
		c = ciudadanos.get(2);
		assertEquals("Ana", c.getNombre());
		assertEquals("Torres Pardo", c.getApellidos());
		assertEquals("ana@example.com", c.getEmail());
		assertEquals(new Date(1960 - 1900, 1 - 1, 1), c.getFecha_nacimiento());
		assertEquals("Av. De la Constitución 8", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("09940449X", c.getDni());

		assertNotNull(bd.findParticipant("90500084Y"));
		assertNotNull(bd.findParticipant("19160962F"));
		assertNotNull(bd.findParticipant("09940449X"));
		
		bd.deleteParticipant("90500084Y");
		bd.deleteParticipant("19160962F");
		bd.deleteParticipant("09940449X");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCrearCorreo() {
		Participant c = new Participant("Marcos", "Garcia", "marcos@mail.com", "C/ peru 3", "Español", "87963215P",
				new Date(1990 - 1900, 2, 10),"MarcosGarcia");
		c.setPassword(CreatePassword.crearPassword());
		assertNotNull(c.getPassword());

		String rutaFichero = "./correos/" + c.getNombre()+"-"+c.getDni() + ".txt";
		File fichero = new File(rutaFichero);
		assertFalse(fichero.exists());

		CrearCorreo.mandarCorreo(c);
		String[] correo = new String[9];

		correo[0] = "Buenos dias " + c.getNombre();
		correo[1] = "Usted a sido dado de alta con exito en el sistema de particion ciudadana.";
		correo[2] = "Sus credenciales son:";
		correo[3] = "\tUsuario: " + c.getNombre()+c.getApellidos();
		correo[4] = "\tContraseña: " + c.getPassword();
		correo[5] = "";
		correo[6] = "Un saludo y gracias por darse de alta.";
		correo[7] = "";
		correo[8] = "Atentamente el ayuntamiento.";
		fichero = new File(rutaFichero);
		assertTrue(fichero.exists());

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fichero));

			int i = 0;
			while (reader.ready()) {
				assertEquals(correo[i], reader.readLine());
				i++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fichero.delete();
	}

	@Test
	public void testEqualsXlsxCsv() {
		List<Participant> xlsx = new ArrayList<Participant>();
		List<Participant> cvs = new ArrayList<Participant>();

		String rutaXLSX = "./src/test/java/es/uniovi/asw/test.xlsx";
		String rutaCVS = "./src/test/java/es/uniovi/asw/test.csv";

		xlsx = read.leerParticipantsXlsx(rutaXLSX);
		
		bd.deleteParticipant("90500084Y");
		bd.deleteParticipant("19160962F");
		bd.deleteParticipant("09940449X");
		cvs = read.leerParticipantsCsv(rutaCVS);
		
		bd.deleteParticipant("90500084Y");
		bd.deleteParticipant("19160962F");
		bd.deleteParticipant("09940449X");
		for (int i = 0; i < cvs.size(); i++) {
			assertTrue((xlsx.get(i)).equals(cvs.get(i)));
			assertTrue((xlsx.get(i)).hashCode() == (cvs.get(i)).hashCode());
		}
	}
	
	@Test
	public void exceptionXLSX(){
		@SuppressWarnings("unused")
		List<Participant> xlsx = new ArrayList<Participant>();
		String rutaXLSX = "./src/test/java/es/uniovi/asw/test1.xlsx";
		read.leerParticipantsXlsx(rutaXLSX);
		
	}
	
	@Test
	public void exceptionCSV(){
		@SuppressWarnings("unused")
		List<Participant> cvs = new ArrayList<Participant>();
		String rutaCVS = "./src/test/java/es/uniovi/asw/test.csv";
		cvs = read.leerParticipantsCsv(rutaCVS);
		
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testEliminarCiudadano() {

		Participant ciudadano = new Participant("Hugo", "Perez", "yo@me.com", "Calle no se que Oviedo", "español",
				"123456789A", new Date(18, 7, 1995),"HugoPerez");

		bd.addParticipant(ciudadano);
		Participant cBBDD = bd.findParticipant("123456789A");
		assertNotNull(cBBDD);

		bd.deleteParticipant("123456789A");

		cBBDD = bd.findParticipant("123456789A");
		assertNull(cBBDD);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testEliminarTodosCiudadanos() {
		Participant ciudadano = new Participant("Hugo", "Perez", "yo@me.com", "Calle no se que Oviedo", "español",
				"123456789A", new Date(18, 7, 1995),"HugoPerez");

		bd.addParticipant(ciudadano);
		Participant cBBDD = bd.findParticipant("123456789A");
		assertNotNull(cBBDD);

		bd.deleteParticipant("123456789A");
		

		cBBDD = bd.findParticipant("123456789A");
		assertNull(cBBDD);
	}

//	@SuppressWarnings("deprecation")
//	@Test
//	public void testCrearPassword() {
//
//		Participant ciudadano = new Participant("Hugo", "Perez", "yo@me.com", "Calle no se que Oviedo", "español", "1234A",
//				new Date(18, 7, 1995),"HugoPerez");
//
//		bd.addParticipant(ciudadano);
//		Participant cBBDD = bd.findParticipant("1234A");
//		String password = CreatePassword.crearPassword();
//		//bd.guardaarPasswordUsuario("1234A", password);
//		cBBDD = bd.findParticipant("1234A");
//
//		assertEquals(password, cBBDD.getPassword());
//	}

}
