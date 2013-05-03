package tools;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Scanner;
/**
 * 
 * Clase Menu que permite crear un menú
 * con un conjunto de opciones
 *
 * @author Rafael García Maliga
 * @version 1.0
 *
 */
public class Menu {
	private ArrayList<String> menu = null;
	private ListIterator<String> iterator = null;
	private String title = null;
	private int counter = 0;
	private boolean exit = true;
	private Scanner scan = null;
	
	/**
	 * @brief Constructor menú con título
	 * @param title
	 * @param exit true/false
	 */
	public Menu(String title, boolean exit){
		super();
		this.title = title;
		this.exit = exit;
		this.menu = new ArrayList<String>();
		scan = new Scanner(System.in);
	}
	
	/**
	 * @brief Constructor menú sin título
	 * @param exit true/false
	 */
	public Menu(boolean exit){
		super();
		this.title = null;
		this.exit = exit;
		this.menu = new ArrayList<String>();
		scan = new Scanner(System.in);
	}
	
	/**
	 * @brief Agrega nueva opción al menú
	 * @param option String
	 */
	public void add(String option){
		menu.add(option);
	}
	
	/**
	 * @brief Seleciona una opción valida del menú
	 * @return int opcion seleccionada
	 */
	public int select(){
		int num = 0;
		int totalSize = 0;
		if(exit){
			totalSize = (menu.size() + 1);
		}else{
			totalSize = menu.size();
		}
		do{
			try
		    {
				System.out.print("\n> ");
		        num = scan.nextInt();
		    }
		    catch (InputMismatchException exception)
		    {
		    	System.err.print("Invalid input. Try again... ");
		    	scan.next();
		    }
		}while(num <= 0 || num > totalSize );
		if(exit && num == totalSize){
			System.out.println("Closed...");
			this.menu = null;
			System.exit(0);
		}
		return num;
	}
	
	/**
	 * @brief Imprime el menú en pantalla
	 */
	public void print(){
		if(title != null){
			System.out.println(printTitle());
		}
		
		iterator = menu.listIterator();
		counter = 0;
		while(iterator.hasNext()){
			System.out.println("> " + ++counter + ".- " + iterator.next());
		}
		if(exit){
			System.out.println("> " + ++counter + ".- Exit");
		}
	}
	
	/**
	 * @brief Formatea título del menú
	 * @return String
	 */
	private String printTitle(){
		String s = "\n" + title + "\n";
		for(int i = 0; i < title.length(); i++){
			s += "_";
		}
		s += "\n";
		return s;
	}
}
