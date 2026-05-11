package model;

import java.awt.Rectangle;
import java.util.Random; // to make the enemy go in different directions

public class Enemy {

    public enum Direzione {
        NORD, SUD, EST, OVEST
    }

    //enemy profile
    private int x;
    private int y; //coordinates

    private int velocita = 2; //speed
    private int puntiVita = 2; ///health points (2=1 heart)

    //to make the enemy move
    private Direzione direzione = Direzione.SUD;

    private Random random = new Random();

    private int contatorePassi = 0;
    //construct
    public Enemy(int xIniziale, int yIniziale) {

        this.x = xIniziale;
        this.y = yIniziale;
    }
    //the brain, it will be called by the game loop each 60 updates
    public void aggiorna() {

        contatorePassi++;

        if (contatorePassi > 60) {

            scegliDirezioneCasuale();

            contatorePassi = 0;
        }
   //movement based on the preferred direction
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
        }
    }

    private void scegliDirezioneCasuale() {

        int numero = random.nextInt(4);

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
        }
    }
  //to understand if the Enemy made contact with the Player or an object
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
//when the enemy loses health points
    public void subisciDanno(int danno) {
        puntiVita -= danno;
    }
//when enemy dead
    public boolean isMorto() {
        return puntiVita <= 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}