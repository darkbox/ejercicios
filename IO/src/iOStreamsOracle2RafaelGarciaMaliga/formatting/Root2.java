package iOStreamsOracle2RafaelGarciaMaliga.formatting;
/**
 * 
 * Clase Root2.java
 *
 * @author Rafael García Maliga
 * @since 09/04/2013
 * @version 1.0
 *
 */
public class Root2 {
	/**
	 * 
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
        int i = 2;
        double r = Math.sqrt(i);
        
        System.out.format("The square root of %d is %f.%n", i, r);
    }
}
