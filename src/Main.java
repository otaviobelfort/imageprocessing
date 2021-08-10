
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
import util.MorfologiaBinaria;
import util.MorfologiaMonocromatica;
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
        //imgMorfologiaBin();
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

    // ----------------------------------------------
    // ---- ### Dithering
    // ----------------------------------------------
    // ------  Limiar simples;
    private static void imgDitheringSimples(){
        int limiar[] = {16, 64, 128};
        Image imgE = new Image(Main.Image_MASSA);
        imgE.viewImage("Imagem MASSA");
        Dithering.limiarSimples(imgE, limiar[2]).viewImage("Simples - Limiar 128");
        Dithering.limiarSimples(imgE, limiar[1]).viewImage("Simples - Limiar 64");
        //Dithering.limiarSimples(imgE, limiar[0]).viewImage("Limiar 16");

    }

    // ------  Limiar com modulação aleatória
    private static void imgDitheringAleatorio(){
        int limiar[] = {16, 64, 128};
        Image imgE = new Image(Main.Image_MASSA);
        imgE.viewImage("Imagem MASSA");
        Dithering.limiarodulacaoAleatoria(imgE, 128).viewImage("Aleatorio - Limiar 128");
        Dithering.limiarodulacaoAleatoria(imgE, 64).viewImage("Aleatorio - Limiar 64");
        //Dithering.limiarSimples(imgE, limiar[0]).viewImage("Limiar 16");

    }

    // ------  Limiar com modulação ordenada periódico aglomerado
    private static void imgDitheringAglomerado(){
        int N[][] = {{0,1},{3,1}};
        int N2[][] = {{6, 8, 4}, {1, 0, 3}, {5, 2, 7}};
        Image imgA = new Image("imagens/ariele.png");
        imgA.viewImage("Imagem ariele");
        Dithering.limiarPeriodicoAglomerado(imgA, N2).viewImage("Periodico Aglomerado");
    }

    // ------ Limiar com modulação ordenada periódico disperso;
    private static void imgDitheringDisperso(){
        int limiar[] = {16, 64, 128};
        Image imgE = new Image(Main.Image_MASSA);
        imgE.viewImage("Imagem MASSA");

        // limiar periodico disperso
        // pata ficar com valores entre 0-3, na linha 17 do metodo quantize e 
        // altere para mat[c][y][x] = (int) (cor), isso porque classe trabalhar 
        // com apenas 256  niveis de cinza 
        int N[][] = {{0,2},{3,1}};
        Dithering.limiarPeriodicoDisperso(imgE, N).viewImage("Periodico Disperso 2x2");
    }                                                                                                                                                                                                                                                                                                                                                                                                                   
    
    // ------ Limiar com modulação ordenada aperiódico 
    private static void imgDitheringAperiodico(){
        int N2[][] = {{6, 8, 4}, {1, 0, 3}, {5, 2, 7}};
        Image imgA = new Image("imagens/ariele.png");
        imgA.viewImage("Imagem ariele");
        Dithering.limiarAperiodicoDisperso(imgA).viewImage("Aperiodico Disperso");
   
    }

    // ----------------------------------------------
    // ---- ### Morfologia matemática binária
    // ---------------------------------------------- 
    private static void imgMorfologiaBinariaErosao(){
        Image imgB = new Image("imagens/b.png");
        //Image imgE = new Image(Main.Image_MASSA);
        imgB.viewImage("Imagem B");
        int EE1[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
        int EE[][] = {{0, 0, 255, 0, 0}, {0, 255, 255, 255, 0}, {
            255, 255, 255, 255, 255}, {0, 255, 255, 255, 0}, {0, 0, 255, 0, 0}};

        MorfologiaBinaria.erosao2(imgB, EE1).viewImage("Image Erosão");
    }

    private static void imgMorfologiaBinariaDilatacao(){
        Image imgB = new Image("imagens/b.png");
        //Image imgE = new Image(Main.Image_MASSA);
        imgB.viewImage("Imagem B");
        int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
        
        MorfologiaBinaria.dilatacao(imgB, EE).viewImage("Image B - Erosão");
    }

    
    private static void imgMorfologiaBinariaAbertura(){

        Image imgB = new Image("imagens/b.png");
        //Image imgE = new Image(Main.Image_MASSA);
        imgB.viewImage("Imagem B");
        int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
        
        MorfologiaBinaria.abertura(imgB, EE).viewImage("Image B - Abertura");
    }

    private static void imgMorfologiaBinariaFechamento(){

        Image imgB = new Image("imagens/b.png");
        //Image imgE = new Image(Main.Image_MASSA);
        imgB.viewImage("Imagem B");
        int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
        
        MorfologiaBinaria.fechamento(imgB, EE).viewImage("Image B - Fechamento");
    }

    private static void imgMorfologiaBinariaBordaInterna(){

        Image imgB = new Image("imagens/b.png");
        //Image imgE = new Image(Main.Image_MASSA);
        imgB.viewImage("Imagem B");
        int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
        
        MorfologiaBinaria.bordaInterna(imgB, EE).viewImage("Image B - Borda Interna");
    }

    private static void imgMorfologiaBinariaBordaExterna(){

        Image imgB = new Image("imagens/b.png");
        //Image imgE = new Image(Main.Image_MASSA);
        imgB.viewImage("Imagem B");
        int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
        
        MorfologiaBinaria.abertura(imgB, EE).viewImage("Image B - Borda Externa");
    }


// -------------------------------------------------------
// Morfologia monocromatica
//--------------------------------------------------------
private static void imgMorfologiaMonocromaticaDilatacao(){

    //Image imgB = new Image("imagens/julia.png");
    Image imgB = new Image("imagens/lenna.jpg");
    //Image imgE = new Image(Main.Image_MASSA);
    imgB.viewImage("Imagem lenna");
    int EE1[][] = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {
        1, 1, 2, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
    int EE3[][] = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1}};
    int EE2[][] = {{255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}, {
            255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}};

    int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
    
    MorfologiaMonocromatica.dilatacao(imgB, EE2).viewImage("Morfo...Mono - Dilatação");
}

private static void imgMorfologiaMonocromaticaErosao(){

    Image imgB = new Image("imagens/cinza1.png");
    //Image imgB = new Image("imagens/lenna.jpg");
    //Image imgE = new Image(Main.Image_MASSA);
    imgB.viewImage("Imagem B");
    int EE1[][] = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {
        1, 1, 2, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
    int EE3[][] = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1}};
    int EE2[][] = {{255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}, {
            255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}};

    int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
    
    MorfologiaMonocromatica.erosao(imgB, EE1).viewImage("Morfo...Mono - Erosao");
}

private static void imgMorfologiaMonocromaticaAbertura(){

    Image imgB = new Image("imagens/julia.png");
    //Image imgB = new Image("imagens/lenna.jpg");
    //Image imgE = new Image(Main.Image_MASSA);
    imgB.viewImage("Imagem B");
    int EE1[][] = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {
        1, 1, 2, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
    int EE3[][] = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1}};
    int EE2[][] = {{255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}, {
            255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}};

    int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
    
    MorfologiaMonocromatica.erosao(imgB, EE1).viewImage("Morfo...Mono - Abertura");
}

private static void imgMorfologiaMonocromaticaFechamento(){

    //Image imgB = new Image("imagens/cinza1.png");
    Image imgB = new Image("imagens/lenna.jpg");
    //Image imgE = new Image(Main.Image_MASSA);
    imgB.viewImage("Imagem B");
    int EE1[][] = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {
        1, 1, 2, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
    int EE3[][] = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1}};
    int EE2[][] = {{255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}, {
            255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}};

    int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
    
    MorfologiaMonocromatica.erosao(imgB, EE1).viewImage("Morfo...Mono - Fechamento");
}

private static void imgMorfologiaMonocromaticaGradiente(){

    //Image imgB = new Image("imagens/julia.png");
    Image imgB = new Image("imagens/lenna.jpg");
    //Image imgE = new Image(Main.Image_MASSA);
    imgB.viewImage("Imagem B");
    int EE1[][] = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {
        1, 1, 2, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
    int EE3[][] = {{1, 1, 1}, {1, 2, 1}, {1, 1, 1}};
    int EE2[][] = {{255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}, {
            255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}, {255, 255, 255, 255, 255}};

    int EE[][] = {{255,255,255}, {255,255,255}, {255,255,255}};
    
    MorfologiaMonocromatica.erosao(imgB, EE3).viewImage("Morfo...Mono - Gradiente");
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

    // --------------------------
    // ########## MENU ##########
    //---------------------------
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
        JMenu morfoBinMenu = new JMenu("Morfologia Binaria");
        JMenu morfoMonoMenu = new JMenu("Morfologia Monocromatica");
        //ditheMenu.add()
        menuBar.add(ditheMenu);
        menuBar.add(morfoBinMenu);
        menuBar.add(morfoMonoMenu);

        JMenuItem limiarSimple = new JMenuItem("Limiar simples");
        JMenuItem limiarAlea = new JMenuItem("modulação aleatória");
        JMenuItem modulaOrdePerioAglomerado = new JMenuItem("modulação ordenada periódico aglomerado");
        JMenuItem modulPerioDisper = new JMenuItem("Modulação Periodica Disperso");
        JMenuItem modulOrdenaAperi = new JMenuItem("modulação ordenada aperiódico");

        JMenuItem erosao = new JMenuItem("Erosão");
        JMenuItem dilatacao = new JMenuItem("Dilatação");
        JMenuItem abertura = new JMenuItem("Abertura");
        JMenuItem fechamento = new JMenuItem("Fechamento");
        JMenuItem borda_interna = new JMenuItem("Borda interna");
        JMenuItem borda_externa = new JMenuItem("Borda externa");

        JMenuItem erosao_mo = new JMenuItem("Erosão");
        JMenuItem dilatacao_mo = new JMenuItem("Dilatação");
        JMenuItem abertura_mo = new JMenuItem("Abertura");
        JMenuItem fechamento_mo = new JMenuItem("Fechamento");
        JMenuItem gradiente = new JMenuItem("Gradiente");
        //JMenuItem borda_externa_mo = new JMenuItem("Borda externa");


        ditheMenu.add(limiarSimple);
        ditheMenu.add(limiarAlea);
        ditheMenu.add(modulaOrdePerioAglomerado);
        ditheMenu.add(modulPerioDisper);
        ditheMenu.add(modulOrdenaAperi);
        ditheMenu.addSeparator();
        morfoBinMenu.add(erosao);
        morfoBinMenu.add(dilatacao);
        morfoBinMenu.add(abertura);
        morfoBinMenu.add(fechamento);
        morfoBinMenu.add(borda_interna);
        morfoBinMenu.add(borda_externa);
        ditheMenu.addSeparator();
        morfoMonoMenu.add(erosao_mo);
        morfoMonoMenu.add(dilatacao_mo);
        morfoMonoMenu.add(abertura_mo);
        morfoMonoMenu.add(fechamento_mo);
        morfoMonoMenu.add(gradiente);
        //morfoMonoMenu.add(borda_externa);
        //morfoMonoMenu.addSeparator();

        // ----------------------------------------------
        // ---- ### Dithering
        // ----------------------------------------------
        limiarSimple.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {    
                imgDitheringSimples();
                
            }
        });

        limiarAlea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {    
                imgDitheringAleatorio();
                
            }
        });

        modulaOrdePerioAglomerado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {    
                imgDitheringAglomerado();
                
            }
        });

        modulPerioDisper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {    
                imgDitheringDisperso();
                
            }
        });

        modulOrdenaAperi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {    
                imgDitheringAperiodico();
                
            }
        });

        // ----------------------------------------------
        // ---- ### Morfologia matemática binária
        // ----------------------------------------------
        erosao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { 
                imgMorfologiaBinariaErosao();
                //System.out.println("Clicou.....em Morfologia Binaria -> Erosão");
            }
        });

        dilatacao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { 
                imgMorfologiaBinariaDilatacao();
                
            }
        });
        abertura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { 
                imgMorfologiaBinariaAbertura();
                
            }
        });
        fechamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {    
                imgMorfologiaBinariaFechamento();
                
            }
        });

        borda_externa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {   
                imgMorfologiaBinariaBordaExterna();
                
            }
        });

        borda_interna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { 
                imgMorfologiaBinariaBordaInterna();
                
            }
        });

        // ----------------------------------------------
        // ---- ### Morfologia matemática monocromática
        // ----------------------------------------------
        dilatacao_mo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { 
                imgMorfologiaMonocromaticaDilatacao();
                
            }
        });
        
        erosao_mo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { 
                imgMorfologiaMonocromaticaErosao();
                
            }
        });

        fechamento_mo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { 
                imgMorfologiaMonocromaticaFechamento();
                
            }
        });

        abertura_mo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { 
                imgMorfologiaMonocromaticaAbertura();
                
            }
        });

        gradiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) { 
                imgMorfologiaMonocromaticaGradiente();
                
            }
        });

        frame.setVisible(true);
    }
   



}

