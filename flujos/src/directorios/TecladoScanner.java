package directorios;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * Crea la clase TecladoScanner para la lectura 
 * de datos desde el teclado
 *
 * @author Rafael García Maliga
 * @version 1.0
 *
 */
public class TecladoScanner {
	
	private static TecladoScanner INSTANCE = new TecladoScanner(System.in);
	private Scanner s;

	/**
	 * Constructor
	 * @param in entrada InputStream
	 */
	private TecladoScanner(InputStream in) {
		super();
		this.s = new Scanner(in);
	}
	
	/**
	 * Devuleve una instancia de TecladoScanner
	 * para cumplir con el patrón Singleton
	 * @return devuelve la instancia de TecladoScanner
	 */
	public static TecladoScanner getInstance(){
		return INSTANCE;
	}
	
	/**
	 * Sobrescribe el método clone de Object para que
	 * solo pueda existir una única instancia
	 */
	public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException(); 
	}
	
	/**
	 * Lee un entero
	 * @return int devuelve entero
	 */
	int leerEntero(){
		while(true){
			try {
				return s.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Se requiere un entero.");
				s.nextLine(); // Obligatorio para que haga una pausa al saltar de linea
			} 
		}
	}
	
	/**
	 * Lee un entero y muestra un mensaje
	 * @param msg cadena de texto a mostrar
	 * @return int devuelve entero
	 */
	int leerEntero(String msg){
		System.out.print(msg);
		return leerEntero();
	}
	
	/**
	 * Lee un decimal
	 * @return double devuelve un decimal de tipo double
	 */
	double leerDecimal(){
		while(true){
			try {
				return s.nextDouble();
			} catch (InputMismatchException e) {
				System.err.println("Se requiere un decimal.");
				s.nextLine(); // Obligatorio para que haga una pausa al saltar de linea
			} 
		}
	}
	
	/**
	 * Lee un decimal y muestra un mensaje
	 * @param msg  cadena de texto a mostrar
	 * @return double devuelve un decimal de tipo double
	 */
	double leerDecimal(String msg){
		System.out.println(msg);
		return leerDecimal();
	}
	
	/**
	 * Lee un carácter (todos excepto numeros)
	 * @return char carácter
	 */
	char leerCaracter(){
		while(true){
			try {
				return s.next("[^\\d]+.*").charAt(0);
			} catch (InputMismatchException e) {
				System.err.println("Se requiere un carï¿½cter.");
				s.nextLine(); // Obligatorio para que haga una pausa al saltar de linea
			} 
		}
	}
	
	/**
	 * Lee un caracter y muestra un mensaje
	 * @param msg cadena de texto a mostrar
	 * @return char carácter
	 */
	char leerCaracter(String msg){
		System.out.println(msg);
		return leerCaracter();
	}
	
	/**
	 * Lee una cadena de texto
	 * @return String cadena de texto
	 */
	String leerCadena(){
		while(true){
			try {
				return s.nextLine();
			} catch (InputMismatchException e) {
				System.err.println("Se requiere una cadena.");
				s.nextLine(); // Obligatorio para que haga una pausa al saltar de linea
			} 
		}
	}
	
	/**
	 * Lee una cadena de texto y muestra un mensaje
	 * @param msg cadena de texto a mostrar
	 * @return String cadena de texto
	 */
	String leerCadena(String msg){
		System.out.println(msg);
		return leerCadena();
	}
}
