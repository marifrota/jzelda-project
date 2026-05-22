package model;

import java.io.*;

public class SaveManager {
    private static final String nomeFile = "highscore.txt";

    public static void saveScore(int score) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeFile))) {
            writer.println(score);
            System.out.println("Punteggio totale " + score);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    public static int loadScore() {
        File file = new File(nomeFile);
        if (!file.exists()) {
            return 0; 
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea = reader.readLine();
            if (linea != null) {
                return Integer.parseInt(linea.trim());
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Errore durante il caricamento del punteggio: " + e.getMessage());
        }
        return 0;
    }
}