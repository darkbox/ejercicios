package testIndulgencia2;

import java.util.Calendar;

/**
 * 
 * Clase TestIndulgencia2
 * @author Rafael García Maliga
 * @since 16/04/2013
 * @version 1.0
 *
 */
public class TestIndulgencia2 {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar ahora = Calendar.getInstance();
		System.out.println(ahora.getClass());

		ahora = addDate(2004, 32, 32);
		System.out.println(ahora.getTime());


		ahora = addDate(2004, 1, 7, 7, 0, 0);
		System.out.println(ahora.getTime());
	}
	/**
	 * Agrega una fecha válida, sino devuelve la fecha actual.
	 * @param year
	 * @param month
	 * @param date
	 * @param h
	 * @param m
	 * @param s
	 * @return Calendar
	 */
	private static Calendar addDate(int year, int month, int date, int h, int m, int s) {
		Calendar localDate = Calendar.getInstance();
		localDate.setLenient(false);
		try {
			localDate.set(year, month, date,h, m, s);
			localDate.getTime();
			return localDate;
		} catch (Exception e) {
			System.err.println("Fecha no válida");
			return Calendar.getInstance();
		}	
	}

	/**
	 * Agrega una fecha válida, sino devuelve la fecha actual.
	 * @param date
	 * @return Calendar
	 */
	private static Calendar addDate(int year,int month,int date){
		Calendar localDate = Calendar.getInstance();
		localDate.setLenient(false);
		try {
			localDate.set(year, month, date);
			localDate.getTime();
			return localDate;
		} catch (Exception e) {
			System.err.println("Fecha no válida");
			return Calendar.getInstance();
		}	
		
	}
}
