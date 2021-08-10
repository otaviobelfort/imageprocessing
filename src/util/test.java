package util;

public class test {



/*
// Correct, but creates a copy of the image which is inefficient
int[][] dilate(int[][] image){
    int[][] imagecopy = new int[image.length][image[0].length];
    for (int i=0; i<image.length; i++){
        for (int j=0; j<image[i].length; j++){
            if (image[i][j] == 1){
                imagecopy[i][j] = 1;
                if (i>0) imagecopy[i-1][j] = 1;
                if (j>0) imagecopy[i][j-1] = 1;
                if (i+1<image.length) imagecopy[i+1][j] = 1;
                if (j+1<image[i].length) imagecopy[i][j+1] = 1;
            }
        }
    }
    return imagecopy;


    for every dst pixel location (x,y){
        dst[x][y] = src[x][y];
        for (i = -xKey; i < M - xKey; i++){
           for (j = -yKey; j < N - yKey; j++){
             if(src[x+i,y+i]==1 && Key(xKey+i, yKey+j)==1){
                dst[x][y] = 1; break;
              }
           }
        }
}

*/


/* 
public static Image dilatacao(Image img, int EE[][]){
    // possui aepnas um channel
    int raio = (EE.length -1) / 2;
    int alt = img.getHeight();
    int larg = img.getWidth();
    int [][][]matIn = img.getMatriz();
    int [][][]matOut = new int[1][alt][larg];
    int color;
    boolean trueIgual;
    //EE == percorrer o elemento estruturante
    //trueIgual: para não percorrer desnecessariamente EE
    for (int y = 0; y < alt-raio; y++) {
        for (int x = 0; x < larg-raio; x++) { 
            trueIgual =  true;
            for( int dy = -raio; trueIgual && dy <= raio; dy++){ 
                for( int dx = -raio;trueIgual && dx <= raio; dx++){
                    if(EE[dy+raio][dx+raio] != -1){
                        //color = matIn[0][y+dy][x+dx];
                        //matOut[0][y+dy][x+dx] = 1;
                        //trueIgual = matIn[0][y+dy][x+dx] != EE[dy+raio][dx+raio];
                        if(dy>0){ }
                    }

                }  
            }
            matOut[0][y][x] = trueIgual?255:0;
        }
    }
    return new Image(matOut);
} */

/* 
    public static Image abertura(Image img, int EE[][]){
        Image open,outIn;
        int matOu[][];
        open = erosao(img, EE);
        outIn = dilatacao(open, EE);
        //return new Image(dilatacao(erosao(img, EE), EE));
        return new Image(outIn.getHeight(), outIn.getWidth(),outIn.getChannel());
        
        
    }
    public static Image fechamento(Image img, int EE[][]){
        Image open,outIn;
        int matOu[][];
        open = dilatacao(img, EE);
        outIn = erosao(open, EE);
        //return new Image(dilatacao(erosao(img, EE), EE));
        return new Image(outIn.getHeight(), outIn.getWidth(),outIn.getChannel());
        
        
    }
    

 */



/* public static Image erosao(Image img, int EE[][]){
    // possui aepnas um channel
    int raio = (EE.length -1) / 2;
    int alt = img.getHeight();
    int larg = img.getWidth();
    int [][][]matIn = img.getMatriz();
    int [][][]matOut = new int[1][alt][larg];
    int color ;
    int c = img.getChannel();
    System.out.print("\n Erosão: r= " + raio + "\n Alt = " + alt + " \n EE = " + EE.length);

    boolean trueIgual;
    //EE == percorrer o elemento estruturante
    //trueIgual: para não percorrer desnecessariamente EE
   // for (int cc = 0; cc< c;cc++ ){
        for (int y = raio; y < alt-raio; y++) {
            for (int x = raio; x < larg-raio; x++) { 
                
                trueIgual = true;
                for( int dy = -raio; dy < raio+1; dy++){ 
                    for( int dx = -raio; dx < raio+1; dx++){

                        System.out.print("EE["+ (dy+raio) + "][" + (dx+raio) +"]\n");
                        if(EE[dy+raio][dx+raio] == 255){
                        System.out.print("EE["+ (dy+raio) + "][" + (dx+raio) +"]\n");
                        trueIgual = matIn[0][y+dy][x+dx] != EE[dy+raio][dx+raio];    
                        if(trueIgual){
                            trueIgual = false;
                        }
                        // color = matIn[0][y+dy][x+dx];
                       // System.out.print("\n y = " + y + "\n dy = " + dy);
                            
                            
                        }

                    }  
                }
                matOut[0][y][x] = trueIgual?matIn[0][y][x]:0; // EE[][]
            }
        }
   // }
    return new Image(matOut);
} */


/* public static Image limiarPeriodicoAglomerado(Image imgIn, int N[][]){
    // dimensão
    int tam = N.length;
    int BLACK = 0;
    int WHITE = 255;
    int i, j;
    // trabalha com 256 niveis de cinza
    // NxN+1
    Image imgQuantize = Processador.quantize(imgIn, 256,tam*tam+1);
    //imgQuantize.viewImage("Image Quantize");
    int nChannel = imgQuantize.getChannel(); 
    int alt = imgQuantize.getHeight();
    int larg = imgQuantize.getWidth();
    int [][][]matIn = imgQuantize.getMatriz();
    int [][][]matOut = new int[nChannel][alt][larg];
    int color;
    for (int c = 0; c < nChannel; c++) {
        for (int y = 0; y < alt; y++) {
            for (int x = 0; x < larg; x++){
                //color = matIn[c][y][x];
                for (i = 0; i < N.length ; i++){
                    for (j = 0; j < N.length ; j++){
                       if( matIn[c][y][x] > N[i][j]){
                           matOut[c][y+i][x+j] = WHITE ;
                       }else{
                            matOut[c][y+i][x+j] = BLACK;
                       }

                        ///matOut[c][y+(-1+i)][x+(-1+j)] = color < N[i][j] ? BLACK : WHITE;
                    }
                }
            }

        }

    }
    return new Image(matOut); 
} */

}

