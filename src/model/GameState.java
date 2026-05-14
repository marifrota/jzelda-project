package model;


public class GameState {
    
    // World's elements
    private Player player;
    private Level livelloAttuale;
    private boolean gameOver = false;

    //construct
    public GameState() {
        // PLayer spawns in the middle of the screen
        player = new Player(200, 200);
        
        // creating level (with map and enemies)
        livelloAttuale = new Level();
    }

    // the total brain
    public void update() {
        if (gameOver) { 
        	return; // so we stop when game over
        }

      
        

        // 2) checks if player is alive or not
        if (player.getPuntiVita() <= 0) {
            gameOver = true;
        }
        
        // 3) colliding controls
    }

    // 4. GETTERS 
    public Player getPlayer() { return player; }
    public Level getLivelloAttuale() { return livelloAttuale; }
    public boolean isGameOver() { return gameOver; }
}