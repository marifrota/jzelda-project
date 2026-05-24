package view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

/**
 * HUD display panel,hows score, lives, rupees, wins and losses
 */
public class HUDPanel extends JPanel implements GameObserver{
	
	private JLabel scoreLabel;
	private JLabel livesLabel;
	private JLabel nicknameLabel;
	private JLabel rupeesLabel;
	private JLabel lossesLabel;
	private JLabel winsLabel;
	private int giocate;
	private JLabel giocateLabel;
	/**
	 * Creates the HUD interface and labels.
	 */
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
		//the fonts of the words on the screen of the game
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
		//it draws on the screen the elements: nickname, losses, wins, partite.
		nicknameLabel.setBounds(140, 0, 180, 30);
		scoreLabel.setBounds(320, 0, 130, 30);
		livesLabel.setBounds(470, 0, 120, 30);
		rupeesLabel.setBounds(600, 0, 130, 30);
		lossesLabel = new JLabel("Defeats: 0");
		add(lossesLabel);
		lossesLabel.setFont(hudFont);
		lossesLabel.setForeground(new Color(220,70,70));
		lossesLabel.setBounds(730, 0, 120, 30);
		winsLabel = new JLabel("Wins: 0");
		add(winsLabel);
		winsLabel.setFont(hudFont);
		winsLabel.setForeground(new Color(80,220,120));
		winsLabel.setBounds(850, 0, 110, 30);
		giocateLabel = new JLabel("Partite: 0");
		add(giocateLabel);
		giocateLabel.setFont(hudFont);
		giocateLabel.setForeground(new Color(120,200,255));
		giocateLabel.setBounds(850, 25, 150, 30);
	}
	/**
	 * Sets the player's nickname.
	 */
	public void setNickname(String nickname) {
	    nicknameLabel.setText("Player: " + nickname);
	}
	/**
	 * update the score
	 */
	public void aumentapunteggio(int score) {
		scoreLabel.setText("Score: " + score);
	}
	/**
	 * update the partite giocata
	 */
	public void aumentaGiocate(int giocate) {
	    this.giocate = giocate;
	    giocateLabel.setText("Partite: " + giocate);
	}
	/**
	 * update the life
	 */
	public void aumentavita(int vita) {
	    livesLabel.setText("♥ ".repeat(vita));
	}
	/**
	 * update the wins
	 */
	public void aumentaWins(int wins) {
	    winsLabel.setText("Wins: " + wins);
	}
	/**
	 * update the rupee
	 */
	public void aumentaRupees(int rupees) {
	    rupeesLabel.setText("Rupees: " + rupees);
	}
	/**
	 * update the lost
	 */
	public void aumentaLosses(int losses) {
	    lossesLabel.setText("Losses: " + losses);
	}
	/**
	 * Updates the HUD observer.
	 */
	public void update() {System.out.println("HUD updated!");
	}
}
