package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    
    public boolean su, giu, sinistra, destra, attacco;

    @Override
    public void keyPressed(KeyEvent e) {
        int codice = e.getKeyCode();
// i used the arrows instead of WASD
        if (codice == KeyEvent.VK_UP)    su = true;
        if (codice == KeyEvent.VK_DOWN)  giu = true;
        if (codice == KeyEvent.VK_LEFT)  sinistra = true;
        if (codice == KeyEvent.VK_RIGHT) destra = true;
        
        if (codice == KeyEvent.VK_SPACE) attacco = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int codice = e.getKeyCode();

      
        if (codice == KeyEvent.VK_UP)    su = false;
        if (codice == KeyEvent.VK_DOWN)  giu = false;
        if (codice == KeyEvent.VK_LEFT)  sinistra = false;
        if (codice == KeyEvent.VK_RIGHT) destra = false;
        
        if (codice == KeyEvent.VK_SPACE) attacco = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}