package editor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
/**
 * Level editor used to create and modify maps as well as the enemies
 */
public class LevelEditor extends JFrame {
    private int[][] mappa = new int[7][15]; // 
    private JButton[][] bottoni = new JButton[7][15];
    private String nomeFile = "resources/levels/lvl1.txt";
    private java.util.List<String> entita = new java.util.ArrayList<>();
    private boolean enemyMode = false;
    /**
     * Creates the level editor window.
     */
    public LevelEditor() {
    	String input = JOptionPane.showInputDialog(this, "Which level do you want to edit?");
        if (input != null && !input.isEmpty()) {
            this.nomeFile = "resources/levels/" + input + ".txt";
        }
        setTitle("Level Editor - " + nomeFile);
        setTitle("Level Editor - JZelda");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel panelGriglia = new JPanel(new GridLayout(7, 15));
        add(panelGriglia, BorderLayout.CENTER);
        JPanel panelBottoni = new JPanel();
        
        // 24/05 button menu
        JButton pMenu = new JButton("MENU");
        pMenu.addActionListener(e -> {
            this.dispose(); 
            view.GameWindow.main(null); 
        });
        JButton pSave = new JButton("SAVE");
        pSave.addActionListener(e -> salvaFile());
        JButton pToggle = new JButton("Mode: TILES");
        pToggle.addActionListener(e -> { enemyMode = !enemyMode;
            pToggle.setText(enemyMode ? "Mode: ENEMIES" : "Mode: TILES"); });
        JButton pReset = new JButton("RESET");
        pReset.addActionListener(e -> resetMappa());

        panelBottoni.add(pMenu);
        panelBottoni.add(pToggle);
        panelBottoni.add(pSave);
        panelBottoni.add(pReset);
        add(panelBottoni, BorderLayout.SOUTH);
        caricaFile();
        griglia(panelGriglia); 
        setVisible(true);
    }
    /**
     * reset mapa
     */
    private void resetMappa() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Reset to last save?", 
            "Confirm Reset", 
            JOptionPane.YES_NO_OPTION);   
        if (confirm == JOptionPane.YES_OPTION) {
            caricaFile();
            
           for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 15; j++) {
                    bottoni[i][j].setText(String.valueOf(mappa[i][j])); 
                    String checkEntita = "ENEMY," + (j * 64) + "," + (i * 64);
                    if (entita.contains(checkEntita)) {
                        bottoni[i][j].setBackground(Color.RED);
                    } else {
                        bottoni[i][j].setBackground(UIManager.getColor("Button.background")); 
                    }
                }
            }
            System.out.println("Map resetted successfully.");
        }
    }
/**
 * creates the editable grid interface
 */
    private void griglia(JPanel panel) {
        for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 15; j++) {
                JButton p = new JButton(String.valueOf(mappa[i][j])); //p is button (pulsante) 
                int riga = i; int col = j;
                p.setOpaque(true);
                p.setBorderPainted(false);
                
                String checkEntita = "ENEMY," + (col * 64) + "," + (riga * 64);
                if (entita.contains(checkEntita)) {
                    p.setBackground(Color.RED);
                }
                p.addActionListener(e -> {
                    if (!enemyMode) {
                        // map mode
                        mappa[riga][col] = (mappa[riga][col] + 1) % 7;
                        p.setText(String.valueOf(mappa[riga][col]));
                    } else {
                        // enemy mode
                        String nuovaEntita = "ENEMY," + (col * 64) + "," + (riga * 64);
                        
                        if (!entita.contains(nuovaEntita)) {
                            // add
                        	entita.add(nuovaEntita);
                            p.setBackground(Color.RED); 
                            System.out.println(nuovaEntita + " added");  
                        } else {
                            // remove
                            entita.remove(nuovaEntita);
                            p.setBackground(UIManager.getColor("Button.background"));
                            System.out.println(nuovaEntita + " removed");
                        }
                    } 
                });
                panel.add(p);
                bottoni[i][j] = p;
            }
        }
    }
    /**
     * Saves map and entities into files.
     */
    private void salvaFile() {
        // save map
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeFile))) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 15; j++) {
                    writer.print(mappa[i][j] + (j < 14 ? "," : "")); 
                }
                writer.println();  
            }
        } catch (IOException e) { e.printStackTrace(); }
        
        String nomeFileEntita = nomeFile.replace(".txt", "_entities.txt");

        // save creature
        try (PrintWriter pw = new PrintWriter(new FileWriter(nomeFileEntita))) {
            for (String s : entita) {
                pw.println(s);
            }
        } catch (IOException e) { e.printStackTrace(); }
        
        JOptionPane.showMessageDialog(this, "Salvataggio completato");
    }
    /**
     * Loads map and entities from files.
     */
    private void caricaFile() {
        try (Scanner sc = new Scanner(new File(nomeFile))) {
            for (int i = 0; i < 7; i++) {
                String[] riga = sc.nextLine().split(",");
             for (int j = 0; j < 15; j++) {
                    mappa[i][j] = Integer.parseInt(riga[j]);
                }
            }
        } catch (Exception e) { System.out.println("File not found"); }
     entita.clear();
    String nomeFileEntita = nomeFile.replace(".txt", "_entities.txt");
    try (Scanner scEntita = new Scanner(new File(nomeFileEntita))) {
        while (scEntita.hasNextLine()) {
            entita.add(scEntita.nextLine());
        }
    } catch (Exception e) { System.out.println("Entities file not found (normal if new level)"); }
    }
    /**
     * Starts the level editor
     */
    public static void main(String[] args) { new LevelEditor(); }
}