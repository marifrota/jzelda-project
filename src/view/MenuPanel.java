package view;
import javax.swing.JFrame;
import editor.LevelEditor; //24/05 
import javax.swing.JPanel;
import javax.swing.JButton;
import controller.GameController;
import model.GameState;
import controller.InputHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
/** 
 * its the main menu panel of the game that allows the player to start and also to exit the game
 */
public class MenuPanel extends JPanel{
	
	private JFrame window;
	 /**
	  *  it creates the game menu and starts the buttons and the game state and the controller
	  */
	public MenuPanel(JFrame w, String nickname) {		window = w;
			//nao pode usar this dentro do main(cant use this inside the main)
			GameState gameState = new GameState();
			gameState.setNickname(nickname);
			GamePanel gamePanel = new GamePanel(gameState);
			InputHandler inputHandler = new InputHandler(gamePanel);			
			GameController controller = new GameController(gameState, gamePanel, inputHandler);
			//screen to be black
			setLayout(null);
			setBackground(Color.BLACK);
			//buttons
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
	        //start and exit buttons size
	        startButton.setBounds(360,230,250,70);
	        exitButton.setBounds(360,325,250,70);//(x,y,largura,altura)(x,y,large,height)
	        
	        //24/05 Edits the levels 
	        JButton editorButton = new JButton("LEVEL EDITOR");
	        add(editorButton);
	        Font editorFont = new Font("Arial", Font.PLAIN, 16); 
	        editorButton.setFont(editorFont);
	        editorButton.setBackground(Color.DARK_GRAY);
	        editorButton.setForeground(Color.WHITE);
	        editorButton.setFocusPainted(false);
	        editorButton.setBounds(770, 25, 180, 30);	        
	        editorButton.addActionListener(e -> {
	            System.out.println("Apertura Level Editor in corso");
	            new LevelEditor(); 
	            window.dispose();  
	        });

	     // Starts the game
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
			// Closes the application
			exitButton.addActionListener(e -> {

	            System.exit(0);
	        });
			
	    }
	/** 
	 * Draws the menu background and title screen "JZELDA"  in the color gold with the brown shadow
	 */
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    // background
	    g2.setColor(Color.BLACK);
	    g2.fillRect(0,0,getWidth(),getHeight());
	    // title
	    g2.setFont(new Font("Serif", Font.BOLD, 90));
	    // shadow
	    g2.setColor(new Color(80,40,0));
	    g2.drawString("JZELDA", 315, 125);
	    // gold color
	    g2.setColor(new Color(255,215,0));
	    g2.drawString("JZELDA", 310, 120);
	    // subtitle
	    g2.setFont(new Font("Arial", Font.PLAIN, 28));
	    g2.setColor(Color.WHITE);
	    g2.drawString("The Hell of Bugs", 360, 175);
	}
}