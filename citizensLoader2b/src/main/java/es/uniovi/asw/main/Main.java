package es.uniovi.asw.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.uniovi.asw.model.Participant;
import es.uniovi.asw.parser.RList;
import es.uniovi.asw.parser.ReadList;


public class Main {


	public static void main(String[] args) {
		ReadList read = new RList();
		//BDUpdate bd = new BDUpdateImpl();
		
		System.out.print("Inserte ruta:");
		Scanner scanner = new Scanner(System.in);
		String ruta = scanner.nextLine();
		ruta = ruta.replace("\"", "");
		
//		String[] trozos = ruta.split("/");
//		String[] trozoFinal = trozos[trozos.length-1].split(".");
//		String extension = trozoFinal[trozoFinal.length-1];
		
		String[] subcadenas = ruta.split("\\.");
		String extension = subcadenas[subcadenas.length-1];
		extension = extension.toLowerCase();
		
		scanner.close();
		List<Participant> ciudadanos = new ArrayList<Participant>();
		if(extension.equals("csv")){
			
			ciudadanos.addAll(read.leerParticipantsCsv(ruta));
		}
		if(extension.equals("xlsx")){
			ciudadanos.addAll(read.leerParticipantsXlsx(ruta));
		}
		if(ciudadanos.size()>0){
			for (Participant ciudadano : ciudadanos) {
				System.out.println("Participante añadido " + ciudadano);
			}
		}else{
			System.out.println("Ningun participante a añadir");
		}
		
//		bd.eliminarCiudadanos();
//		Participant otro = bd.obtenerCiudadano(ciudadanos.get(0).getDni());
//		System.out.println(otro);
	}

}
