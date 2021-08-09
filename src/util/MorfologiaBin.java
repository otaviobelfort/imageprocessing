package util;

public class MorfologiaBin {
    
    public static Image erosao(Image img, int EE[][]){
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
                    for( int dy = -raio; trueIgual && dy <= raio+1; dy++){ 
                        for( int dx = -raio; trueIgual && dx <= raio+1; dx++){

                            System.out.print("EE["+ (dy+raio) + "][" + (dx+raio) +"]\n");
                            if(EE[dy+raio][dx+raio] != -1){
                                System.out.print("EE["+ (dy+raio) + "][" + (dx+raio) +"]\n");
                                
                                trueIgual = matIn[0][y+dy][x+dx] != EE[dy+raio][dx+raio];    
                            } // color = matIn[0][y+dy][x+dx];  // System.out.print("\n y = " + y + "\n dy = " + dy);   
                        }   
                    }
                    matOut[0][y][x] = trueIgual?255:0; // EE[][]
                }
                
            }
            return new Image(matOut);
        }
        
    

    /* public static Image dilatacao(Image img, int EE[][]){
        int BLACK = 0;
        int WHITE = 255;
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
                            trueIgual = matIn[0][y+dy][x+dx] != EE[dy+raio][dx+raio];
                            
                        }

                    }  
                }
                matOut[0][y][x] = trueIgual? WHITE:BLACK; // EE[][]
            }
        }
        return new Image(matOut);
    } */



}

