package flujosRafaelGarciaMaligaAcumulaSumas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * Clase TestAcumulaSumas.java
 *
 * @author Rafael García Maliga
 * @since 09/04/2013
 * @version 1.0
 *
 */
public class TestAcumulaSumas {

	private static Scanner scan;

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		int suma = 0;
		/* Inicialmente se parte del entero almacenado en 
		 * un fichero txt. Si el fichero no existe, 
		 * se parte desde 0.
		 */
		suma = comprobarFichero();
		/*
		 * El programa continúa pidiendo al usuario números 
		 * enteros y los suma, hasta que el usuario introduce
		 *  en número 0.
		 */
		suma = sumar(suma);
		/*
		 * Una vez hecha la suma, el resultado se almacenará 
		 * en el mismo fichero txt
		 */
		try {
			escribirFichero(suma+"");
			System.out.println("Fichero guardado con éxito");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * sumar
	 * @param suma
	 * @return
	 */
	private static int sumar(int suma) {
		scan = new Scanner(System.in);
		int temp = 0;
		System.out.println("Introduce 0 para finalizar la suma.");
		while(true){
			try {
				temp = scan.nextInt();
				if(temp == 0){
					return suma;
				}else{
					suma += temp;
					System.out.println("Valor actual: " + suma);
				}
			} catch (InputMismatchException e) {
				System.err.println("Valor incorrecto. Vuelve a intentarlo >");
				scan.nextLine();
			}
		}
	}

	/**
	 * 
	 * comprobarFichero
	 * @return int
	 */
	private static int comprobarFichero() {
		try {
			return Integer.parseInt(leerFichero());
		} catch (IOException e) {
			System.err.println("El fichero no existe.");
			try {
				escribirFichero("0");
			} catch (IOException e1) {
				System.err.println("No se pudo crear el fichero.");
			}
		} catch (NumberFormatException e) {
			System.err.println("El fichero no contiene un entero.");
		}
		return 0;
	}

	/**
	 * 
	 * crearFichero
	 * @param s
	 * @throws IOException
	 */
	private static void escribirFichero(String s) throws IOException{
		PrintWriter outputStream = null;

		try {
			outputStream = new PrintWriter(new FileWriter("sumas.txt"));
			outputStream.write(s);
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
	
	/**
	 * 
	 * leerFichero
	 * @return String
	 * @throws IOException
	 */
	private static String leerFichero() throws IOException{
		BufferedReader inputStream = null;
		String linea = null;
        try {
            inputStream = new BufferedReader(new FileReader("sumas.txt"));
            // Devuelve solo la primera linea
            linea = inputStream.readLine();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return linea;
	}
}
