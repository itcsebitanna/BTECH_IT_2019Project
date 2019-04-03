package archana.akka;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;
import javax.media.MediaLocator;


public class frame2video{

  public static final File dir = new File("F:\\Smooth\\");
  public static final String[] extensions = new String[]{"jpg", "png"};
  public static final FilenameFilter imageFilter = new FilenameFilter(){ 
   // @Override
    public boolean accept(final File dir, String name) {
        for (final String ext : extensions) {
            if (name.endsWith("." + ext)) {
                return (true);
            }
        }
        return (false);
//    };
    }
    };

// Main function 
public static void main(String[] args) throws IOException {
    File file = new File("F:\\a19.mp4");
    if (!file.exists()) {
        file.createNewFile();
    }
    Vector<String> imgLst = new Vector<>();
    if (dir.isDirectory()) {
        int counter = 1;
        for (final File f : dir.listFiles(imageFilter)) {               
            imgLst.add(f.getAbsolutePath());             

        }
    }
    makeVideo("file:\\" + file.getAbsolutePath(), imgLst);        
}
  

 public static void makeVideo(String fileName, Vector imgLst) throws MalformedURLException {
    JpegImagesToMovie imageToMovie = new JpegImagesToMovie();
    MediaLocator oml;
    if ((oml = imageToMovie.createMediaLocator(fileName)) == null) {
        System.err.println("Cannot build media locator from: " + fileName);
        System.exit(0);
    }
    int interval = 40;
    imageToMovie.doIt(256, 256, (1000 / interval), imgLst, oml);
 }  
  }
