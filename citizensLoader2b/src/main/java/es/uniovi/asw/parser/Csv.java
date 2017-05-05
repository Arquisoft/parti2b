package es.uniovi.asw.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.uniovi.asw.bdupdate.WreportR;
import es.uniovi.asw.bdupdate.WriteReport;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.parser.util.CrearCorreo;
import es.uniovi.asw.parser.util.CreatePassword;

public class Csv {

	public List<Participant> leerParticipant(String ruta) {
		List<Participant> participants = new ArrayList<Participant>();
		Insert bd = new InsertR();
		WriteReport wreport = new WreportR();
		StringBuilder sb = new StringBuilder();
		try {
			FileInputStream is = new FileInputStream(ruta);
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader buffReader = new BufferedReader(isr);

			String str = "";

			while ((str = buffReader.readLine()) != null) {
				String[] trozos = str.split(";");
				if (trozos[0].equals("Nombre"))
					continue;
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(trozos[3]);
				java.sql.Date nacimiento = new java.sql.Date(date.getTime());
				
				String nombreP = trozos[0];
				String apellidos = trozos[1];
				String dni= trozos[6];
				
				Participant p = bd.findParticipant(dni);
				
				
				if(p==null){
					Participant ciu = new Participant(nombreP,apellidos, trozos[2], trozos[4], trozos[5], dni,
							nacimiento,nombreP+apellidos);
					String password = CreatePassword.crearPassword();
					ciu.setPassword(password);
					participants.add(ciu);
					CrearCorreo.mandarCorreo(ciu);
					bd.addParticipant(ciu);
				}else{
					sb.append("No se ha insertado el usuario "+nombreP +" "+ apellidos+"con dni: "+ dni+ ", porque ya existe\n");
				}
			}
			wreport.addReport(sb.toString());

			buffReader.close();

		} catch (Exception e) {
			wreport.addReport("Error al leer el archivo csv");
		}
		return participants;
	}
}
