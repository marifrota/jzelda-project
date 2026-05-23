package model;
import view.GameObserver;
import java.util.ArrayList;
import java.util.List;
import model.SaveManager;
import view.AudioManager;
import model.Rupee;
import java.awt.Rectangle;

public class GameState {
    // WORLD'S ELEMENTS
    private Player player;
    private Enemy enemy;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Enemy enemy5;
       private Level livelloAttuale;
    private int livelloCorrente = 1;
    private boolean gameOver = false;
    private boolean colliding = false;
    private int score = 0;
    private int rupees;
	private List<GameObserver> observers  = new ArrayList<>();
	private String nickname;
    private int cooldownDamage = 0;
    private List<Rupee> rupeesOnGround = new ArrayList<>();
    private int losses = 0;
    private int wins = 0;
    
    //CONSTRUCT
    public GameState() {
        // coordinates of the characters
        player = new Player(128, 320);
 
        enemy = new Enemy(512,128);
        enemy2 = new Enemy(128,128); // 2nd enemy, he does not move 15/05, now he does 20/05
        enemy3 = new Enemy(768, 320);
        enemy4 = new Enemy(640, 128);
        enemy5 = new Enemy(704, 320);
        // creating level (with map and enemies)
        livelloAttuale = new Level(1);
    }
    //OSTACOLI TT
    public boolean touchObstacle(Rectangle bounds) {

        int step = 8;

        for (int x = bounds.x; x < bounds.x + bounds.width; x += step) {

            for (int y = bounds.y; y < bounds.y + bounds.height; y += step) {

                if (livelloAttuale.presenzaOstacolo(x, y)) {
                    return true;
                }
            }
        }

        return false;
    }
        //RUPIE
    public List<Rupee> getRupeesOnGround() {
	   return rupeesOnGround;
    }
    // score system
    public void addScore(int points) { score += points; }
    public void addRupees(int value) {
        rupees += value;
    }
    public int getScore() { return score; }
    public int getLivelloCorrente() {
        return livelloCorrente;
    }
    public int getRupees() {
        return rupees;
    }
    public void nextLevel() {
        livelloCorrente++;
    }
    //  the brain
    public void update() {
        if (gameOver) return; // so we stop when game over
      
 // first enemy
        if (enemy.isAlive()) {
      enemy.aggiorna();
      enemy.moveThere(this);
        }
        
       //20/05 manage Second Enemy collision
     // enemy2 orizontal movement
        if (enemy2.isAlive()) {
            enemy2.moveThere(this);
        }

        // enemy3 vertical
        if (enemy3.isAlive()) {

            enemy3.moveThere(this);
        }

        // enemy4 random and slow
        if (enemy4.isAlive()) {

            enemy4.aggiorna();

            enemy4.moveThere(this);
        }

        // enemy5 idle
        if (enemy5.isAlive()) {

        }
              
      if(cooldownDamage > 0) {
    	  cooldownDamage--;
      }
      for(Rupee rupee : rupeesOnGround) {
    	    if(!rupee.isCollected()&&player.getBounds().intersects(rupee.getBounds())) {
    	    	rupee.collect();
    	    	addRupees(1);
    	    }
    	}
            //20/05 manage all of the collisions
         checkCollision();
        }
        
      
        private void checkCollision() {
      
        //colliding controls (MARIANA UPDATE 15/5)
      
        //COMBAT, the player attacks the enemies
        if (player.isStaAttaccando()) {
            // Enemy1 attacked
            if (enemy.isAlive() && player.getAttackBounds().intersects(enemy.getBounds())) {
                enemy.subisciDanno(1);
                if (enemy.nemicoMorto()) {
                    addScore(50); 
                    rupeesOnGround.add(new Rupee(enemy.getX(),enemy.getY()));
                }
            }
            // Enemy2 attacked
            if (enemy2.isAlive() && player.getAttackBounds().intersects(enemy2.getBounds())) {
                enemy2.subisciDanno(1);
                if (enemy2.nemicoMorto()) {
                    addScore(100);
                    rupeesOnGround.add(new Rupee(enemy2.getX() + 40, enemy2.getY() + 20));
                }
            }
            if (enemy3.isAlive() &&player.getAttackBounds().intersects(enemy3.getBounds())) {
            	enemy3.subisciDanno(1);
            	if (enemy3.nemicoMorto()) {
            		addScore(150);
                    rupeesOnGround.add(new Rupee(enemy3.getX(),enemy3.getY()));
            	}
            }
            if (enemy4.isAlive() &&player.getAttackBounds().intersects(enemy4.getBounds())) {
            		enemy4.subisciDanno(1);
            	    if (enemy4.nemicoMorto()) {
            	        addScore(200);
                        rupeesOnGround.add(new Rupee(enemy4.getX(),enemy4.getY()));
            	    }
            }
            if (enemy5.isAlive() &&player.getAttackBounds().intersects(enemy5.getBounds())) {
            	    enemy5.subisciDanno(1);
            	    if (enemy5.nemicoMorto()) {
            	        addScore(250);
                        rupeesOnGround.add(new Rupee(enemy5.getX(),enemy5.getY())); }
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
            addLosses();
            addWins();
        AudioManager.getInstance().play("resources/11-Game-Over.wav");
           
         SaveManager.saveScore(score);
    	}
    }
    
    public void resetGame() {
    	player = new Player(128,320);
        enemy = new Enemy(512, 128);
        enemy2 = new Enemy(128,128);
        enemy3 = new Enemy(768, 320);
        enemy4 = new Enemy(640, 128);
        enemy5 = new Enemy(704, 320);
        
        enemy2.setDirezione(Enemy.Direzione.EST);
        enemy3.setDirezione(Enemy.Direzione.SUD);
        enemy4.scegliDirezioneCasuale();
        gameOver = false;
        score = 0;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNickname() {
        return nickname;
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
    public Enemy getEnemy3() { return enemy3; }
    public Enemy getEnemy4() { return enemy4; }
    public Enemy getEnemy5() { return enemy5; }
    public int getLosses() {  return losses; }
    public int getWins() { return wins; }
    public void addWins() { wins++; }
    public void addLosses() { losses++; }
    public Level getLivelloAttuale() { return livelloAttuale; }
    public boolean isGameOver() { return gameOver; }
    public boolean isColliding() { return colliding; } //MARIANA UPDATE  15/5
}