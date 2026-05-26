package view;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
/**
 * This is for control the background music and sound effects for the game and also uses the Singleton pattern to make sure that will exist only one audio manager
 */
public class AudioManager {
    
    
    private static AudioManager instance;
    private Clip ost;
    private Clip sound;
    /**
     * It returns the unique object of audiomanager
     */
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();//creates the unique object
        }
        return instance;//returns the same object
    }
    
    /**
     * this is a private constructor for singleton pattern
     */
    private AudioManager() {
    }
    /**
     * this public void playMusica plays a background musica track and loops it continuosly is it is a dungeon or theme soundtrack
     */
    public void playMusica(String filename) {
        try {
        	
        	if (ost != null) {
                ost.stop();
                ost.close();
                ost = null;
            }
        	  
        	File file = new File(filename);
            ost = AudioSystem.getClip(); //creates a new audio clip
            ost.open(AudioSystem.getAudioInputStream(file));//opens the audio stream
            if (filename.contains("Dungeon") || filename.contains("Theme")) {
                ost.loop(Clip.LOOP_CONTINUOUSLY);
            }
            ost.start(); 
        } catch (Exception e) {
            System.out.println("Errore musica: " + e.getMessage());
        }
    }
    /**
     * it plays a short sound effect once
     */
    public void playSound(String filename) {
        try {
            File file = new File(filename);
            Clip effetto = AudioSystem.getClip(); 
            effetto.open(AudioSystem.getAudioInputStream(file));
            effetto.start(); 
        } catch (Exception e) {
            System.out.println("Errore suono: " + e.getMessage());
        }
    }
}