package model;

import java.util.ArrayList;
import java.util.List;

public class Level {
    
    // The map
    // 0 = Grass (Player can walk in it), 1 = Wall (it's solid)
    private int[][] mappa;
    
    // Enemies in the room
    private List<Enemy> nemici;
    	


    // dimensions
    private static final int COLONNE = 16;
    private static final int RIGHE = 11;
    private static final int TILE_SIZE = 32; // measure of each block

    // construct 
    public Level() {
        // to start the empty list of enemies
        nemici = new ArrayList<>();
        
        // loads the structure of the map
        caricaMappa();
        
        
        nemici.add(new Enemy(100, 100));
        nemici.add(new Enemy(300, 200));
    }

    // creating a closed room
    private void caricaMappa() {
        mappa = new int[RIGHE][COLONNE];
        
        for (int riga = 0; riga < RIGHE; riga++) {
            for (int col = 0; col < COLONNE; col++) {
                // if we are on the outside border it creates a wall (1), else grass (0)
                if (riga == 0 || riga == RIGHE - 1 || col == 0 || col == COLONNE - 1) {
                    mappa[riga][col] = 1; // Wall/Muro
                } else {
                    mappa[riga][col] = 0; // Grass/Erba
                }
            }
        }
    }

    // for the colliding
    
    // to warn about a solid object, like a wall
    public boolean isOstacoloSolido(int x, int y) {
      
        int colonna = x / TILE_SIZE;
        int riga = y / TILE_SIZE;
        
        // to not crash the game
        if (colonna < 0 || colonna >= COLONNE || riga < 0 || riga >= RIGHE) {
            return true; // to not go on the outside of the screen
        }
        
        
        return mappa[riga][colonna] == 1;
    }

    public int[][] getMappa() { return mappa; }
    
    public List<Enemy> getNemici() { return nemici; }
    
    public int getTileSize() { return TILE_SIZE; }
}