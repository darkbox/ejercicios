package game.core;

import java.awt.Graphics;

/**
 * 
 * Clase Game de la cual luego podremos extender
 * con los métodos:
 * -init()
 * -update()
 * -draw()
 *
 * @author Rafael García Maliga
 * @version 1.0
 *
 */
public abstract class Game {
    
    protected boolean over = false;
    protected long delay = 30;
    protected int width = 800, height = 600;
    protected String title = "My Game";
        
    public abstract void init();
    
    public abstract void update();
    
    public abstract void draw(Graphics g);
    
    public String getTitle(){
        return title;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public long getDelay(){
        return delay;
    }
    
    public boolean isOver(){
        return over;
    }
    
}
