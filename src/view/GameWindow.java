// título, tamanho, fechar quando clicar no x, adicionar um painel e tornar visível
package view;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;


public class GameWindow extends JFrame {
	
    public static void main(String[] args) {
   
        JFrame window = new JFrame("JZelda");
      
        window.setSize(800,800);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setLocationRelativeTo(null);//centralizar a tela(center the window)
        
        window.setLayout(new BorderLayout());//definir o layout(set the layout)
        
        window.setContentPane(new MenuPanel(window));//colocar dentro do menu(place it inside the menu)
        		
        window.setVisible(true);
    }
}