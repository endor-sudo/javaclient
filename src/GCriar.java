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
        JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11;

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));

        t1 = new JTextField("Nome");
        t2 = new JTextField("Morada");
        t3 = new JTextField("Localidade");
        t4 = new JTextField("Código Postal");
        t5 = new JTextField("Cidade");
        t6 = new JTextField("Distrito");
        t7 = new JTextField("Telefone");
        t8 = new JTextField("Email");
        t9 = new JTextField("Cartão de Cidadão");
        t10 = new JTextField("Nº de Contribuinte");
        t11 = new JTextField("Crédito Máximo");

        ArrayList<JTextField> fields = new ArrayList<JTextField>();

        fields.add(t1);
        fields.add(t2);
        fields.add(t3);
        fields.add(t4);
        fields.add(t5);
        fields.add(t6);
        fields.add(t7);
        fields.add(t8);
        fields.add(t9);
        fields.add(t10);
        fields.add(t11);

        panel.add(Homebutton);
        get_back(Homebutton, frame);
        for (JTextField i : fields) {
            i.setBounds(50, 50, 100, 50);
            panel.add(i);
        }

        JButton createbBtn = new JButton("Criar");
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
                    }
                catch (IOException ex) {
                    System.out.println("Ocorreu um erro!");
                }
            }  
        });  
    }

}