package model;

import java.awt.Rectangle;
import java.io.Serializable;
import view.AudioManager;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

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

    public void update() {

        if (timerAttacco > 0) {
            timerAttacco--;
            staAttaccando = true;
        } else {
            staAttaccando = false;
        }
    }

    public void startAttack() {

        if (timerAttacco == 0) {
            timerAttacco = 10;
            AudioManager.getInstance().playSound("resources/15 - SE_GAME_ATTACK1.wav");
        }
    }

    public Player(int xIniziale, int yIniziale) {
        this.x = xIniziale;
        this.y = yIniziale;
    }

    public void muoviSu() {
        muovi(Direzione.NORD);
    }

    public void muoviGiu() {
        muovi(Direzione.SUD);
    }

    public void muoviDestra() {
        muovi(Direzione.EST);
    }

    public void muoviSinistra() {
        muovi(Direzione.OVEST);
    }

    public void muovi(Direzione direzione) {

        this.direzione = direzione;

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

    //rectangle for colliding Area
    public java.awt.Rectangle getBounds() {
    	
        return new java.awt.Rectangle(x + 6, y + 6, 46, 46);
    }

    //rectangle for the sword
    public Rectangle getAttackBounds() {
    	//MERYEM THIS WAS THE ATTACK BUG!!!
        switch (direzione) {

            case NORD:
                return new Rectangle(x, y - 48, 54, 54);

            case SUD:
                return new Rectangle(x, y + 48, 54, 54);

            case EST:
                return new Rectangle(x + 48, y, 54, 54);

            case OVEST:
                return new Rectangle(x - 48, y, 54, 54);

            default:
                return getBounds();
        }
    }

    //GETTERS AND SETTERS
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocita() {
        return velocita;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public void setPuntiVita(int puntiVita) {
        this.puntiVita = puntiVita;
    }

    public Direzione getDirezione() {
        return direzione;
    }

    public void setDirezione(Direzione direzione) {
        this.direzione = direzione;
    }

    public boolean isStaAttaccando() {
        return staAttaccando;
    }

    public void setStaAttaccando(boolean staAttaccando) {
        this.staAttaccando = staAttaccando;
    }
}