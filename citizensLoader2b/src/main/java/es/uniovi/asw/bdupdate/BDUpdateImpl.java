package es.uniovi.asw.bdupdate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import es.uniovi.asw.model.Participant;
import es.uniovi.asw.persistence.finder.ParticipantFinder;
import es.uniovi.asw.persistence.util.Jpa;

public class BDUpdateImpl implements BDUpdate{

	/**
	 * Metodo que establece conexión con la base de datos local
	 * 
	 * @return objeto conexion
	 */
//	private static Connection crearConexion() {
//		Connection conexion = null;
//		try {
//			DriverManager.registerDriver(new JDBCDriver());
//			String url = "jdbc:hsqldb:file:./DB/data/test";
//			//Descomentar para probar los test en local.
//			//String url = "jdbc:hsqldb:hsql://localhost/";
//			String user = "SA";
//			String pass = "";
//			conexion = DriverManager.getConnection(url, user, pass);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conexion;
//	}

	/**
	 * Añade ciudadanos a la base de datos
	 * 
	 * @param ciudadanos,
	 *            lista de ciudadanos a insertar en la base de datos
	 */
	@Override
	public void addParticipant(Participant participant) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		if(ParticipantFinder.findByDni(participant.getDni())==null){
			Jpa.getManager().persist(participant);
		}
		trx.commit();
	}

	/**
	 * Elimina 1 ciudadano cuyo dni se introduce como parametro
	 * 
	 * @param dni
	 *            del ciudadano a borrar
	 */
	@Override
	public void deleteParticipant(String dni) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Participant participant = ParticipantFinder.findByDni(dni);
		if(participant !=null){
			Participant p=Jpa.getManager().find(Participant.class, participant.getId());
			if(p!=null){
				Jpa.getManager().remove(p);
				
			}
		}
		
		trx.commit();

	}

	/**
	 * Se actualizan los datos de un usuario. Los nuevos datos se añaden a un
	 * objeto ciudadano que sera el que se use para actualizar los datos (se
	 * basa en el dni)
	 * 
	 * @param ciudadano
	 *            a actualizar
	 */
	@Override
	public void updateParticipant(Participant participant) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		if(participant!=null)
			Jpa.getManager().merge(participant);
		trx.commit();
	}
	@Override
	public Participant findParticipant(String dni) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		Participant c =ParticipantFinder.findByDni(dni);
		return c;
	}

//	/**
//	 * Metodo que guarda en la base de datos la contraseña asociada al usuario
//	 * que se identifica con el dni
//	 */
//	@Override
//	public void guardaarPasswordUsuario(String dni, String password) {
//		Connection con = crearConexion();
//		String consulta = "update Ciudadano set password = ? where dni = ?";
//		PreparedStatement ps = null;
//		try {
//			ps = con.prepareStatement(consulta);
//			ps.setString(1, password);
//			ps.setString(2, dni);
//			ps.executeUpdate();
//			ps.close();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Elimina todos los ciudadanos
	 */
	@Override
	public void deleteAllParticipants() {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		List<Participant> participants = ParticipantFinder.findAll();
		for (Participant participant : participants) {
			Participant p=Jpa.getManager().find(Participant.class, participant.getId());
			if(p!=null){
				Jpa.getManager().remove(p);
			}
		}
		trx.commit();
	}

}
