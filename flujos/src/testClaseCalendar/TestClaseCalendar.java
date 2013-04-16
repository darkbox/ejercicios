package testClaseCalendar;
/**
 * 
 * Clase TestClaseCalendar
 * @author Rafael Garc�a Maliga
 * @since 16/04/2013
 * @version 1.0
 * 
 * Crea el programa TestClaseCalendar con estos requisitos:
 * Ha de mostrar un men� con los siguientes elementos: Captura el instante, 
 * A�o, Mes, D�a, D�a de la semana, Hora, Minutos, Segundos, Salir.
 * La primera opci�n crea una instancia de Calendar con el instante actual
 *  	Calendar.getInstance(); 
 *  
 * El resto de opciones (excepto Salir) extraen y muestran la informaci�n 
 * del objeto de tipo Calendar de dos formas distintas:
 * 		Mediante println():
 *   		System.out.println("A�o con get: " + ahora.get(Calendar.YEAR)); 
 *   	Mediante format() utilizando todos los formatos posibles:
 *    		System.out.format("A�o con format: %tC, %<tY, %<ty %n", ahora); 
 *    
 * En el caso en que se invoque a cualquiera de las opciones sin haber creado
 * el objeto Calendar capturar� la excepci�n NullPointerException y se 
 * mostrar� el mensaje "Captura el instante antes de analizarlo."
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
