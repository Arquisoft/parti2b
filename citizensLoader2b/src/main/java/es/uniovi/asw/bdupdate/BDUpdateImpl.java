package es.uniovi.asw.bdupdate;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.hsqldb.jdbc.JDBCDriver;
import es.uniovi.asw.model.Participant;


public class BDUpdateImpl implements BDUpdate{



	/**
	 * Metodo que establece conexión con la base de datos local
	 * 
	 * @return objeto conexion
	 */
	@SuppressWarnings("finally")
	public static Connection crearConexion() {
		Connection conexion = null;
		try {
			DriverManager.registerDriver(new JDBCDriver());
			String url = "jdbc:hsqldb:file:./DB/data/test";
			// String url = "jdbc:hsqldb:hsql://localhost/";
			String user = "SA";
			String pass = "";
			conexion = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return conexion;
		}
	}

	/**
	 * Añade ciudadanos a la base de datos
	 * 
	 * @param ciudadanos,
	 *            lista de ciudadanos a insertar en la base de datos
	 */
	@Override
	public void addParticipant(Participant participant) {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into participant ");
			sb.append("(nombre, apellidos, email, direccion, nacionalidad, dni, fecha_nacimiento, password,usuario) ");
			sb.append("values (?,?,?,?,?,?,?,?,?)");
			PreparedStatement ps = con.prepareStatement(sb.toString());
				ps.setString(1, participant.getNombre());
				ps.setString(2, participant.getApellidos());
				ps.setString(3, participant.getEmail());
				ps.setString(4, participant.getDireccion());
				ps.setString(5, participant.getNacionalidad());
				ps.setString(6, participant.getDni());
				
				Date date = participant.getFecha_nacimiento();
				java.sql.Date fecha = new java.sql.Date(date.getTime());
				ps.setDate(7, fecha);
				ps.setString(8, participant.getPassword());
				ps.setString(9, participant.getUsuario());
				ps.execute();
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Elimina 1 ciudadano cuyo dni se introduce como parametro
	 * 
	 * @param dni
	 *            del ciudadano a borrar
	 */
	@Override
	public void deleteParticipant(String dni) {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("delete from participant ");
			sb.append("where dni = ?");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setString(1, dni);
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.print("Seguramente es porque el formato dni es incorrecto");
			e.printStackTrace();
		}

	}

	/**
	 * Se actualizan los datos de un usuario. Los nuevos datos se añaden a un
	 * objeto ciudadano que sera el que se use para actualizar los datos (se
	 * basa en el dni)
	 * 
	 * @param participant
	 *            a actualizar
	 */
	@Override
	public void updateParticipant(Participant participant) {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE participant "
					+ "set nombre= ?, apellidos= ?, email= ?, fecha_nacimiento= ?, direccion= ?, nacionalidad= ?"
					+ "where dni=?");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setString(1, participant.getNombre());
			ps.setString(2, participant.getApellidos());
			ps.setString(3, participant.getEmail());
			
			Date date = participant.getFecha_nacimiento();
			java.sql.Date fecha = new java.sql.Date(date.getTime());
			ps.setDate(4, fecha);
			ps.setString(5, participant.getDireccion());
			ps.setString(6, participant.getNacionalidad());
			ps.setString(7, participant.getDni());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("no existe el ciudadano especificado");
			e.printStackTrace();
		}
	}
	@Override
	public Participant findParticipant(String dni) {
		Connection con = crearConexion();
		String consulta = "SELECT c.* FROM participant c WHERE c.dni = ?";
		Participant participant = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(consulta);
			ps.setString(1, dni);
			rs = ps.executeQuery();
			while (rs.next()) {
				participant = new Participant(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"),
						rs.getString("direccion"), rs.getString("nacionalidad"), rs.getString("dni"),
						rs.getDate("fecha_nacimiento"),rs.getString("nombre")+ rs.getString("apellidos"));
				participant.setPassword(rs.getString("password"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participant;
	}



	/**
	 * Elimina todos los ciudadanos
	 */
	@Override
	public void deleteAllParticipants() {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("delete from participant ");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.print("Error al borrar todos los ciudadanos");
			e.printStackTrace();
		}
	}

}