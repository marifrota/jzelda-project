package controller;

import model.GameState;
import view.GamePanel;

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
    	if (inputHandler.su) gameState.getPlayer().muoviSu();
        if (inputHandler.giu) gameState.getPlayer().muoviGiu();
        if (inputHandler.sinistra) gameState.getPlayer().muoviSinistra();
        if (inputHandler.destra) gameState.getPlayer().muoviDestra();
        if (inputHandler.attacco) {
        	gameState.getPlayer().setStaAttaccando(true);
        } else {
        	gameState.getPlayer().setStaAttaccando(false);
        }//MARIAN UPDATE 15/5
        gameState.update(); 
        if(inputHandler.attacco && gameState.getEnemy().isAlive() && gameState.getPlayer().getBounds().intersects(gameState.getEnemy().getBounds())) { 
        	gameState.getEnemy().setAlive(false);
        	gameState.addScore(100);
        }
    }

    private void render() {
        //Asks Swing to redimension the panel 
        gamePanel.repaint();
    }
}