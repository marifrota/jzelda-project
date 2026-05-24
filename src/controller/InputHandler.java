package controller;
import java.awt.event.KeyEvent;
import model.SaveManager;
import java.awt.event.KeyListener;
import view.GamePanel;
import model.GameState;
/**Handles keyboard input for the game, controls player movement, attacks, shop, retry, exit commands and items */
public class InputHandler implements KeyListener {

	private GamePanel gamePanel;
	/** Movement and action keys */
    public boolean su, giu, sinistra, destra, attacco;
    /** Retry and exit commands */
    public boolean retry, exit; //MARIANA (detect keys for exit and try again)
    /** Shop state */
    public boolean shop;
    // CONSTRUCTOR
    /**
     * Creates a new input handler*/
    public InputHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    /**
     * Detects when a key is pressed, controls movement, attacks, shop opening and item purchases*/
    @Override
    public void keyPressed(KeyEvent e) {
        int codice = e.getKeyCode();
// i used the arrows instead of WASD
        // movement keys
        if (codice == KeyEvent.VK_UP)    su = true;
        if (codice == KeyEvent.VK_DOWN)  giu = true;
        if (codice == KeyEvent.VK_LEFT)  sinistra = true;
        if (codice == KeyEvent.VK_RIGHT) destra = true;
        // attack key
        if (codice == KeyEvent.VK_SPACE) attacco = true;
        // retry command
        if(codice == KeyEvent.VK_R)
            retry = true;//TRY AGAIN
        // exit command
        if(codice == KeyEvent.VK_ESCAPE)
            exit = true;//EXIT
       //opne and close the shop
        if(codice == KeyEvent.VK_B) {
            shop = !shop;
            gamePanel.setShopOpen(shop);
            gamePanel.repaint();
        }
     //// buy potion
        if(codice == KeyEvent.VK_1) {
            System.out.println("POTION");
            gamePanel.getGameState().buyPotion();
            gamePanel.repaint();
        }
     // buy shield
        if(codice == KeyEvent.VK_2) {
            gamePanel.getGameState().buyscudo();
            gamePanel.repaint();
        }// buy sword
        if(codice == KeyEvent.VK_3) {
            gamePanel.getGameState().buyspada();
            gamePanel.repaint();
        }
        
     // KEY S TO SAVE
        if (codice == KeyEvent.VK_S) {
            SaveManager.saveGame(gamePanel.getGameState());
            System.out.println("Salvataggio completato");
        }

        // Key L TO LOAD, 23/05 meryem
        if (codice == KeyEvent.VK_L) {
            GameState loadedState = SaveManager.loadGame();
            if (loadedState != null) {
                gamePanel.setGameState(loadedState);
                loadedState.addObserver(gamePanel); 
                
                gamePanel.repaint();
                System.out.println("Caricamento completato");
            } else {
                System.out.println("Nessun salvataggio trovato.");
            }
        }
    }
    /**Detects when a key is released and stops the movement with the released key*/
    @Override
    public void keyReleased(KeyEvent e) {
        int codice = e.getKeyCode();
        //MOVEMENT REALEASE(rilascio del movimento)
      
        if (codice == KeyEvent.VK_UP)    su = false;
        if (codice == KeyEvent.VK_DOWN)  giu = false;
        if (codice == KeyEvent.VK_LEFT)  sinistra = false;
        if (codice == KeyEvent.VK_RIGHT) destra = false;
        if (codice == KeyEvent.VK_SPACE) attacco = false;
        if(codice == KeyEvent.VK_R)
            retry = false;
        if(codice == KeyEvent.VK_ESCAPE)
            exit = false;
    }
    /**Detects typed keys.*/
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}