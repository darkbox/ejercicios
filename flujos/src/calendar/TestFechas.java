package calendar;

import java.util.Calendar;

/**
 * 
 * Clase TestFechas
 * @author Rafael Garc�a Maliga
 * @since 17/04/2013
 * @version 1.0
 *
 * Este programa TestFecha maneja objetos de la clase Calendar. Almac�nalo en el paquete "calendar"
 * Mostrar� un men� con las siguientes opciones:
 * 
 * Comprobar a�os transcurridos
 * Es futuro?
 * Es pasado?
 * Salir
 * S�lo se admiten fechas v�lidas. En caso de no serlas, se lanzar�a una excepci�n FechaNoValida que 
 * se capturar�a y mostrar�a el error.
 * 
 * Comprobar a�os transcurridos solicita dos fechas por teclado e indica cu�ntos a�os han transcurrido
 *  entre ellas. Se indica tambi�n si el tiempo ha pasado hacia delante o hacia atr�s.
 *  Es futuro? indica si la fecha introducida por teclado es futuro o no.
 *  Es pasado?indica si la fecha introducida por teclado es pasado o no.
 * 
 */
public class TestFechas {

	private static TecladoScanner t = TecladoScanner.getInstance();
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		Menu menu = new Menu("Test fechas", true);
		menu.add("Comprobar a�os transcurridos");
		menu.add("Es futuro?");
		menu.add("Es pasado?");
		
		while(true){
			menu.print();
			switch (menu.select()) {
			case 1: // Comprobar a�os
				System.out.println(diffYears(newDate(), newDate()));
				break;
			case 2: // es futuro
				if(isFuture(newDate()))
					System.out.println("S�.");
				else
					System.out.println("No.");
				break;
			case 3: // es pasado
				if(isPast(newDate()))
					System.out.println("S�.");
				else
					System.out.println("No.");
				break;
			}
		}
	}


	/**
	 * Comprueba si una fecha es pasada o no
	 * @param newDate
	 * @return false si es pasado
	 */
	private static boolean isPast(Calendar newDate) {
		// Captura fecha del sistema
		Calendar present = Calendar.getInstance();
		if(newDate.compareTo(present) <= 0){
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si es futuro una fecha
	 * @param newDate
	 * @return true si es futuro
	 */
	private static boolean isFuture(Calendar newDate) {
		// Captura fecha del sistema
		Calendar present = Calendar.getInstance();
		if(newDate.compareTo(present) > 0){
			return true;
		}
		return false;
	}


	/**
	 * Comprueba los a�os transcurridos entre dos fechas
	 * @param newDate
	 * @param newDate2
	 * @return int
	 */
	private static String diffYears(Calendar newDate, Calendar newDate2) {
		String s = "Han pasado ";
		int temp = (newDate.get(Calendar.YEAR)-newDate2.get(Calendar.YEAR));
		if(temp < 0){
			temp *= -1;
			s += " hacia atr�s " + temp + " a�o/s";
		}else{
			s += " hacia delante " + temp + " a�o/s";
		}
		return s;
	}


	/**
	 * Recoge una fecha por teclado
	 * @return Calendar
	 */
	private static Calendar newDate() {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		System.out.println("Introduzca una fecha:");
		while(true){
			try {
				cal.set(t.leerEntero("A�o >"), t.leerEntero("Mes >")-1, t.leerEntero("D�a >")); // Nota: -1 para poder introducir los meses desde el 1 al 12 y no desde el 0 al 11
				cal.getTime();
				return cal;
			} catch (Exception e) {
				Exception FechaNoValida = new Exception("Fecha no v�lida.");
				try {
					throw FechaNoValida;
				} catch (Exception e1) {
					System.err.println(e1.getMessage());
				}
			}
		}
	}

	
}
