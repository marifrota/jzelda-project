package model;
import java.awt.Rectangle;
import view.GameObserver;
import java.util.ArrayList;
import java.util.List;
import model.SaveManager;
import view.AudioManager;

public class GameState {
    // World's elements
    private Player player;
    private Enemy enemy;
    private Enemy enemy2; //update 15/05
    private Enemy enemy3;
    private Enemy enemy4;
    private Enemy enemy5;
    private Level livelloAttuale;
    private int livelloCorrente = 1;
    private boolean gameOver = false;
    private boolean colliding = false;
    private int score = 0;
	private List<GameObserver> observers  = new ArrayList<>();
	
    private int cooldownDamage = 0;
    
    //CONSTRUCT
    public GameState() {
        // coordinates of the characters
        player = new Player(100, 150);
        enemy = new Enemy(400,200);
        enemy2 = new Enemy(500,300); // 2nd enemy, he does not move 15/05, now he does 20/05
        enemy3 = new Enemy(600, 250);
        enemy4 = new Enemy(700, 350);
        enemy5 = new Enemy(800, 150);
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
    public int getLivelloCorrente() {
        return livelloCorrente;
    }
    public void nextLevel() {
        livelloCorrente++;
    }
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
                
                enemy2.scegliDirezioneCasuale();
            }     
        }
         
         if (enemy3.isAlive()) {
        	    int vecchiaX3 = enemy3.getX();
        	    int vecchiaY3 = enemy3.getY();
        	    enemy3.followPlayer(player);
        	    if (touchObstacle(enemy3.getX(), enemy3.getY())) {
        	        enemy3.setX(vecchiaX3);
        	        enemy3.setY(vecchiaY3);
        	        
                    enemy3.scegliDirezioneCasuale();
        	    }
        	}
         
         if (enemy4.isAlive()) {
        	    int vecchiaX4 = enemy4.getX();
        	    int vecchiaY4 = enemy4.getY();
        	    enemy4.followPlayer(player);
        	    if (touchObstacle(enemy4.getX(), enemy4.getY())) {
        	        enemy4.setX(vecchiaX4);
        	        enemy4.setY(vecchiaY4);
        	        
                    enemy4.scegliDirezioneCasuale();
        	    }
        	}
         
         if (enemy5.isAlive()) {
        	    int vecchiaX5 = enemy5.getX();
        	    int vecchiaY5 = enemy5.getY();
        	    enemy5.followPlayer(player);
        	    if (touchObstacle(enemy5.getX(), enemy5.getY())) {
        	        enemy5.setX(vecchiaX5);
        	        enemy5.setY(vecchiaY5);
        	        
                    enemy5.scegliDirezioneCasuale();
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
            if (enemy3.isAlive() &&player.getAttackBounds().intersects(enemy3.getBounds())) {
            	enemy3.subisciDanno(1);
            	if (enemy3.nemicoMorto()) {
            		addScore(150);
            	}
            }
            if (enemy4.isAlive() &&player.getAttackBounds().intersects(enemy4.getBounds())) {
            		enemy4.subisciDanno(1);
            	    if (enemy4.nemicoMorto()) {
            	        addScore(200);
            	    }
            }
            if (enemy5.isAlive() &&player.getAttackBounds().intersects(enemy5.getBounds())) {
            	    enemy5.subisciDanno(1);
            	    if (enemy5.nemicoMorto()) {
            	        addScore(250);
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
        if (enemy3.isAlive() &&player.getBounds().intersects(enemy3.getBounds())) {
        	    colliding = true;
        	    if (cooldownDamage == 0) {
        	        loseLife();
        	    }
        }
        if (enemy4.isAlive() && player.getBounds().intersects(enemy4.getBounds())) {
        	    colliding = true;
        	    if (cooldownDamage == 0) {
        	        loseLife();
        	    }
        }
        if (enemy5.isAlive() &&player.getBounds().intersects(enemy5.getBounds())) {
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
            
        AudioManager.getInstance().play("resources/11-Game-Over.wav");
           
         SaveManager.saveScore(score);
    	}
    }
    
    public void resetGame() {
    	player = new Player(200,200);
        enemy = new Enemy(300,300);
        enemy2 = new Enemy(500,300);
        enemy3 = new Enemy(600,250);
        enemy4 = new Enemy(700,350);
        enemy5 = new Enemy(800,150);
        gameOver = false;
        score = 0;
    }
    
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }//GameObserve allows that GameState notify the HUD and the others objects when some information changes.
    
    public void notifyObservers() {
        for(GameObserver observer: observers) {
            observer.update();
        }
    }
    // GETTERS 
    public Player getPlayer() { return player; }
    public Enemy getEnemy() { return enemy; }
    public Enemy getEnemy2() { return enemy2; }
    public Enemy getEnemy3() {
        return enemy3;
    }
    public Enemy getEnemy4() {
        return enemy4;
    }
    public Enemy getEnemy5() {
        return enemy5;
    }
    public Level getLivelloAttuale() { return livelloAttuale; }
    public boolean isGameOver() { return gameOver; }
    public boolean isColliding() { return colliding; } //MARIANA UPDATE  15/5
}