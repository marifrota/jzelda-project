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
    private List<Enemy> enemies = new ArrayList<>(); 
    //23/05
    // creating level (with map and enemies)
    private Level livelloAttuale= new Level(1);
    private int livelloCorrente = 1;
    private boolean gameOver = false;
    private boolean colliding = false;
    private int score = 0;
    private int rupees;
	private List<GameObserver> observers= new ArrayList<>();
	private String nickname;
    private int cooldownDamage= 0;
    private List<Rupee> rupeesOnGround= new ArrayList<>();
    private int losses = 0;
    private int wins = 0;
    private boolean levelCompleted = false;
    private boolean scudo = false;
    private boolean spada = false;
    // CONSTRUCT
    public GameState() {
        // coordinates of the characters
        player = new Player(128, 320);
        spawnEnemies(); }
        private void spawnEnemies() {
            enemies.clear();
            enemies.add(new Enemy(512, 128)); //(Enemy 1)
            enemies.add(new Enemy(128, 128)); //(Enemy 2)
            enemies.add(new Enemy(768, 320)); //(Enemy 3)
            enemies.add(new Enemy(640, 128)); //(Enemy 4)
            enemies.add(new Enemy(704, 320)); //(Enemy 5)
            
            getEnemy2().setDirezione(Enemy.Direzione.EST);
            getEnemy3().setDirezione(Enemy.Direzione.SUD);
            getEnemy4().scegliDirezioneCasuale();
        }
        
      //23/05 LIVELLI
        public void lvlSuccessivo() {
            if (livelloCorrente < 16) {
                livelloCorrente++;
                livelloAttuale = new Level(livelloCorrente); 
                
                player.setX(128); 
                player.setY(320);
                
                spawnEnemies();
                
                rupeesOnGround.clear(); 
                
                System.out.println("Livello " + livelloCorrente);
            } else {
                gameOver = true;
                addWins(); // 
                System.out.println("THE END!");
            }
        }
        
        //RESET 
        public void resetGame() {
        	player = new Player(128,320);
           spawnEnemies();
            gameOver = false;
            score = 0;
            livelloCorrente = 1;
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
        //RUPIE (mariana)
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
   
    //  BRAIN 
    public void update() {
        if (gameOver) return; // so we stop when game over
      
 //Enemies pattern
        if (getEnemy().isAlive()) {
            getEnemy().aggiorna();
            getEnemy().moveThere(this);
        }
        if (getEnemy2().isAlive()) {   // 20/05 manage 2 enemy, colliding, update 23/05 
            getEnemy2().moveThere(this);
        }
        if (getEnemy3().isAlive()) {
            getEnemy3().moveThere(this);
        }
        if (getEnemy4().isAlive()) {
            getEnemy4().aggiorna();
            getEnemy4().moveThere(this);
        }
        if (getEnemy5().isAlive()) {
        	getEnemy5().aggiorna();
            getEnemy5().moveThere(this);
        	// idle
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
         //23/05
         boolean allDefeated = enemies.stream().noneMatch(Enemy::isAlive);

         if (allDefeated && livelloAttuale.isPorta(player.getX() + 32, player.getY() + 32)) {
             lvlSuccessivo(); //changed to Stream
         }
     }
        
        private void checkCollision() {
        	colliding = false;
      
        //colliding controls (MARIANA UPDATE 15/5)
        //COMBAT, the player attacks the enemies
        if (player.isStaAttaccando()) {
            // Enemy1 attacked
        	if (player.isStaAttaccando()) {
                if (getEnemy().isAlive() && player.getAttackBounds().intersects(getEnemy().getBounds())) {
                    getEnemy().subisciDanno(spada ? 2 : 1);
                    if (getEnemy().nemicoMorto()) {
                        addScore(50); 
                        rupeesOnGround.add(new Rupee(getEnemy().getX(), getEnemy().getY()));
                    }
                }
                if (getEnemy2().isAlive() && player.getAttackBounds().intersects(getEnemy2().getBounds())) {
                    getEnemy2().subisciDanno(spada ? 2 : 1);;
                    if (getEnemy2().nemicoMorto()) {
                        addScore(100);
                        rupeesOnGround.add(new Rupee(getEnemy2().getX() + 40, getEnemy2().getY() + 20));
                    }
                }
                if (getEnemy3().isAlive() && player.getAttackBounds().intersects(getEnemy3().getBounds())) {
                    getEnemy3().subisciDanno(spada ? 2 : 1);;
                    if (getEnemy3().nemicoMorto()) {
                        addScore(150);
                        rupeesOnGround.add(new Rupee(getEnemy3().getX(), getEnemy3().getY()));
                    }
                }
                if (getEnemy4().isAlive() && player.getAttackBounds().intersects(getEnemy4().getBounds())) {
                    getEnemy4().subisciDanno(spada ? 2 : 1);;
                    if (getEnemy4().nemicoMorto()) {
                        addScore(200);
                        rupeesOnGround.add(new Rupee(getEnemy4().getX(), getEnemy4().getY()));
                    }
                }
                if (getEnemy5().isAlive() && player.getAttackBounds().intersects(getEnemy5().getBounds())) {
                    getEnemy5().subisciDanno(spada ? 2 : 1);;
                    if (getEnemy5().nemicoMorto()) {
                        addScore(250);
                        rupeesOnGround.add(new Rupee(getEnemy5().getX(), getEnemy5().getY())); 
                    }
                }
            }
        
        	if (getEnemy().isAlive() && player.getBounds().intersects(getEnemy().getBounds())) {
                colliding = true; if (cooldownDamage == 0) loseLife();
            }
            if (getEnemy2().isAlive() && player.getBounds().intersects(getEnemy2().getBounds())) {
                colliding = true; if (cooldownDamage == 0) loseLife();
            }
            if (getEnemy3().isAlive() && player.getBounds().intersects(getEnemy3().getBounds())) {
                colliding = true; if (cooldownDamage == 0) loseLife();
            }
            if (getEnemy4().isAlive() && player.getBounds().intersects(getEnemy4().getBounds())) {
                colliding = true; if (cooldownDamage == 0) loseLife();
            }
            if (getEnemy5().isAlive() && player.getBounds().intersects(getEnemy5().getBounds())) {
                colliding = true; if (cooldownDamage == 0) loseLife();
            }
        }
        }


    public void loseLife() {
    	if(scudo) {
    		scudo = false;
    	  	System.out.println("Shield broken!");
    	}else {
    	    player.setPuntiVita(player.getPuntiVita() - 1);
    	}    	
    	cooldownDamage = 30;
    	if (player.getPuntiVita() <= 0) {
            gameOver = true;       
            addLosses();
            AudioManager.getInstance().playMusica("resources/11-Game-Over.wav");
            SaveManager.saveScore(score);
    	}
    }
    
   // PER LA LISTA
    public Enemy getEnemy()  { return enemies.get(0); }
    public Enemy getEnemy2() { return enemies.get(1); }
    public Enemy getEnemy3() { return enemies.get(2); }
    public Enemy getEnemy4() { return enemies.get(3); }
    public Enemy getEnemy5() { return enemies.get(4); }
    public List<Enemy> getEnemiesList() { return enemies; }
    
    
    
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
    // SHOP (MARIANA)
    public Player getPlayer() { return player; }
    public boolean scudo() { return scudo; }
    public boolean spada() { return spada; }
    public void buyPotion() {
    	if(rupees >= 5) {
    		rupees -= 5;
    		player.setPuntiVita(player.getPuntiVita() + 1);
    		System.out.println(player.getPuntiVita());
    	}
    }//doesnt need private boolean because it used imediately
    public void buyscudo() {
        if(rupees >= 10) {
            rupees -= 10;
            scudo = true;
        }
    }
    
    public void buyspada() {
        if(rupees >= 15) {
            rupees -= 15;
            spada = true;
        }
    }
    
//github.com/marifrota/jzelda-project.git
    //GETTERS
    public Level getLivelloAttuale() { return livelloAttuale; }
    public boolean isGameOver() { return gameOver; }
    public boolean isColliding() { return colliding; } //MARIANA UPDATE  15/5
    public int getLosses() { return losses; }
    public int getWins() { return wins; }
    public void addWins() { wins++; }
    public void addLosses() {  losses++; }
}