package testClaseCalendar;

import java.util.Calendar;

/**
 * 
 * Clase TestClaseCalendar
 * @author Rafael García Maliga
 * @since 16/04/2013
 * @version 1.0
 * 
 * Crea el programa TestClaseCalendar con estos requisitos:
 * Ha de mostrar un menú con los siguientes elementos: Captura el instante, 
 * Año, Mes, Día, Día de la semana, Hora, Minutos, Segundos, Salir.
 * La primera opción crea una instancia de Calendar con el instante actual
 *  	Calendar.getInstance(); 
 *  
 * El resto de opciones (excepto Salir) extraen y muestran la información 
 * del objeto de tipo Calendar de dos formas distintas:
 * 		Mediante println():
 *   		System.out.println("Año con get: " + ahora.get(Calendar.YEAR)); 
 *   	Mediante format() utilizando todos los formatos posibles:
 *    		System.out.format("Año con format: %tC, %<tY, %<ty %n", ahora); 
 *    
 * En el caso en que se invoque a cualquiera de las opciones sin haber creado
 * el objeto Calendar capturará la excepción NullPointerException y se 
 * mostrará el mensaje "Captura el instante antes de analizarlo."
 * 
 */
public class TestClaseCalendar {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar cal = null;
		Menu menu = new Menu("TestClaseCalendar", true);
		menu.add("Capturar instante");
		menu.add("Año");
		menu.add("Mes");
		menu.add("Día");
		menu.add("Día de la semana");
		menu.add("Hora");
		menu.add("Minutos");
		menu.add("Segundos");
		
		while(true){
			try {
				menu.print();
				switch (menu.select()) {
				case 1: // Captura instante
					cal = Calendar.getInstance();
					break;
				case 2: // Año
					System.out.println("Año con get: " + cal.get(Calendar.YEAR));
					System.out.format("Año con format: %tC, %<tY, %<ty %n", cal);
					break;
				case 3: // Mes
					System.out.println("Mes con get: " + cal.get(Calendar.MONTH));
					System.out.format("Mes con format: %tB, %<tb, %<tm %n", cal);
					break;
				case 4: // Día
					System.out.println("Dia con get: " + cal.get(Calendar.DAY_OF_MONTH));
					System.out.format("Dia con format: %td, %<te, %<tj %n", cal);
					break;
				case 5: // Día de la semana
					System.out.println("Dia de la semana con get: " + cal.get(Calendar.DAY_OF_WEEK));
					System.out.format("Dia de la semana con format: %tA, %<ta %n", cal);
					break;
				case 6: // Hora
					System.out.println("Hora con get: " + cal.get(Calendar.HOUR));
					System.out.format("Hora con format: %tH, %<tI%<tp, %<tk, %<tl%<tp %n", cal);
					break;
				case 7: // Minutos
					System.out.println("Minuto con get: " + cal.get(Calendar.MINUTE));
					System.out.format("Minuto con format: %tM %n", cal);
					break;
				case 8: // Segundos
					System.out.println("Segundo con get: " + cal.get(Calendar.SECOND));
					System.out.format("Segundo con format: %tS %n", cal);
					break;
				}
			} catch (NullPointerException e) {
				System.err.println("Captura el instante antes de analizarlo.");
			}
		}
	
	}

}
