package flujosRafaelGarciaMaliga;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Clase TestDelTecladoAlFichero.java
 *
 * Envía un programa que cumpla los siguientes requisitos:
 * Almacenará todo lo que el usuario introduzca por teclado en el fichero "salida.txt"
 * No utilizarás la clase Teclado
 * Enviará una excepción al main en el caso en el que el fichero ya exista, 
 * informando al usuario.
 * El programa acabará cuando el usuario introduzca la palabra SALIR 
 * (aislado en una línea y en mayúsculas). Dicha línea no se introducirá en
 * el flujo de salida.
 *
 * @author Rafael García Maliga
 * @since 09/04/2013
 * @version 1.0
 *
 */
public class TestDelTecladoAlFichero {

	private static Scanner scan = new Scanner(System.in);
	private static String cache = "";
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		// Comprobamos si el fichero existe o no
		try {
			leerFichero("salida.txt");
		} catch(FileAlreadyExistsException e){
			System.err.println(e.getMessage());
		} catch(FileNotFoundException e){
			System.err.println("El fichero no existe.");
			crearFichero("salida.txt"); // Crea el fichero vacio
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		// Bucle principal del programa
		Pattern pattern = Pattern.compile("^SALIDA$");
		
		while(true){
			String linea = scan.nextLine();
			Matcher matcher = pattern.matcher(linea);
			if(matcher.find()){
				try {
					escribirFichero("salida.txt", cache);
				} catch (IOException e) {
					System.out.println("No se pudo guardar el fichero.");
				}
				System.out.println("Adios!!!");
				linea = null;
				break;
			}else{
				cache += linea+"\r\n";
			}
		}
	}

	/**
	 * 
	 * crearFichero
	 * @param fileName
	 */
	private static void crearFichero(String fileName) {
		try {
			new FileWriter(fileName);
			System.out.println("Fichero creado.");
		} catch (IOException e) {
			System.err.println("No se pudo crear el fichero.");
			e.printStackTrace();
		} 	
	}

	/**
	 * 
	 * escribirLinea
	 * @param FileName
	 * @param text
	 * @throws IOException
	 */
	private static void escribirFichero(String FileName, String text) throws IOException {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileWriter(FileName));
			outputStream.write(text);
			System.out.println("Fichero Guardado.");
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	/**
	 * 
	 * leerFichero
	 * @param file
	 * @throws IOException
	 */
	private static void leerFichero(String file) throws IOException {
		BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader(file));
            throw new FileAlreadyExistsException("El fichero ya existe");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }	
	}

}
