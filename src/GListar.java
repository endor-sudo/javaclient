import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
    private JScrollPane scrollPane;

    public GListar (JFrame frame){

        JLabel title = new JLabel("Clientes");
        JButton Homebutton=new JButton(new ImageIcon("homeSm.png"));

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
        
        int j=0;
        for (ArrayList<String> i : allclients){
            JPanel client_panel=new JPanel();
            String label_text;
            JLabel label;
            JButton inspecionarBtn;

            if (i.get(1).equals("true")){
                label_text=i.get(0)+'-'+i.get(2);
                label=new JLabel(label_text); 
                inspecionarBtn=new JButton(new ImageIcon("MG.png"));
                if (j%2==0){
                    label.setForeground(Color.black);
                }
                else{
                    label.setForeground(Color.DARK_GRAY);
                }
                go_inpeccionar(inspecionarBtn, label_text, frame, panel_top );
                client_panel.add(inspecionarBtn);
                client_panel.add(label);
                client_panel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
                panel.add(client_panel);
                j++;
            }
            else{
                continue;
            }
        }

        scrollPane = new JScrollPane(panel);

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
    public void go_inpeccionar(JButton button, String cliente_nome, JFrame frame, JPanel panel_top) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                new Ficha(cliente_nome, frame, panel_top, scrollPane);
            }  
        });  
    }    
}