package model;

import java.awt.Rectangle;
import java.util.Random; // to make the enemy go in different directions

public class Enemy {
	private boolean hit = false;
	private boolean alive = true;
    public enum Direzione {
        NORD, SUD, EST, OVEST, IDLE
    } //

    //enemy profile
    private int x;
    private int y; //coordinates

    private int velocita = 1; //speed
    private int puntiVita = 2; ///health points (2=1 heart)

    //to make the enemy move
    private Direzione direzione = Direzione.SUD;
    private final Random random = new Random();
    private int contatorePassi = 0;
    
    //construct
    public boolean isHit() {
        return hit;
    }
    public void setHit(boolean hit) {
        this.hit = hit;
    }
    
    public Enemy(int xIniziale, int yIniziale) {

        this.x = xIniziale;
        this.y = yIniziale;
    }
    public boolean isAlive() {
    	return alive;
    }
    public void setAlive(boolean alive) {
    	this.alive = alive;
    }
    
 
    //the brain, it will be called by the game loop each 60 updates
    public void aggiorna() {
        contatorePassi++;
        if (contatorePassi > 60) {
        scegliDirezioneCasuale();
        contatorePassi = 0;
        }
      //20/05
        moveEnemy();
    }
    
//20/05 
    public void followPlayer(Player player) {

        int dx = player.getX() - this.x;
        int dy = player.getY() - this.y;

        if (Math.abs(dx) > Math.abs(dy)) {

            if (dx > 0) { direzione = Direzione.EST;
                x += velocita;
            } else {
                direzione = Direzione.OVEST;
                x -= velocita;
            }

        } else {
            if (dy > 0) {
                direzione = Direzione.SUD;
                y += velocita;
            } else {
                direzione = Direzione.NORD;
                y -= velocita;
            }
        }
    }
    
   //movement based on the preferred direction
    public void moveEnemy() {
        switch (direzione) {

            case NORD:
                y -= velocita;
                break;
            case SUD:
                y += velocita;
                break;
            case EST:
                x += velocita;
                break;
            case OVEST:
                x -= velocita;
                break;
            case IDLE:
            	break;
        }
    }

    public void scegliDirezioneCasuale() {
        int numero = random.nextInt(5);
        switch (numero) {

            case 0:
                direzione = Direzione.NORD;
                break;
            case 1:
                direzione = Direzione.SUD;
                break;
            case 2:
                direzione = Direzione.EST;
                break;
            case 3:
                direzione = Direzione.OVEST;
                break;
            case 4:
            	direzione = Direzione.IDLE;
            	break;
        }
    }
    
  //to understand if the Enemy made contact with the Player or an object
    public Rectangle getBounds() {
        return new Rectangle(x, y, 80, 60);
    }
    
//when the enemy loses health points
    public void subisciDanno(int danno) {
      puntiVita-= danno;
      
      if (puntiVita <=0) {
    	  alive = false;
      }
    }
    
//when enemy dead
    public boolean nemicoMorto() {
        return puntiVita <= 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    //20/05
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}