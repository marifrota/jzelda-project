package view;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;


public class MenuPanel extends JPanel{
	
	private JFrame window;
		public MenuPanel(JFrame w) {
			window = w;
			JButton startButton = new JButton("start");
			add(startButton);
		}
}

