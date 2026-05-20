package model;
import java.awt.Rectangle;


public class GameState {
    
    // World's elements
    private Player player;
    private Enemy enemy;
    private Enemy enemy2; //update 15/05
    private Level livelloAttuale;
    private boolean colliding;

    private boolean gameOver = false;
    private boolean collision = false;
    private int score = 0;
    
    private int cooldownDamage = 0;
    
    //construct
    public GameState() {
        // PLayer spawns in the middle of the screen
        player = new Player(200, 200);
        enemy = new Enemy(300,300);
        enemy2 = new Enemy(120,120); // 2nd enemy, he does not move 15/05
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
        if (gameOver) return; // so we stop when game over
        
        
     // 15/05
        enemy.aggiorna(); // to move 1st enemy
        
       //20/05
        enemy2.followPlayer(player);
       
      if(cooldownDamage > 0) {
    	  cooldownDamage--;
      }
       
            //20/05 manage all of the collisions
         checkCollision();
         
        }
        
      
        private void checkCollision() {
        	collision = false;
       
        //colliding controls (MARIANA UPDATE 15/5)
        if (livelloAttuale.presenzaOstacolo(player.getX(), player.getY())) {
        	collision = true;
        } 
        //enemy 1
        if(player.getBounds().intersects(enemy.getBounds())) {
            collision = true;
            if (cooldownDamage == 0) {
            	loseLife();
           }
           //enemy 2 
        if(player.getBounds().intersects(enemy2.getBounds())) {
        	collision = true;
        	if (cooldownDamage == 0) {
            	loseLife();
            	
        	 }
          }
       }
    }


    public void loseLife() {
  
    	player.setPuntiVita(player.getPuntiVita() - 1);
    	
    	cooldownDamage = 30;
    	
    	if (player.getPuntiVita() <= 0) {
            gameOver = true;
    	}
    }
    
    
    // GETTERS 
    public Player getPlayer() { return player; }
    public Enemy getEnemy() {
    	return enemy;
    }
    public Enemy getEnemy2() {
    	return enemy2;
    }
    public Level getLivelloAttuale() { return livelloAttuale; }
    public boolean isGameOver() { return gameOver; }
    public boolean isCollision() {
    	return collision;
    } //MARIANA UPDATE  15/5
}