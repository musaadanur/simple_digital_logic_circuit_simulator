package logic_circuit_simulator_musaadanur;

import java.util.ArrayList;

public class operations {
    
    // "not" "oriki" "oruc" "andiki" "anduc" "inputbir" "inputsıfır" stringlerini barındırabilir
    public ArrayList<String> gatesInputs=new ArrayList<String>(); 
    public ArrayList<Integer> giLocX=new ArrayList<Integer>();
    public ArrayList<Integer> giLocY=new ArrayList<Integer>();

    public int orAnd=-1; // ekranda or var ise 1 and var ise 0 degerini alır
    public int birSay=1; // input bir leri sayar
    public int notVarmi=-1; // sahnde not var ise 1 degerini alır
    
    public int[] yollananXY =new int[3]; // ilk deger x kordinati ikincisi y 3.sü sonuc 1 mi sıfırmı
 
    public int[][] bxyler= new int[10][4]; //  baglantı çizgimizin konumları nı tutar x1 y1 x2 y2
    
    public int[] assets(int assetler , int simulate) {
        
        if (simulate>1) { // aynı ekranda birden fazla devre simulate için
            orAnd=-1;
            birSay=1;
            notVarmi=-1;
        }
        
        System.out.println("****************");

        // aşagıdaki kontrol yapısı sahnede tek devre elemanının simule edilmesi için tasarlanmıştır
        // ekranda 1 and kapısı ve 2 giriş var ise yani toplamda varlık sayımız 3 olur ilk koşul saglanır
        if (assetler==3 || assetler==4 || assetler==2) 
        { 
            for (int i = 0; i < assetler; i++) // ekrandaki nesnelerin türleri belirleniyor
            {
                if (gatesInputs.get(i).equals("oriki") || gatesInputs.get(i).equals("oruc") ) {
                    orAnd=1; 
                    yollananXY[0]=giLocX.get(i);
                    yollananXY[1]=giLocY.get(i);
                }
                else if( gatesInputs.get(i).equals("andiki") || gatesInputs.get(i).equals("anduc") ) {
                    orAnd=0; 
                    yollananXY[0]=giLocX.get(i);
                    yollananXY[1]=giLocY.get(i);
                }
                else if (gatesInputs.get(i).equals("not")) {
                    notVarmi=1;
                    yollananXY[0]=giLocX.get(i);
                    yollananXY[1]=giLocY.get(i);
                }
                else if( gatesInputs.get(i).equals("inputbir") ) {
                    birSay++;
                }
            }
            
            if (orAnd==1 ){ // or kapısının girişlerinden birtanesi bir ise 
                if (  birSay > 1 ) {
                    System.out.println("SONUC 1");
                    yollananXY[2]=1;
                }
                else{ 
                System.out.println("SONUC 0"); // 2 girişte sıfır ise
                yollananXY[2]=0;
                }
            }
            
            else if (notVarmi==1) { 
                if (birSay==1) { // not girisinde 0 varsa
                    System.out.println("SONUC 1");
                    yollananXY[2]=1;
                }
                else if(birSay>1){System.out.println("SONUC 0");  yollananXY[2]=0;}
            }
            
            else if ( orAnd==0  ) { // and kapısının girişlerinden birtanesi sifir ise
                if ( birSay == assetler ){
                    System.out.println("SONUC 1");
                    yollananXY[2]=1;
                }
                else{ 
                System.out.println("SONUC 0"); // 2 girişte bir ise
                yollananXY[2]=0;
                }
            }
        }
        
        return yollananXY;
    }
   

    
}
