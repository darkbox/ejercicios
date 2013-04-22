package iOStreamsOracle2RafaelGarciaMaliga.scanning;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;
/**
 * 
 * Clase ScanSum.java
 *
 * @author Rafael García Maliga
 * @since 08/04/2013
 * @version 1.0
 *
 */
public class ScanSum {
	/**
	 * 
	 * main
	 * @param args
	 * @throws IOException
	 */
    public static void main(String[] args) throws IOException {

        Scanner s = null;
        double sum = 0;

        try {
            s = new Scanner(new BufferedReader(new FileReader("usnumbers.txt")));
            s.useLocale(Locale.US);

            while (s.hasNext()) {
                if (s.hasNextDouble()) {
                    sum += s.nextDouble();
                } else {
                    s.next();
                }   
            }
        } finally {
            s.close();
        }

        System.out.println(sum);
    }
}