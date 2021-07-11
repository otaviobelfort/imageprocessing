package util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public final class Image{

    public static final String JPEG = "JPEG";
    public static final String PNG = "PNG";
    public static final int GRAY = BufferedImage.TYPE_BYTE_GRAY;
    public static final int RGB = BufferedImage.TYPE_INT_RGB;
    private BufferedImage image = null;
    private WritableRaster raster = null;
    private int type;
    private int height;
    private int width;

    public Image(String path) {
        try {
            image = ImageIO.read(new File(path));
            // percorrer as matrizes
            raster = image.getRaster();
            type = image.getType();
            height = image.getHeight();
            width = image.getWidth();
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image(int height, int width, int type) {
        image = new BufferedImage(height, width, type);
        raster = image.getRaster();
        this.type = type;
        this.height = height;
        this.width = width;
    }

    public Image(int mat[][][]) {
        this.type = mat.length == 1 ? GRAY : RGB;
        this.height = mat[0].length;
        this.width = mat[0][0].length;
        image = new BufferedImage(width, height, type);
        raster = image.getRaster();
        this.setMatriz(mat);
    }

    public Image toGray() {
        int matIn[][][] = getMatriz();
        int matOut[][][] = new int[1][height][width];
        int R, G, B, gray;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                R = matIn[0][y][x];
                G = matIn[1][y][x];
                B = matIn[2][y][x];
                gray = (int) (0.2989 * R + 0.5870 * G + 0.1140 * B);
                matOut[0][y][x] = gray;
            }
        }
        return new Image(matOut);
    }

    public Image toRGB() {
        int matIn[][][] = getMatriz();
        int matOut[][][] = new int[3][height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                matOut[0][y][x] = matIn[0][y][x];
                matOut[1][y][x] = matIn[0][y][x];
                matOut[2][y][x] = matIn[0][y][x];
            }
        }
        return new Image(matOut);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getChannel() {
        return type < 10 ? 3 : 1;
    }

    public int[][][] getMatriz() {
        int nChannel = getChannel();
        int m[][][] = new int[nChannel][height][width];
        for (int channel = 0; channel < nChannel; channel++) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    m[channel][y][x] = raster.getSample(x, y, channel);
                }
            }
        }
        return m;
    }

    public void setMatriz(int[][][] mat) {
        int nChannel = getChannel();
        int pixel[] = new int[nChannel];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int c = 0; c < nChannel; c++) {
                    pixel[c] = mat[c][y][x];
                }
                raster.setPixel(x, y, pixel);
            }
        }
    }

    protected BufferedImage getBuffer() {
        return image;
    }

    public void save(String name, String typeOut) {
        try {
            ImageIO.write(image, typeOut, new File(name));
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void titleImage(String title) {
        new IFrame(title, this).setVisible(true);
    }

    public void titleImage() {
        new IFrame("", this).setVisible(true);
    }
}

class IFrame extends JFrame {

    private static final long serialVersionUID = 4074185320511690112L;
    static int incremento = 0;

    private int xIni = 0;
    private int yIni = 0;
    private int xFim = 0;
    private int yFim = 0;
    JScrollPane scroll = null;

    final class GCanvas extends JPanel {

        private final Image img;

        public GCanvas(Image img) {
            this.img = img;
            setSize(getPreferredSize());
            this.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    scrollMouseClicked(evt);
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    scrollMousePressed(evt);
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    scrollMouseReleased(evt);
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(img.getWidth(), img.getHeight());
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(img.getBuffer(), 0, 0, this);
        }

        public void scrollMouseClicked(MouseEvent e) {
        }

        public void scrollMousePressed(MouseEvent e) {
            xIni = e.getX();
            yIni = e.getY();
        }

        public void scrollMouseReleased(MouseEvent e) {
            xFim = e.getX();
            yFim = e.getY();
            int dx = xFim - xIni;
            int dy = yFim - yIni;
            int posAtual = scroll.getHorizontalScrollBar().getValue();
            scroll.getHorizontalScrollBar().setValue(posAtual + dx);
            posAtual = scroll.getVerticalScrollBar().getValue();
            scroll.getVerticalScrollBar().setValue(posAtual + dy);
        }

        public void scrollMouseEntered(MouseEvent e) {
        }

        public void scrollMouseExited(MouseEvent e) {
        }
    }

    public IFrame(String titulo, Image img) //constructor
    {
        super(titulo);
        scroll = new JScrollPane(new GCanvas(img));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setMaximumSize(new Dimension(img.getWidth(), img.getHeight()));
        getContentPane().add(scroll);
        setSize(img.getWidth() + 20, img.getHeight() + 45);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(incremento, incremento);
        setVisible(true);
        incremento += 20;//desloca as várias imagens na diagonal ao serem mostradas
    }
}
