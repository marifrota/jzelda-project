package model;
import java.awt.Rectangle;


public class GameState {
    
    // World's elements
    private Player player;
    private Enemy enemy;
    private Enemy enemy2; //update 15/05
    private Level livelloAttuale;

    private boolean gameOver = false;
    private boolean colliding = false;
    private int score = 0;
    
    private int cooldownDamage = 0;
    
    //CONSTRUCT
    public GameState() {
        // coordinates of the characters
        player = new Player(100, 150);
        enemy = new Enemy(400,200);
        enemy2 = new Enemy(500,300); // 2nd enemy, he does not move 15/05, now he does 20/05
        
        // creating level (with map and enemies)
        livelloAttuale = new Level();
    }
        boolean touchObstacle(int x, int y) {
        boolean upLeft = livelloAttuale.presenzaOstacolo(x, y);
        boolean upRight   = livelloAttuale.presenzaOstacolo(x + 31, y);
        boolean downLeft = livelloAttuale.presenzaOstacolo(x, y + 31);
        boolean downRight   = livelloAttuale.presenzaOstacolo(x + 31, y + 31);
        
        return upLeft || upRight || downLeft || downRight;
    }
    
    // score system
    public void addScore(int points) { score += points; }
    public int getScore() { return score; }

    //  the brain
    public void update() {
        if (gameOver) return; // so we stop when game over
       
      
     // Manage Player collision
        if (touchObstacle(player.getX(), player.getY())) {
            int speed = player.getVelocita();
            switch (player.getDirezione()) {
                case NORD:
                    player.setY(player.getY() + speed); 
                    break;
                case SUD:
                    player.setY(player.getY() - speed); 
                    break;
                case EST:
                    player.setX(player.getX() - speed); 
                    break;
                case OVEST:
                    player.setX(player.getX() + speed);
                    break;
                default:
                    break;
            }
        }
     // 15/05  manage First Enemy collision
        if (enemy.isAlive()) {
            int vecchiaX = enemy.getX(); // save previous position
            int vecchiaY = enemy.getY();  
            
            enemy.aggiorna();
            
         if (touchObstacle(enemy.getX(), enemy.getY())) {
                enemy.setX(vecchiaX); // goes back
                enemy.setY(vecchiaY);
                enemy.scegliDirezioneCasuale(); // change direction
                
            } 
        }
            
       //20/05 manage Second Enemy collision
         if (enemy2.isAlive()) {
            int vecchiaX2 = enemy2.getX();
            int vecchiaY2 = enemy2.getY();
            
            enemy2.followPlayer(player);
            
         if (touchObstacle(enemy2.getX(), enemy2.getY())) {
                enemy2.setX(vecchiaX2);
                enemy2.setY(vecchiaY2);
            }     
        }
              
      if(cooldownDamage > 0) {
    	  cooldownDamage--;
      }
            //20/05 manage all of the collisions
         checkCollision();
        }
      
        private void checkCollision() {
        	colliding= false;
       
        //colliding controls (MARIANA UPDATE 15/5)
        if (touchObstacle(player.getX(), player.getY())) {
        	colliding= true;
        } 
        //COMBAT, the player attacks the enemies
        if (player.isStaAttaccando()) {
            // Enemy1 attacked
            if (enemy.isAlive() && player.getAttackBounds().intersects(enemy.getBounds())) {
                enemy.subisciDanno(1);
                if (enemy.nemicoMorto()) {
                    addScore(50); 
                }
            }
            // Enemy2 attacked
            if (enemy2.isAlive() && player.getAttackBounds().intersects(enemy2.getBounds())) {
                enemy2.subisciDanno(1);
                if (enemy2.nemicoMorto()) {
                    addScore(100);
                }
            }
        }
        
        if (enemy.isAlive() && player.getBounds().intersects(enemy.getBounds())) {
            colliding = true;
            if (cooldownDamage == 0) {
                loseLife();
            }
        }
        
        if (enemy2.isAlive() && player.getBounds().intersects(enemy2.getBounds())) {
            colliding = true;
            if (cooldownDamage == 0) {
                loseLife();
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
    public Enemy getEnemy() { return enemy; }
    public Enemy getEnemy2() { return enemy2; }
    public Level getLivelloAttuale() { return livelloAttuale; }
    public boolean isGameOver() { return gameOver; }
    public boolean isColliding() { return colliding; } //MARIANA UPDATE  15/5
}