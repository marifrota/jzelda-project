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
    private Clip ost;
    private Clip sound;
    
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }
    
    
    private AudioManager() {
    }
    
    public void playMusica(String filename) {
        try {
        	
        	if (ost != null) {
                ost.stop();
            }
        	  
        	File file = new File(filename);
            ost = AudioSystem.getClip(); 
            ost.open(AudioSystem.getAudioInputStream(file));
            ost.start(); 
        } catch (Exception e) {
            System.out.println("Errore musica: " + e.getMessage());
        }
    }
    
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