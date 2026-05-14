package view;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import model.GameState;
import model.Player;
import model.Enemy;

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
	}
	
	private void drawPlayer(Graphics g) {
		g.setColor(Color.RED);
		Player player = gameState.getPlayer();
		g.fillRect(player.getX(),player.getY(),32,32);//(Square that works as a "fake player")	
	}
	
	private void drawEnemy(Graphics g) {
		g.setColor(Color.BLACK);
		Enemy enemy = gameState.getEnemy();
		g.fillRect(enemy.getX(),enemy.getY(),32,32);//(Square that works as a "fake enemy")	
	}
	
	private void drawMap(Graphics g) {
		g.setColor(new Color(34,139,34));
		// Dark green color (little red, lots of green, little blue)
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(139, 69, 19));
		g.fillRect(0, 0, getWidth(), 50);
		// Draws a wall at the top
		// (0,0) = starts at the top left corner
		// getWidth() = full screen width
		// 50 = height of 50 pixels (wall thickness)
		g.fillRect(0,  getHeight()-50, getWidth(), 50);
		g.fillRect(getWidth()-50, 0, 50, getHeight());
		g.fillRect(0, 0, 50, getHeight());
		g.setColor(new Color(70, 130, 200));
		g.fillRect(200, 200, 60, 60);
	}
	
	public void aumentapunteggio(int xscore) {
		score += xscore;
		hud.aumentapunteggio(score);
		repaint();
	}
	
	public void aumentavita( int vita) {
		vite += vita;
		hud.aumentavita(vita);
		repaint();
	}
}
