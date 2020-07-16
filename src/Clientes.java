import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JTextField;

public class Clientes {
    public static ArrayList<ArrayList<String>>  clients(){

        String FileName = "bd/clientes.csv"; 
        File file = null;
        FileReader fileReader = null; BufferedReader bufferedReader = null; String line;
        ArrayList<ArrayList<String>> clients_ = new ArrayList<ArrayList<String>>();
        try{
            file = new File(FileName);
            fileReader = new FileReader(file); 
            bufferedReader = new BufferedReader(fileReader);
            System.out.println("Conteúdo do ficheiro ");
            do{
                line = bufferedReader.readLine();
                if (line != null) {
                    StringTokenizer tokenizer = new StringTokenizer(line, ";");
                    ArrayList<String> client_info= new ArrayList<String>();
                    while (tokenizer.hasMoreElements()) {
                        String token=tokenizer.nextToken();
                        client_info.add(token);
                    }
                    clients_.add(client_info);
                } 
            } while (line != null);
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return clients_;
    }
    public static ArrayList<JTextField> campos(){
        JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11;
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

        return fields;

    }
}