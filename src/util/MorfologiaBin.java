package util;

public class MorfologiaBin {
    public static Image erosao(Image img, int EE[][]){
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
                            color = matIn[0][y+dy][x+dx];
                            trueIgual = matIn[0][y+dy][x+dx] == EE[dy+raio][dx+raio];
                            
                        }

                    }  
                }
                matOut[0][y][x] = trueIgual?255:0; // EE[][]
            }
        }
        return new Image(matOut);
    }

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
    }

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
    


}



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