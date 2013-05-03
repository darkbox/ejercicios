package game.core;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * 
 * Contiene nuestro elemento o Componente Canvas personalizado
 *
 * @author Rafael García Maliga
 * @version 1.0
 *
 */
public class GameCanvas extends JComponent {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Game game;
    
    public GameCanvas(Game game){
        this.game = game;
    }
    
    @Override
    public void paintComponent(Graphics g){
        game.draw(g);
    }
    
}
