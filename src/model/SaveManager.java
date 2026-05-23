package model;

import java.io.*;

public class SaveManager {
    private static final String fileScore = "highscore.txt";
    private static final String fileSave = "savegame.dat";
    
    // SCORE
    public static void saveScore(int score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileScore))) {
            writer.println(score);
            System.out.println("Punteggio totale " + score);
        } catch (IOException e) {
            System.out.println("Errore salvataggio: " + e.getMessage());
        }
    }

    public static int loadScore() {
        File file = new File(fileScore);
        if (!file.exists()) {
            return 0; 
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea = reader.readLine();
            if (linea != null) {
                return Integer.parseInt(linea.trim());
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Errore con il punteggio: " + e.getMessage());
        }
        return 0;
    }
 // SAVE
    public static void saveGame(GameState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSave))) {
            oos.writeObject(state);
            System.out.println("Partita salvata");
        } catch (IOException e) {
            System.err.println("Errore salvataggio: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // LOAD
    public static GameState loadGame() {
        File file = new File(fileSave);
        if (!file.exists()) {
            System.out.println("Nessun salvataggio trovato.");
            return null;
        }
            
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileSave))) {
                return (GameState) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Errore nel caricamento: " + e.getMessage());
                return null;
            }
          }
    }