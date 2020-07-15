import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*; 
import java.awt.event.*;

public class GI{
    private JPanel panel;

    public GI (JFrame frame) {


        JButton button0=new JButton("Gerir Clientes");
        JButton button1=new JButton("Criar Cliente");
        JButton button2=new JButton("Consultar Saldos");
        JButton button3=new JButton("Lançar Movimento");
        button0.setPreferredSize(new Dimension(300, 150));

        gerir_clientes(button0, frame);
        criar_cliente(button1, frame);

        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));
        
        panel.add(button0);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Gestao Pagamentos");
        frame.pack();
        frame.setVisible(true);
    }

    public void gerir_clientes(JButton button, JFrame frame) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                frame.remove(panel);
                panel=null;
                new GListar(frame);
            }  
        });  
    }
    public void criar_cliente(JButton button, JFrame frame) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                frame.remove(panel);
                panel=null;
                new GCriar(frame);
            }  
        });  
    }    
}