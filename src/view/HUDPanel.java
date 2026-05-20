package view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;


public class HUDPanel extends JPanel implements GameObserver{
	
	private JLabel scoreLabel;
	private JLabel livesLabel;

	public HUDPanel() {//no inicio(at the beggingin)
		scoreLabel = new JLabel("Score: 0");
		livesLabel = new JLabel("Lives: 3");
		add(scoreLabel);
		add(livesLabel);
		
		Font hudFont = new Font("Arial", Font.BOLD, 20);
		
		scoreLabel.setFont(hudFont);
		livesLabel.setFont(hudFont);
		setOpaque(false);
		scoreLabel.setForeground(Color.YELLOW);
		livesLabel.setForeground(Color.YELLOW);
	}
	public void aumentapunteggio(int score) {
		scoreLabel.setText("Score: " + score);
	}
	
	public void aumentavita(int vita) {
		livesLabel.setText("Lives: " + vita);
	}
	
	public void update() {
	    System.out.println(
	        "HUD updated!"
	    );
	}
}
