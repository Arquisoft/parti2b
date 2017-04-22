package es.uniovi.asw.reportWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class WreportP implements WriteReport {

	@SuppressWarnings("deprecation")
	@Override
	public void writeReport(String report) {
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter writer = null;
		
		try {
			Date fecha = new Date();
			String nombre = fecha.toGMTString().replaceAll(":", "-");
			file = new File("./reports/"  + nombre+ ".txt");
			fileWriter = new FileWriter(file);
			writer = new BufferedWriter(fileWriter);
			writer.write(report);
			
		} catch (IOException e) {
			System.out.println("No se ha podido crear el fichero");
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("Error cerrando el fichero report");
				e.printStackTrace();
			}
			
		}
		
	}

}
