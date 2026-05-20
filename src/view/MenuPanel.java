package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import controller.GameController;
import model.GameState;
import controller.InputHandler;
import java.awt.Color;
import java.awt.Font;

public class MenuPanel extends JPanel{
	
	private JFrame window;
		public MenuPanel(JFrame w) {
			window = w;
			//nao pode usar this dentro do main(cant use this inside the main)
			
			GameState gameState = new GameState();
			GamePanel gamePanel = new GamePanel(gameState);
			InputHandler inputHandler = new InputHandler();
			GameController controller = new GameController(gameState, gamePanel, inputHandler);
		
			
			JButton startButton = new JButton("START");
			add(startButton);
			JButton exitButton = new JButton("EXIT");
			add(exitButton);
	        Font buttonFont = new Font("Arial", Font.BOLD, 24);
	        startButton.setFont(buttonFont);
	        exitButton.setFont(buttonFont);
	        startButton.setBackground(Color.BLACK);
	        startButton.setForeground(Color.WHITE);
	        exitButton.setBackground(Color.RED);
	        exitButton.setForeground(Color.WHITE);
	        startButton.setFocusPainted(false);
	        exitButton.setFocusPainted(false);   
	            
	        startButton.setBounds(350,180,250,70);
	        exitButton.setBounds( 350,300,250,70);

	        
			startButton.addActionListener( e -> {
				System.out.println("inizio del gioco");
				window.setContentPane(gamePanel);
				gamePanel.setFocusable(true);
				gamePanel.requestFocusInWindow();
				gamePanel.addKeyListener(inputHandler);
				window.revalidate();
				controller.start();
				window.repaint();
			});
			exitButton.addActionListener(e -> {

	            System.exit(0);
	        });
	    }
	}