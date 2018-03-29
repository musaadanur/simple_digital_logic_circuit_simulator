package logic_circuit_simulator_musaadanur;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

// bu class ta mantık kapıları ve input resimlerini imageList isimli arrray e yerleştirdik 
public class gates {
    
    public static int inputResimEnBoy=20; //input resimleri kare şeklinde olduğu için
    public static int gatesResimEn=140; // and or kapılarının 1 tanesinin genişliği
    public static int gatesResimBoy=80; // and or kapılarının 1 tanesinin yüksekliği

    public ArrayList<String> fileList = new ArrayList<String>();
    public ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
    
    public gates() {
        buildFileList();
        loadImages();
    }

   private void buildFileList() // image'larımızın path'lerini array e atıyoruz
    {
        fileList.add( "src\\res\\not.png" );
        fileList.add( "src\\res\\andiki.png" );
        fileList.add( "src\\res\\anduc.png" );
        fileList.add( "src\\res\\oriki.png" );
        fileList.add( "src\\res\\oruc.png" );
        fileList.add( "src\\res\\inputone.png" );
        fileList.add( "src\\res\\inputzero.png" );
        //sosyal medya ikonları
        fileList.add( "src\\res\\behance.png" );
        fileList.add( "src\\res\\dribbble.png" );
        fileList.add( "src\\res\\linkedin.png" );
        fileList.add( "src\\res\\github.png" );
    }
   
   private void loadImages() // image lerin array e yüklüyoruz
    {
        BufferedImage temp = null;
        try
        {
            for( String item : fileList )
            {
                temp = ImageIO.read( new File( item ) );
                imageList.add( temp );
            }
        }
        catch( IOException e )
        {
            System.err.println( e.getMessage() );
        }
    }
}
