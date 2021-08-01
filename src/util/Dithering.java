package util;


public class Dithering {
    public static Image limiarSimples(Image img, int limiar){
        int nChannel = img.getChannel(); //rgb ou gray
        int alt = img.getHeight();
        int larg = img.getWidth();
        int [][][]matIn = img.getMatriz();
        int [][][]matOut = new int[nChannel][alt][larg];
        int color;
        for (int c = 0; c < nChannel; c++) {
            for (int y = 0; y < alt; y++) {
                for (int x = 0; x < larg; x++) {
                    color = matIn[c][y][x];
                    matOut[c][y][x] = color < limiar ? 0 : 255;  

                }
            }
        }

        return new Image(matOut);

    }

    public static Image limiarPeriodicoDisperso(Image imgIn, int N[][]){
        
        int tam = N.length;
        int i, j;
        // trabalha com 256 niveis de cinza
        Image imgQuantize = Processador.quantize(imgIn, 256,tam*tam);
        imgQuantize.viewImage("Image Quantize");
        int nChannel = imgQuantize.getChannel(); //rgb ou gray
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
                    matOut[c][y][x] = color < N[j][i] ? 0 : 255;
                }

            }

        }
        return new Image(matOut);
    }
}
