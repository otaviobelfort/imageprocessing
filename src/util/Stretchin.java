package util;

import javax.swing.text.AttributeSet.ColorAttribute;

public class Stretchin {
    // ver parametros a, b
    public static Image linear(Image img, float a, float  b){
        int nChannel = img.getChannel(); //rgb ou gray
        int alt = img.getHeight();
        int larg = img.getWidth();
        int matIn[][][] = img.getMatriz();
        int matOut[][][] = new int[nChannel][alt][larg];
        float color;
        for( int c = 0; c < nChannel; c++){
            for(int y = 0; y < alt; y++){
                for(int x = 0; x < larg; x++){
                    color = matIn[c][y][x];
                    matOut[c][y][x] = (int)(a*color+b);
                }
            }
        }
        return new Image(matOut);

    }

    public static Image minMax(Image img){
        int nChannel = img.getChannel(); //rgb ou gray
        int alt = img.getHeight();
        int larg = img.getWidth();
        int [][][]matIn = img.getMatriz();
        int [][][]matOut = new int[nChannel][alt][larg];
        int color;
        int min;
        int max;

        for( int c = 0; c < nChannel; c++){
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            for(int y = 0; y < alt; y++){
                for(int x = 0; x < larg; x++){
                    color = matIn[c][y][x];
                    if( color > max){
                        max = color;
                    }
                    if(color < min){
                        min = color;
                    }
                }
            }
            for(int y = 0; y < alt; y++){
                for(int x = 0; x < larg; x++){
                    color = matIn[c][y][x];
                    matOut[c][y][x] = (color-min)*255 /(max - min);
                }
            }
        }
        return new Image(matOut);

    }
    
}
