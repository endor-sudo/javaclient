import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

public class Gmovim{
    private double balance;
    public Gmovim(JFrame gmovim_frame, String cliente_nome, String maxScred, String activo){
        JPanel panel= new JPanel();
        JPanel scroll_mov= new JPanel();
        JPanel max_mov= new JPanel();
        JPanel bal_mov= new JPanel();
        scroll_mov.setLayout(new GridLayout(0,1));
        
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        ArrayList<ArrayList<String>> movimentos=ler_TXT(cliente_nome);


        JLabel max_label=new JLabel("Crédito Máximo:"+maxScred);
        max_label.setForeground(Color.red);
        max_mov.add(max_label);
        ArrayList<Double> mov_doubles=new ArrayList<>();
        for (ArrayList<String> i:movimentos){
            JLabel label=new JLabel(i.get(0)+"                "+i.get(1));
            scroll_mov.add(label);
            mov_doubles.add(Double.parseDouble(i.get(1)));
        }

        double total = mov_doubles.stream().mapToDouble(f -> f.doubleValue()).sum();

        balance=Double.parseDouble(maxScred)+total;
        JLabel balance_label=new JLabel("Crédito Actual:"+String.valueOf(balance));
        balance_label.setForeground(Color.blue);
        bal_mov.add(balance_label);

  
        JScrollPane scrollPane = new JScrollPane(scroll_mov);
        panel.add(max_mov);
        panel.add(scrollPane);
        panel.add(bal_mov);

        if (activo.equals("true")){
            JRadioButton r1=new JRadioButton("A) Débito");    
            JRadioButton r2=new JRadioButton("B) Crédito");    
            r1.setBounds(75,50,100,30);    
            r2.setBounds(75,100,100,30);    
            ButtonGroup bg=new ButtonGroup();    
            bg.add(r1);bg.add(r2);    
            panel.add(r1);
            panel.add(r2);     

            JTextField valor=new JTextField();
            valor.setText("Valor a lançar");;
            panel.add(valor);

            JButton make_mov=new JButton("Lançar");
            panel.add(make_mov);
            make_mov_action(make_mov, gmovim_frame, r1,r2, cliente_nome, valor, panel, maxScred);
        }

        gmovim_frame.add(panel);
        gmovim_frame.setTitle("Lançar Movimento a "+cliente_nome);
        gmovim_frame.pack();
        gmovim_frame.setVisible(true);
    }
    public ArrayList<ArrayList<String>> ler_TXT(String cliente_nome){
        
        String FileName=get_file_name(cliente_nome);

        File file = null;
        FileReader fileReader = null; 
        BufferedReader bufferedReader = null; String line;
        ArrayList<ArrayList<String>> movements = new ArrayList<ArrayList<String>>();
        try{
            file = new File(FileName);
            fileReader = new FileReader(file); 
            bufferedReader = new BufferedReader(fileReader);
            System.out.println("Conteúdo do ficheiro ");
            do{
                line = bufferedReader.readLine();
                if (line != null) {

                    StringTokenizer tokenizer = new StringTokenizer(line, ";");
                    ArrayList<String> mov_data= new ArrayList<String>();
                    while (tokenizer.hasMoreElements()) {
                        String token=tokenizer.nextToken();
                        mov_data.add(token);
                    }
                    movements.add(mov_data);
                } 
            } while (line != null);
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return movements;

    }
    public void make_mov_action(JButton button, JFrame frame, JRadioButton r1, JRadioButton r2, 
    String cliente_nome, JTextField valor, JPanel panel, String maxScred) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                double valor_double;
                boolean success=true;
                String valor_texto=valor.getText();

                if(r1.isSelected()){   
                }    
                else if(r2.isSelected()){    
                }    
                else{
                    JFrame f=new JFrame();  
                    JOptionPane.showMessageDialog(f,
                    "Seleccione a Natureza do Lançamento\nDébito ou Crédito?","Alert",JOptionPane.WARNING_MESSAGE);     
                    success=false;
                }

                try{
                    valor_double=Double.parseDouble(valor_texto);
                    System.out.println(valor_double);
                    System.out.println(balance);
                    System.out.println(r2.isSelected());
                    if (valor_double>balance && r2.isSelected()){
                        JFrame f=new JFrame();  
                    JOptionPane.showMessageDialog(f,
                    "O lançamento ultrapassa o crédito disponível!","Alert",JOptionPane.WARNING_MESSAGE);  
                        success=false;
                    }
                }
                catch (NumberFormatException ex){
                    JFrame f=new JFrame();  
                    JOptionPane.showMessageDialog(f,
                    "Só são permitidos números no lançamento...","Alert",JOptionPane.WARNING_MESSAGE);  
                    success=false;
                }
                if (success){
                    button.setText("De certeza?");
                    button.setForeground(Color.magenta);
                    make_mov_action_mesmo(button, frame, r1, r2, cliente_nome, valor, panel, maxScred);
                }
                else{
                    button.setText("Isso não conta!");
                    button.setForeground(Color.RED);
                }
            }  
        });  
    }    
    public void make_mov_action_mesmo(JButton button, JFrame frame, JRadioButton r1, JRadioButton r2, 
    String cliente_nome, JTextField valor, JPanel panel, String maxScred) {
        button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                String valor_texto=valor.getText();
                escrever_mov(r1,r2, cliente_nome, valor_texto);
                frame.remove(panel);
                String activo="true";
                new Gmovim(frame, cliente_nome, maxScred, activo);
            }  
        });  
    }    
    public void escrever_mov(JRadioButton r1, JRadioButton r2, String cliente_nome, String valor_texto){

        String MyFileName=get_file_name(cliente_nome);
        Calendar calendar = Calendar.getInstance(); 
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String time_stamp=formatter.format(calendar.getTime());

        String mov_nature="";
        if(r1.isSelected()){    
            mov_nature="+";  
        }    
        else if(r2.isSelected()){    
            mov_nature="-";     
        }    

        try{
            File file = new File(MyFileName);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(time_stamp+";"+mov_nature+valor_texto);

            printWriter.close(); 
            bufferedWriter.close(); 
            fileWriter.close();

            }

        catch (IOException ex) {
            System.out.println("Ocorreu um erro!");
        }

    }

    public String get_file_name(String ref){
        StringTokenizer tokenizer = new StringTokenizer(ref, "-");
        String codigo=tokenizer.nextToken();

        String FileName = "bd/"+codigo+".txt"; 

        return FileName;
    }
}