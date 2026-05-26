package model;

import java.io.*;
/**
 * this public class its to control the save and load, high score and game progress
 */
public class SaveManager {
    private static final String fileScore = "highscore.txt"; // High score file
    private static final String fileSave = "savegame.dat"; // Save game file
    
    // SCORE
    /**
     * this is to save player score into a text file
     */
    public static void saveScore(int score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileScore))) {
            writer.println(score);
            System.out.println("Punteggio totale " + score);// Creates writer for score file
        } catch (IOException e) {
            System.out.println("Errore salvataggio: " + e.getMessage());
        }
    }
/**
 * it loads saved score
 */
    public static int loadScore() {
        File file = new File(fileScore);
        if (!file.exists()) {
            return 0; 
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // Opens score file
            String linea = reader.readLine();
            if (linea != null) {   // Converts text into integer score
                return Integer.parseInt(linea.trim());
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Errore con il punteggio: " + e.getMessage());
        }
        return 0;
    }
 // SAVE
    /**
     * save game state
     */
    public static void saveGame(GameState state, int profile) { //fixing another bug! 25/05 
    	String savedGame = "savegame_" + profile + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedGame))) {//create binary output stream
            oos.writeObject(state); // Serializes game state object
            System.out.println("Game saved in:" + profile);
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
            e.printStackTrace();
        }
    }

    // LOAD
    /**
     * load game state
     */
    public static GameState loadGame(int profile) {
    	String savedGame= "savegame_" + profile + ".dat";
        File file = new File(savedGame);
        if (!file.exists()) {
            System.out.println("Nessun salvataggio trovato");
            return null;
        }
            
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedGame))) {
                return (GameState) ois.readObject();// Unserializes saved game object
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Errore:" + e.getMessage());
                return null;
            }
          }
    }