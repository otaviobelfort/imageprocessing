package util;

public class Processador {
    // img - imagem a ser quantizada
    // in - quatidade de níveis (por canal) da imagem original
    // out -  quantidade de níveis (por canal) da imagem resultante
    public static Image quantize(Image img, int in, int out) {
        int cor, faixa = (in) / (out);
        int mat[][][] = img.getMatriz();
        int qntCanais = img.getChannel(); //rgb ou gray
        int alt = img.getHeight();
        int larg = img.getWidth();
        for (int c = 0; c < qntCanais; c++) {
            for (int y = 0; y < alt; y++) {
                for (int x = 0; x < larg; x++) {
                    cor = (mat[c][y][x])/faixa;
                    mat[c][y][x] = (int) (cor);
                    //mat[c][y][x] = (int) (cor * faixa);
                }
            }
        }
        return new Image(mat);
    }
    public static Image sum(Image imgA, Image imgB) {
        int matA[][][] = imgA.getMatriz();
        int matB[][][] = imgB.getMatriz();
        int qntCanais = imgA.getChannel(); //rgb ou gray
        int alt = imgA.getHeight();
        int larg = imgA.getWidth();
        // evitar passar o limite superio 255
        int corSoma;
        int matOut[][][] = new int[qntCanais][alt][larg];
        for (int c = 0; c < qntCanais; c++) {
            for (int y = 0; y < alt; y++) {
                for (int x = 0; x < larg; x++) {
                    corSoma = matA[c][y][x]+matB[c][y][x];
                    if(corSoma>255)
                        corSoma=255;
                    matOut[c][y][x] = corSoma;
                }
            }
        }
        return new Image(matOut);
    }
    public static Image media(Image imgA, Image imgB) {
        int matA[][][] = imgA.getMatriz();
        int matB[][][] = imgB.getMatriz();
        int qntCanais = imgA.getChannel(); //rgb ou gray
        int alt = imgA.getHeight();
        int larg = imgA.getWidth();
        int corSoma;
        int matOut[][][] = new int[qntCanais][alt][larg];
        for (int c = 0; c < qntCanais; c++) {
            for (int y = 0; y < alt; y++) {
                for (int x = 0; x < larg; x++) {
                    corSoma = (matA[c][y][x]+matB[c][y][x])/2;
                    matOut[c][y][x] = corSoma;
                }
            }
        }
        return new Image(matOut);
    }
/*
    public Image media_n(Image[] vetImages){
        //int matN[][][];
        // qntCanais;
        int corSoma;
        for (int i=0; i <= vetImages.length;i++ ){
            int matN[][][] = vetImages[i].getMatriz();
            int qntCanais = vetImages[i].getChannel();
            int alt = vetImages[i].getHeight();
            int larg = vetImages[i].getWidth();
            int matOut[][][] = new int[qntCanais][alt][larg];
            for (int c = 0; c < qntCanais; c++) {
                for (int y = 0; y < alt; y++) {
                    for (int x = 0; x < larg; x++) {
                        corSoma = (matN[c][y][x]);

                        matOut[c][y][x] = corSoma;
                    }
                }
            }
        }
        return new Image(vetImages[0]);
    }
*/
    public static Image subtraction(Image imgA, Image imgB) {
        int matA[][][] = imgA.getMatriz();
        int matB[][][] = imgB.getMatriz();
        int qntCanais = imgA.getChannel(); //rgb ou gray
        int alt = imgA.getHeight();
        int larg = imgA.getWidth();
        int corSub;
        int matOut[][][] = new int[qntCanais][alt][larg];
        for (int c = 0; c < qntCanais; c++) {
            for (int y = 0; y < alt; y++) {
                for (int x = 0; x < larg; x++) {
                    corSub = matA[c][y][x]-matB[c][y][x];
                    if(corSub<0)
                        corSub=0;
                    matOut[c][y][x] = corSub;
                }
            }
        }
        return new Image(matOut);
    }
    public static Image not(Image imgA) {
        int matA[][][] = imgA.getMatriz();
        int qntCanais = imgA.getChannel(); //rgb ou gray
        int alt = imgA.getHeight();
        int larg = imgA.getWidth();
        int cor;
        int matOut[][][] = new int[qntCanais][alt][larg];
        for (int c = 0; c < qntCanais; c++) {
            for (int y = 0; y < alt; y++) {
                for (int x = 0; x < larg; x++) {
                    cor = matA[c][y][x];
                    cor=cor==0?255:0;
                    matOut[c][y][x] = cor;
                }
            }
        }
        return new Image(matOut);
    }

    public static void print(Image imgA){
        int matA[][][] = imgA.getMatriz();
        int qntCanais = imgA.getChannel(); 
        int alt = imgA.getHeight();
        int larg = imgA.getWidth();
        int cor;
        String out = "";
        for (int c = 0; c < qntCanais; c++) {
            for (int y = 0; y < alt; y++) {
                for (int x = 0; x < larg; x++) {
                    cor = matA[c][y][x];
                    out+=cor+ " ";
                }
                out+="\n";
            }
            out+="\n";
        }
        System.out.println(out);
    }
     

}
