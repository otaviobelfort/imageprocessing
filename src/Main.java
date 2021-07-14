
import util.Histogram;
import util.Image;
import util.Processador;

public class Main {

    // file path
    static String Image_A = "imagens/a.png";
    static String Image_B = "imagens/b.png";
    static String Image_LENNA = "imagens/lenna.jpg";
    static String Image_OLHO = "imagens/olho.png";
    static String Image_TESTE = "imagens/teste.png";
    static String Image_MASSA = "imagens/massa.jpg";
    static String Image_CINZA2 = "imagens/cinza2.jpg";

    

    public static void main(String args[]) {
        //viewImage();
        //rgbToGray();
        //grayToRgb();
        //imgQuantize(); 
        //imgSum();
        //imgSubtraction();
        //imgMedia();
        //imgNot();
        imgEqualization();
    }

    //View images
    private static void viewImage() {
        Image imgA = new Image(Main.Image_A);
        Image imgB = new Image(Main.Image_B);
        Image imgC = new Image(Main.Image_LENNA);
        Image imgD = new Image(Main.Image_OLHO);
        imgA.titleImage("Image A");
        imgB.titleImage("Image B");
        imgC.titleImage("Image C");
        imgD.titleImage();
    }

    //transforms an RGB image to GRAY and modifies this image
    private static void rgbToGray() {
        Image imgRGB = new Image(Main.Image_LENNA);
        imgRGB.titleImage("Image RGB");

        // toGray in class Image
        Image imgGray = imgRGB.toGray();

        //todas as modificações devem ocorrer numa matriz[alt][larg][canais]
        int mat[][][] = imgGray.getMatriz();
        for (int d = 0; d < 100; d++) {
            mat[0][d][d] = 255; // apenas canal 0 -> cinza
        }
        // a Image deve ser atualizada
        imgGray.setMatriz(mat);
        imgGray.titleImage("Lenna Cinza Modificada com uma linha");
    }

    //transforma uma Image GRAY em RGB e modifica esta Image
    private static void grayToRgb() {
        Image imgGray = new Image(Main.Image_OLHO);
        imgGray.titleImage("Image gray");
        Image imgRGB = imgGray.toRGB();

        //todas as modificações devem ocorrer numa matriz[alt][larg][canais]
        int mat[][][] = imgRGB.getMatriz();
        for (int d = 0; d < 100; d++) {
            mat[0][d][d] = 5; // canal 0 -> R
            mat[1][d][d] = 120; // canal 1 -> G
            mat[2][d][d] = 100; // canal 2 -> B
        }
        // a Image deve ser atualizada
        imgRGB.setMatriz(mat);
        imgRGB.titleImage("Image RGB modificada");
        imgRGB.save("c:/teste/olho_reta.jpg", Image.JPEG);
    }

    private static void imgQuantize() {
        Image imgGray = new Image(Main.Image_MASSA);
        imgGray.titleImage("Image gray");

        Image bin = Processador.quantize(imgGray, 256, 2);
        bin.titleImage(" Image Quantize 02");
        Processador.print(bin);
        
        /*
        Processador.quantize(imgGray, 256, 2).titleImage("Image quantizada 2");
        Processador.quantize(imgGray, 256, 3).titleImage("Image quantizada 3");
        Processador.quantize(imgGray, 256, 4).titleImage("Image quantizada 4");
        Processador.quantize(imgGray, 256, 8).titleImage("Image quantizada 8");
        Processador.quantize(imgGray, 256, 16).titleImage("Image quantizada 16");

        Image imgRGB = new Image(Main.Image_LENNA);
        imgRGB.titleImage("Image RGB");
        Processador.quantize(imgRGB, 256, 2).titleImage("Image quantizada 2");
        Processador.quantize(imgRGB, 256, 4).titleImage("Image quantizada 4");
        Processador.quantize(imgRGB, 256, 8).titleImage("Image quantizada 8");
        Processador.quantize(imgRGB, 256, 16).titleImage("Image quantizada 16");
        */
    }

    private static void imgSum() {
        Image imgA = new Image(Main.Image_A);
        Image imgB = new Image(Main.Image_B);
        Image imgC = Processador.sum(imgA, imgB);
        imgA.titleImage("Image A");
        imgB.titleImage("Image B");
        imgC.titleImage("soma de A e B");
    }

    private static void imgSubtraction() {
        Image imgA = new Image("imagens/cinza1.png");
        Image imgB = new Image("imagens/cinza2.png");
        Image imgC = Processador.subtraction(imgA, imgB);
        imgA.titleImage("Image A");
        imgB.titleImage("Image B");
        imgC.titleImage("Subtração de A e B");
    }

    private static void imgMedia() {
        Image imgA = new Image(Main.Image_A);
        Image imgB = new Image(Main.Image_B);
        Image imgC = Processador.media(imgA, imgB);
        imgA.titleImage("Image A");
        imgB.titleImage("Image B");
        imgC.titleImage("media entre A e B");
    }

    private static void imgNot() {
        Image imgA = new Image(Main.Image_B);
        Image imgC = Processador.not(imgA);
        imgA.titleImage("Image A");
        imgC.titleImage("Não A");
    }

    private static void imgEqualization() {
        //Image imgA = new Image("imagens/cinza2.png");
        Image imgA = new Image("imagens/lenna.jpg");
        Histogram hist = new Histogram(imgA);
        Image imgB = hist.getImage();
        imgA.titleImage("Image A");
        imgB.titleImage("Image B - Equalized");
        imgB.save("Image B Quantize", "imagens_out/");
    }
}
