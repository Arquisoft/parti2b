package es.uniovi.asw.parser;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.bdupdate.BDUpdate;
import es.uniovi.asw.bdupdate.BDUpdateImpl;
import es.uniovi.asw.bdupdate.WreportR;
import es.uniovi.asw.bdupdate.WriteReportBD;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.parser.util.CrearCorreo;
import es.uniovi.asw.parser.util.CreatePassword;

public class Xlsx {

	public List<Participant> leerCiudadanos( String ruta) {
		List<Participant> ciudadanos = new ArrayList<Participant>();
		BDUpdate bd = new BDUpdateImpl();
		WriteReportBD wreport = new WreportR();
		StringBuilder sb = new StringBuilder();
		try {
			FileInputStream file = new FileInputStream(new File(ruta));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				ArrayList<Object> aux = new ArrayList<Object>();
				for (int i = 0; i < 7; i++) {
					aux.add(row.getCell(i) != null ? row.getCell(i).toString() : null);
				}

				String nombre = row.getCell(0) != null ? row.getCell(0).toString() : null;
				if (nombre != null && nombre.equals("Nombre"))
					continue;
				String fecha = row.getCell(3) != null ? row.getCell(3).toString() : null;

				Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(fecha);
				java.sql.Date nacimiento = new java.sql.Date(date.getTime());
				String nombreP =aux.get(0).toString() ;
				String apellidos = aux.get(1).toString();
				String dni= aux.get(6).toString();
				Participant p = bd.findParticipant(dni);
				if(p==null){
					Participant ciudadano = new Participant(nombreP, apellidos, aux.get(2).toString(),
							aux.get(4).toString(), aux.get(5).toString(), dni, nacimiento,nombreP+apellidos);
					String password = CreatePassword.crearPassword();
					ciudadano.setPassword(password);
					ciudadanos.add(ciudadano);
					CrearCorreo.mandarCorreo(ciudadano);
					bd.addParticipant(ciudadano);
				}else{
					
					sb.append("No se ha insertado el usuario "+nombreP +" "+ apellidos+"con dni: "+ dni+ ", porque ya existe\n");

				}
			}
			wreport.addReport(sb.toString());
			file.close();
			workbook.close();
		} catch (Exception e) {
			System.err.println("Error al leer del excel xlsx");
		}
		return ciudadanos;
	}

}