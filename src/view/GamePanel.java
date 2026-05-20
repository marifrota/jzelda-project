package view;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import model.GameState;
import model.Player;
import model.Enemy;
import java.awt.Graphics2D;
import java.awt.Font;


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
	
	private void drawAttackEffect(Graphics g) {
		Player player = gameState.getPlayer();
		if(player.isStaAttaccando()) {
			g.setColor(Color.YELLOW);
			g.fillRect(player.getX()+32, player.getY(), 20,20);
		}
	}
	
	private void drawCOLLISSION(Graphics g) {
		if(gameState.isColliding()) {
			g.setColor(Color.WHITE);
			g.drawString("COLLISION!", 350,50);
		}
	}
	
	private void drawGameOver(Graphics g) {
		if(gameState.isGameOver()) {
			g.setColor(new Color(0,0,0,180));
			g.fillRect(0,0,getWidth(),getHeight());
			g.setFont(new Font("Arial", Font.BOLD, 60));
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER",295,225);
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 60));
			g.drawString("GAME OVER", 290, 220);
			g.setFont( new Font("Arial", Font.PLAIN, 26));
			g.setColor(Color.WHITE);
			g.drawString("Press R to Try Again", 290, 340);
			g.drawString("Press ESC to Exit", 290, 390);
			g.setColor(Color.YELLOW);
			g.drawString("Press R to Try Again", 291, 341);
			g.drawString("Press ESC to Exit", 291, 391);
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawMap(g);
		drawPlayer(g);
		drawEnemy(g);
		drawAttackEffect(g);
		drawGameOver(g);
		
		if(!gameState.isGameOver()) {
			if(gameState.isColliding()) { // tua amie was here!
				g.drawString("COLLISION!", 200, 200);
			}
			
			if(gameState.getPlayer().isStaAttaccando()) {
				g.drawString("ATTACCO!!!", 250,250);
			}
		}
		drawGameOver(g);
		hud.aumentapunteggio(gameState.getScore());
		hud.aumentavita(gameState.getPlayer().getPuntiVita());
	}
	
	private void drawPlayer(Graphics g) {
		g.setColor(Color.BLACK);
		Player player = gameState.getPlayer();
		g.fillOval(player.getX()-2,player.getY()-2,36,36);
		g.setColor(new Color(220,20,60));
		g.fillOval(player.getX(),player.getY(), 32, 32);
	}
	
	private void drawEnemy(Graphics g) {
		if(gameState.getEnemy().isAlive()) {
			g.setColor(Color.BLACK);
			Enemy enemy = gameState.getEnemy();
			g.fillRect(enemy.getX(),enemy.getY(),32,32);//(Square that works as a "fake enemy")	
		}
		
		if(gameState.getEnemy2().isAlive()) {
			g.setColor(Color.BLUE);
			Enemy enemy2 = gameState.getEnemy2();
			g.fillRect(enemy2.getX(),enemy2.getY(),32,32);// 15/05 Meryem has been here! drawing for the second enemy	
		
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
