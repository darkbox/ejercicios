package population;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import population.people.*;
/**
 * 
 * Clase TestPerson.java
 *
 * @author Rafael García Maliga
 * @since 15/04/2013
 * @version 1.0
 *
 */
public class TestPerson {

	private static final File FILE = new File("person");
	private static ObjectInputStream in;
	private static ObjectOutputStream out;
	
	/**
	 * main
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Calendar birth = Calendar.getInstance();
		birth.set(1990, 1, 20);
		Person dude = new Person("Rafael","Garcia Maliga", birth);
		
		if(FILE.exists()){
			try {
				in = new ObjectInputStream(new FileInputStream(FILE));
				readObjectFile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				in.close();
			}
		}else{
			try {
				out = new ObjectOutputStream(new FileOutputStream(FILE));
				out.writeObject(dude);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				out.close();
			}
		}
	}

	private static void readObjectFile() {
		try {
			System.out.println(in.readObject().toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
