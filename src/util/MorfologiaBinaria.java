package util;

public class MorfologiaBinaria {
    public static Image erosao2(Image img, int EE[][]){
        // possui aepnas um channel
        int raio = (EE.length -1) / 2;
        int alt = img.getHeight();
        int larg = img.getWidth();
        int [][][]matIn = img.getMatriz();
        int [][][]matOut = new int[1][alt][larg];
        //System.out.print("\n Erosão: r= " + raio + "\n Alt = " + alt + " \n EE = " + EE.length);

        boolean trueIgual;
        //EE == percorrer o elemento estruturante
        //trueIgual: para não percorrer desnecessariamente EE
       // for (int cc = 0; cc< c;cc++ ){
        for (int y = raio; y < alt-raio; y++) {
            for (int x = raio; x < larg-raio; x++) { 
                trueIgual = true;
                for( int dy =-raio; trueIgual && dy <= raio; dy++){ 
                    for( int dx =-raio; trueIgual && dx <= raio; dx++){
                        if(EE[dy+raio][dx+raio]!=-1){                            
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
        int color ;
        int c = img.getChannel();
        //System.out.print("\n Erosão: r= " + raio + "\n Alt = " + alt + " \n EE = " + EE.length);

        boolean trueIgual;
        //EE == percorrer o elemento estruturante
        //trueIgual: para não percorrer desnecessariamente EE
       // for (int cc = 0; cc< c;cc++ ){
        for (int y = raio; y < alt-raio; y++) {
            for (int x = raio; x < larg-raio; x++) { 
                trueIgual = true;
                for( int dy =-raio; trueIgual && dy <= raio; dy++){ 
                    for( int dx = -raio; trueIgual && dx <= raio; dx++){
                        if(EE[dy+raio][dx+raio] != -1){                            
                            trueIgual = matIn[0][y+dy][x+dx] != EE[dy+raio][dx+raio];    
                        } 
                    }   
                }
                matOut[0][y][x] = trueIgual?0:255; // EE[][]
            }
            
        }
        return new Image(matOut);
    }

    public static Image abertura(Image img, int EE[][]){
        Image imgErosao,matOut;
        //int matOu[][];
        imgErosao = erosao2(img, EE);
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
        matOut = erosao2(imgDilatacao, EE);
        //return new Image(dilatacao(erosao(img, EE), EE));
        //Processador imgOut = new Processador();
        //im.viewImage(matOut);
        return matOut;
        
    }

    public static Image bordaInterna(Image img, int EE[][]){
        Image imgErosao,subtract;
        imgErosao = erosao2(img, EE);
        subtract =  Processador.subtraction(img, imgErosao);
        return subtract;
        
    }
    public static Image bordaExterna(Image img, int EE[][]){
        Image imgDilatacao,subtract;
        imgDilatacao = dilatacao(img, EE);
        subtract = Processador.subtraction(imgDilatacao, img);
        return subtract;
        
    }
}
