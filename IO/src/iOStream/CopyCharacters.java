package iOStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 
 * Clase CopyCharacters.java
 *
 * @author Rafael García Maliga
 * @since 08/04/2013
 * @version 1.0
 *
 */
public class CopyCharacters {

	/**
	 * main
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("characteroutput.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

}
