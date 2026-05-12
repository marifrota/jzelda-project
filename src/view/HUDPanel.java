package view;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class HUDPanel extends JPanel{
	
	private JLabel scoreLabel;
	private JLabel livesLabel;

	public HUDPanel() {//no inicio(at the beggingin)
		scoreLabel = new JLabel("Score: 0");
		livesLabel = new JLabel("Lives: 3");
		add(scoreLabel);
		add(livesLabel);
	}
	public void aumentapunteggio(int score) {
		scoreLabel.setText("Score: " + score);
	}
	
	public void aumentavita(int vita) {
		livesLabel.setText("Lives: " + vita);
	}
}
