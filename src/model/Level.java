package model;

import java.awt.Graphics2D;
import java.util.List;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
public class Level {
	
	private static final int erba = 0;
    private static final int muro = 1;
    private static final int porta = 2;
    private static final int acqua= 3;
    private static final int sabbia= 4; //sand
    private static final int pavimento= 5; //ground
    private static final int lava= 6; 

    private static final Color coloreMuro = new Color(101,67,33);
    private static final Color coloreErba = new Color(0, 128, 0);
    private static final Color colorePorta = new Color(0, 0, 0); //22/05 added door to shift levels
    private static final Color coloreAcqua = new Color(30, 144, 255);
    private static final Color coloreSabbia = new Color(210, 180, 140);
    private static final Color colorePavimento = new Color(202, 205, 255);
    private static final Color coloreLava = new Color(238, 118, 0);


    // 0 = grass, 1 = wall
    private int[][] mappa; //removed matrice from here to add levels 
    private final int tileSize = 64; // 32x32 pixel
    private int livelloCorrente = 1;
       
    public Level(int numeroLvl) {
        this.livelloCorrente = numeroLvl;
        caricaLvl(numeroLvl);
    }
    
    private void caricaLvl(int numeroLvl) {
        String file = "resources/levels/lvl" + numeroLvl;
        
        try {
            java.util.List<String> totRighe = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(file));
            
            this.mappa = new int[totRighe.size()][];
            
            for (int i = 0; i < totRighe.size(); i++) {
                String riga = totRighe.get(i);
                //using Stream
                this.mappa[i] = java.util.stream.Stream.of(riga.split(","))
                                                       .mapToInt(Integer::parseInt)
                                                       .toArray(); }
            
        System.out.println("Level " + numeroLvl);
            
        } catch (java.io.IOException | NumberFormatException e) {
            System.out.println("Errore: " + e.getMessage());
            // safety map
            this.mappa = new int[0][0]; 
        }
    }
 // Later add enemies in the room
   

    public Level() {
        
    }

    public void render(Graphics2D g2) {
        for (int riga = 0; riga < mappa.length; riga++) {
            for (int colonna = 0; colonna < mappa[riga].length; colonna++) {
                
                int x = colonna * tileSize;
                int y = riga * tileSize;

                if (mappa[riga][colonna] == muro) {
                    g2.setColor(coloreMuro); }
                    // Wall, gray 
                 else if (mappa[riga][colonna] == porta){ 
                	g2.setColor(colorePorta); //add door
                } 
                 else if (mappa[riga][colonna] == sabbia){ 
                 	g2.setColor(coloreSabbia); //
                 } 
                 else if (mappa[riga][colonna] == acqua){ 
                 	g2.setColor(coloreAcqua); 
                 } 
                 else if (mappa[riga][colonna] == pavimento){ 
                  	g2.setColor(colorePavimento); //
                  } 
                  else if (mappa[riga][colonna] == lava){ 
                  	g2.setColor(coloreLava); 
                  } 
                else {
                    g2.setColor(coloreErba); // Grass, green
                }
                
                g2.fillRect(x, y, tileSize, tileSize); //The tile borders were softened to give the map a more vintage and retro look
                g2.setColor(Color.BLACK);
                g2.drawRect(x,y,tileSize,tileSize);
            }
        }
    }

    // for colliding
    public boolean presenzaOstacolo(int x, int y) {
        int colonna = x / tileSize;
        int riga = y / tileSize;

        //if outside, its a wall
        if (riga < 0 || riga >= mappa.length || colonna < 0 || colonna >= mappa[0].length) {
            return true;
        }
        int tipoOstacolo = mappa[riga][colonna];
        
        return (tipoOstacolo == 1 || tipoOstacolo == 3 || tipoOstacolo == 6);
    }

    public int getTileSize() { return tileSize; }
}