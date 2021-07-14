package util;

public class Histogram {

    private final Image img;
    int histAcum[][];
    int hist[][];
    int tranf[][];

    public Histogram(Image img){
        this.img = img;
        calculateHistogram();
        calculateHistogramAcum();
        calculateTransformation();
        getImage();
    }

    public Image getImage() {
        int nChannel = img.getChannel();
        int alt = img.getHeight();
        int larg = img.getWidth();
        int matIn[][][] = img.getMatriz();
        int matOut[][][] = new int[nChannel][alt][larg];
        int color;
        for (int channel = 0; channel < nChannel; channel++){
            for (int y = 0; y < alt; y++){
                for(int x = 0; x < larg; x++){
                    color = matIn[channel][y][x];
                    matOut[channel][y][x] = tranf[channel][color];
                    
                }
            }
        }
        return  new Image(matOut);

    }

    private void calculateTransformation() {
        int nChannel = img.getChannel();
        int alt =  img.getHeight();
        int larg = img.getWidth();
        float t = 255f/(float)(alt*larg);
        tranf = new int[nChannel][256];
        for(int channel = 0; channel < nChannel; channel++){
            for(int p = 0; p < 255; p++){
                tranf[channel][p] = (int)(t*histAcum[channel][p]);
            }
        }
    }

    private void calculateHistogramAcum() {
        int nChannel = img.getChannel();
        histAcum = new int[nChannel][256];
        for(int channel = 0; channel < nChannel; channel++){
            histAcum[channel][0] = hist[channel][0];
            for (int i = 1; i < 256; i++){
                histAcum[channel][i] = histAcum[channel][i - 1] + hist[channel][i];
                
            }
        }
    }

    private void calculateHistogram() {
        int nChannel = img.getChannel();
        int alt = img.getHeight();
        int larg = img.getWidth();
        int mat[][][] = img.getMatriz();
        int color;
        hist = new int[nChannel][256];
        for(int channel = 0; channel < nChannel; channel++){
            for (int y = 0; y < alt; y++){
                for (int x = 0; x < larg; x++){
                    color = mat[channel][y][x];
                    hist[channel][color]++; 
                }
            }
        }
    }

    
    
}
