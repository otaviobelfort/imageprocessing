package util;

import java.util.Random;

public class Dithering {
    public static Image limiarSimples(Image img, int limiar){
        int nChannel = img.getChannel(); 
        int alt = img.getHeight();
        int larg = img.getWidth();
        int [][][]matIn = img.getMatriz();
        int [][][]matOut = new int[nChannel][alt][larg];
        int color;
        int black = 0;
        int white = 255;
        for (int c = 0; c < nChannel; c++) {
            for (int y = 0; y < alt; y++) {
                for (int x = 0; x < larg; x++) {
                    color = matIn[c][y][x] ;
                    matOut[c][y][x] = color < limiar ? black : white;  

                }
            }
        }

        return new Image(matOut);

    }

    public static Image modulacaoAleatoria(Image img, int limiar){
        int nChannel = img.getChannel(); 
        int alt = img.getHeight();
        int larg = img.getWidth();
        int [][][]matIn = img.getMatriz();
        int [][][]matOut = new int[nChannel][alt][larg];
        int color;
        int black = 0;
        int white = 255;
        Random random = new Random();

        for (int c = 0; c < nChannel; c++) {
            for (int y = 0; y < alt; y++) {
                for (int x = 0; x < larg; x++) {
                    // temp -> pixel[x][y] + random() 
                    // pertubação aleatória
                    color = matIn[c][y][x] + (random.nextInt(15) + random.nextInt(-10));
                    matOut[c][y][x] = color < limiar ? black : white;  

                }
            }
        }

        return new Image(matOut);

    }

    public static Image modulacaoOrdenada(Image img, int limiar){
        int nChannel = img.getChannel(); 
        int alt = img.getHeight();
        int larg = img.getWidth();
        int [][][]matIn = img.getMatriz();
        int [][][]matOut = new int[nChannel][alt][larg];
        int color;
        int black = 0;
        int white = 255;
        for (int c = 0; c < nChannel; c++) {
            for (int y = 0; y < alt; y++) {
                for (int x = 0; x < larg; x++) {
                    color = matIn[c][y][x] ;
                    matOut[c][y][x] = color < limiar ? black : white;  

                }
            }
        }

        return new Image(matOut);

    }

    // testar matriz  2x2, 3x3, 6x6
    public static Image limiarPeriodicoDisperso(Image imgIn, int N[][]){
        // dimensão
        int tam = N.length;
        int black = 0;
        int white = 255;
        int i, j;
        // trabalha com 256 niveis de cinza
        Image imgQuantize = Processador.quantize(imgIn, 256,tam*tam);
        imgQuantize.viewImage("Image Quantize");
        int nChannel = imgQuantize.getChannel(); 
        int alt = imgQuantize.getHeight();
        int larg = imgQuantize.getWidth();
        int [][][]matIn = imgQuantize.getMatriz();
        int [][][]matOut = new int[nChannel][alt][larg];
        int color;
        for (int c = 0; c < nChannel; c++) {
            for (int y = 0; y < alt; y++) {
                j = y % tam;
                for (int x = 0; x < larg; x++){
                    i =  x % tam;
                    color = matIn[c][y][x];
                    matOut[c][y][x] = color < N[j][i] ? black : white;
                }

            }

        }
        return new Image(matOut); 
    }


    // Um ponto na matriz de entrada quantizada = Conjunto de pontos na de saida
    // Imagem de saida será sempre maior que a de entrada
    public static Image limiarPeriodicoAglomerado(Image imgIn, int N[][]){
        // dimensão
        int tam = N.length;
        int black = 0;
        int white = 255;
        int i, j;
        // trabalha com 256 niveis de cinza
        // NxN+1
        Image imgQuantize = Processador.quantize(imgIn, 256,tam*tam+1);
        imgQuantize.viewImage("Image Quantize");
        int nChannel = imgQuantize.getChannel(); 
        int alt = imgQuantize.getHeight();
        int larg = imgQuantize.getWidth();
        int [][][]matIn = imgQuantize.getMatriz();
        int [][][]matOut = new int[nChannel][alt][larg];
        int color;
        for (int c = 0; c < nChannel; c++) {
            for (int y = 0; y < alt; y++) {
               // j = y;
                for (int x = 0; x < larg; x++){
                    //i =  x;
                    color = matIn[c][y][x];
                    int linha = x;

                    for (i = 0; i < larg; i++){
                        for (j = 0; j < larg; j++){
                            matOut[c][y+(-1+i)][x+(-1+j)] = color < N[i][j] ? black : white;
                        }
                    }
                }

            }

        }
        return new Image(matOut); 
    }


    public static Image limiarAperiodicoDisperso(Image imgIn, int N[][]){
        // dimensão
        int tam = N.length;
        int black = 0;
        int white = 255;
        int i, j;
      
        Image imgQuantize = Processador.quantize(imgIn, 256,tam*tam+1);
        imgQuantize.viewImage("Image Quantize");
        int nChannel = imgQuantize.getChannel(); 
        int alt = imgQuantize.getHeight();
        int larg = imgQuantize.getWidth();
        int [][][]matIn = imgQuantize.getMatriz();
        int [][][]matOut = new int[nChannel][alt][larg];
        int color, error;
        for (int c = 0; c < nChannel; c++) {
            for (int y = 0; y < alt-1; y++) {
               j = y;
                for (int x = 0; x < larg-1; x++){
                    i =  x;
                    color = matIn[c][y][x];
                    
                    matOut[c][y][x] = color < N[i][j] ? black : white;
                    if(color < N[i][j]){
                        error = matOut[c][y][x] - black;
                        matOut[c][y][x] = black;
                    }else{
                        error = matOut[c][y][x] - white;
                        matOut[c][y][x] = white;
                    }
                    matOut[c][y+1][x] += (3/8)*error;
                    matOut[c][y][x+1] += (3/8)*error;
                    matOut[c][y+1][x+1] += (2/8)*error;
                }

            }

        }
        return new Image(matOut); 
    }
}
