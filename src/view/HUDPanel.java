package view;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class HUDPanel extends JPanel{
	
	private JLabel scoreLabel;

	public HUDPanel() {//no inicio(at the beggingin)
		scoreLabel = new JLabel("Score: 0");
		add(scoreLabel);
	}
	public void aumentapunteggio(int score) {
		scoreLabel.setText("Score: " + score);
	}
}
