package view;
import javax.swing.JPanel;
import java.awt.Graphics;


public class GamePanel extends JPanel{

	private int score = 0;
	private HUDPanel hud;
	
	public GamePanel() {
		hud = new HUDPanel();
		add(hud);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawString("Gioco iniziato!",100,100);//retangulo com a frase no meio(Rectangle with centered text)
		g.fillRect(50,50,50,50);//quadrado que funciona como um "fake player"(Square that works as a "fake player")
	}
	
	
	public void aumentapunteggio(int xscore) {
		score += xscore;
		hud.aumentapunteggio(score);
		repaint();
	}
}
