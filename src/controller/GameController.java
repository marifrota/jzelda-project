package controller;

import model.GameState;
import view.GamePanel;

public class GameController implements Runnable {

    private GameState gameState;
    private GamePanel gamePanel;
    private Thread gameThread;
    private boolean isRunning = false;

    // 60 should be standard
    private static final int FPS = 60;

    public GameController(GameState gameState, GamePanel gamePanel) {
        this.gameState = gameState;
        this.gamePanel = gamePanel;
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
        gameState.update();
    }

    private void render() {
        //Asks Swing to redimension the panel 
        gamePanel.repaint();
    }
}