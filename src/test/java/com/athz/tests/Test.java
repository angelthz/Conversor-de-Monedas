package com.athz.tests;

import java.time.LocalDate;

public class Test {
	public static void main(String[] args) {
		System.out.println();
		String test = "2023-05-07";
		
		System.out.println(LocalDate.parse(test).plusDays(7));
		System.out.println(LocalDate.now());
		System.out.println(LocalDate.parse(test).plusDays(7).isEqual(LocalDate.now()));
		System.out.println(LocalDate.parse(test).plusDays(7).isBefore(LocalDate.now()));
/* 
		LocalDate actual = LocalDate.now().plusDays(7);
		LocalDate pasada = LocalDate.of(2023, 05, 21);
		System.out.println(actual);
		
		System.out.println(pasada);

		if(actual.equals(pasada)){
			System.out.println("Hora de actualizar");
		}
		else
			System.out.println("Aun no hay que actualizar");

 */

			
		System.out.println();
	}
}
