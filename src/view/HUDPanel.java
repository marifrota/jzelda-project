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
		setLayout(null);
		scoreLabel = new JLabel("Score: 0");
		livesLabel = new JLabel("♥ ♥ ♥");
		rupeesLabel = new JLabel("Rupees: 0");
		nicknameLabel = new JLabel("Player: ");
		add(nicknameLabel);
		add(scoreLabel);
		add(livesLabel);
		add(rupeesLabel);
		
		Font hudFont = new Font("Arial", Font.BOLD, 20);
		nicknameLabel.setFont(hudFont);
		rupeesLabel.setFont(hudFont);
		rupeesLabel.setForeground(new Color(255,120,255));
		nicknameLabel.setForeground(Color.WHITE);
		scoreLabel.setFont(hudFont);
		livesLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));;
		setOpaque(false);
		scoreLabel.setForeground(new Color(255,215,0));
		livesLabel.setForeground(new Color(220,40,40));
		
		nicknameLabel.setBounds(220, 0, 200, 30);
		scoreLabel.setBounds(420, 0, 150, 30);
		livesLabel.setBounds(580, 0, 150, 30);
		rupeesLabel.setBounds(760, 0, 150, 30);
	}
	
	public void setNickname(String nickname) {
	    nicknameLabel.setText("Player: " + nickname);
	}
	
	public void aumentapunteggio(int score) {
		scoreLabel.setText("Score: " + score);
	}
	
	public void aumentavita(int vita) {
	    livesLabel.setText("♥ ".repeat(vita));
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
