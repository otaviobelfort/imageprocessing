package util;

public class MorfologiaBin {
    public static Image erosao(Image img, int EE[][]){
        // possui aepnas um channel
        int raio = (EE.length -1) / 2;
        int alt = img.getHeight();
        int larg = img.getWidth();
        int [][][]matIn = img.getMatriz();
        int [][][]matOut = new int[1][alt][larg];
        //int color;
        boolean trueIgual;
        //EE == percorrer o elelmento estruturante
        //trueIgual: para não percorrer desnecessariamente EE
        for (int y = 0; y < alt-raio; y++) {
            for (int x = 0; x < larg-raio; x++) { 
                trueIgual =  true;
                for( int dy = -raio; trueIgual && dy <= raio; dy++){ 
                    for( int dx = -raio;trueIgual && dx <= raio; dx++){
                        if(EE[dy+raio][dx+raio] != -1){
                            //color = matIn[c][y+dy][x+dx];
                            trueIgual = matIn[0][y+dy][x+dx] == EE[dy+raio][dx+raio];

                        }

                    }  
                }
                matOut[0][y][x] = trueIgual?255:0;
            }
        }
        return new Image(matOut);
    }
}
