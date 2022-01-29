package com.techmarket.utils;

import com.techmarket.model.Matricula;

public class GenerateEnrollmentUtils {

	public static String generateEnrollment(Matricula ultimaMatricula) {
		
		int matricula = 0;
		String matriculaGerada = "1";
		
		if (ultimaMatricula != null) {
			matricula = Integer.valueOf(ultimaMatricula.getMatricula()) + 1;
			matriculaGerada = String.valueOf(matricula);
		}
		
		
		if (matricula >= 0 && matricula <= 9) {
			
			matriculaGerada = "0".repeat(5) + matriculaGerada;
			
		}if (matricula >= 10 && matricula <= 99) {
			
			matriculaGerada = "0".repeat(4) + matriculaGerada;
			
		}if (matricula >= 100 && matricula <= 999) {
			
			matriculaGerada = "0".repeat(3) + matriculaGerada;
			
		}if (matricula >= 1000 && matricula <= 9999) {
			
			matriculaGerada = "0".repeat(2) + matriculaGerada;
			
		}if (matricula >= 10000 && matricula <= 99999) {
			
			matriculaGerada = "0".repeat(1) + matriculaGerada;
			
		}
		
		return matriculaGerada;
	}

}
