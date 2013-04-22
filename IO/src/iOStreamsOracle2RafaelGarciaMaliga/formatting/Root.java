package iOStreamsOracle2RafaelGarciaMaliga.formatting;
/**
 * 
 * Clase Root.java
 *
 * @author Rafael García Maliga
 * @since 09/04/2013
 * @version 1.0
 *
 */
public class Root {
	/**
	 * 
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
        int i = 2;
        double r = Math.sqrt(i);
        
        System.out.print("The square root of ");
        System.out.print(i);
        System.out.print(" is ");
        System.out.print(r);
        System.out.println(".");

        i = 5;
        r = Math.sqrt(i);
        System.out.println("The square root of " + i + " is " + r + ".");
    }
}
