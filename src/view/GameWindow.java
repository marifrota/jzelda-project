// título, tamanho, fechar quando clicar no x, adicionar um painel e tornar visível
package view;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import controller.InputHandler;
import javax.swing.JOptionPane;
public class GameWindow extends JFrame {
	
    public static void main(String[] args) {
    	
        JFrame window = new JFrame("JZelda");
        
        String nickname = JOptionPane.showInputDialog("Enter your nickname:");
      
        window.setSize(975,485);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setLocationRelativeTo(null);//centralizar a tela(center the window)
        
        window.setLayout(new BorderLayout());//definir o layout(set the layout)
        
        window.setContentPane(new MenuPanel(window, nickname));        		
        
        window.setVisible(true);
               
    }
}