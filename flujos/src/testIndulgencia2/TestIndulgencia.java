package testIndulgencia2;

import java.util.Calendar;

/**
 * 
 * Clase TestIndulgencia
 * @author Rafael García Maliga
 * @since 16/04/2013
 * @version 1.0
 *
 * Quita el comentario en la línea
 *  ahora.setLenient(false); 
 * y vuelve a ejecutarlo. 
 *  
 *  ¿Qué hace la línea 
 *   ahora.setLenient(false);
 *   => Evita fechas ficticias.
 *   
 *  ¿Le encuentras alguna utilidad?
 *   => Si.
 *  
 *  Modifica el código y diseña un método que puedas reutilizar
 *  para futuros programas con fechas. Entrégalo como TestIndulgencia2
 *
 *
 */
public class TestIndulgencia {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar ahora = Calendar.getInstance();
		System.out.println(ahora.getClass());
		
		//ahora.setLenient(false);
		ahora.set(2004, 32, 32);
		System.out.println(ahora.getTime());
		
		ahora.set(2004, 1, 7, 7, 0, 0);
		System.out.println(ahora.getTime());
	}

}
