package iOStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 
 * Clase CreaXanadu.java
 *
 * @author Rafael García Maliga
 * @since 08/04/2013
 * @version 1.0
 *
 */
public class CreaXanadu {

	/*
	 * NOTA: \r\n es para que haga un salto de linea en sistemas windows
	 * de lo contrario no realiza el salto del lina con \n
	 */
	private static String text = "In Xanadu did Kubla Khan"
			+"\r\nA stately pleasure-dome decree:"
			+"\r\nWhere Alph, the sacred river, ran"
			+"\r\nThrough caverns measureless to man"
			+"\r\nDown to a sunless sea.";

	/**
	 * 
	 * main
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		PrintWriter outputStream = null;

		try {
			outputStream = new PrintWriter(new FileWriter("xanadu.txt"));
			outputStream.write(text);
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

}
