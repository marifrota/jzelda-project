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
import model.Rupee;
import javax.swing.JButton;

public class GamePanel extends JPanel implements GameObserver{
	private JButton retryButton;
	private JButton exitButton;
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
	private Image moeda;
	private boolean shopOpen = false;
	private Image porcao;
	private Image espada;
	private Image escudo;
	private Image ganon;
	private Image gohma;
	private Image stalfos;	
	private Image gibo;
	
	public void setGameState(GameState gameState) {
	    this.gameState = gameState;
	}
	/**Creates the main game panel and loads, sprites, HUD, buttons and game resources.
	 */
	public GamePanel(GameState gameState) {
		this.gameState = gameState;
		setLayout(null);
		hud = new HUDPanel();
		hud.setNickname(gameState.getNickname());
		add(hud);
		hud.setBounds(0, 0, 975, 50);//define a posição e o tamanho do HUD para ele voltar a aparecer quando  usa setLayout(null)( the HUD position and size so it becomes visible again when using setLayout(null).
		enemyMoblinSprite = new ImageIcon("resources/sprites/enemy_moblin (2)_ORANGE.png").getImage();
		enemyOctorokSprite = new ImageIcon("resources/sprites/OCTOROK_BLUE.png").getImage();
		enemyLinelSprite = new ImageIcon("resources/sprites/LINEL_ENEMY_BLUE.png").getImage();
		zorablue = new ImageIcon("resources/sprites/ZORA_BLUE.png").getImage();
		tektiteblue = new ImageIcon("resources/sprites/TEKTITE_BLUE.png").getImage();
		linelblue = new ImageIcon("resources/sprites/LINEL_BLUE.png").getImage();
		oktorokblue = new ImageIcon("resources/sprites/OCTOROK_BLUE.png").getImage();
		playerzelda = new ImageIcon("resources/sprites/PLAYER (1).png").getImage();
		moeda = new ImageIcon("resources/sprites/MOEDAS (1).png").getImage();
		escudo = new ImageIcon("resources/sprites/ESCUDO.png").getImage();
		espada = new ImageIcon("resources/sprites/SPADA.png").getImage();
		porcao = new ImageIcon("resources/sprites/PORCAO.png").getImage();
		ganon = new ImageIcon("resources/sprites/ganon (1).png").getImage();
		gohma = new ImageIcon("resources/sprites/gohma.png").getImage();
		stalfos = new ImageIcon("resources/sprites/stalfos (1).png").getImage();
		gibo = new ImageIcon("resources/sprites/gibo.png").getImage();
		
		retryButton = new JButton("TRY AGAIN");
		exitButton = new JButton("EXIT");
		retryButton.setBounds(300, 280, 180, 60);
		retryButton.setBackground(new Color(20,20,20));
		retryButton.setForeground(new Color(255,215,0));
		retryButton.setFont(new Font("Arial", Font.BOLD, 22));
		retryButton.setFocusPainted(false);
		exitButton.setBounds(500, 280, 180, 60);
		exitButton.setBackground(new Color(120,0,0));
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(new Font("Arial", Font.BOLD, 22));
		retryButton.setVisible(false);
		exitButton.setVisible(false);
		add(retryButton);
		add(exitButton);
		retryButton.addActionListener(e -> {
		    gameState.resetGame();
		    retryButton.setVisible(false);
		    exitButton.setVisible(false);
		    repaint();
		});
		exitButton.addActionListener(e -> {System.exit(0);});
	}
	/** 
	 * Draw the yellow attack effect*/
	private void drawAttackEffect(Graphics g) {
		Player player = gameState.getPlayer();
		if(player.isStaAttaccando()) {
			g.setColor(Color.YELLOW);
			g.fillOval(player.getX()+20, player.getY()-5, 30,30);
		}
	}
	/**
	 * Draw the big red string "collision" always when colide with an enemy*/
	private void drawCOLLISSION(Graphics g) {
	    if(gameState.isColliding()) {
	        g.setFont(new Font("Arial", Font.BOLD, 40));
	        g.setColor(Color.RED);
	        g.drawString("COLLISION!", 320, 220);
	    }
	}
	/**Draw GAME OVER in the center with the dark red and the gold for the shadow, also it draws the retry and exit*/
	private void drawGameOver(Graphics g) {
		if(gameState.isGameOver() && gameState.getPlayer().getPuntiVita() <= 0) {
			g.setColor(new Color(0,0,0,180));
			g.fillRect(0,0,getWidth(),getHeight());
			g.setFont(new Font("Serif", Font.BOLD, 110));
			g.setColor(new Color(255,215,0));
			g.drawString("GAME OVER",145,225);
			g.setColor(new Color(160,0,0));
			g.setFont(new Font("Serif", Font.BOLD, 110));
			g.drawString("GAME OVER", 140, 220);
			retryButton.setVisible(true);
			exitButton.setVisible(true);
		}
	}
	//24/05 level 16 finished
	private void drawGameEnded(Graphics g) {
		if(gameState.isGameOver() && gameState.getLivelloCorrente() == 16 && gameState.getPlayer().getPuntiVita() > 0) {
			g.setColor(new Color(0,0,0,180));
			g.fillRect(0,0,getWidth(),getHeight());
			
			g.setFont(new Font("Serif", Font.BOLD, 90));
			g.setColor(new Color(200, 150, 0)); 
			g.drawString("GAME ENDED", 105, 225);
			g.setColor(new Color(255, 215, 0));
			g.drawString("GAME ENDED", 100, 220);
			
			exitButton.setVisible(true); 
			retryButton.setVisible(true); 
		}
	}
	/**Draw all game elements on  the screen like map, player, elements, HUD, shop, attack effects and game over screen*/
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawMap(g);
		drawPlayer(g);
		drawEnemy(g);
		drawAttackEffect(g);
		drawGameOver(g);
		drawGameEnded(g);
		drawLevel(g);
		drawRupees(g);
		drawCOLLISSION(g);
		drawItems(g);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Press B to open SHOP",  20, 430);
		//meryem 23/05
		g.drawString("Press S to SAVE", 250, 430);
		g.drawString("Press L to LOAD", 420, 430);
		
		if(!gameState.isGameOver()) {
			if(gameState.getPlayer().isStaAttaccando()) {
				g.drawString("ATTACCO!!!", 250,250);
			}
		}/**Drawing the shop*/
		if(shopOpen) {
			g.setColor(new Color(0,0,0,220));
		    g.fillRect(200,100,500,300);
		    g.setColor(Color.WHITE);
		    g.setFont(new Font("Arial", Font.BOLD, 30));
		    g.drawString("SHOP", 360, 150);
		    g.setFont(new Font("Arial", Font.PLAIN, 22));
		    g.drawImage(porcao, 220, 200, 40, 40, null);
		    g.drawString("1 - POTION (5)", 280, 230);
		    g.drawImage(escudo, 220, 250, 40, 40, null);
		    g.drawString("2 - SHIELD (10)", 280, 280);
		    g.drawImage(espada, 220, 300, 40, 40, null);
		    g.drawString("3 - MASTER SWORD (15)", 280, 330);
		    g.drawString("3 - MASTER SWORD (15)", 280, 330);
		    g.setFont(new Font("Arial", Font.PLAIN, 16));
		    g.drawString("Press B to exit", 320, 380);
		}
		drawGameOver(g);
		hud.aumentapunteggio(gameState.getScore());
		hud.aumentavita(gameState.getPlayer().getPuntiVita());
		hud.aumentaRupees(gameState.getRupees());
		hud.aumentaLosses(gameState.getLosses());
	}
	/**Draw player*/
	private void drawPlayer(Graphics g) {
		Player player = gameState.getPlayer();
        g.drawImage(playerzelda,player.getX(),player.getY(),105,65,null);
	}
	/**Draw enemy1*/
	private void drawEnemy(Graphics g) {
	    if(gameState.getEnemy().isAlive()) {
	        Enemy enemy = gameState.getEnemy();
	        if(enemy.isHit()) {
	            g.setColor(Color.RED);
	        } else {
	            g.setColor(Color.BLACK);
	        }
	        g.drawImage(enemyMoblinSprite,enemy.getX(),enemy.getY(),105,65,null);
	    }
	    /**Draw enemy2*/
	    if(gameState.getEnemy2().isAlive()) {
	        Enemy enemy2 = gameState.getEnemy2();
	        g.drawImage(enemyOctorokSprite,enemy2.getX(),enemy2.getY(),105,65,null);
	    }
	    /**Draw enemy3*/
	    if(gameState.getEnemy3().isAlive()) {
	        Enemy enemy3 = gameState.getEnemy3();
	        g.drawImage(gibo,enemy3.getX(),enemy3.getY(),105,65,null);
	    }
	    /**Draw enemy4*/
	    if(gameState.getEnemy4().isAlive()) {
	        Enemy enemy4 = gameState.getEnemy4();
	        g.drawImage(stalfos,enemy4.getX(),enemy4.getY(),105,65,null);
	    }
	    /**Draw enemy5*/
	    if(gameState.getEnemy5().isAlive()) {
	        Enemy enemy5 = gameState.getEnemy5();
	        g.drawImage(ganon,enemy5.getX(),enemy5.getY(),105,65,null);
	    }
	}
		
	/**draw map*/
	private void drawMap(Graphics g) {
	    gameState.getLivelloAttuale().render((Graphics2D) g);
	}
	/**to gain points(aumentare punteggio)*/
	public void aumentapunteggio(int xscore) {
		score += xscore;
		hud.aumentapunteggio(score);
		repaint();
	}
	/*to gain life*/
	public void aumentavita( int vita) {
		vite += vita;
		hud.aumentavita(vite);
		repaint();
	}
	/*open the shop and also close*/
	public void setShopOpen(boolean shopOpen) {
	    this.shopOpen = shopOpen;
	}

	/**Draw the level*/
	public void drawLevel(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Level: " + gameState.getLivelloCorrente(), 20, 23);
	}
	/**
	 * Returns the current game state like the current life, score, enemys, level, rupees...
	 */
	public GameState getGameState() {
	    return gameState;
	}
	/**Draw rupie*/
	
		private void drawRupees(Graphics g) {
		    // CHANGED STRAEM
		    gameState.getRupeesOnGround().stream()
		             .filter(rupee -> !rupee.isCollected())
		             .forEach(rupee -> g.drawImage(moeda, rupee.getX(), rupee.getY(), 32, 32, null));
		}
	/*draw scudo and the sworm*/
	private void drawItems(Graphics g) {
	    if(gameState.scudo()) {
	    	g.drawImage(escudo,820,35,32,32,null);
	    }
	    if(gameState.spada()) {
	    	g.drawImage(espada,860,35,32,32,null);
	    }
	}
	@Override
    public void update() {
        repaint(); 
    }
}
