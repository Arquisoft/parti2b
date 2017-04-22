package es.uniovi.asw;

import java.util.ArrayList;
import java.util.Scanner;

import es.uniovi.asw.model.Participant;


public class MainPruebas {


	public static void main(String[] args) {
		System.out.print("Inserte ruta:");
		Scanner scanner = new Scanner(System.in);
		String ruta = scanner.nextLine();
		ruta = ruta.replace("\"", "");
		scanner.close();
		ArrayList<Participant> ciudadanos = new ArrayList<Participant>();
		Leer.Ciudadanos(ciudadanos, ruta);
		for (Participant ciudadano : ciudadanos) {
			System.out.println("imprimiendo desde el main " + ciudadano);
		}
		
		BBDD.eliminarCiudadanos();
		BBDD.insertarCiudadano(ciudadanos);
		Participant otro = BBDD.obtenerCiudadano(ciudadanos.get(0).getDni());
		System.out.println(otro);
	}

}
