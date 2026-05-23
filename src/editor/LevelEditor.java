package editor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class LevelEditor extends JFrame {
    private int[][] mappa = new int[15][15]; // 
    private JButton[][] bottoni = new JButton[15][15];
    private String nomeFile = "resources/levels/lvl1.txt";
    private java.util.List<String> entita = new java.util.ArrayList<>();
    private boolean enemyMode = false;

    public LevelEditor() {
    	String input = JOptionPane.showInputDialog(this, "Which level do you want to edit?");
        if (input != null && !input.isEmpty()) {
            this.nomeFile = "resources/levels/" + input + ".txt";
        }
        setTitle("Level Editor - " + nomeFile);
        setTitle("Level Editor - JZelda");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(15, 15));
        setLayout(new BorderLayout());
        
        JPanel panelGriglia = new JPanel(new GridLayout(15, 15));
        add(panelGriglia, BorderLayout.CENTER);
        JPanel panelBottoni = new JPanel();
        JButton pSave = new JButton("SAVE");
        pSave.addActionListener(e -> salvaFile());
        JButton pToggle = new JButton("Mode: TILES");
        pToggle.addActionListener(e -> { enemyMode = !enemyMode;
            pToggle.setText(enemyMode ? "Mode: ENEMIES" : "Mode: TILES");
        });

        panelBottoni.add(pToggle);
        panelBottoni.add(pSave);
        add(panelBottoni, BorderLayout.SOUTH);
        caricaFile();
        griglia(panelGriglia); 
        setVisible(true);
    }

    private void griglia(JPanel panel) {
        for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 15; j++) {
                JButton p = new JButton(String.valueOf(mappa[i][j])); //p is button (pulsante) 
                
                int riga = i; int col = j;
                    p.addActionListener(e -> {
                      if (!enemyMode) {
                        mappa[riga][col] = (mappa[riga][col] + 1) % 7;
                        p.setText(String.valueOf(mappa[riga][col]));
                    } else {
                        String nuovaEntita = "ENEMY," + (col * 64) + "," + (riga * 64);
                        entita.add(nuovaEntita);
                        p.setBackground(Color.RED); 
                        System.out.println(nuovaEntita + " added");  } 
                });
                panel.add(p);
                bottoni[i][j] = p;
            }
        }
    }

    private void salvaFile() {
        // save map
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomeFile))) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    writer.print(mappa[i][j] + (j < 14 ? "," : "")); 
                }
                writer.println();  
            }
        } catch (IOException e) { e.printStackTrace(); }
        
        String nomeFileEntita = nomeFile.replace(".txt", "_entities.txt");

        // save creature
        try (PrintWriter pw = new PrintWriter(new FileWriter("resources/levels/lvl1_entities.txt"))) {
            for (String s : entita) {
                pw.println(s);
            }
        } catch (IOException e) { e.printStackTrace(); }
        
        JOptionPane.showMessageDialog(this, "Salvataggio completato");
    }

    private void caricaFile() {
        try (Scanner sc = new Scanner(new File(nomeFile))) {
            for (int i = 0; i < 15; i++) {
                String[] riga = sc.nextLine().split(",");
             for (int j = 0; j < 15; j++) {
            	 
                    mappa[i][j] = Integer.parseInt(riga[j]);
                }
            }
        } catch (Exception e) { System.out.println("File not found"); }
    }

    public static void main(String[] args) { new LevelEditor(); }
}