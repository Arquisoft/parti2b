package es.uniovi.asw.parser.util;

import java.util.Random;

public class CreatePassword {
	
	
	
	public static String crearPassword() {
		String password = "";
		char[] minusculas = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] mayusculas = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
		char[] numeros = "0123456789".toCharArray();
		char[] simbolos = "'¿?*+-$%".toCharArray();
		
		// Tiene una letra mayuscula
		Random random = new Random();
		int pos = random.nextInt(mayusculas.length);
		password += mayusculas[pos];
		
		// Tiene 5 letras minusculas
		for (int i = 0; i < 5; i++) {
			random = new Random();
			pos = random.nextInt(minusculas.length);
			password += minusculas[pos];
		}
		
		// Tiene un numero
		random = new Random();
		pos = random.nextInt(numeros.length);
		password += numeros[pos];
		
		// Tiene un simbolo especial 
		random = new Random();
		pos = random.nextInt(simbolos.length);
		password += simbolos[pos];
		
		return password;
	}
}
