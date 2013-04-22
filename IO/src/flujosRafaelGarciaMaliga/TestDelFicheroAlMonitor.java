package flujosRafaelGarciaMaliga;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * Clase TestDelFicheroAlMonitor.java
 *
 * Envía un programa que cumpla los siguientes requisitos:
 * Mostrará por pantalla todo lo que aparezca en el fichero "entrada.txt"
 * "entrada.txt" contendrá un listado de nombres y apellidos, uno por línea
 * Las excepciones se capturarán en el main, informando al usuario
 *
 * @author Rafael García Maliga
 * @since 09/04/2013
 * @version 1.0
 *
 */
public class TestDelFicheroAlMonitor {

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			leerMostrarFichero("entrada.txt");
		} catch (FileNotFoundException e) {
			System.err.println("El fichero no existe. Se creará uno si es posible.");
			try {
				crearEntrada();
			} catch (IOException e1) {
				System.err.println("No se pudo crear el fichero.");
			}
		}catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	/**
	 * 
	 * leerMostrarFichero
	 * @param file
	 * @throws IOException
	 */
	private static void leerMostrarFichero(String file) throws IOException {
		BufferedReader inputStream = null;
		String line;
        try {
            inputStream = new BufferedReader(new FileReader(file));
            while ((line = inputStream.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }		
	}
	/**
	 * 
	 * crearEntrada
	 * @throws IOException
	 */
	private static void crearEntrada() throws IOException{
		PrintWriter outputStream = null;
		String text = "ARCOS ALVARES ABDIEL SALVADOR"
				+"\r\nARGUELLO SANCHEZ ALEJANDRO"
				+"\r\nBANDA ESTRADA DAVID ABRAHAM"
				+"\r\nBUENO CONTRERAS JOSE JORGE"
				+"\r\nCAMACHO CORDERO JUAN RAMON"
				+"\r\nGONZALEZ TOKMAN ALEJANDRO LUIS"
				+"\r\nJARAMILLO GIL ARTURO"
				+"\r\nLOREDO MENDEZ MARIA DEL ROSARIO"
				+"\r\nMEDINA HERRERA FRANCISCO JAVIER"
				+"\r\nPAREDON MATA CARLOS GERARDO"
				+"\r\nRODRIGUEZ GALLARDO CLAUDIA ARACELI"
				+"\r\nSOTO SUAREZ MARGARITA";
		try {
			outputStream = new PrintWriter(new FileWriter("entrada.txt"));
			outputStream.write(text);
			System.out.println("Fichero creado.");
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}
