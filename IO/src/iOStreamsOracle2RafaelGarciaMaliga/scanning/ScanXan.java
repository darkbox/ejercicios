package iOStreamsOracle2RafaelGarciaMaliga.scanning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * 
 * Clase ScanXan.java
 *
 * @author Rafael García Maliga
 * @since 08/04/2013
 * @version 1.0
 *
 */
public class ScanXan {
	/**
	 * 
	 * main
	 * @param args
	 * @throws IOException
	 */
	 public static void main(String[] args) throws IOException {

	        Scanner s = null;

	        try {
	            s = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));

	            while (s.hasNext()) {
	                System.out.println(s.next());
	            }
	        } finally {
	            if (s != null) {
	                s.close();
	            }
	        }
	    }
}
