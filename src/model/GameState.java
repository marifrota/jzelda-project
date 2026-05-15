package model;
import java.awt.Rectangle;


public class GameState {
    
    // World's elements
    private Player player;
    private Enemy enemy;
    private Level livelloAttuale;
    private boolean gameOver = false;
    private boolean collision = false;
    private int score = 0;
    //construct
    public GameState() {
        // PLayer spawns in the middle of the screen
        player = new Player(200, 200);
        enemy = new Enemy(300,300);
        // creating level (with map and enemies)
        livelloAttuale = new Level();
    }
    
    public void addScore(int points) {
    	score += points;
    }
    
    public int getScore() {
    	return score;
    }

    // the total brain
    public void update() {
        if (gameOver) { 
        	return; // so we stop when game over
        }
        

        // checks if player is alive or not
        if (player.getPuntiVita() <= 0) {
            gameOver = true;
        }
        
        //colliding controls (MARIANA UPDATE 15/5)
        if (livelloAttuale.presenzaOstacolo(player.getX(), player.getY())) {
        	collision = true;
        } else {
        	collision = false;
        }
        
        if(player.getBounds().intersects(enemy.getBounds())) {
            collision = true;
        }
    }

    // GETTERS 
    public Player getPlayer() { return player; }
    public Enemy getEnemy() {
    	return enemy;
    }
    public Level getLivelloAttuale() { return livelloAttuale; }
    public boolean isGameOver() { return gameOver; }
    public boolean isCollision() {
    	return collision;
    } //MARIANA UPDATE  15/5
}