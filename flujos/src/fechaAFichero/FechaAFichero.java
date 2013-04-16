package fechaAFichero;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

/**
 * Clase FechaAFichero
 * @author Rafael García Maliga
 * @since 16/04/2013
 * @version 1.0
 *
 * Implementa el programa "FechaAFichero.java" que:
 * Guarde la fecha actual en el fichero "fecha".
 * Utiliza la clase Calendar
 * 
 */
public class FechaAFichero {

	private static Calendar cal = Calendar.getInstance();
	private static final File FILE = new File("fecha");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectOutputStream oo = null;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(FILE));
			oo.writeObject(cal.getTime());
			System.out.println("Fichero creado");
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(oo != null){
				try {
					oo.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
