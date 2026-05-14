package view;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import controller.GameController;
import model.GameState;

public class MenuPanel extends JPanel{
	
	private JFrame window;
		public MenuPanel(JFrame w) {
			
			window = w;
			//nao pode usar this dentro do main(cant use this inside the main)
			
			GameState gameState = new GameState();
			GamePanel gamePanel = new GamePanel(gameState);
			GameController controller = new GameController(gameState, gamePanel);
	
			JButton startButton = new JButton("start");
			add(startButton);
			
			startButton.addActionListener( e -> {
				System.out.println("inizio del gioco");
				window.setContentPane(gamePanel);
				window.revalidate();
				controller.start();
				window.repaint();
			});
		}
}