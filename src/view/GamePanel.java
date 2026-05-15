package view;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import model.GameState;
import model.Player;
import model.Enemy;
import java.awt.Graphics2D;

public class GamePanel extends JPanel{
	
	private GameState gameState;
	private int score = 0;
	int vite = 3;
	private HUDPanel hud;
	
	public GamePanel(GameState gameState) {
		this.gameState = gameState;
		hud = new HUDPanel();
		add(hud);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawString("Gioco iniziato!",100,100);//retangulo com a frase no meio(Rectangle with centered text)
		drawMap(g);
		drawPlayer(g);
		drawEnemy(g);
		
		if(gameState.isCollision()) {
			g.drawString("Collision!", 200, 200);
		}
		
		if(gameState.isGameOver()) {
			g.drawString("GAME OVER!", 300, 300);
		}
		
		if(gameState.getPlayer().isStaAttaccando()) {
			g.drawString("ATTACCO!!!", 250,250);
		}
		hud.aumentapunteggio(gameState.getScore());

	}
	
	private void drawPlayer(Graphics g) {
		g.setColor(Color.RED);
		Player player = gameState.getPlayer();
		g.fillRect(player.getX(),player.getY(),32,32);//(Square that works as a "fake player")	
	}
	
	private void drawEnemy(Graphics g) {
		if(gameState.getEnemy().isAlive()) {
			g.setColor(Color.BLACK);
			Enemy enemy = gameState.getEnemy();
			g.fillRect(enemy.getX(),enemy.getY(),32,32);//(Square that works as a "fake enemy")	
		}
	}
	
	private void drawMap(Graphics g) {
	    gameState.getLivelloAttuale().render((Graphics2D) g);
	}
	
	public void aumentapunteggio(int xscore) {
		score += xscore;
		hud.aumentapunteggio(score);
		repaint();
	}
	
	public void aumentavita( int vita) {
		vite += vita;
		hud.aumentavita(vite);
		repaint();
	}
}
