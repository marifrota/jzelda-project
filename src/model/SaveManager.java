package model;

import java.io.*;
/**
 * controls save and load, high score and game progress
 * */
public class SaveManager {
    private static final String fileScore = "highscore.txt"; // High score file
    private static final String fileSave = "savegame.dat"; // Save game file
    
    // SCORE
    /**
     * saves player score into a text file
     * */
    public static void saveScore(int score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileScore))) {
            writer.println(score);
            System.out.println("Punteggio totale " + score);// Creates writer for score file
        } catch (IOException e) {
            System.out.println("Errore salvataggio: " + e.getMessage());
        }
    }
/**
 * loads saved score
 * */
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
     * */
    public static void saveGame(GameState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSave))) {//create binary output stream
            oos.writeObject(state); // Serializes game state object
            System.out.println("Partita salvata");
        } catch (IOException e) {
            System.err.println("Errore salvataggio: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // LOAD
    /**
     * load game stateo
     * */
    public static GameState loadGame() {
        File file = new File(fileSave);
        if (!file.exists()) {
            System.out.println("Nessun salvataggio trovato.");
            return null;
        }
            
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileSave))) {
                return (GameState) ois.readObject();// Deserializes saved game object
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Errore nel caricamento: " + e.getMessage());
                return null;
            }
          }
    }