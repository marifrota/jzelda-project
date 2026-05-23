package controller;

import model.GameState;
import view.GamePanel;
import view.AudioManager;
import model.Player;
import java.awt.Rectangle;

public class GameController implements Runnable {

    private GameState gameState;
    private GamePanel gamePanel;
    private Thread gameThread;
    private InputHandler inputHandler;
    private boolean isRunning = false;

    // 60 should be standard
    private static final int FPS = 60;

    public GameController(GameState gameState, GamePanel gamePanel, InputHandler inputHandler) {
        this.gameState = gameState;
        this.gamePanel = gamePanel;
        this.inputHandler = inputHandler;
        
        AudioManager.getInstance().playMusica("resources/03.-Dungeon-Theme_1.wav");
    }
    // to start game engine 
    public void start() {
        if (!isRunning) {
            isRunning = true;
            gameThread = new Thread(this);
            gameThread.start(); // to start run method()
        }
    }

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
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; 
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
    	if(inputHandler.exit) {
            System.exit(0);
        }
    	//EXIT
    	if(inputHandler.retry && gameState.isGameOver()) {
    		gameState.resetGame();
    	} 
    	//TRY AGAIN
    	if(!gameState.isGameOver()) {
 
   Player player = gameState.getPlayer(); //22/05 change to debug
   
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
	            gameState.getPlayer().startAttack();
	        }
	        gameState.getPlayer().update();
	        gameState.update(); 
    	}
	    }
    

    private void render() {
        //Asks Swing to redimension the panel 
        gamePanel.repaint();
    }
}
