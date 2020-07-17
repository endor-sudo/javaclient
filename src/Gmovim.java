import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.*; 

public class Gmovim{
    public Gmovim(String cliente_nome){
        JFrame frame= new JFrame();
        JPanel panel= new JPanel();
        
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));
        
  
        JRadioButton r1=new JRadioButton("A) Débito");    
        JRadioButton r2=new JRadioButton("B) Crédito");    
        r1.setBounds(75,50,100,30);    
        r2.setBounds(75,100,100,30);    
        ButtonGroup bg=new ButtonGroup();    
        bg.add(r1);bg.add(r2);    
        panel.add(r1);
        panel.add(r2);     

        JTextField valor=new JTextField();
        panel.add(valor);

        frame.add(panel);
        frame.setTitle("Lançar Movimento");    
        frame.setSize(300,300); 
        frame.setVisible(true);
    }
}