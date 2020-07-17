import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Ficha {


    private Fregues inprecionado=null;
    private JFrame client_file= new JFrame();

    public Ficha(String cliente_nome, JFrame frame, JPanel panel_top, JScrollPane scrollPane){
        JPanel painel=new JPanel();
        JButton movimBtn= new JButton(new ImageIcon("dollar.png"));
        painel.add(movimBtn);
        lancar_movim(movimBtn, cliente_nome);

        new Clientes();
        ArrayList<JTextField> fields=Clientes.campos();
        
        ArrayList<ArrayList<String>> lista_cliente=Clientes.clients();
        ArrayList<Fregues> obj_clientes= new ArrayList<>();
        String control_nome;

        for (ArrayList<String> i:lista_cliente){
            obj_clientes.add(new Fregues(i.get(0),i.get(1),i.get(2),i.get(3),
            i.get(4),i.get(5),i.get(6),i.get(7),i.get(8),i.get(9),i.get(10),i.get(11)));
        }

        System.out.println(cliente_nome);
        for (Fregues i: obj_clientes){
            control_nome=i.cod+'-'+i.nome;
            System.out.println(control_nome);
            if (control_nome.equals(cliente_nome)){
                inprecionado=i;
                System.out.println(inprecionado.nome);
                break;
            }
        }

        //acho que era mais fácil fazer isto sem uma classe para os clientes, estando a usar uma GUI,
        //mas só a criei porque fazia parte do é avaliado
        fields.get(0).setText(inprecionado.nome);
        fields.get(1).setText(inprecionado.morada1);
        fields.get(2).setText(inprecionado.morada2);
        fields.get(3).setText(inprecionado.localidade);
        fields.get(4).setText(inprecionado.distrito);
        fields.get(5).setText(inprecionado.telefone);
        fields.get(6).setText(inprecionado.email);
        fields.get(7).setText(inprecionado.n_cidadão);
        fields.get(8).setText(inprecionado.contribuinte);
        fields.get(9).setText(inprecionado.max_cred);
        

        for (JTextField i : fields) {
            i.setPreferredSize(new Dimension(300, 50));
            painel.add(i);
        }

        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        painel.setLayout(new GridLayout(0, 1));

        JButton editar=new JButton("Confirmar Edição");
        editar.setForeground(Color.blue);
        editar.setPreferredSize(new Dimension(300, 50));
        editar_ficha(editar, fields, obj_clientes, cliente_nome, frame, panel_top, scrollPane);
        painel.add(editar);

        JButton eliminar=new JButton("Eliminar");
        eliminar.setForeground(Color.red);
        eliminar.setPreferredSize(new Dimension(300, 50));
        eliminar_ficha(eliminar, fields, obj_clientes, cliente_nome, frame, panel_top, scrollPane);
        painel.add(eliminar);
        
        client_file.add(painel);
        client_file.setTitle(cliente_nome);
        client_file.pack();
        client_file.setVisible(true);
    }
    public void editar_ficha(JButton button, ArrayList<JTextField> fields, ArrayList<Fregues> obj_clientes,
     String cliente_nome, JFrame frame, JPanel panel_top, JScrollPane scrollPane) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                button.setText("Editar de certeza?");
                button.setForeground(Color.cyan);
                editar_ficha_mesmo(button, fields, obj_clientes, cliente_nome, frame, panel_top, scrollPane);
            }  
        });  
    }    
    public void eliminar_ficha(JButton button, ArrayList<JTextField> fields, ArrayList<Fregues> obj_clientes, 
    String cliente_nome, JFrame frame, JPanel panel_top, JScrollPane scrollPane) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                button.setText("Eliminar de certeza?");
                button.setForeground(Color.magenta);
                eliminar_ficha_mesmo(button, fields, obj_clientes, cliente_nome, frame, panel_top, scrollPane);
            }  
        });  
    }    
    public void editar_ficha_mesmo(JButton button, ArrayList<JTextField> fields, ArrayList<Fregues> obj_clientes, 
    String cliente_nome, JFrame frame, JPanel panel_top, JScrollPane scrollPane) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){

                String control_nome;

                inprecionado.nome=fields.get(0).getText();
                inprecionado.morada1=fields.get(1).getText();
                inprecionado.morada2=fields.get(2).getText();
                inprecionado.localidade=fields.get(3).getText();
                inprecionado.distrito=fields.get(4).getText();
                inprecionado.telefone=fields.get(5).getText();
                inprecionado.email=fields.get(6).getText();
                inprecionado.n_cidadão=fields.get(7).getText();
                inprecionado.contribuinte=fields.get(8).getText();
                inprecionado.max_cred=fields.get(9).getText();

                for (Fregues i: obj_clientes){
                    control_nome=i.cod+'-'+i.nome;
                    System.out.println(control_nome);
                    if (control_nome.equals(cliente_nome)){
                        i=inprecionado;
                        System.out.println(inprecionado.nome);
                        break;
                    }
                }

                new Escrever();
                Escrever.escrever_ficheiro(obj_clientes, button, client_file);
                

                frame.remove(panel_top);
                frame.remove(scrollPane);
                new GListar(frame);
                
            }  
        });  
    }    
    public void eliminar_ficha_mesmo(JButton button, ArrayList<JTextField> fields, ArrayList<Fregues> obj_clientes,
     String cliente_nome, JFrame frame, JPanel panel_top, JScrollPane scrollPane) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){

                String control_nome;

                for (Fregues i: obj_clientes){
                    control_nome=i.cod+'-'+i.nome;
                    if (control_nome.equals(cliente_nome)){
                        i.natureza="false";
                        break;
                    }
                }

                new Escrever();
                Escrever.escrever_ficheiro(obj_clientes, button, client_file);
                

                frame.remove(panel_top);
                frame.remove(scrollPane);
                new GListar(frame);
                
            }  
        });  
    }    
    public void lancar_movim(JButton button, String cliente_nome) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                new Gmovim(cliente_nome);
            }  
        });  
    }    
}    
