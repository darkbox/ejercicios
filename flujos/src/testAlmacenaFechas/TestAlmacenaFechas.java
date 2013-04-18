package testAlmacenaFechas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;

/**
 * 
 * Clase TestAlmacenaFechas
 * @author Rafael García Maliga
 * @since 16/04/2013
 * @version 1.0
 * 
 * Diseña el programa TestAlamacenaFechas que cumpla los siguientes requisitos:
 * 1 Leerá del fichero "fechas" e irá mostrando el tiempo transcurrido hasta la fecha actual.
 * 2 Solicitará al usuario nuevas fechas y mostrará de nuevo el tiempo transcurrido para cada una de ellas.
 * 3 Cuando el usuario finalice, el programa almacenará todas las fechas de nuevo en fechas.
 */
public class TestAlmacenaFechas {

	private static final File FILE = new File("fechas");
	private static ArrayList<Calendar> dates = new ArrayList<Calendar>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Menu menu = new Menu("TestAlmacenaFechas", false);
		menu.add("Leer fichero 'fechas'");
		menu.add("Agregar fechas");
		menu.add("Guardar y salir");
		
		while(true){
			menu.print();
			switch (menu.select()) {
			case 1:
				readFile();
				showDates();
				break;
			case 2:
				addDate();
				showDates();
				break;
			case 3:
				writeFile();
				System.out.println("Closed...");
				System.exit(0);
				break;
			}
		}
	}

	/**
	 * Muestra las fechas
	 */
	private static void showDates() {
		ListIterator<Calendar> it = dates.listIterator();
		while(it.hasNext()){
			Calendar oldDate = (Calendar) it.next();
			System.out.println("Desde " + oldDate.getTime() + " hasta ahora, han pasado " + elapsedTime(oldDate));
		}
	}

	/**
	 * Compara la fecha actual con una dada
	 * @return
	 */
	private static String elapsedTime(Calendar cal1) {
		long h,m,s;  
		Calendar cal2 = Calendar.getInstance();

		// conseguir la representacion de la fecha en milisegundos
		long milis1 = cal1.getTimeInMillis();
		long milis2 = cal2.getTimeInMillis();

		// calcular la diferencia en milisengundos
		long diff = milis2 - milis1;

		// calcular la diferencia en segundos
		long diffSeconds = diff / 1000;
	
		h=diffSeconds/3600;  
        m=(diffSeconds-(3600*h))/60;  
        s=diffSeconds-((h*3600)+(m*60));  

		return  h + " Horas, "
				+ m + " Minutos, "
				+ s + " Segundos";
	}

	/**
	 * Escribe el fichero
	 */
	private static void writeFile() {
		ObjectOutputStream oo = null;
		try {
			oo = new ObjectOutputStream(new FileOutputStream(FILE));
			oo.writeObject(dates);
			System.out.println("Cambios efectuados");
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

	/**
	 * Agrega la fecha actual a la lista
	 */
	private static void addDate() {
		Calendar cal = Calendar.getInstance();
		dates.add(cal);		
	}

	/**
	 * Lee el fichero 
	 */
	@SuppressWarnings("unchecked")
	private static void readFile() {
		ObjectInputStream oi = null;
		try {
			oi = new ObjectInputStream(new FileInputStream(FILE));
			dates =  (ArrayList<Calendar>) oi.readObject();
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
		}		
	}

}
