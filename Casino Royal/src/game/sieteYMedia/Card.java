/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.sieteYMedia;

/**
 * 
 * Clase Card (carta)
 *
 * @author Rafael García Maliga
 * @version 1.0
 *
 */
public class Card {

	private String name;
	private int suit;
	private int number;
	private float value;

	/**
	 * Constructor de Card
	 * @param name
	 * @param suit
	 * @param number
	 * @param value
	 */
	public Card(String name, int suit, int number, float value) {
		this.name = name;
		this.suit = suit;
		this.number = number;
		this.value = value;
	}

	/**
	 * Obtiene el nombre del palo
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define el nombre del Palo
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene el número del palo
	 * (referente a la matriz de la imagen)
	 * @return int
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * Define el numero de la carta de
	 * su respectiva posición en la matriz
	 * de la imagen
	 * @param suit
	 */
	public void setSuit(int suit) {
		this.suit = suit;
	}

	/**
	 * Obtiene el número de la carta
	 * @return int
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Define el número de la carta
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Obtiene el valor de la carta
	 * @return float
	 */
	public float getValue() {
		return value;
	}

	/**
	 * Defiene el valor de la carta
	 * @param value
	 */
	public void setValue(float value) {
		this.value = value;
	}



}
