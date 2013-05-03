package game.core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Clase GameLoop
 * Es la encarga en generar un Thread para
 * la ejecucion de nuestro juego y sus funciones
 * init() al inicio y 
 * update() y draw() de forma continua dentro
 * del bucle infinito.
 *
 * @author Rafael García Maliga
 * @version 1.0
 *
 */
public class GameLoop extends Thread {
   
   private final Game game;
   private final GameCanvas canvas;
    
   public GameLoop(Game game, GameCanvas canvas){
       this.game = game;
       this.canvas = canvas;
   }
    
    @Override
   public void run(){
       game.init();
       
       while(!game.isOver()){
           game.update();
           canvas.repaint();
           try {
               Thread.sleep(game.getDelay());
           } catch (InterruptedException ex) {
               Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
}
