package game.core;

import javax.swing.JFrame;

/**
 * 
 * Clase GameApp
 * Se encarga de generar el Jframe 
 * o ventana para el juego e inicializar
 * el bucle con su thread independiente.
 *
 * @author Rafael García Maliga
 * @version 1.0
 *
 */
public class GameApp {
     public static void start(Game game){
        JFrame frame = new JFrame(game.getTitle());
        frame.setSize(game.getWidth(), game.getHeight());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameCanvas canvas = new GameCanvas(game);
        frame.add(canvas);
        frame.setVisible(true);
        GameLoop loop = new GameLoop(game, canvas);
        loop.start();
    }
}
