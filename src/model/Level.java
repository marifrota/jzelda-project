package model;

import java.awt.Graphics2D;
import java.util.List;
import java.awt.Color;

public class Level {
	
	private static final int erba = 0;
    private static final int muro = 1;

    private static final Color coloreMuro = new Color(101,67,33);
    private static final Color coloreErba = new Color(0, 128, 0);


    // 0 = grass, 1 = wall
    private int[][] mappa = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1},
        {1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    
 // Later add enemies in the room
   

    private final int tileSize = 64; // 32x32 pixel

    public Level() {
        
    }

    public void render(Graphics2D g2) {
        for (int riga = 0; riga < mappa.length; riga++) {
            for (int colonna = 0; colonna < mappa[riga].length; colonna++) {
                
                int x = colonna * tileSize;
                int y = riga * tileSize;

                if (mappa[riga][colonna] == muro) {
                    g2.setColor(coloreMuro); // Wall, gray
                } else {
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

        
        return mappa[riga][colonna] == 1;
    }

    public int getTileSize() { return tileSize; }
}