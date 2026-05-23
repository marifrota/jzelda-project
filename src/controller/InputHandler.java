package controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import view.GamePanel;

public class InputHandler implements KeyListener {

	private GamePanel gamePanel;
    public boolean su, giu, sinistra, destra, attacco;
    public boolean retry, exit; //MARIANA (detect keys for exit and try again)
    public boolean shop;
    // CONSTRUCTOR
    public InputHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int codice = e.getKeyCode();
// i used the arrows instead of WASD
        if (codice == KeyEvent.VK_UP)    su = true;
        if (codice == KeyEvent.VK_DOWN)  giu = true;
        if (codice == KeyEvent.VK_LEFT)  sinistra = true;
        if (codice == KeyEvent.VK_RIGHT) destra = true;
        if (codice == KeyEvent.VK_SPACE) attacco = true;
        if(codice == KeyEvent.VK_R)
            retry = true;//TRY AGAIN
        if(codice == KeyEvent.VK_ESCAPE)
            exit = true;//EXIT
        if(codice == KeyEvent.VK_B) {
            shop = !shop;
            gamePanel.setShopOpen(shop);
            gamePanel.repaint();
        }
        if(codice == KeyEvent.VK_1) {
            System.out.println("POTION");
            gamePanel.getGameState().buyPotion();
            gamePanel.repaint();
        }
        if(codice == KeyEvent.VK_2) {
            gamePanel.getGameState().buyscudo();
            gamePanel.repaint();
        }
        if(codice == KeyEvent.VK_3) {
            gamePanel.getGameState().buyspada();
            gamePanel.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int codice = e.getKeyCode();

      
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

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}