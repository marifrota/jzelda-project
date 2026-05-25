package model;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Random; // to make the enemy go in different directions
/**
 * this class is to put an enemy in the game, control the enemy movement, direction, collisions and health
 */
public class Enemy implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean hit = false;
	private boolean alive = true;
    public enum Direzione {
        NORD, SUD, EST, OVEST, IDLE
    } //

    //enemy profile
    private int x;
    private int y; //coordinates

    private int velocita = 1; //speed
    private int puntiVita = 4; ///health points (2=1 heart)

    //to make the enemy move
    private Direzione direzione = Direzione.SUD;
    private final Random random = new Random();
    private int contatorePassi = 0;
    
    private static final int offSet = 8;
    private static final int size = 48;
    /**
     * enemy movements
     */
    public void moveThere(GameState gameState) {
    	//predicts collision area
        Rectangle future = getFutureBounds();

        if (!gameState.touchObstacle(future)) {

            moveEnemy();

        } else {

            switch (direzione) {//changes direction after collision

                case NORD:
                    direzione = Direzione.SUD;
                    break;

                case SUD:
                    direzione = Direzione.NORD;
                    break;

                case EST:
                    direzione = Direzione.OVEST;
                    break;

                case OVEST:
                    direzione = Direzione.EST;
                    break;

                default:
                    scegliDirezioneCasuale();
                    break;
            }
        }
    }
    /**
     * return true is the enemy is hit
     */
    //construct
    public boolean isHit() {
        return hit;
    }
    /**
     * change the hit state of the enemy
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }
    /**
     * new enemy
     */
    public Enemy(int xIniziale, int yIniziale) {

        this.x = xIniziale;
        this.y = yIniziale;
    }
    /**
     * verify if its alive
     */
    public boolean isAlive() {
    	return alive;
    }
    /**
     * changes the enemy alive state
     */
    public void setAlive(boolean alive) {
    	this.alive = alive;
    }
    /**
     *Changes the enemy speed 
     */
    public void setVelocita(int velocita) {
    	this.velocita = velocita;
    }
    
    /**
     * the brain, it will be called by the game loop each 60 updates
     */
    //the brain, it will be called by the game loop each 60 updates
    public void aggiorna() {
        contatorePassi++;
        if (contatorePassi > 60) {
        scegliDirezioneCasuale();
        contatorePassi = 0;
        }
    }
    /**
     * makes the enemy follow the player  DECIDE WHERE THE ENEMY SHOULD GO
     */
//20/05 
    public void followPlayer(Player player) {

        int dx = player.getX() - this.x;//horizontal distance from the player
        int dy = player.getY() - this.y;//vertical distance from the player

        if (Math.abs(dx) > Math.abs(dy)) {//prefers the largest distance

            if (dx > 0) { direzione = Direzione.EST;
             } else {
                direzione = Direzione.OVEST; }
             } else {
            if (dy > 0) {
                direzione = Direzione.SUD;
            } else {
                direzione = Direzione.NORD;
            }
        }
    }
    /**
     * REALLY MOVE THE ENEMY
     */
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
    }/**
    Change enemy direction
    */
    public void setDirezione(Direzione direzione) {
        this.direzione = direzione;
    }
    /**
     * Return enemy direction
     */
    public Direzione getDirezione() {
        return direzione;
    } // spostare
    /**
     * Changes to a random movement direction
     */
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
    
    /**
     * to understand if the Enemy made contact with the Player or an object
     */
  //to understand if the Enemy made contact with the Player or an object
    public Rectangle getBounds() {

        return new Rectangle( x + offSet, y + offSet, size, size );
    }
    /**
     * Predicts future collision hitbox
     */
    public Rectangle getFutureBounds() {
        int nextX = x;
        int nextY = y;
        /**
         * predicts next movement position
         */
        switch (direzione) {
            case NORD:  nextY -= velocita; break;
            case SUD:   nextY += velocita; break;
            case EST:   nextX += velocita; break;
            case OVEST: nextX -= velocita; break;
        }
        //return predicted hitbox
        return new Rectangle( nextX + offSet, nextY + offSet, size, size);
    }
    
 /**
  * applies damage to the enemy life
  */   
//when the enemy loses health points
    public void subisciDanno(int danno) {
      puntiVita-= danno;
      
      if (puntiVita <=0) {//kills
    	  alive = false;
      }
    }
    /**
     * return true if its dead
     */
//when enemy dead
    public boolean nemicoMorto() {
        return puntiVita <= 0;
    }
/**
 * return enemy position x
 */
    public int getX() {
        return x;
    }
/**
 * return enemy y position
 */
    public int getY() {
        return y;
    }
    //20/05
    /**
     * change enemy x position
     */
    public void setX(int x) {
        this.x = x;
    }
/**
 * change enemy y position
 */
    public void setY(int y) {
        this.y = y;
    }
}