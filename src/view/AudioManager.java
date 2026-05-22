package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    
    
    private static AudioManager instance;
    
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }
    
    
    private AudioManager() {
    }
    
    
    public void play(String filename) {
        try {
            File file = new File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip sound = AudioSystem.getClip();
            sound.open(audioStream);
            sound.start(); 
        } catch (UnsupportedAudioFileException e1) {
            System.out.println("Formato audio non supportato: " + e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e1) {
            System.out.println("Errore di lettura del file audio: " + e1.getMessage());
            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            System.out.println("Linea audio non disponibile: " + e1.getMessage());
            e1.printStackTrace();
        }
    }
}