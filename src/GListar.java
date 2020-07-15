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
        panel_top.setLayout(new GridLayout(0,1));
        panel_top.add(Homebutton);
        panel_top.add(title);
        
        new Clientes();
        ArrayList<ArrayList<String>> allclients=Clientes.clients();
        
        for (ArrayList<String> i : allclients){
            String label_text;
            JLabel label;
            label_text=i.get(0)+'-'+i.get(2);
            label=new JLabel(label_text); 
            panel.add(label); 
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
}