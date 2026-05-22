package view;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import model.GameState;
import model.Player;
import model.Enemy;
import java.awt.Graphics2D;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Image;

public class GamePanel extends JPanel{
	
	private GameState gameState;
	private int score = 0;
	int vite = 3;
	private HUDPanel hud;
	private Image enemyMoblinSprite;//orange
	private Image enemyOctorokSprite;//orange
	private Image enemyLinelSprite;//blue
	private Image zorablue;
	private Image tektiteblue;
	private Image linelblue;
	private Image oktorokblue;
	private Image playerzelda;

	
	public GamePanel(GameState gameState) {
		this.gameState = gameState;
		hud = new HUDPanel();
		add(hud);
		enemyMoblinSprite = new ImageIcon("resources/sprites/enemy_moblin (2)_ORANGE.png").getImage();
		enemyOctorokSprite = new ImageIcon("resources/sprites/OCTOROK_ENEMY_BLUE.png").getImage();
		enemyLinelSprite = new ImageIcon("resources/sprites/LINEL_ENEMY_BLUE.png").getImage();
		zorablue = new ImageIcon("resources/sprites/ZORA_BLUE.png").getImage();
		tektiteblue = new ImageIcon("resources/sprites/TEKTITE_BLUE.png").getImage();
		linelblue = new ImageIcon("resources/sprites/LINEL_BLUE.png").getImage();
		oktorokblue = new ImageIcon("resources/sprites/OCTOROK_BLUE.png").getImage();
		playerzelda = new ImageIcon("resources/sprites/PLAYER (1).png").getImage();
	}
	
	private void drawAttackEffect(Graphics g) {
		Player player = gameState.getPlayer();
		if(player.isStaAttaccando()) {
			g.setColor(Color.YELLOW);
			g.fillOval(player.getX()+20, player.getY()-5, 30,30);
		}
	}
	
	private void drawCOLLISSION(Graphics g) {
		if(gameState.isColliding()) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial",Font.BOLD,28));
				g.setColor(Color.WHITE);
				g.drawString("COLLISION!",220,200);		
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
		drawLevel(g);
		
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
		Player player = gameState.getPlayer();
        g.drawImage(playerzelda,player.getX(),player.getY(),105,65,null);
	}
	
	private void drawEnemy(Graphics g) {
	    if(gameState.getEnemy().isAlive()) {
	        Enemy enemy = gameState.getEnemy();
	        if(enemy.isHit()) {
	            g.setColor(Color.RED);
	        } else {
	            g.setColor(Color.BLACK);
	        }
	        g.drawImage(tektiteblue,enemy.getX(),enemy.getY(),105,65,null);
	    }

	    if(gameState.getEnemy2().isAlive()) {
	        g.setColor(Color.BLUE);
	        Enemy enemy2 = gameState.getEnemy2();
	        g.drawImage(zorablue,enemy2.getX(),enemy2.getY(),175,175,null);
	    }

	    if(gameState.getEnemy3().isAlive()) {
	        g.setColor(Color.ORANGE);
	        Enemy enemy3 = gameState.getEnemy3();
	        g.drawImage(enemyLinelSprite,enemy3.getX(),enemy3.getY(),105,65,null);
	    }

	    if(gameState.getEnemy4().isAlive()) {
	        g.setColor(Color.PINK);
	        Enemy enemy4 = gameState.getEnemy4();
	        g.drawImage(oktorokblue,enemy4.getX(),enemy4.getY(),105,65,null);

	    }

	    if(gameState.getEnemy5().isAlive()) {
	        g.setColor(Color.YELLOW);
	        Enemy enemy5 = gameState.getEnemy5();
	        g.drawImage(enemyMoblinSprite,enemy5.getX(),enemy5.getY(),105,65,null);
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
	public void drawLevel(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Level: " + gameState.getLivelloCorrente(), 20, 30);
	}
}
