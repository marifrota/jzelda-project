// título, tamanho, fechar quando clicar no x, adicionar um painel e tornar visível
package view;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import controller.InputHandler;
import javax.swing.JOptionPane;
/**
 * its the main game windown and this starts the application and loads the menu screen
 */
public class GameWindow extends JFrame {
	//starts the game
	/**
	 * start the game app
	 */
    public static void main(String[] args) {
    	//it creates the main game window
        JFrame window = new JFrame("JZelda");
        //asks the players name
     //   String nickname = JOptionPane.showInputDialog("Enter your nickname:"); lm removing it for the profile management 25/05
      //put the window size
        window.setSize(975,485);
        //close the application when press exit
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //centers the window on the screen
        window.setLocationRelativeTo(null);//centralizar a tela(center the window)
        
        window.setLayout(new BorderLayout());//definir o layout(set the layout)
      //sets the main menu panel
        window.setContentPane(new MenuPanel(window, ""));      //removed nickname 25/05  		
        //makes the window visible
        window.setVisible(true);
               
    }
}