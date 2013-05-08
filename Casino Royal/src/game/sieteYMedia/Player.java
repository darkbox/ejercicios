/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.sieteYMedia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ListIterator;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import tools.Sound;

/**
 * 
 * Clase Player (jugador)
 *
 * @author Rafael García Maliga
 * @version 1.0
 *
 */
public class Player {

	private String name;
	private int wins;
	private int lost;
	private float score;
	private boolean out;
	private boolean won;
	private Stack<Card> hand;
	// Imagen
	private BufferedImage shadow;

	/**
	 * Constructor de Player
	 * @param name
	 */
	public Player(String name){
		this.name = name;
		this.wins = 0;
		this.lost = 0;
		this.score = 0;
		this.out = false;
		this.won = false;
		this.hand = new Stack<Card>();
		// Carga recursos
		try{
			shadow = ImageIO.read(getClass().getResource("/shadow.png"));
		}catch(Exception e){
			System.err.print("An error has ocurred loading textures: " + e);
		}
	}


	/**
	 * Muestra el menú del jugador
	 * @return int valor seleccionado
	 */
	public int options(){
		int option = JOptionPane.showOptionDialog(null, 
		        "¿Qué desea hacer?", 
		        this.name, 
		        JOptionPane.OK_CANCEL_OPTION, 
		        JOptionPane.INFORMATION_MESSAGE, 
		        null, 
		        new String[]{"Recibir carta", "Plantarse"}, // this is the array
		        "default");
		// Devolvemos el valor seleccionado por el jugador
		return option;
	}

	/**
	 * Reinicia al jugador en la partida
	 */
	void restart(){
		setHand(new Stack<Card>());
		setOut(false); 
		setWon(false);
	}

	/**
	 * Actualiza los datos y estado del jugador
	 */
	public void update(){
		// Cuenta la puntuación actual
		if(hand != null){
			score = 0;
			ListIterator<Card> it = hand.listIterator();
			while(it.hasNext()){
				score += it.next().getValue();
			}
		}else{
			score = 0;
		}
		// Comprueba si hemos ganado o perdido
		if(score == 7.5f){
			if(!out){
				Sound.playSound("/sounds/win" + ((int) (1+Math.random()*3)) + ".wav", false);
				this.wins++; // Ha ganado
			}
			this.setOut(true);
			this.setWon(true);
		}
		if(score > 7.5f){
			if(!out){

				Sound.playSound("/sounds/fail" + ((int) (1+Math.random()*5)) + ".wav", false);
				this.lost++; // Ha perdido
			}
			this.setOut(true);
		}
	}

	/**
	 * Método encargado de dibujar en pantalla al jugador
	 * 
	 * @param g Graphics
	 * @param x
	 * @param y
	 * @param deck BufferedImage[]
	 * @return Graphics
	 */
	public Graphics draw(Graphics g, int x, int y, BufferedImage[] deck){
		// Transformamos los graficos a Java2D
		Graphics2D g2 = (Graphics2D) g;    
		g2.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int span = 0;
		// Mostramos el nombre
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial", Font.PLAIN, 18));
		g2.drawString(name + " Mano: " + score + " Wins: " + wins + " lost: " + lost, x, y);

		// Dibujamos las cartas
		ListIterator<Card> it = hand.listIterator();
		while(it.hasNext()){
			g2.drawImage(shadow, (x + span), (y + 18), null);
			g2.drawImage(deck[it.next().getSuit()], (x + span), (y + 18), null);   
			span += 60;
		}
		// Comprobamos los estados y los dibujamos
		if(isOut()){

			if(score < 7.5f){
				g2.setColor(new Color(0,0,0,80));
				g2.fillRect(x+290, y+45, 120, 30);
				g2.setColor(Color.orange);
				g2.drawString("¡Estás fuera!", x+300, y+65);
			}else if(isWon()){
				g2.setColor(new Color(0,0,0,80));
				g2.fillRect(x+290, y+45, 180, 30);
				g2.setColor(Color.green);
				g2.drawString("¡WOW has ganado!", x+300, y+65);
			}else{
				g2.setColor(new Color(0,0,0,80));
				g2.fillRect(x+290, y+45, 125, 30);
				g2.setColor(Color.red);
				g2.drawString("¡Has perdido!", x+300, y+65);
			}
		}

		return (Graphics) g2;
	}    

	/**
	 * Devuelve si está fuera de la partida
	 * @return True si está fuera
	 */
	public boolean isOut() {
		return out;
	}

	/**
	 * Define si está fuera de la partida
	 * @param out boolean
	 */
	public void setOut(boolean out) {
		this.out = out;
	}

	/**
	 * Obtiene el nombre
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define el nombre
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtiene las partidas ganadas
	 * @return int
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * Define si ha ganado
	 * @param boolean
	 */
	public void setWon(boolean won) {
		this.won = won;
	}
	
	/**
	 * Devuelve si ha ganado o no
	 * @return true si ha ganado
	 */
	public boolean isWon() {
		return this.won;
	}

	/**
	 * Define las veces ganadas
	 * @param wins int
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * Obtiene la puntuación
	 * @return float
	 */
	public float getScore() {
		return score;
	}

	/**
	 * Obtiene la mano de cartas
	 * del jugador
	 * @return Stack<Card>
	 */
	public Stack<Card> getHand() {
		return hand;
	}

	/**
	 * Define la mano del jugador
	 * @param hand
	 */
	public void setHand(Stack<Card> hand) {
		this.hand = hand;
	}

	/**
	 * Agrega una carta a la mano del
	 * jugador
	 * @param card
	 */
	public void addCard(Card card){
		hand.add(card);        
	}

}
