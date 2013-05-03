/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author dexter
 */
public class Sound {
    
    /**
     * Reproduce un sonido
     * @param file
     * @param loop true o false
     */
    
    public static synchronized void playSound(final String file, final boolean loop) {
        new Thread(new Runnable() {
            @Override
          public void run() {
            try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(file));
                Clip clip = AudioSystem.getClip();
                clip.open(inputStream);
                if(loop){
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }else{
                    clip.start();
                }
                
                
            } catch (Exception e) {
              System.err.println("No se pudo reproducir el sonido: " + e.getMessage());
            }
          }
        }).start();
    }
}
