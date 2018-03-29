package logic_circuit_simulator_musaadanur;

import java.io.IOException;
import javax.swing.JFrame;

public class main {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
    JFrame frame=new JFrame(); // form çerçevemizi oluşturuyoruz
    
    pencerem pen=new pencerem(); 
    frame.add(pen); // çerçeve içerisine panelimizi yerleştiriyoruz
    
    frame.setBounds(300,70,920,800);
//    frame.setResizable(false); // frame sabitlemesi için
    frame.setVisible(true);
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    
    }
    
}