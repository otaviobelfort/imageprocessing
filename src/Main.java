
import java.util.EventListener;
import java.util.Random;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import util.Dithering;
import util.Histogram;
import util.Image;
import util.MorfologiaBin;
import util.Processador;
import util.Stretchin;

public class Main extends JFrame {

    // file path
    static String Image_A = "imagens/a.png";
    static String Image_B = "imagens/b.png";
    static String Image_LENNA = "imagens/lenna.jpg";
    static String Image_OLHO = "imagens/olho.png";
    static String Image_TESTE = "imagens/teste.png";
    static String Image_MASSA = "imagens/massa.jpg";
    static String Image_CINZA2 = "imagens/cinza2.jpg";

    

    public static void main(String args[]) {
        
        //viewImg();
        //rgbToGray();
        //grayToRgb();
        //imgQuantize(); 
        //imgSum();
        //imgSubtraction();
        //imgMedia();
        //imgNot();
        //imgEqualization();
        //imgStretchin();
        // imgDithering();
        // imgMorfologiaBin();
        //mat();
        Menu();
    }

   


    private static void mat(){
        int x, y;
        int [][]matOut = {{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1}};
        int [][]matIn = {{1,2,4,5,6},{1,2,4,5,6},{1,2,4,5,6},{1,2,4,5,6},{1,2,4,5,6}};
        int [][]N = {{0, 2},{3,1}};
        int color;
        int larg = 5;
        int alt = 5;
        //N = [[1,2],[2,2]];
        int [][][]matriz = new int[4][5][5]; 
         Random num = new Random();
        for(var i=0; i<matIn.length; i++){
            for(int j=0; j<matIn[i].length; j++){
                for(int k=0; k<matIn[i].length; k++){
                    color = matIn[j][k];
                    
                    for (x = 0; x < larg; x++){
                        for (y = 0; y < larg; y++){
                            matOut[j+(x)][k+(j)] = color < N[y][x] ? 0 : 255;
                        }
                    }
                   // matOut[j][k]= num.nextInt(4);
                    System.out.print(matOut[0][k]);
                }
                System.out.println(matOut[j][0]);
            }
            //System.out.println(matOut[i][0][0]);
        }
    }

    //View images
    private static void viewImg() {
        Image imgA = new Image(Main.Image_A);
        Image imgB = new Image(Main.Image_B);
        Image imgC = new Image(Main.Image_LENNA);
        Image imgD = new Image(Main.Image_OLHO);
        Image imgE = new Image(Main.Image_MASSA);

        imgA.viewImage("Image A");
        imgB.viewImage("Image B");
        imgC.viewImage("Image C");
        imgD.viewImage();
    }

    //transforms an RGB image to GRAY and modifies this image
    private static void rgbToGray() {
        Image imgRGB = new Image(Main.Image_LENNA);
        imgRGB.viewImage("Image RGB");

        // toGray in class Image
        Image imgGray = imgRGB.toGray();

        //todas as modificações devem ocorrer numa matriz[alt][larg][canais]
        int mat[][][] = imgGray.getMatriz();
        for (int d = 0; d < 100; d++) {
            mat[0][d][d] = 255; // apenas canal 0 -> cinza
        }
        // a Image deve ser atualizada
        imgGray.setMatriz(mat);
        imgGray.viewImage("Lenna Cinza Modificada com uma linha");
    }

    //transforma uma Image GRAY em RGB e modifica esta Image
    private static void grayToRgb() {
        Image imgGray = new Image(Main.Image_OLHO);
        imgGray.viewImage("Image gray");
        Image imgRGB = imgGray.toRGB();

        //todas as modificações devem ocorrer numa matriz[alt][larg][canais]
        int mat[][][] = imgRGB.getMatriz();
        for (int d = 0; d < 100; d++) {
            mat[0][d][d] = 5; // canal 0 -> R
            mat[1][d][d] = 120; // canal 1 -> G
            mat[2][d][d] = 100; // canal 2 -> B
        }
        // a Image deve ser atualizada
        imgRGB.setMatriz(mat);
        imgRGB.viewImage("Image RGB modificada");
        imgRGB.save("c:/teste/olho_reta.jpg", Image.JPEG);
    }

    private static void imgQuantize() {
        Image imgGray = new Image(Main.Image_MASSA);
        imgGray.viewImage("Image gray");

        Image bin = Processador.quantize(imgGray, 256, 2);
        bin.viewImage(" Image Quantize 02");
        Processador.print(bin);
        
        /*
        Processador.quantize(imgGray, 256, 2).viewImage("Image quantizada 2");
        Processador.quantize(imgGray, 256, 3).viewImage("Image quantizada 3");
        Processador.quantize(imgGray, 256, 4).viewImage("Image quantizada 4");
        Processador.quantize(imgGray, 256, 8).viewImage("Image quantizada 8");
        Processador.quantize(imgGray, 256, 16).viewImage("Image quantizada 16");

        Image imgRGB = new Image(Main.Image_LENNA);
        imgRGB.viewImage("Image RGB");
        Processador.quantize(imgRGB, 256, 2).viewImage("Image quantizada 2");
        Processador.quantize(imgRGB, 256, 4).viewImage("Image quantizada 4");
        Processador.quantize(imgRGB, 256, 8).viewImage("Image quantizada 8");
        Processador.quantize(imgRGB, 256, 16).viewImage("Image quantizada 16");
        */
    }

    private static void imgSum() {
        Image imgA = new Image(Main.Image_A);
        Image imgB = new Image(Main.Image_B);
        Image imgC = Processador.sum(imgA, imgB);
        imgA.viewImage("Image A");
        imgB.viewImage("Image B");
        imgC.viewImage("soma de A e B");
    }

    private static void imgSubtraction() {
        Image imgA = new Image("imagens/cinza1.png");
        Image imgB = new Image("imagens/cinza2.png");
        Image imgC = Processador.subtraction(imgA, imgB);
        imgA.viewImage("Image A");
        imgB.viewImage("Image B");
        imgC.viewImage("Subtração de A e B");
    }

    private static void imgMedia() {
        Image imgA = new Image(Main.Image_A);
        Image imgB = new Image(Main.Image_B);
        Image imgC = Processador.media(imgA, imgB);
        imgA.viewImage("Image A");
        imgB.viewImage("Image B");
        imgC.viewImage("media entre A e B");
    }

    private static void imgNot() {
        Image imgA = new Image(Main.Image_B);
        Image imgC = Processador.not(imgA);
        imgA.viewImage("Image A");
        imgC.viewImage("Não A");
    }

    private static void imgEqualization() {
        //Image imgA = new Image("imagens/cinza2.png");
        Image imgA = new Image("imagens/lenna.jpg");
        Histogram hist = new Histogram(imgA);
        Image imgB = hist.getImage();
        imgA.viewImage("Image A");
        imgB.viewImage("Image B - Equalized");
        imgB.save("Image B Quantize", "imagens_out/");
    }
    //26.m'
    private static void imgStretchin(){
        Image imgE = new Image(Main.Image_MASSA);
        imgE.viewImage("Imagem MASSA");
        //Stretchin img1 = new Stretchin();
        
        Stretchin.linear(imgE, 1.4f, 10f).viewImage("Imagem MASSA 1.4");
        Stretchin.linear(imgE, 1.6f, 0f).viewImage("Imagem MASSA 1.2");
        Stretchin.linear(imgE, 0.2f, 20f).viewImage("Imagem MASSA 0.2");

        Stretchin.minMax(Stretchin.linear(imgE, 1.4f, 10f)).viewImage("Imagem MASSA 1.4");
        Stretchin.minMax(Stretchin.linear(imgE, 1.6f, 0f)).viewImage("Imagem MASSA 1.2");
        Stretchin.minMax(Stretchin.linear(imgE, 0.2f, 20f)).viewImage("Imagem MASSA 0.2");
    }

    private static void imgDithering(){
        int limiar[] = {16, 64, 128};
        Image imgE = new Image(Main.Image_MASSA);
        imgE.viewImage("Imagem MASSA");

        // limiar simles
        // Dithering.limiarSimples(imgE, limiar[2]).viewImage("Limiar 128");
        // Dithering.limiarSimples(imgE, limiar[1]).viewImage("Limiar 64");
        // Dithering.limiarSimples(imgE, limiar[0]).viewImage("Limiar 16");

        // limiar periodico disperso
        // pata ficar com valores entre 0-3, na linha 17 do metodo quantize e 
        // altere para mat[c][y][x] = (int) (cor), isso porque classe trabalhar 
        // com apenas 256  niveis de cinza 
        int N[][] = {{0,2},{3,1}};
        Dithering.limiarPeriodicoDisperso(imgE, N).viewImage("Periodico Disperso 2x2");
    }

 

    
    private static void imgDitheringSimples(){
        int limiar[] = {16, 64, 128};
        Image imgE = new Image(Main.Image_MASSA);
        imgE.viewImage("Imagem MASSA");

        // limiar simles
        Dithering.limiarSimples(imgE, limiar[2]).viewImage("Limiar 128");
        Dithering.limiarSimples(imgE, limiar[1]).viewImage("Limiar 64");
        Dithering.limiarSimples(imgE, limiar[0]).viewImage("Limiar 16");

         }
    private static void imgMorfologiaBin(){

        Image imgE = new Image(Main.Image_B);
        //Image imgE = new Image(Main.Image_MASSA);
        imgE.viewImage("Imagem B");
        int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
        MorfologiaBin.erosao(imgE, EE).viewImage("Image Erosão");
    }





    private class MyMenuItem extends JMenuItem 
      implements ActionListener {
      public MyMenuItem(String text) {
         super(text);
         addActionListener(this);
      }
      public void actionPerformed(ActionEvent e) {
         //System.out.println("Item clicked: "+e.getActionCommand());
      }
    
        
    }

    private static void Menu(){
        JFrame frame = new JFrame();
        frame.setTitle("Menu Example");
        frame.setSize(500, 500);

        // Cria uma barra de menu para o JFrame
        JMenuBar menuBar = new JMenuBar();

        // Adiciona a barra de menu ao  frame
        frame.setJMenuBar(menuBar);

        // Define e adiciona dois menus drop down na barra de menus
        JMenu ditheMenu = new JMenu("Dithering");
        JMenu morfoBinMenu = new JMenu("Morfologia Bin");
        //ditheMenu.add()
        menuBar.add(ditheMenu);
        menuBar.add(morfoBinMenu);

        JMenuItem limiarSimple = new JMenuItem("Limiar simples");
        JMenuItem limiarAlea = new JMenuItem("modulação aleatória");
        JMenuItem modulaOrdePerio = new JMenuItem("modulação ordenada periódico aglomerado");
        JMenuItem modulPerioDisper = new JMenuItem("Modulação Periodica Disperso");
        JMenuItem modulOrdenaAperi = new JMenuItem("modulação ordenada aperiódico");

        JMenuItem eros = new JMenuItem("Erosão");
        JMenuItem dilata = new JMenuItem("Dilatação");
        JMenuItem abert = new JMenuItem("Abertura");
        JMenuItem fechament = new JMenuItem("Fechamento");
        JMenuItem bord_int = new JMenuItem("Borda interna");
        JMenuItem board_ext = new JMenuItem("Borda externa");


        ditheMenu.add(limiarSimple);
        ditheMenu.add(limiarAlea);
        ditheMenu.add(modulaOrdePerio);
        ditheMenu.add(modulPerioDisper);
        ditheMenu.add(modulOrdenaAperi);
        ditheMenu.addSeparator();
        morfoBinMenu.add(eros);
        morfoBinMenu.add(dilata);
        morfoBinMenu.add(abert);
        morfoBinMenu.add(fechament);
        morfoBinMenu.add(bord_int);
        morfoBinMenu.add(board_ext);
        //ditheMenu.addSeparator();

        limiarSimple.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {    
                imgDitheringSimples();
                System.out.println("Clicou.....");
            }
        });

        modulPerioDisper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {    
                imgDithering();
                System.out.println("Clicou.....");
            }
        });



        frame.setVisible(true);
    }
   



}

