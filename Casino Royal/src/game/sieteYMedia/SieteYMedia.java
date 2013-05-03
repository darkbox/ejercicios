package game.sieteYMedia;

import game.core.Game;
import game.core.GameApp;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ListIterator;
import java.util.Stack;
import javax.imageio.ImageIO;
import tools.Menu;
import tools.Sound;
import tools.Teclado;

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
 * @version 1.0
 *
 */
public class SieteYMedia extends Game{

	// Controla el estado del juego
	private static int gameState = 0;
	private static boolean endOfMach = false;
	// Menus
	private static Menu mainMenu;
	private static Menu multiplayerMenu;
	private static Menu inGameMenu;
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
			feltFabric = ImageIO.read(new File("feltFabric.png"));
			deck = sliptImage(ImageIO.read(new File("deck.png")), 4, 10); // Divido el sprite y lo guardo
		}catch(Exception e){
			System.err.print("An error has ocurred loading textures: " + e);
		}
		// Creamos la baraja
		createDeck();
		System.out.print(printCards()); // Para depuración
		// Crea la pila de jugadores
		players = new Stack();
		// Inicializamos los menus
		// Menu principal
		mainMenu = new Menu("Siete y Media", true);
		mainMenu.add("Un jugador");
		mainMenu.add("Varios jugadores");
		mainMenu.add("About");
		// Menu Multijugador
		multiplayerMenu = new Menu("Modo multijugador", false);
		multiplayerMenu.add("Seleccionar número de jugadores");
		multiplayerMenu.add("Volver");
		// Menu del juego
		inGameMenu = new Menu("¿Repetir partida?", false);
		inGameMenu.add("Sí");
		inGameMenu.add("No");
		// Sonido
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
		case 0: // Muestra el menú principal
			mainMenu.print();
			switch(mainMenu.select()){
			case 1: // Un jugador
				System.out.println("Modo un solo jugador seleccionado.");
				// Creamos al jugador
				createPlayers(1);
				// Inicializamos la partida
				gameState = 3;
				break;
			case 2: // Multijugador
				System.out.println("Modo multijugador seleccionado.");
				gameState = 1;
				break;
			case 3: // About
				System.out.println("Version: 0.1 Author: Rafael García Maliga");
				break;
			}
			break;
		case 1: // Muestra el submenu de multijugador
			multiplayerMenu.print();
			switch(multiplayerMenu.select()){
			case 1: // Selecciona el número de jugadores
				int numberOfPlayers = 0;
				do{
					numberOfPlayers = Teclado.leerEntero("Selecciona el número de jugadores. (de 2 a ...)> ");
				}while(numberOfPlayers < 2);
				// Creamos los jugadores
				createPlayers(numberOfPlayers);
				// Inicializamos la partida
				gameState = 3;
				break;
			case 2: // Vuelve al menú principal
				gameState = 0;
				break;

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
			players.add(new Player(Teclado.leerCadena("Escriba el nombre para el jugador #"+i+"> ")));
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
				case 1: // Recibir carta
					Sound.playSound("sounds/beepclear.wav", false);
					handOutCards(tempPlayer);
					tempPlayer.update();
					if(tempPlayer.isOut()){
						playersLeft++; // Se incrementa el numero de jugadores fuera de la partida
					}
					break;
				case 2: // Plantarse
					Sound.playSound("sounds/fail1.wav", false);
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
			inGameMenu.print();
			switch(inGameMenu.select()){
			case 1: // Si
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
			case 2: // No
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
			g.drawString("Configura tu partida en el menu de consola.", 50, 200);
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
		deckOfCards = new Stack(); // Creamos la baraja

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
