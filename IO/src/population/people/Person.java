package population.people;

import java.io.Serializable;
import java.util.Calendar;
/**
 * 
 * Clase Person.java
 *
 * @author Rafael García Maliga
 * @since 15/04/2013
 * @version 1.0
 *
 */
public class Person implements Serializable{
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -7767561911531541464L;
	private String name;
	private String lastName;
	private Calendar birthday;
	/**
	 * Constructor 
	 * @param name
	 * @param lastName
	 * @param birthday
	 */
	public Person(String name, String lastName, Calendar birthday) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.birthday = birthday;
	}
	
	public int getAge(){
		Calendar date = Calendar.getInstance();
		//Se restan la fecha actual y la fecha de nacimiento
		int year = date.get(Calendar.YEAR)- birthday.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH)- birthday.get(Calendar.MONTH);
		int day = date.get(Calendar.DATE)- birthday.get(Calendar.DATE);
		//Se ajusta el año dependiendo el mes y el día
		if(month<0 || (month==0 && day<0)){
		       year--;
		}

		return  year;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the birthday
	 */
	public Calendar getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
	
	public String toString(){
		return "Name: " + this.name + " Lastname: " + this.lastName + " Age: " + this.getAge();
	}
}
