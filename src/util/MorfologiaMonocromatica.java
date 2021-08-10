package util;

public class MorfologiaMonocromatica {
    public static Image erosao(Image img, int EE[][]){
        int raio = (EE.length -1) / 2;
        int alt = img.getHeight();
        int larg = img.getWidth();
        int [][][]matIn = img.getMatriz();
        int nChannel = img.getChannel();
        int [][][]matOut = new int[nChannel][alt][larg];
        int color ;
        //System.out.print("\n Erosão: c= " + nChannel + "\n Alt = " + alt + " \n EE = " + EE.length);
        int min = 1000000000;
        int cont;
        
       for (int c = 0; c < nChannel;c++ ){
        //System.out.print("\n Erosão: r= " + c );
            for (int y = raio; y < alt-raio; y++) {
                for (int x = raio; x < larg-raio; x++) { 
                    min = 1000000000; 
                    for( int dy =-raio; dy <= raio; dy++){ //EE == percorrer o elemento estruturante
                        for( int dx = -raio; dx <= raio; dx++){
                            if(min > matIn[c][y+dy][x+dx] + EE[dy+raio][dx+raio]){  

                                min = matIn[c][y+dy][x+dx] + EE[dy+raio][dx+raio];    
                            } 
                        }   
                    }
                    matOut[c][y][x] = min > 0?min:0; 
                    
                }
                
            }
        }
        return new Image(matOut);
    }

    public static Image dilatacao(Image img, int EE[][]){
        int raio = (EE.length -1) / 2;
        int alt = img.getHeight();
        int larg = img.getWidth();
        int nChannel = img.getChannel();
        int [][][]matIn = img.getMatriz();
        int [][][]matOut = new int[nChannel][alt][larg];
        int color ;
        
        //System.out.print("\n Erosão: r= " + raio + "\n Alt = " + alt + " \n EE = " + EE.length);
        int max = -1000000000;
        int cont;

       for (int c = 0; c < nChannel;c++ ){
            for (int y = raio; y < alt-raio; y++) {
                for (int x = raio; x < larg-raio; x++) { 
                    max = -1000000000; 
                    for( int dy =-raio; dy <= raio; dy++){ 
                        for( int dx = -raio; dx <= raio; dx++){
                            if(max < matIn[c][y+dy][x+dx] + EE[dy+raio][dx+raio]){  

                                max = matIn[c][y+dy][x+dx] + EE[dy+raio][dx+raio];    
                            } 
                        }   
                    }
                    matOut[c][y][x] = max > 0?max:0; 
                    
                }
                
            }
        }
        return new Image(matOut);
    }

    public static Image abertura(Image img, int EE[][]){
        Image imgErosao,matOut;
        //int matOu[][];
        imgErosao = erosao(img, EE);
        matOut = dilatacao(imgErosao, EE);
        //return new Image(dilatacao(erosao(img, EE), EE));
        //Processador imgOut = new Processador();
        //im.viewImage(matOut);
        return matOut;
        
    }
    public static Image fechamento(Image img, int EE[][]){
        Image imgDilatacao,matOut;
        //int matOu[][];
        imgDilatacao = dilatacao(img, EE);
        matOut = erosao(imgDilatacao, EE);
        //return new Image(dilatacao(erosao(img, EE), EE));
        //Processador imgOut = new Processador();
        //im.viewImage(matOut);
        return matOut;
        
    }

    public static Image gradiente(Image img, int EE[][]){
        Image imgDilatacao,imgErosao, matOut;
        //int matOu[][];
        imgDilatacao = dilatacao(img, EE);
        imgErosao = erosao(img, EE);
        matOut = Processador.subtraction(imgDilatacao, imgErosao);
        //return new Image(dilatacao(erosao(img, EE), EE));
        //Processador imgOut = new Processador();
        //im.viewImage(matOut);
        return matOut;
        
    }

    
}
