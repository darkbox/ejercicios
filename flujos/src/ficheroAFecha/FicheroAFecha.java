package ficheroAFecha;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

/**
 * Clase FicheroAFecha
 * @author Rafael García Maliga
 * @since 16/04/2013
 * @version 1.0
 * 
 * Implemanta el programa "FicheroAFecha.java" que:
 * Lea el objeto fecha del fichero "fecha".
 * Muestre:
 * Año
 * Mes en letras
 * Mes en número
 * Día de la semana en letras
 * Día de la semana en número
 * Hora
 * Minutos
 * Segundos
 * 
 */
public class FicheroAFecha {

	private static final File FILE = new File("fecha");
	private static Date date = null;
	
	/**
	 * @param args
	 * @throws  
	 */
	public static void main(String[] args) {
		ObjectInputStream oi = null;
		try {
			oi = new ObjectInputStream(new FileInputStream(FILE));
			date = (Date) oi.readObject();
			System.out.format("Año: %tY %n" +
					"Mes en letras: %<tB %n" +
					"Mes en numero: %<tm %n" +
					"Dia en letras: %<tA %n" +
					"Dia en numero: %<td %n" +
					"Hora: %<tH %n" +
					"Minutos: %<tM %n" +
					"Segundos: %<tS", date);
		}catch(ClassNotFoundException e){	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(oi != null){
				try {
					oi.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			date = null;
		}
	}

}
