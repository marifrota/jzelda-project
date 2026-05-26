package controller;

import model.GameState;
import view.GamePanel;
import view.AudioManager;
import model.Player;
import java.awt.Rectangle;
/**
 * this is to control the main gamee loop, update the gamestate and renders the graphics on the screen.
 * This is also to implements runnavle to allow the game to run in a separate thread
 */
public class GameController implements Runnable {

    private GameState gameState;
    private GamePanel gamePanel;
    private Thread gameThread;
    private InputHandler inputHandler;
    private boolean isRunning = false;
/**
 * this is a target frames per second in 60 FPS for the game loop
 */
    // 60 should be standard
    private static final int FPS = 60;
/**
 * creates the game controller and it initializes the game components and music
 */
    public GameController(GameState gameState, GamePanel gamePanel, InputHandler inputHandler) {
        this.gameState = gameState;
        this.gamePanel = gamePanel;
        this.inputHandler = inputHandler;
        //starts the music
        AudioManager.getInstance().playMusica("resources/03.-Dungeon-Theme_1.wav");
    }
    /**
     * start the game
     */    
    // to start game engine 
    public void start() {
        if (!isRunning) {// it prevents that a lot of threads happens from the begginging
            isRunning = true;
            gameThread = new Thread(this);
            gameThread.start(); // to start run method()
        }
    }
    /**
     * Controls the main game loop, updates the game state
     * and renders graphics on the screen.
     * Implements Runnable to run the game in a separate thread.
     */
    @Override
    public void run() {
        // Time calculation to have 60 constant FPS
        double drawInterval = 1000000000.0 / FPS; 
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (isRunning) {
            
            //Move characters, check health and colliding
            update();

            // Ask Swing panel to show changes
            render();

            // Pause a bit the game
            try {
                double remainingTime = nextDrawTime - System.nanoTime();//calculates remaining time before next frame
                remainingTime = remainingTime / 1000000; 
                if (remainingTime < 0) {//prevents negative sleep value
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);// Pauses the thread to maintain stable FPS
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * updates the game logic like movement, collisions, attacks and game states 
     */
    private void update() {
    	this.gameState = gamePanel.getGameState();// Synchronizes local game state with the panel
    	if(inputHandler.exit) {
            System.exit(0);//close the game when pressed exit
        }
    	
    	if(inputHandler.retry && gameState.isGameOver()) {//restart the game after gameover
            gameState.resetGame();
            gamePanel.resetUILoaded();
            AudioManager.getInstance().playMusica("resources/03.-Dungeon-Theme_1.wav");
        }
    	//EXIT
    	
    	//TRY AGAIN
    	if(!gameState.isGameOver()) { // Updates game when the game over is false
 
   Player player = gameState.getPlayer(); //22/05 change to debug, // Gets the current player object
   
//MOVEMENT

   // up
   if (inputHandler.su) {
       int nextY = player.getY() - player.getVelocita();
       Rectangle future = new Rectangle(player.getX() + 8, nextY + 8, 48, 48);

       if (!gameState.touchObstacle(future)) {
           player.muoviSu();
       } else {
           player.setDirezione(Player.Direzione.NORD);
       }
   }

   // down
   else if (inputHandler.giu) {
       int nextY = player.getY() + player.getVelocita();
       Rectangle future = new Rectangle(player.getX() + 8, nextY + 8, 48,48);
       if (!gameState.touchObstacle(future)) {
           player.muoviGiu();
       } else {
           player.setDirezione(Player.Direzione.SUD);
       }
   }

   // left
   else if (inputHandler.sinistra) {
       int nextX = player.getX() - player.getVelocita();
       Rectangle future = new Rectangle(nextX +8,player.getY() +8, 48,48);

       if (!gameState.touchObstacle(future)) {
           player.muoviSinistra();
       } else {
           player.setDirezione(Player.Direzione.OVEST);
       }
   }

   // right
   else if (inputHandler.destra) {
       int nextX = player.getX() + player.getVelocita();
       
       Rectangle future = new Rectangle( nextX + 8, player.getY() + 8, 48, 48);
       if (!gameState.touchObstacle(future)) {
           player.muoviDestra();
       } else {
           player.setDirezione(Player.Direzione.EST);
       }
   }
    	//MARIANA UPDATE 15/5
	      //  if(inputHandler.attacco && gameState.getEnemy().isAlive() && gameState.getPlayer().getBounds().intersects(gameState.getEnemy().getBounds())) { 
	        //	gameState.getEnemy().setHit(true);
	        //	gameState.getEnemy().setAlive(false);
	        //	gameState.addScore(100);
    		//Attack
	        if (inputHandler.attacco) {
	            gameState.getPlayer().startAttack();// Starts sword attack 
	        }
	        gameState.getPlayer().update();
	        gameState.update(); // Updates enemies, collisions and game systems
    	}
	    }
    /**
     * overides main game state reference
     */
    public void setGameState(GameState state) {
        this.gameState = state;
    }   
/**
 * this request the screen to repaint and display the updated game graphics screen
 */
    private void render() {
        //Asks Swing to redimension the panel 
        gamePanel.repaint(); // Requests Swing to redraw the screen
    }
}
