import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*; 
import java.awt.event.*;

public class GI{
    private JFrame frame;
    private JPanel panel;

    public GI (){

        frame=new JFrame();

        JButton button0=new JButton("Listar Clientes Activos");
        JButton button1=new JButton("Adicionar Cliente Novo");
        JButton button2=new JButton("Eliminar Cliente-Desactivar");
        JButton button3=new JButton("Modificar Cliente/Actualizar dados");
        JButton button4=new JButton("Consultar Movimentos-Crédito Disponível");
        JButton button5=new JButton("Lançar Movimento-Debitar/Creditar");

        change_text(button0, frame);

        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));
        
        panel.add(button0);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Gestao Pagamentos");
        frame.pack();
        frame.setVisible(true);
    }

    public void change_text(JButton button, JFrame frame) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                frame.remove(panel);
                panel=null;
                new GListar(frame);
            }  
        });  
    }    
}