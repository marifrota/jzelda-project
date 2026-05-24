package model;

import java.awt.Rectangle;
import java.io.Serializable;
import view.AudioManager;
/**
 * this class represents the LINK that controls the movement, attack, health and collision
 */
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Represents player movement directions.
	 */
    public enum Direzione {
        NORD, SUD, EST, OVEST, IDLE
    }

    //character profile
    private int x, y;                // coordinates
    private int velocita = 4;        // speed movement
    private int puntiVita = 3;       // MARIANA CHANGED
    private Direzione direzione = Direzione.SUD; // initial direction
    private boolean staAttaccando = false;
    private int timerAttacco = 0;
    private boolean hasSword = false;
   /*
    * *updates attack animation state
    */
    public void update() {

        if (timerAttacco > 0) {
            timerAttacco--;//reduce timer
            staAttaccando = true;
        } else {
            staAttaccando = false;
        }
    }
/**
 * starts player attack animation
 */
    public void startAttack() {
    	//prevents extremelly fast attack
        if (timerAttacco == 0) {
            timerAttacco = 10;
            AudioManager.getInstance().playSound("resources/15 - SE_GAME_ATTACK1.wav");
        }
    }
/**
 * creates a new playe
 * r*/
    public Player(int xIniziale, int yIniziale) {
        this.x = xIniziale;
        this.y = yIniziale;
    }
    /**
     * return the upgrade of the sword
     */
    public boolean hasSword() {
        return hasSword;
    }
/**
 * change the sword upgrade state
 */
    public void setHasSword(boolean hasSword) {
        this.hasSword = hasSword;
    }
/**
 * move player for the north
 */
    public void muoviSu() {
        muovi(Direzione.NORD);
    }
/**
 * move the player to the south
 */
    public void muoviGiu() {
        muovi(Direzione.SUD);
    }
/**
 * move the player to the right
 */
    public void muoviDestra() {
        muovi(Direzione.EST);
    }
/**
 * move the player to the left
 */
    public void muoviSinistra() {
        muovi(Direzione.OVEST);
    }
/**
 * manages player movement
 */
    public void muovi(Direzione direzione) {

        this.direzione = direzione;//updates player direction

        switch (direzione) {//verify the direction

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
/**
 * rectangle for colliding Area
 */
    //rectangle for colliding Area
    public java.awt.Rectangle getBounds() {
    	
        return new java.awt.Rectangle(x + 6, y + 6, 46, 46);
    }
/**
 * rectangle for the sword
 */
    //rectangle for the sword
    public Rectangle getAttackBounds() {
    	//MERYEM THIS WAS THE ATTACK BUG!!!
    	int size = hasSword ? 72 : 48; // this is to make the sword powerful
    	switch (direzione) {
	        case NORD:
	            return new Rectangle(x, y - size, size, size);
	        case SUD:
	            return new Rectangle(x, y + size, size, size);
	        case EST:
	            return new Rectangle(x + size, y, size, size);
	        case OVEST:
	            return new Rectangle(x - size, y, size, size);
	        default:
	            return getBounds();
    	}
    }

    //GETTERS AND SETTERS
    /**
     * Returns player X position.
     */
    public int getX() {
        return x;
    }
    /**
     * Changes player X position.
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Returns player Y position.
     */
    public int getY() {
        return y;
    }
    /**
     * Changes player Y position.
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * Returns movement speed.
     */
    public int getVelocita() {
        return velocita;
    }
    /**
     * Returns player health points
     */
    public int getPuntiVita() {
        return puntiVita;
    }
    /**
     * Changes player health points.
     */
    public void setPuntiVita(int puntiVita) {
        this.puntiVita = puntiVita;
    }
    /**
     * Returns current player direction.
     */
    public Direzione getDirezione() {
        return direzione;
    }
    /**
     * Changes player direction.
     */
    public void setDirezione(Direzione direzione) {
        this.direzione = direzione;
    }
    /**
     * Returns true if player is attacking.
     */
    public boolean isStaAttaccando() {
        return staAttaccando;
    }
    /**
     * Changes attack state.
     */
    public void setStaAttaccando(boolean staAttaccando) {
        this.staAttaccando = staAttaccando;
    }
}