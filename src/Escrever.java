import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Escrever {
    public static void escrever_ficheiro(ArrayList<Fregues> obj_clientes, JButton button, JFrame client_file){
        String MyFileName = "bd/clientes.csv";
        try{
            File file = new File(MyFileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            for (Fregues i: obj_clientes){
                printWriter.println(i.cod+";"+i.natureza+";"+i.nome+";"+i.morada1+";"+i.morada2+";"+i.localidade+";"+i.distrito+";"+
                i.telefone+";"+i.email+";"+i.n_cidadão+";"+i.contribuinte+";"+i.max_cred);
            }

            printWriter.close(); 
            bufferedWriter.close(); 
            fileWriter.close();

            client_file.dispose();

            }

        catch (IOException ex) {
            System.out.println("Ocorreu um erro!");
        }
    }
}