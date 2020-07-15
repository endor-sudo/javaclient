import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
}