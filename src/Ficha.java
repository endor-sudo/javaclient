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

    JFrame gmovim_frame;
    private Fregues inprecionado=null;//sou mau a criar nomes de variáveis

    public Ficha(String cliente_nome, JFrame frame, JPanel panel_top, JScrollPane scrollPane, 
    String activo, JFrame client_file){
        JPanel painel=new JPanel();
        JButton movimBtn= new JButton(new ImageIcon("dollar.png"));//botão para consultar/lançar movimentos de conta
        painel.add(movimBtn);
        

        new Clientes();
        ArrayList<JTextField> fields=Clientes.campos();//invocar a função que devolve os campos do cliente.
        
        ArrayList<ArrayList<String>> lista_cliente=Clientes.clients();//devolve os clientes a cruzar com a variável control_nome; reitero:sou mau a criar nomes de variáveis
        ArrayList<Fregues> obj_clientes= new ArrayList<>();//para guardar os objectos criados a partir de lista_cliente; optaria por algo mais elegante se rescrevesse o código, mas usando as funções já criadas, ficou assim.
        String control_nome;

        for (ArrayList<String> i:lista_cliente){
            obj_clientes.add(new Fregues(i.get(0),i.get(1),i.get(2),i.get(3),
            i.get(4),i.get(5),i.get(6),i.get(7),i.get(8),i.get(9),i.get(10),i.get(11)));
        }//popular obj_clientes

        System.out.println(cliente_nome);
        for (Fregues i: obj_clientes){
            control_nome=i.cod+'-'+i.nome;
            System.out.println(control_nome);
            if (control_nome.equals(cliente_nome)){
                inprecionado=i;
                System.out.println(inprecionado.nome);
                break;
            }
        }//obter o cliente pretendido ao cruzar e coincidir dados

        //no seguimento do que seria mais elegante: usei uma abordagem híbrida de arraylist e classe Fregues, mas sendo a classe Fregues o que era requerido para avaliação, acho que seria essa a abordagem a optar sigularmente.
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

        String maxScred=inprecionado.max_cred;
        lancar_movim(movimBtn, cliente_nome,maxScred, activo, client_file);
        

        for (JTextField i : fields) {
            i.setPreferredSize(new Dimension(300, 50));
            painel.add(i);
        }

        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        painel.setLayout(new GridLayout(0, 1));

        if (activo.equals("true")){//mais um filtro da apresentação
            JButton editar=new JButton("Confirmar Edição");
            editar.setForeground(Color.blue);
            editar.setPreferredSize(new Dimension(300, 50));
            editar_ficha(editar, fields, obj_clientes, cliente_nome, frame, panel_top, scrollPane, client_file);
            painel.add(editar);

            JButton eliminar=new JButton("Eliminar");
            eliminar.setForeground(Color.red);
            eliminar.setPreferredSize(new Dimension(300, 50));
            eliminar_ficha(eliminar, fields, obj_clientes, cliente_nome, frame, panel_top, scrollPane, activo, client_file);
            painel.add(eliminar);
        }
        else{
            JButton recuperar=new JButton("Recuperar Cliente");
            recuperar.setForeground(Color.orange);
            recuperar.setPreferredSize(new Dimension(300, 50));
            eliminar_ficha(recuperar, fields, obj_clientes, cliente_nome, frame, panel_top, scrollPane, activo, client_file);
            painel.add(recuperar);
        }
        
        client_file.add(painel);
        client_file.setTitle(cliente_nome);
        client_file.pack();
        client_file.setVisible(true);
    }
    public void editar_ficha(JButton button, ArrayList<JTextField> fields, ArrayList<Fregues> obj_clientes,
     String cliente_nome, JFrame frame, JPanel panel_top, JScrollPane scrollPane, JFrame client_file) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                button.setText("Editar de certeza?");
                button.setForeground(Color.cyan);
                editar_ficha_mesmo(button, fields, obj_clientes, cliente_nome, frame, panel_top, scrollPane, client_file);
            }  
        });  
    }//passo de confirmação    
    public void eliminar_ficha(JButton button, ArrayList<JTextField> fields, ArrayList<Fregues> obj_clientes, 
    String cliente_nome, JFrame frame, JPanel panel_top, JScrollPane scrollPane, String activo, JFrame client_file) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                if (activo.equals("true")){
                    button.setText("Eliminar de certeza?");
                }
                else{
                    button.setText("Recuperar de certeza?");
                }
                button.setForeground(Color.magenta);
                eliminar_ficha_mesmo(button, fields, obj_clientes, cliente_nome, frame, panel_top, scrollPane, activo, client_file);
            }  
        });  
    }//passo de confirmação        
    public void editar_ficha_mesmo(JButton button, ArrayList<JTextField> fields, ArrayList<Fregues> obj_clientes, 
    String cliente_nome, JFrame frame, JPanel panel_top, JScrollPane scrollPane, JFrame client_file) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){

                String control_nome;
                //obtem dados actualizados
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

                for (Fregues i: obj_clientes){//debugging
                    control_nome=i.cod+'-'+i.nome;
                    System.out.println(control_nome);
                    if (control_nome.equals(cliente_nome)){
                        i=inprecionado;
                        System.out.println(inprecionado.nome);
                        break;
                    }
                }

                new Escrever();
                Escrever.escrever_ficheiro(obj_clientes, button, client_file);//actualiza BD
                

                frame.remove(panel_top);
                frame.remove(scrollPane);
                String activo="true";
                new GListar(frame, activo);//actualiza GUI
                
            }  
        });  
    }    
    public void eliminar_ficha_mesmo(JButton button, ArrayList<JTextField> fields, ArrayList<Fregues> obj_clientes,
     String cliente_nome, JFrame frame, JPanel panel_top, JScrollPane scrollPane, String activo, JFrame client_file) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){

                String control_nome;

                for (Fregues i: obj_clientes){
                    control_nome=i.cod+'-'+i.nome;
                    if (control_nome.equals(cliente_nome)){
                        if (activo.equals("true")){
                            i.natureza="false";
                        }
                        else{
                            i.natureza="true";
                        }
                        break;
                    }
                }//modifica o campo do cliente

                new Escrever();
                Escrever.escrever_ficheiro(obj_clientes, button, client_file);//actualiza o BD
                

                frame.remove(panel_top);
                frame.remove(scrollPane);
                new GListar(frame, activo);//actualiza a GUI
                
            }  
        });  
    }    
    public void lancar_movim(JButton button, String cliente_nome, String maxScred, String activo, JFrame client_file) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                if (gmovim_frame==null){
                    gmovim_frame= new JFrame();
                    gmovim_frame.addWindowListener(new WindowAdapter(){
                        @Override
                        public void windowClosing(WindowEvent e)
                        {
                            e.getWindow().dispose();
                            gmovim_frame.dispose();
                            gmovim_frame=null;
                        }
                    });//permite descartar a nova janela correctamente
                    client_file.addWindowListener(new WindowAdapter(){
                        @Override
                        public void windowClosing(WindowEvent e)
                        {
                            e.getWindow().dispose();
                            if (gmovim_frame!=null){
                                gmovim_frame.dispose();
                            }
                        }
                    });//fecha a janela de movimentos se a ficha do cliente for fechada
                    new Gmovim(gmovim_frame,cliente_nome, maxScred, activo);//lança janela dos movimentos
                }
            }  
        });  
    }    
}    
