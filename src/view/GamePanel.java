package view;
import javax.swing.JPanel;
import java.awt.Graphics;


public class GamePanel extends JPanel{

	private int score = 0;
	int vite = 3;
	private HUDPanel hud;
	
	public GamePanel() {
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
	g.fillRect(50,50,50,50);//quadrado que funciona como um "fake player"(Square that works as a "fake player")	
	}
	
	private void drawEnemy(Graphics g) {
	}
	
	private void drawMap(Graphics g) {
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
