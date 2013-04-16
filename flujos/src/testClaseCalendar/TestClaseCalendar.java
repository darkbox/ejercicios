package testClaseCalendar;
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
