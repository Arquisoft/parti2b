package es.uniovi.asw;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import es.uniovi.asw.model.Participant;

public class Leer {
	
	public static ArrayList<Participant> Ciudadanos(ArrayList<Participant> ciudadanos, String ruta){
		ArrayList<Participant> aux = new ArrayList<Participant>();
		String[] subcadenas = ruta.split("\\.");
		String extension = subcadenas[subcadenas.length-1];
		extension = extension.toLowerCase();
		extension = Character.toUpperCase(extension.charAt(0)) + extension.substring(1);
		try {
			Class<?> cl = Class.forName("es.uniovi.asw."+extension);
			Constructor<?> con = cl.getConstructor();
			Formatos xyz = (Formatos) con.newInstance();
			aux = xyz.leerCiudadanos(ciudadanos, ruta);
		} catch (Exception e) {
			System.out.println("Extensión no soportada");
		} catch (Error e) {
			System.out.println("Extensión no soportada");
		} 
		return aux;
	}
}
