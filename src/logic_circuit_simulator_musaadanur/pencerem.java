package logic_circuit_simulator_musaadanur;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class pencerem extends JPanel implements MouseMotionListener, MouseListener, ActionListener{

    gates gates=new gates();
    operations op=new operations();
    
    public BufferedImage toolbox; // sahnemize arkaplan olarak çizecegimiz toolbox resmimizi tutacak değişken
    
    public int assetsSayac=0; // sahnedeki elemanları sayar
    public int durum=0; // hangi kapıyı seçtiğimizin kararını durum değişkeni ile ayırt ediyoruz
    public int xkor=0;  // sol alttaki mouse hareketlerine göre koordinati ekrana basmak için
    public int ykor=0;
    public int gatex=0; // kapıların inputların sahneye bırakılacağı yeri tutup ekrana çizdirmek için
    public int gatey=0;
    
    public int x1; // baglantı oluşturacagımız çizginin kordinatları ve ilgili değişkenleri
    public int y1;
    public int x2;
    public int y2;
    public int flag=1;
    public boolean ciz=false;
    
    public int simulate=0; // program açık iken 1 den fazla simulate yapmamızı saglamak için
    
    public int sonuc=-1;
    public int[] opGelenxy = new int[3];
    
    public float[] dash2={1f};
    
    public int anim=0; // sag alt kösedeki ikonların animasyonu için
    public int say=0;
    
    public int baglantıSay=0;
    
    public pencerem() throws IOException {
        addMouseMotionListener(this);
        addMouseListener(this);
        Timer timer=new Timer(50,this);
        timer.start();
        toolbox = ImageIO.read(new File("src\\res\\toolbox.png"));
    }
    
    @Override
    public void paint(Graphics g)
    {
//        super.paint(g);
        g.drawImage(toolbox, 0, 0, null); // ekrana toolbox cizdiriyoruz
        g.setFont(new Font("TimesRoman", Font.BOLD, 12)); 
        g.setColor(new Color(97,111,127));
        g.fillRect(5, getHeight()-26 , 115, 20); // sol alt kösedeki mouse koordinatlarını göstermek için
        g.setColor(Color.white);
        g.drawString("X : "+xkor, 10, getHeight()-12);
        g.drawString("Y : "+ykor, 70, getHeight()-12);
        
        Graphics2D g2d=(Graphics2D) g; // kapıları baglayacagımız kablonun kalınlıgını ayarlıyoruz
        BasicStroke bs1 = new BasicStroke(3, BasicStroke.JOIN_ROUND,BasicStroke.JOIN_ROUND,1.0f,dash2,1f);
	g2d.setStroke(bs1);
        
        g.setFont(new Font("Arial", Font.BOLD, 19)); 
        g.setColor(new Color(97,111,127));
        g.drawString("|  musaadanur  ", getWidth()-150, getHeight()-17);
        g.drawImage(gates.imageList.get(7), getWidth()-320, getHeight()-32+anim, null); 
        g.drawImage(gates.imageList.get(8), getWidth()-280, getHeight()-32+anim, null);
        g.drawImage(gates.imageList.get(9), getWidth()-240, getHeight()-32+anim, null);
        g.drawImage(gates.imageList.get(10), getWidth()-200, getHeight()-32+anim, null);
        
        // sahnemize elemanları çizdiğimiz kısım
        // Not sahnemiz 160 px den sonra başladığı için >160 koşulunu kullandık
        if(gatey>160) 
        {
            if(durum==1) // not kapısını cizer
            {
                assetsSayac++;
                 g.drawImage(gates.imageList.get(0), gatex , gatey, null);
                 op.gatesInputs.add("not");
                 op.giLocX.add(gatex);
                 op.giLocY.add(gatey);
            }
            else if(durum==2) // andiki kapısını cizer
            {
                assetsSayac++;
                 g.drawImage(gates.imageList.get(1), gatex , gatey, null);
                 op.gatesInputs.add("andiki");
                 op.giLocX.add(gatex);
                 op.giLocY.add(gatey);
            }
            else if(durum==3) // anduc kapısını cizer
            {    
                assetsSayac++;
                 g.drawImage(gates.imageList.get(2), gatex , gatey, null);
                 op.gatesInputs.add("anduc");
                 op.giLocX.add(gatex);
                 op.giLocY.add(gatey);
            }
            else if(durum==4) // oriki kapısını cizer
            {
                assetsSayac++;
                 g.drawImage(gates.imageList.get(3), gatex , gatey, null);
                 op.gatesInputs.add("oriki");
                 op.giLocX.add(gatex);
                 op.giLocY.add(gatey);
            }
            else if(durum==5) // oruc kapısını cizer
            {
                assetsSayac++;
                 g.drawImage(gates.imageList.get(4), gatex , gatey, null);
                 op.gatesInputs.add("oruc");
                 op.giLocX.add(gatex);
                 op.giLocY.add(gatey);
            }
            else if(durum==6) // input1 cizer
            {
                assetsSayac++;
                 g.drawImage(gates.imageList.get(5), gatex , gatey, null);
                 op.gatesInputs.add("inputbir");
                 op.giLocX.add(gatex);
                 op.giLocY.add(gatey);
            }
            else if(durum==7) // input 0 cizer
            {
                assetsSayac++;
                 g.drawImage(gates.imageList.get(6), gatex , gatey, null);
                 op.gatesInputs.add("inputsifir");
                 op.giLocX.add(gatex);
                 op.giLocY.add(gatey);
            }
            
            durum=0;

            if(flag==2) // baglantıları olusturmak için x1 y1 konumu alındı
            {
                flag=3;
            }
            if (ciz==true)
            {
                g.setColor(Color.black);
                    assetsSayac++;
                    g.drawLine(x1, y1, x2, y2); // x2 y2 konumu alındı baglantı cizilebilir hale getirildi
                    op.bxyler[baglantıSay][0]=x1;
                    op.bxyler[baglantıSay][1]=y1;
                    op.bxyler[baglantıSay][2]=x2;
                    op.bxyler[baglantıSay][3]=y2;
                    baglantıSay++;
                    ciz=false;
                    flag=1;
            }
        }
        if (durum==8) { // sonuc ekrana basma
                if (opGelenxy[2]==1) {
                    g.drawImage(gates.imageList.get(5), opGelenxy[0]+gates.gatesResimEn-gates.inputResimEnBoy/2 , (opGelenxy[1]+gates.gatesResimBoy/2-gates.inputResimEnBoy/2) , null);
                }
                else if (opGelenxy[2]==0) {
                    g.drawImage(gates.imageList.get(6), opGelenxy[0]+gates.gatesResimEn-gates.inputResimEnBoy/2 , (opGelenxy[1]+gates.gatesResimBoy/2-gates.inputResimEnBoy/2) , null);
                }
                
                for (int i = 0; i < opGelenxy.length; i++) { //dizimiz birsonraki çalıştırlma iççin geçersiz bir deger ile dolduruluyor
                    opGelenxy[i]=-1;
                }
                opGelenxy[2]=-1;
                baglantıSay=0;
                op.gatesInputs.clear();// bir sonraki simulate için arraylar boşaltılıyor
                op.giLocX.clear();
                op.giLocY.clear();
                assetsSayac=0; 
            }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
        if(e.getY()<160 && e.getButton()==e.BUTTON1) // yani toolbox içerisine girmiş isek ve mouse sol klik tıklanmış ise
        {
            if(e.getX()<150)
            {
                System.out.println("NOT kapısı seçildi");
                durum=1;
            }
            else if(e.getX()>150 && e.getX()<300)
            {
                System.out.println("ANDİKİ kapısı seçildi");
                 durum=2;
            }
            else if(e.getX()>300 && e.getX()<450)
            {
                System.out.println("ANDUC kapısı seçildi");
                durum=3;
            }
            else if(e.getX()>450 && e.getX()<600)
            {
                System.out.println("ORİKİ kapısı seçildi");
                durum=4;
            }
            else if(e.getX()>600 && e.getX()<750)
            {
                System.out.println("ORUC kapısı seçildi");
                durum=5;
            }
            else if(e.getX()>750 && e.getX()<825 && e.getY()<80)
            {
                System.out.println("INPUTBİR seçildi");
                durum=6;
            }
            else if(e.getX()>825 && e.getX()<910 && e.getY()<80)
            {
                System.out.println("INPUTSIFIR seçildi");
                durum=7;
            }
            else if(e.getX()>750 && e.getX()<910 && e.getY()>80) // simulate tıklanmış ise
            {  simulate++;
                System.out.println("SİMULATE seçildi");
                opGelenxy=op.assets(assetsSayac,simulate);
                durum=8;
                
            }
        }
        if(durum>=1 || durum<=2) // 
        {
            gatex= e.getX();
            gatey= e.getY();
        }
                                       // sağklik yapıldıgında
        if (e.getButton()==e.BUTTON3 && flag==1) { // bu ve  altındaki kontroller bağlantı için
            x1=e.getX();
            y1=e.getY();
            flag=2;
        }
        else if (e.getButton()==e.BUTTON3 && flag==3) { 
            x2=e.getX();
            y2=e.getY();
            ciz=true;
        }
        
        repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
/////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {
        xkor= e.getX(); // mouse koordinatlarını sol köşede basmak için x y degerlerini aldık
        ykor= e.getY();
        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (say==0)
        {
            anim++;
            if (anim>2)
            { 
              say=61;
            }
        }
        
        if (say==61)
        {
            anim--;
            if (anim<-6)
            {
              say=0;
            }
        }
        repaint();
    }
}
