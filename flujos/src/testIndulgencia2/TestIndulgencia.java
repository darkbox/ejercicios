package testIndulgencia2;

import java.util.Calendar;

/**
 * 
 * Clase TestIndulgencia
 * @author Rafael García Maliga
 * @since 16/04/2013
 * @version 1.0
 *
 */
public class TestIndulgencia {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar ahora = Calendar.getInstance();
		System.out.println(ahora.getClass());
		
		// ahora.setLenient(false);
		ahora.set(2004, 32, 32);
		System.out.println(ahora.getTime());
		
		ahora.set(2004, 1, 7, 7, 0, 0);
		System.out.println(ahora.getTime());
	}

}
