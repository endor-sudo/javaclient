import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Gestao {

    public static JFrame frame = new JFrame();

    public static void main(String[] args) throws Exception {

        //usar MyFile.createNewFile()

        File MyFile = new File("bd/clientes.csv");
        try{
            if (MyFile.exists()){
                System.out.println("O ficheiro " + MyFile.getName() + " já existe!"); 
            }
            else{
                MyFile.createNewFile();
            } 
        }
        catch (IOException ex) {
            System.out.println("Ocorreu um erro");
            ex.printStackTrace(); 
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }
        new GI(frame);
    }

}