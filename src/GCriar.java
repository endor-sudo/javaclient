import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class GCriar {

    private JPanel panel;

    public GCriar(JFrame frame) {

        JButton Homebutton = new JButton("Menu");

        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));

        new Clientes();
        ArrayList<JTextField> fields=Clientes.campos();

        panel.add(Homebutton);
        get_back(Homebutton, frame);
        for (JTextField i : fields) {
            i.setBounds(50, 50, 100, 50);
            panel.add(i);
        }

        JButton createbBtn = new JButton("Criar");
        createbBtn.setForeground(Color.blue);
        panel.add(createbBtn);
        create_client(createbBtn, frame, fields);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void get_back(JButton button, JFrame frame) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                panel = null;
                new GI(frame);
            }
        });
    }

    public void create_client(JButton button, JFrame frame, ArrayList<JTextField> fields) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button.setForeground(Color.red);
                button.setText("Está tudo certo?");
                create_client_mesmo(button, frame, fields);
            }  
        });  
    }

    public void create_client_mesmo(JButton button, JFrame frame, ArrayList<JTextField> fields) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String MyFileName = "bd/clientes.csv";
                
                try{
                    File file = new File(MyFileName);
                    FileWriter fileWriter = new FileWriter(file,true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);

                    LinkedList<String> dados_cliente=new LinkedList<String>();

                    for (JTextField i: fields){
                        dados_cliente.add(i.getText());
                    }

                    dados_cliente.addFirst("true");

                    new Clientes();
                    ArrayList<ArrayList<String>> allclients=Clientes.clients();
                    int n_of_cl=allclients.size();
                    int client_number=n_of_cl+1000;
                    client_number++;

                    String str_num_cliente=String.valueOf(client_number);

                    dados_cliente.addFirst(str_num_cliente);
                    String last=dados_cliente.getLast();
                    dados_cliente.removeLast();

                    for (String i: dados_cliente){
                        printWriter.print(i);
                        printWriter.print(";");
                    }
                    printWriter.print(last);
                    printWriter.println("");

                    printWriter.close(); 
                    bufferedWriter.close(); 
                    fileWriter.close();

                    frame.remove(panel);
                    new GCriar(frame);

                    }
                catch (IOException ex) {
                    System.out.println("Ocorreu um erro!");
                }
            }  
        });  
    }

}