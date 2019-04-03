package garbagedetection;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import jjil.algorithm.RgbAvgGray;
import jjil.core.Point;
import jjil.core.Rect;
import jjil.core.RgbImage;
import jjil.j2se.RgbImageJ2se;

public class ObjectDetection {

    static public int lipcnt = 0;
    static public int x, xx, y, yy, w, ww, h, hh;

    public static BufferedImage[] findFaces(BufferedImage bi, int minScale, int maxScale) {
        try {

            //   countobject(bi,minScale,maxScale,new File("c:/dd/jpg"));
            BufferedImage lipimg, out[] = new BufferedImage[2];
            InputStream is = ObjectDetection.class.getResourceAsStream("/jjilexample/haar/HCSB.txt");
            Gray8DetectHaarMultiScale detectHaar = new Gray8DetectHaarMultiScale(is, minScale, maxScale);
            RgbImage im = RgbImageJ2se.toRgbImage(bi);
            RgbAvgGray toGray = new RgbAvgGray();
            toGray.push(im);
            List<Rect> results = detectHaar.pushAndReturn(toGray.getFront());

            lipcnt = results.size();
            File ss = new File(new File("").getCanonicalPath() + "\\dataset\\objectcount");
            if (!ss.exists()) {
                ss.mkdir();
            }
            else
            {
            String[] delfile = ss.list();
            System.out.println(delfile.length);
              for (int i = 0; i < delfile.length; i++) {
                System.out.println(delfile[i]);
                if(delfile[i].endsWith(".jpg"))
                   new File(ss.getCanonicalPath() + "\\" + delfile[i]).delete();
            }
            }
            lipimg = bi.getSubimage(0, 0, 50, 50);
            if (lipcnt != 0) {
                Graphics2D g2d = bi.createGraphics();
                g2d.setStroke(new BasicStroke(2));
                for (int i = 0; i < lipcnt; i++) {
                    Rect r1 = results.get(i);
                    Point p = r1.getTopLeft();
                    x = p.getX();
                    y = p.getY();
                    w = r1.getWidth();
                    h = r1.getHeight();
                    g2d.drawRect(x, y, w, h);
                    ww = w / 3;
                    hh = h - (h / 3);
                    xx = x + ww - 15;
                    yy = y + hh - 15;
                    ww = (ww + (ww / 3)) + 15;
                    hh = (hh / 2);
                    lipimg = bi.getSubimage(x, y, w, h);
                    File snap = new File(new File("").getCanonicalPath() + "\\dataset");
                    snap.delete();
                    if (!snap.exists()) {
                        snap.mkdir();
                    }

                    int w = lipimg.getWidth();
                    int h = lipimg.getHeight();
                    String foldername = w + "X" + h;
                    ss = new File(new File("").getCanonicalPath() + "\\dataset\\" + foldername);
                    if (!ss.exists()) {
                        ss.mkdir();
                    }
                    String outputFilePrefix = new File("").getCanonicalPath() + "\\dataset\\" + foldername + "\\snapshot";
                    String outputFilename = outputFilePrefix
                            + System.currentTimeMillis() + ".jpg";
                    ImageIO.write(lipimg, "jpg", new File(outputFilename));


                    ss = new File(new File("").getCanonicalPath() + "\\dataset\\objectcount");
                    if (!ss.exists()) {
                        ss.mkdir();
                    }

                    outputFilePrefix = new File("").getCanonicalPath() + "\\dataset\\objectcount\\snapshot";
                    outputFilename = outputFilePrefix
                            + i + ".jpg";
                    ImageIO.write(lipimg, "jpg", new File(outputFilename));




                    //  g2d.drawRect(xx, yy, ww, hh);
                }

                //  System.out.println("Found " + results.size() + " faces");
            }

            out[0] = bi;
            out[1] = lipimg;
            return out;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public static void countobject(BufferedImage bi, int minScale, int maxScale, File output) {
        try {
            InputStream is = ObjectDetection.class.getResourceAsStream("/jjilexample/haar/HCSB.txt");
            Gray8DetectHaarMultiScale detectHaar = new Gray8DetectHaarMultiScale(is, minScale, maxScale);
            RgbImage im = RgbImageJ2se.toRgbImage(bi);
            RgbAvgGray toGray = new RgbAvgGray();
            toGray.push(im);
            List results = detectHaar.pushAndReturn(toGray.getFront());
            System.out.println("Found " + results.size() + " faces");
            /* Image i = detectHaar.getFront();
            Gray8Rgb g2rgb = new Gray8Rgb();
            g2rgb.push(i);
            RgbImageJ2se conv = new RgbImageJ2se();
            conv.toFile((RgbImage)g2rgb.getFront(), output.getCanonicalPath());*/
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    /*     public static void main(String[] args) throws Exception {
    BufferedImage bi = ImageIO.read(new File("C:\\Temp\\Picture 001.jpg"));
    BufferedImage bufimg = findFaces(bi, 1, 40); // change as needed
    ImageIO.write(bufimg, "jpg", new File("C:\\Temp\\123.jpg"));
    }*/
}
