package view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;


public class HUDPanel extends JPanel implements GameObserver{
	
	private JLabel scoreLabel;
	private JLabel livesLabel;
	private JLabel nicknameLabel;
	private JLabel rupeesLabel;
	
	public HUDPanel() {//no inicio(at the beggingin)
		scoreLabel = new JLabel("Score: 0");
		livesLabel = new JLabel("Lives: 3");
		rupeesLabel = new JLabel("Rupees: 0");
		nicknameLabel = new JLabel("Player: ");
		add(nicknameLabel);
		add(scoreLabel);
		add(livesLabel);
		add(rupeesLabel);
		
		Font hudFont = new Font("Arial", Font.BOLD, 20);
		nicknameLabel.setFont(hudFont);
		rupeesLabel.setFont(hudFont);
		rupeesLabel.setForeground(Color.PINK);
		nicknameLabel.setForeground(Color.YELLOW);
		scoreLabel.setFont(hudFont);
		livesLabel.setFont(hudFont);
		setOpaque(false);
		scoreLabel.setForeground(Color.YELLOW);
		livesLabel.setForeground(Color.YELLOW);
	}
	
	public void setNickname(String nickname) {
	    nicknameLabel.setText("Player: " + nickname);
	}
	
	public void aumentapunteggio(int score) {
		scoreLabel.setText("Score: " + score);
	}
	
	public void aumentavita(int vita) {
		livesLabel.setText("Lives: " + vita);
	}
	public void aumentaRupees(int rupees) {
	    rupeesLabel.setText("Rupees: " + rupees);
	}
	public void update() {
	    System.out.println(
	        "HUD updated!"
	    );
	}
}
