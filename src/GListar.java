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
    private JFrame client_file;
    private boolean empty=true;

    public GListar (JFrame frame, String activo){

        String titulo;
        //determinar titulo
        if (activo.equals("true")){
            titulo="Clientes";
        }
        else {
            titulo="Clientes Inactivos";
        }
        JLabel title = new JLabel(titulo);

        //botão HOME
        JButton Homebutton=new JButton(new ImageIcon("homeSm.png"));

        panel = new JPanel();//painel cliente
        panel_top = new JPanel();//painel cabeçalho

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        panel_top.setBorder(BorderFactory.createEmptyBorder(30,35,0,35));
        panel_top.setLayout(new GridLayout(0,1));
        panel_top.add(Homebutton);
        panel_top.add(title);
        
        new Clientes();
        ArrayList<ArrayList<String>> allclients=Clientes.clients();//invocar lista de clientes segundo uma metodolgia

        //filtrar os clientes a listar consoante estejam activos e o que foi requisitado pelo utilizador
        for (ArrayList<String> i : allclients){
            if (activo.equals("true")){
                if (i.get(1).equals(activo)){
                    empty=false;
                }
            }
            else{
                if (i.get(1).equals(activo)){
                    empty=false;
                }
            }
        }

        //caso não haja nada a listar, indicar ao utilizador
        if (empty){
            JPanel client_panel=new JPanel();
            JLabel label;
            if (activo.equals("true")){
                label=new JLabel("O negócio está fraquinho, hein...?!"); 
            }
            else{
                label=new JLabel("Não há clientes inactivos."); 
            }
            client_panel.add(label);
            panel.add(client_panel);
        }
        else{//listar os clientes
            int j=0;
            for (ArrayList<String> i : allclients){
                JPanel client_panel=new JPanel();
                String label_text;
                JLabel label;
                JButton inspecionarBtn;

                if (i.get(1).equals(activo)){
                    label_text=i.get(0)+'-'+i.get(2);
                    label=new JLabel(label_text); 
                    inspecionarBtn=new JButton(new ImageIcon("MG.png"));
                    if (j%2==0){
                        label.setForeground(Color.black);
                    }
                    else{
                        label.setForeground(Color.DARK_GRAY);
                    }
                    go_inpeccionar(inspecionarBtn, label_text, frame, panel_top,activo);
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
                frame.remove(scrollPane);//reset frame
                frame.remove(panel_top);
                panel=null;
                panel_top=null;
                boolean first=false;
                new GI(frame, first);
            }  
        });  
    }    
    public void go_inpeccionar(JButton button, String cliente_nome, JFrame frame, JPanel panel_top, String activo) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                if (client_file==null){
                    client_file= new JFrame();//nova janela para inspeccionar cliente
                    client_file.addWindowListener(new WindowAdapter(){//permite discartar a janela a ser criada de modo a que se possa voltar a abrir a janela da ficha do mesmo ou outro cliente
                        @Override
                        public void windowClosing(WindowEvent e)
                        {
                            e.getWindow().dispose();
                            client_file=null;
                        }
                    });
                    new Ficha(cliente_nome, frame, panel_top, scrollPane, activo, client_file);//lança ficha
                }
            }  
        });  
    }    
}