import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class GListar{
    private JPanel panel_top;
    private JPanel panel;

    public GListar (JFrame frame){

        JLabel title = new JLabel("Clientes");
        JButton Homebutton=new JButton("Menu");
        Homebutton.setPreferredSize(new Dimension(300, 50));

        panel = new JPanel();
        panel_top = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        panel_top.setBorder(BorderFactory.createEmptyBorder(30,35,0,35));
        panel_top.add(Homebutton);
        panel_top.add(title);
        panel_top.setLayout(new GridLayout(0,1));
        
        new Clientes();
        ArrayList<ArrayList<String>> allclients=Clientes.clients();
        
        int j=0;
        for (ArrayList<String> i : allclients){
            JPanel client_panel=new JPanel();
            String label_text;
            JLabel label;
            JButton inspecionarBtn;
            label_text=i.get(0)+'-'+i.get(2);
            label=new JLabel(label_text); 
            inspecionarBtn=new JButton("Inspeccionar");
            if (j%2==0){
                label.setForeground(Color.blue);
                inspecionarBtn.setForeground(Color.blue);
            }
            else{
                label.setForeground(Color.red);
                inspecionarBtn.setForeground(Color.red);
            }
            go_inpeccionar(inspecionarBtn, label_text);
            client_panel.add(label);
            client_panel.add(inspecionarBtn);
            client_panel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
            panel.add(client_panel);
            j++;
        }

        JScrollPane scrollPane = new JScrollPane(panel);

        frame.add(panel_top, BorderLayout.NORTH);
        frame.add(scrollPane);
        frame.setVisible(true);

        get_back(Homebutton, frame, scrollPane);
    }

    public void get_back(JButton button, JFrame frame, JScrollPane scrollPane) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                frame.remove(scrollPane);
                frame.remove(panel_top);
                panel=null;
                panel_top=null;
                new GI(frame);
            }  
        });  
    }    
    public void go_inpeccionar(JButton button, String cliente_nome) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                new Ficha(cliente_nome);
            }  
        });  
    }    
}