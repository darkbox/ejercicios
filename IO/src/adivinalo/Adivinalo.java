package adivinalo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Clase Adivinalo.java
 * 
 * Implemanta el juego "Adivinalo". 
 * Consiste en:
 * 
 * Adivinar un número comprendido entre 0 y 100, que será generado de forma automática.
 * El usuario introducirá por teclado el número y el programa informará si es mayor o menor. 
 * El número de intentos será infinito. 
 * Al adivinarlo, se le dará la opción de comenzar de nuevo el juego. 
 * La aplicación almacenará en "record.txt" el menor número de intentos para informar al 
 * usuario en caso de batirlo.
 * 
 * @author Rafael García Maliga
 * @since 09/04/2013
 * @version 1.0
 *
 */
public class Adivinalo {

	private static Scanner scan = new Scanner(System.in);
	private static final Pattern SI = Pattern.compile("^si");
	private static final Pattern NO = Pattern.compile("^no");
	private static final File RECORD_FILE = new File("record.txt");
		
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		do{
			jugar(cargarRecord());
		}while(seguir());
		System.out.println("GAME OVER");
	}


	/**
	 * 
	 * cargarRecord
	 * @return int 
	 */
	private static int cargarRecord() {
		BufferedReader inputStream = null;
		int valor = 0;
		try {
			inputStream = new BufferedReader(new FileReader(RECORD_FILE));
			valor = Integer.parseInt(inputStream.readLine());
		} catch (FileNotFoundException e) {
			grabarRecord(0);
		} catch (NumberFormatException e) {
			System.err.println("No se pudo encontrar un record.");
		} catch (IOException e) {
			System.err.println("ERROR");
		}finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.err.println("No se pudo cerrar el fichero.");
				}
			}
		}
		return valor;
	}

	/**
	 * 
	 * jugar
	 * lógica del juego
	 * @param record
	 */
	private static void jugar(int record) {
		int numero = (int) (Math.random()*100);	
		int nJugador;
		int intentos = 0;
		while(true){
			System.out.printf("Intrucide un número del 0 al 100 [intentos-%d] a batir [%d] %d>", intentos, record,numero);
			nJugador = pedirNumero();
			intentos++;
			if(nJugador == numero){
				System.out.println("Has ganado.");
				// Comprobamos el record
				if(intentos <= record || record == 0){
					System.out.printf("NUEVO RECORD [%d], anterior [%d]", intentos, record);
					grabarRecord(intentos);
				}
				break;
			}else if(nJugador < numero){
				System.out.println("Es mayor.");
			}else{
				System.out.println("Es menor.");
			}
		}
	}

	/**
	 * grabarRecord
	 * graba el record en el fichero
	 * @param intentos
	 */
	private static void grabarRecord(int intentos) {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileWriter(RECORD_FILE));
			outputStream.write(intentos+"");
			System.out.println("Fichero Guardado.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}		
	}


	/**
	 * seguir
	 * Pregunta si se desea continuar
	 * @return false si no desea continuar
	 */
	private static boolean seguir() {
		Matcher matcher, matcher2;
		System.out.println("¿Desea jugar una nueva partida?(si/no)");
		while(true){
			String palabra = scan.next();
			matcher = SI.matcher(palabra);
			matcher2 = NO.matcher(palabra);
			if(matcher.find()){
				// Si
				return true;
			}else if (matcher2.find()){
				// No
				return false;
			}
		}
	}

	/**
	 * 
	 * pedirNumero
	 * @return int
	 */
	private static int pedirNumero(){
		while(true){
			try {
				return scan.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Valor incorrecto, vuelva a intenarlo >");
				scan.nextLine();
			}
		}
	}
}
