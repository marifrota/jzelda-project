package model;
import view.GameObserver;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import model.SaveManager;
import view.AudioManager;
import model.Rupee;
import java.io.Serializable;
import java.awt.Rectangle;
/**
 * keeps and controls all the gamestate like the player, enemies, levels, collision, score, shop and progression
 * 
 */
public class GameState implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tipoRupee;
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
	private transient List<GameObserver> observers= new ArrayList<>();
	private String nickname;
    private int cooldownDamage= 0;
    private List<Rupee> rupeesOnGround= new ArrayList<>();
    private int losses = 0;
    private int wins = 0;
    private boolean levelCompleted = false;
    private boolean scudo = false;
    private boolean spada = false;
    private int giocate = 0;
    // CONSTRUCT
    /**
     * it creates the initial game state
     */
    public GameState() {
    	addGiocate();
        // coordinates of the characters
        player = new Player(128, 320);
        spawnEnemies();//create new enemies
    }
        
        /**
         * make it appears enemies for the current level
         */
        private void spawnEnemies() {
        	if (loadEnemiesFromFile()) {// Loads enemies from external entity file
                //initial direction
        		getEnemy2().setDirezione(Enemy.Direzione.EST);
                getEnemy3().setDirezione(Enemy.Direzione.SUD);
                //random direction
                getEnemy4().scegliDirezioneCasuale();
                getEnemy5().scegliDirezioneCasuale();
             // Different enemy speeds for gameplay variety
                getEnemy().setvelocidade(1);   // weakest
                getEnemy2().setvelocidade(2);
                getEnemy3().setvelocidade(3);
                getEnemy4().setvelocidade(4);
                getEnemy5().setvelocidade(5);  // strongest
                return; 
            }
            enemies.clear();//clear old enemies
         // GEL
            enemies.add(new Enemy(128,128, Rupee.ROSA));
            // OCTOROK
            enemies.add(new Enemy(256,128, Rupee.AZUL));
            // MOBLIN
            enemies.add(new Enemy(384,128, Rupee.VERMELHO));
            // DARKNUT
            enemies.add(new Enemy(512,128, Rupee.ROXO));
            // GANON
            enemies.add(new Enemy(640,128, Rupee.DOURADO));
            
            //LEVEL CONFIGURATIONS
            if(livelloCorrente == 1){
                getEnemy().setX(512);
                getEnemy().setY(192);
                getEnemy2().setAlive(false);
                getEnemy3().setAlive(false);
                getEnemy4().setAlive(false);
                getEnemy5().setAlive(false);
            }else if(livelloCorrente == 2){
                     getEnemy().setX(704);
                     getEnemy().setY(256);
                     getEnemy2().setX(640);
                     getEnemy2().setY(256);
                     getEnemy3().setAlive(false);
                     getEnemy4().setAlive(false);
                     getEnemy5().setAlive(false);
                 }
                 else if(livelloCorrente == 3){
                     getEnemy().setX(256);
                     getEnemy().setY(128);
                     getEnemy2().setX(512);
                     getEnemy2().setY(320);
                     getEnemy3().setX(448);
                     getEnemy3().setY(320);
                     getEnemy4().setAlive(false);
                     getEnemy5().setAlive(false);
                 }
                 else if(livelloCorrente == 4){
                     getEnemy().setX(192);
                     getEnemy().setY(128);
                     getEnemy2().setX(512);
                     getEnemy2().setY(256);
                     getEnemy3().setX(704);
                     getEnemy3().setY(256);
                     getEnemy4().setAlive(false);
                     getEnemy5().setAlive(false);
                 }
                 else if(livelloCorrente == 5){
                     getEnemy().setX(128);
                     getEnemy().setY(128);
                     getEnemy2().setX(512);
                     getEnemy2().setY(192);
                     getEnemy3().setX(704);
                     getEnemy3().setY(320);
                     getEnemy4().setAlive(false);
                     getEnemy5().setAlive(false);
                 }
                 else if(livelloCorrente == 6){
                     getEnemy().setX(320);
                     getEnemy().setY(192);
                     getEnemy2().setX(320);
                     getEnemy2().setY(320);
                     getEnemy3().setX(704);
                     getEnemy3().setY(192);
                     getEnemy4().setX(512);
                     getEnemy4().setY(320);
                     getEnemy5().setAlive(false);
                 }
                 else if(livelloCorrente == 7){ //problema
                     getEnemy().setX(192);
                     getEnemy().setY(128);
                     getEnemy2().setX(640);
                     getEnemy2().setY(128);
                     getEnemy3().setX(576);//problem
                     getEnemy3().setY(192);
                     getEnemy4().setX(704);
                     getEnemy4().setY(320);
                     getEnemy5().setAlive(false);
                 }
                 else if(livelloCorrente == 8){
                     getEnemy().setX(256);
                     getEnemy().setY(128);
                     getEnemy2().setX(640);
                     getEnemy2().setY(128);
                     getEnemy3().setX(640);
                     getEnemy3().setY(256);
                     getEnemy4().setX(704);
                     getEnemy4().setY(192);
                     getEnemy5().setX(448);
                     getEnemy5().setY(256);
                 }
                 else if(livelloCorrente == 9){
                     getEnemy().setX(128);
                     getEnemy().setY(192);
                     getEnemy2().setX(320);
                     getEnemy2().setY(256);
                     getEnemy3().setX(448);
                     getEnemy3().setY(192);
                     getEnemy4().setX(640);
                     getEnemy4().setY(192);
                     getEnemy5().setX(640);
                     getEnemy5().setY(320);
                 }
                 else if(livelloCorrente == 10){
                     getEnemy().setX(192);
                     getEnemy().setY(128);
                     getEnemy2().setX(320);
                     getEnemy2().setY(320);
                     getEnemy3().setX(512);
                     getEnemy3().setY(256);
                     getEnemy4().setX(704);
                     getEnemy4().setY(128);
                     getEnemy5().setX(640);
                     getEnemy5().setY(320);
                 }
                 else if(livelloCorrente == 11){
                     getEnemy().setX(256);
                     getEnemy().setY(128);
                     getEnemy2().setX(640);
                     getEnemy2().setY(128);
                     getEnemy3().setX(384);
                     getEnemy3().setY(256);
                     getEnemy4().setX(704);
                     getEnemy4().setY(320);
                     getEnemy5().setX(512);
                     getEnemy5().setY(192);
                 }
                 else if(livelloCorrente == 12){
                     getEnemy().setX(128);
                     getEnemy().setY(128);
                     getEnemy2().setX(256);
                     getEnemy2().setY(192);
                     getEnemy3().setX(512);
                     getEnemy3().setY(128);
                     getEnemy4().setX(704);
                     getEnemy4().setY(256);
                     getEnemy5().setX(640);
                     getEnemy5().setY(320);
                 }
                 else if(livelloCorrente == 13){ 
                     getEnemy().setX(384);
                     getEnemy().setY(320);
                     getEnemy2().setX(192);
                     getEnemy2().setY(128);
                     getEnemy3().setX(640);
                     getEnemy3().setY(320);
                     getEnemy4().setX(256);
                     getEnemy4().setY(192);
                     getEnemy5().setX(64);
                     getEnemy5().setY(192);
                 }
                 else if(livelloCorrente == 14){
                     getEnemy().setX(256);
                     getEnemy().setY(128);
                     getEnemy2().setX(448);//problema
                     getEnemy2().setY(256);
                     getEnemy3().setX(640);
                     getEnemy3().setY(192);
                     getEnemy4().setX(640);
                     getEnemy4().setY(192);//problema
                     getEnemy5().setX(640);
                     getEnemy5().setY(320);
                 }
                 else if(livelloCorrente == 15){
                     getEnemy().setAlive(false);
                     getEnemy2().setAlive(false);
                     getEnemy3().setAlive(false);
                     getEnemy4().setAlive(false);
                     // GANON
                     getEnemy5().setX(512);
                     getEnemy5().setY(128);//problema
                 }

                 else if(livelloCorrente == 16){
                     getEnemy().setX(256);
                     getEnemy().setY(320);
                     getEnemy2().setX(704);
                     getEnemy2().setY(320);
                     getEnemy3().setX(384);
                     getEnemy3().setY(128);
                     getEnemy4().setX(640);
                     getEnemy4().setY(128);                   
                     // BOSS
                     getEnemy5().setX(512);
                     getEnemy5().setY(192);
                 }
                 // DIRECTIONS
                 getEnemy2().setDirezione(Enemy.Direzione.EST);
                 getEnemy3().setDirezione(Enemy.Direzione.SUD);
                 getEnemy4().scegliDirezioneCasuale();
                 getEnemy5().scegliDirezioneCasuale();
             }
        /**
         * loads enemy position from entity file
         */
        	private boolean loadEnemiesFromFile() {
        	    String path = "resources/levels/lvl" + livelloCorrente + "_entities.txt";
        	    File file = new File(path);
        	    if (!file.exists()) {
        	        return false;
        	    }

        	    enemies.clear();//clear enemy list
        	    for (int i = 0; i < 5; i++) {
        	    	int tipo = Rupee.ROSA;
        	    	if(i == 1) {
        	            tipo = Rupee.AZUL;
        	        }
        	        else if(i == 2) {
        	            tipo = Rupee.VERMELHO;
        	        }
        	        else if(i == 3) {
        	            tipo = Rupee.ROXO;
        	        }
        	        else if(i == 4) {
        	            tipo = Rupee.DOURADO;
        	        }
        	    	Enemy e = new Enemy(0, 0, tipo);
        	        e.setAlive(false);
        	        enemies.add(e);
        	    }
        	    try (Scanner sc = new Scanner(file)) {
        	        int i = 0;
        	        while (sc.hasNextLine() && i < 5) {
        	            String line = sc.nextLine();
        	            String[] parts = line.split(","); // Splits entity information
        	            if (parts[0].equals("ENEMY")) {
        	                enemies.get(i).setX(Integer.parseInt(parts[1]));//enemy position
        	                enemies.get(i).setY(Integer.parseInt(parts[2]));
        	                enemies.get(i).setAlive(true);//activate enemy
        	                i++;
        	            }
        	        }
        	        return true;
        	    } catch (Exception e) {
        	        return false; 
        	    }
        	}
        	/**
        	 * load the next level
        	 */
      //23/05 LIVELLI
        public void lvlSuccessivo() {addWins();
            if (livelloCorrente < 16) {
                livelloCorrente++;
                livelloAttuale = new Level(livelloCorrente);  // Loads new level map
                // Resets player position
                player.setX(128); 
                player.setY(320);
                //make it appears new enemies
                spawnEnemies();
                //remove rupees on the ground
                rupeesOnGround.clear(); 
                
                System.out.println("Livello " + livelloCorrente);
            } else {
                gameOver = true; 
                System.out.println("THE END!");
            }
        }
        /**
         * reset
         */
         
        //RESET 
        public void resetGame() {
        	addGiocate();//add played match
        	gameOver = false;
            score = 0;
            livelloCorrente = 1;// this must be before the spawn enemies because if not it will create all the enemies not respecting the number per level
            livelloAttuale = new Level(1);
        	player = new Player(128,320);
            spawnEnemies();
            AudioManager.getInstance().playMusica("resources/03.-Dungeon-Theme_1.wav");//restarts music
    }
        /**
         * checks collision with map obstacles
         */
    //OSTACOLI TT
    public boolean touchObstacle(Rectangle bounds) {
        int step = 8;// Collision precision step
     // Scans hitbox area
        for (int x = bounds.x; x < bounds.x + bounds.width; x += step) {
        for (int y = bounds.y; y < bounds.y + bounds.height; y += step) {
        	 // Detects obstacle collision
        	if (livelloAttuale.presenzaOstacolo(x, y)) {
                    return true;
                }
            }
        }

        return false;
    }
    /**
     * return the rupees on the map
     */
   
        //RUPIE (mariana)
    public List<Rupee> getRupeesOnGround() {
	   return rupeesOnGround;
    }
    /**
     * add the score to the points
     */
    // score system
    public void addScore(int points) { score += points; }
    /**
     * add number the rupees*
     */
    public void addRupees(int value) {
        rupees += value;
    }
    /**
     * return player score
     */
    public int getScore() { return score; }
    /**
     * return current level
     */
    public int getLivelloCorrente() {
        return livelloCorrente;
    }
    /**
     * return current rupee
     */
     
    public int getRupees() {
        return rupees;
    }
    /**
     * THIS IS FOR THE DIFFERENT RUPEES FOR DIFFERENT ENEMIES
     */
    public int getTipoRupee() {
        return tipoRupee;
    }
    /**
     * advances level manually after killing all the enemies and going to the door
     */
    public void nextLevel() {
        livelloCorrente++;
    }
   /**
    * main logic update method
    */
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
    	    if(!rupee.iscoletada()&&player.getBounds().intersects(rupee.getBounds())) {
    	    	rupee.coletada();
    	    	addRupees(rupee.getValor());
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
        /**
         * controls the combat and collision detection
         */
        private void checkCollision() {
        	colliding = false;
        	//PLAYER ATTACK SYSTEM
        //colliding controls (MARIANA UPDATE 15/5)
        //COMBAT, the player attacks the enemies
        	if (player.isStaAttaccando()) {
                if (getEnemy().isAlive() && player.getAttackBounds().intersects(getEnemy().getBounds())) {
                    getEnemy().subisciDanno(spada ? 2 : 1);//sword damage
                    if (getEnemy().nemicoMorto()) {
                        addScore(50); 
                        rupeesOnGround.add(new Rupee(getEnemy().getX(), getEnemy().getY(),getEnemy().getTipoRupee()));
                    }
                }
                if (getEnemy2().isAlive() && player.getAttackBounds().intersects(getEnemy2().getBounds())) {
                    getEnemy2().subisciDanno(spada ? 2 : 1); 
                    if (getEnemy2().nemicoMorto()) {
                        addScore(100);
                        rupeesOnGround.add(new Rupee(getEnemy2().getX() + 40, getEnemy2().getY() + 20,getEnemy2().getTipoRupee()));
                    }
                }
                if (getEnemy3().isAlive() && player.getAttackBounds().intersects(getEnemy3().getBounds())) {
                    getEnemy3().subisciDanno(spada ? 2 : 1);
                    if (getEnemy3().nemicoMorto()) {
                        addScore(150);
                        rupeesOnGround.add(new Rupee(getEnemy3().getX(), getEnemy3().getY(),getEnemy3().getTipoRupee()));
                    }
                }
                if (getEnemy4().isAlive() && player.getAttackBounds().intersects(getEnemy4().getBounds())) {
                    getEnemy4().subisciDanno(spada ? 2 : 1);
                    if (getEnemy4().nemicoMorto()) {
                        addScore(200);
                        rupeesOnGround.add(new Rupee(getEnemy4().getX(), getEnemy4().getY(),getEnemy4().getTipoRupee()));
                    }
                }
                if (getEnemy5().isAlive() && player.getAttackBounds().intersects(getEnemy5().getBounds())) {
                    getEnemy5().subisciDanno(spada ? 2 : 1);
                    if (getEnemy5().nemicoMorto()) {
                        addScore(250);
                        rupeesOnGround.add(new Rupee(getEnemy5().getX(), getEnemy5().getY(),getEnemy5().getTipoRupee())); 
                    }
                }
            }
        //	player DAMAGE COLLISION
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

/**
 * applies the damage to the player, that is, it loses life
 */
    public void loseLife() {
    	if(scudo) {//shield aborbs damage
    		scudo = false;//after the hit removes the shield
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
    
      /**
       * returns enemy1
       */
     
     
    public Enemy getEnemy()  { return enemies.get(0); }
    /**returns enemy2
     */
    public Enemy getEnemy2() { return enemies.get(1); }
 /**
  * returns enemy3
  */
    public Enemy getEnemy3() { return enemies.get(2); }
    /**
     * returns enemy4
     */
    public Enemy getEnemy4() { return enemies.get(3); }
    /**
     * returns enemy5
     */
    public Enemy getEnemy5() { return enemies.get(4); }
   /**
    * returns the enemy list
    */ 
    public List<Enemy> getEnemiesList() { return enemies; }
    
    
    /**
     * changes the player nickname
     */
    public void setNickname(String nickname) {
       this.nickname = nickname;
    }
    /**
     * returns player nickname
     */
    public String getNickname() {
        return nickname;
    }
    /**
     * adds an observer
     */
    public void addObserver(GameObserver observer) {
    	// Recreates observer list after loading save
    	if (this.observers == null) {
            this.observers = new java.util.ArrayList<>();
        }
        observers.add(observer);
    }//GameObserve allows that GameState notify the HUD and the others objects when some information changes.
    /**
     * Updates all observers.
     */
    public void notifyObservers() {
        for(GameObserver observer: observers) {
            observer.update();
        }
    }
    // SHOP (MARIANA)
    /**
     * Returns player.
     */
    public Player getPlayer() { return player; }
    /**
     * return scudo
     */
    public boolean scudo() { return scudo; }
    /**
     * return spada
     */
    public boolean spada() { return spada; }
    /**
     * return portion
     */
    public void buyPotion() {
    	if(rupees >= 25) {
    		rupees -= 5;
    		player.setPuntiVita(player.getPuntiVita() + 1);
    		System.out.println(player.getPuntiVita());
    	}
    }//doesnt need private boolean because it used imediately
   /**
    * buy scudo
    */
    public void buyscudo() {
        if(rupees >= 50) {
            rupees -= 10;
            scudo = true;
        }
    }
    /**
     * buy spada
     */
    public void buyspada() {
        if(rupees >= 100) {
            rupees -= 15;
            spada = true;
            player.setHasSword(true);
        }
    }
    
//github.com/marifrota/jzelda-project.git
    //GETTERS
    /**
     * returns current level map
     */
    public Level getLivelloAttuale() { return livelloAttuale; }
    /**
     * returns game over
     */
    public boolean isGameOver() { return gameOver; }
   /**
    * 
    * returns collision
    * */
    public boolean isColliding() { return colliding; } //MARIANA UPDATE  15/5
    /**
     * 
     * return total losses
     */
    public int getLosses() { return losses; }
    /**
     * return total wins
     */
    public int getWins() { return wins; }
    /**
     * Returns partite giocate
     */
    public int getGiocate() { return giocate;}
    /**
     * Adds partite giocate registered.
     */
    public void addGiocate() { giocate++;}
    /**
     * Adds win registered.
     */
    public void addWins() { wins++; }
    /** 
     * Adds losses registered
     */
    public void addLosses() {  losses++; }
    
    private int usedProfile = 0;

    public void setProfiloAttivo(int usedProfile) {
        this.usedProfile = usedProfile;
    }
    public int getUsedProfile() {
        return this.usedProfile;
    }
    
    public void setUsedProfile(int usedProfile) {
    	 this.usedProfile = usedProfile;
    }
    
 
}