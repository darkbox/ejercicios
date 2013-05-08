package game.sieteYMedia;

import game.core.Game;
import game.core.GameApp;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ListIterator;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import tools.Sound;


/**
 * 
 * Diseña e implementa un juego de cartas. Para ello, comienza implementando el juego de las siete y media.
 *  Es opcional implementar otro juego. Recuerda que:
 *  A las siete y media se juega con una baraja española
 *  Pueden jugar tantos jugadores como se quiera.
 *  Al principio del juego se indicará el alias de los jugadores implicados. 
 *  Se podrá averiguar el número de partidas ganadas y perdidas por cada jugador.
 *  Se podrá mostrar el ranking de las partidas jugadas 
 *  Al iniciar cada partida se preguntará qué jugador juega y cuál no.
 *
 * @author Rafael García Maliga
 * @version 2.0
 *
 */
public class SieteYMedia extends Game{

	// Controla el estado del juego
	private static int gameState = 0;
	private static boolean endOfMach = false;
	// Variables de imagen
	private BufferedImage feltFabric;
	private BufferedImage[] deck;
	// Baraja de cartas
	private static Stack<Card> deckOfCards;
	// Grupo de jugadores
	private static Stack<Player> players;
	private static int playerOrder=0; // Para no utilizar un bucle y no interrumpir el renderizado.
	private static int playersLeft=0;


	/**
	 * Main de SieteYMedia
	 * @param args 
	 */
	public static void main(String[] args){
		// Instanciamos el motor del juego
		GameApp.start(new SieteYMedia());
	}

	/**
	 * Constructor del juego Siete y Media
	 */
	public SieteYMedia(){
		title = "Siete y Media v1.0";
		width = 1024;
		height = 748;
	}

	// Métodos heredados
	@Override
	public void init() {
		// Cargo los recursos al inicio del juego
		// Carga la imagenes
		try{
			feltFabric = ImageIO.read(getClass().getResource("/feltFabric.png"));
			deck = sliptImage(ImageIO.read(getClass().getResource("/deck.png")), 4, 10); // Divido el sprite y lo guardo
		}catch(Exception e){
			System.err.print("An error has ocurred loading textures: " + e);
		}
		// Creamos la baraja
		createDeck();
		System.out.print(printCards()); // Para depuración
		// Crea la pila de jugadores
		players = new Stack<Player>();
		// Musica
		//Sound.playSound("sounds/looping_radio_mix.wav", true);
	}

	@Override
	public void update() {
		// Recorremos todos los jugadores y actualizamos en pantalla
		ListIterator<Player> it = players.listIterator();
		while(it.hasNext()){
			it.next().update();
		}
		// Logica del juego, etc
		controlMenu();
	}

	@Override
	public void draw(Graphics g) {
		// Dibujo el fondo
		g.drawImage(feltFabric, 0, 0, null);
		// Dibujamos a los jugadores
		drawPlayers(g);
		if(deckOfCards != null){
			// LOG
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.PLAIN, 12));
			g.drawString("Cartas restantes: " + deckOfCards.size() + " Jugadores inactivos: " + playersLeft, 10, 25);
		}
	}


	// Métodos de la clase SieteYMedia
	/**
	 * Controla la navegación entre menús
	 * y opciones
	 */
	private static void controlMenu(){
		switch(gameState){
		case 0: // Seleccionar numero de jugadores
			SpinnerNumberModel sModel = new SpinnerNumberModel(1, 1, 8, 1);
			JSpinner spinner = new JSpinner(sModel);
			int option = JOptionPane.showOptionDialog(null, spinner, "Seleccione el número de jugadores", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Aceptar", "Salir del juego"}, "default");
			if (option == JOptionPane.INFORMATION_MESSAGE)
			{
				// usuario pulsa cancelar
				System.exit(0);
			} else if (option == JOptionPane.OK_OPTION)
			{
				// usuario introduce un numero
				createPlayers((Integer)spinner.getValue());
				gameState = 3; // Cambia el estado del juego
			}
		break;
		case 3: // Partida
			playing(); // Método que controla la logica y el desarrollo de la partida			
			break;
		default:
			System.err.println("Estado del juego invalido.");
			System.exit(0);
		}
	}

	/**
	 * Crea los jugadores y los almacena en un arraylist
	 * @param numPlayers
	 */
	private static void createPlayers(int numPlayers){
		// crea la lista de jugadores
		for(int i = 1; i <= numPlayers; i++){
			// Muestra una ventana de dialogo para introducir el nombre del jugador
			JTextField textField = new JTextField();
			textField.setText("Jugador"+i);
			JOptionPane.showOptionDialog(null, textField, "Escriba el nombre para el jugador #"+i, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Aceptar"}, null);
			players.add(new Player(textField.getText()));
		}
	}    

	/**
	 * Controla la partida, la lógica y el desarrollo de la misma
	 */
	private static void playing(){
		// Los jugadores se recorren uno a uno gracias a la variable
		// playerOrder y al bucle del juego proporcionado por el 
		// método update();
		if(playerOrder >= 0 && playerOrder < players.size()){
			Player tempPlayer = players.get(playerOrder);
			// Comprobamos si el jugador ha perdio o se ha plantado 
			if(!tempPlayer.isOut()){
				/* Preguntamos a cada jugador sus opciones
				 * por orden en la pila.
				 * OPCIONES:
				 *         > Recibir carta
				 *         > Plantarse
				 */
				switch(tempPlayer.options()){
				case JOptionPane.OK_OPTION: // Recibir carta
					Sound.playSound("/sounds/beepclear.wav", false);
					if(handOutCards(tempPlayer)){
						tempPlayer.update();
						if(tempPlayer.isOut()){
							playersLeft++; // Se incrementa el numero de jugadores fuera de la partida
							// Comprueba si ha ganado, si es así se acaba la partida
							if(tempPlayer.isWon()){
								JLabel label = new JLabel(tempPlayer.getName() + " Ha ganado!");
								JOptionPane.showOptionDialog(null, label, "La partida a finalizada", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
								endOfMach = true;
							}
						}
						tempPlayer.update();
					}else{
						// No quedan cartas, se acaba el juego
						endOfMach = true;
					}
					
					break;
				case JOptionPane.INFORMATION_MESSAGE: // Plantarse
					Sound.playSound("/sounds/fail1.wav", false);
					tempPlayer.setOut(true);
					playersLeft++; // Se incrementa el numero de jugadores fuera de la partida
					break;
				}
			}else{
				if(playersLeft >= players.size()){
					endOfMach = true;
				}else{
					endOfMach = false;
				}
			}
			// Continuamos con el siguiente jugador
			playerOrder++;
		}else{
			playerOrder = 0;
		}

		// Compruebo si ha finalizado la partida  
		if(endOfMach){
			// Partida finalizada
			JLabel label = new JLabel("¿Desea reiniciar la mesa?\n Cancelar para elegir nuevos jugadores.");
			int option = JOptionPane.showOptionDialog(null, label, "La partida a finalizada", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			
			switch(option){
			case JOptionPane.YES_OPTION: // Si
				// Reiniciamos la baraja, eliminamos las cartas de los
				// jugadores y les permitimos jugar.
				ListIterator<Player> pIterator = players.listIterator();
				while(pIterator.hasNext()){
					Player tempPlayer = pIterator.next();
					// Quitamos las cartas al jugador y le permitimos jugar
					tempPlayer.restart();                       
				}
				// Reiniciamos la baraja
				createDeck();
				// Modificamos el valor de las variables de juego
				playersLeft = 0;
				playerOrder = 0;
				endOfMach = false;
				break;
			case JOptionPane.NO_OPTION: // No
				// Eliminamos todos los jugadores y reiniciamos las variables
				players.clear();
				// Reiniciamos la baraja
				createDeck();
				// Modificamos el valor de las variables de juego
				playersLeft = 0;
				playerOrder = 0;
				endOfMach = false;
				gameState = 0;
				break;
			}
		}
	}

	/**
	 * Dibuja en pantalla a los jugadores y sus
	 * cartas
	 * @param Graphics g
	 */
	private void drawPlayers(Graphics g){
		// Comprobamos que existen jugadores
		int x = 40, y = 60;
		int spanLeft = 0, spanRight = 0;
		if(players != null){
			//Recorremos los jugadores
			ListIterator<Player> it = players.listIterator();
			while(it.hasNext()){
				if(it.nextIndex()<4){ // los cuatro primeros jugadores van a la izquierda
					it.next().draw(g, x, y+spanLeft, deck);
					spanLeft += 150;
				}else{ // los cuatro siguientes a la derecha
					it.next().draw(g, x+500, y+spanRight, deck);
					spanRight += 150;
				}

			}
		}else{
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.PLAIN, 70));
			g.drawString("¡Bienvenido a Siete y Media!", 50, 150);
			g.setFont(new Font("Arial", Font.PLAIN, 18));
			g.drawString("Configura tu partida.", 50, 200);
		}

	}

	/**
	 * Divide un sprite en subimagenes dado un nÃºmero
	 * de filas y columnas.
	 * @param sprite
	 * @param rows
	 * @param cols
	 * @return BufferedImage[]
	 */
	private BufferedImage[] sliptImage(BufferedImage sprite, int rows, int cols){
		int w = sprite.getWidth() / cols;  
		int h = sprite.getHeight() / rows;  
		int num = 0;  
		BufferedImage imgs[] = new BufferedImage[w * h];  
		for (int y = 0; y < rows; y++) {  
			for (int x = 0; x < cols; x++) {  
				imgs[num] = new BufferedImage(w, h, sprite.getType());  
				Graphics g = imgs[num].createGraphics();  
				g.drawImage(sprite, 0, 0, w, h, w * x, h * y, w * x + w, h * y + h, null);  
				g.dispose();  
				num++;  
			}  
		}  
		return imgs;  
	}

	/**
	 * Crea la baraja con todas sus cartas 
	 * (Baraja española de 40 cartas) 
	 */
	private static void createDeck(){

		int counter = 0; // Cuenta el numero de la carta
		float value = 0; // Cuenta el valor de la carta
		deckOfCards = new Stack<Card>(); // Creamos la baraja

		// Rellenamos el mazo con las cartas
		// OROS
		for(int i=0; i<10; i++){
			// Controla el valor de la carta y su numero
			if(value > 6 || value == 0.5f){
				value = 0.5f;
			}else{
				++value;
			}   
			if(counter > 6 && counter < 10){
				counter = 9;
			}
			deckOfCards.add(new Card("OROS", i, ++counter, value));
		}
		// SOTAS
		counter = 0; 
		value = 0;
		for(int i=10; i<20; i++){
			// Controla el valor de la carta y su numero
			if(value > 6 || value == 0.5f){
				value = 0.5f;
			}else{
				++value;
			}   
			if(counter > 6 && counter < 10){
				counter = 9;
			}           
			deckOfCards.add(new Card("SOTAS", i, ++counter, value));
		}
		// ESPADAS
		counter = 0; 
		value = 0;
		for(int i=20; i<30; i++){
			// Controla el valor de la carta y su numero
			if(value > 6 || value == 0.5f){
				value = 0.5f;
			}else{
				++value;
			}   
			if(counter > 6 && counter < 10){
				counter = 9;
			}           
			deckOfCards.add(new Card("ESPADAS", i, ++counter, value));
		}
		// COPAS
		counter = 0; 
		value = 0;
		for(int i=30; i<40; i++){
			// Controla el valor de la carta y su numero
			if(value > 6 || value == 0.5f){
				value = 0.5f;
			}else{
				++value;
			}   
			if(counter > 6 && counter < 10){
				counter = 9;
			}          
			deckOfCards.add(new Card("COPAS", i, ++counter, value));
		}
	}

	/**
	 * Imprime en consola las cartas que se encuntran
	 * en la bararaja. Sólo a título informativo.
	 * @return String
	 */
	private String printCards(){
		String s = "";
		if(deckOfCards != null){
			for(int i=0; i<deckOfCards.size(); i++){
				s += "Name: " + deckOfCards.get(i).getName() + " Suit: " + deckOfCards.get(i).getSuit() + " Number: " + deckOfCards.get(i).getNumber() + " Value: " + deckOfCards.get(i).getValue() + "\n";
			}
		}else{
			s = "\nThere aren't cards.\n";
		}
		return s;
	}

	/**
	 * Reparte una carta de la baraja a un jugador
	 * @param player
	 * @return true si ha podido repartir una carta
	 */
	private static boolean handOutCards(Player player){
		/* 
		 * Seleccionamos una de las cartas restantes
		 * en la baraja de forma aleatoria y se la 
		 * pasamos a la mano del jugador
		 */
		int index;

		// Comprobamos las cartas restantes en la baraja
		if(deckOfCards.size() <= 0 || deckOfCards == null){
			System.err.println("No hay cartas para repartir.");
			return false;
		}else{
			// Seleccionamos una de las cartas de la baraja
			index = (int) (Math.random()*deckOfCards.size());
			player.addCard(deckOfCards.get(index)); // Agregamos la carta a la mano del jugador
			// Eliminamos la carta de la baraja y lo mostramos en la consola
			System.out.println(player.getName() + " recibió una carta con valor de " + deckOfCards.remove(index).getValue() + " puntos"); 
		}
		return true;
	}

}
