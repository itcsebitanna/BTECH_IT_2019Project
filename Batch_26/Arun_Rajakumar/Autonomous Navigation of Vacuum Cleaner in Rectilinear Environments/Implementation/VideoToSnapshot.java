/*
 * VideoToSnapshot.java
 */
package garbagedetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;

public class VideoToSnapshot {

    boolean ch=false;
    static String snappath = "snapshots";
    public static double SECONDS_BETWEEN_FRAMES = 0.5;
    private static String inputFilename = "";
    private static String outputFilePrefix = "snapshots\\";
    // The video stream index, used to ensure we display frames from one and
    // only one video stream from the media container.
    private static int mVideoStreamIndex = -1;
    // Time of last frame write
    private static long mLastPtsWrite = Global.NO_PTS;
    static int cnt = 1;
    public static long MICRO_SECONDS_BETWEEN_FRAMES =
            (long) (Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);
    static long framecnt = 0;

    public void SanpshotConversion(String filename, String frames) {

        File snap = new File(snappath);
        snap.delete();
        if (!snap.exists()) {
            snap.mkdir();
        }
        outputFilePrefix = snap.getAbsolutePath() + "\\";
        framecnt = 0;
        SECONDS_BETWEEN_FRAMES = Double.parseDouble(frames);
        MICRO_SECONDS_BETWEEN_FRAMES =
                (long) (Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);
        IMediaReader mediaReader = ToolFactory.makeReader(filename);

        // stipulate that we want BufferedImages created in BGR 24bit color space
        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);

        mediaReader.addListener(new ImageSnapListener());

        // read out the contents of the media file and
        // dispatch events to the attached listener
        while (mediaReader.readPacket() == null);
        //System.out.println("frames completed");
        Classification c=new Classification();
        c.check(true);
    }

    private static class ImageSnapListener extends MediaListenerAdapter {

        public void onVideoPicture(IVideoPictureEvent event) {

            if (event.getStreamIndex() != mVideoStreamIndex) {
                // if the selected video stream id is not yet set, go ahead an
                // select this lucky video stream
                if (mVideoStreamIndex == -1) {
                    mVideoStreamIndex = event.getStreamIndex();
                } // no need to show frames from this video stream
                else {
                    return;
                }
            }

            // if uninitialized, back date mLastPtsWrite to get the very first frame
            if (mLastPtsWrite == Global.NO_PTS) {
                mLastPtsWrite = event.getTimeStamp() - MICRO_SECONDS_BETWEEN_FRAMES;
            }

            // if it's time to write the next frame
            if (event.getTimeStamp() - mLastPtsWrite
                    >= MICRO_SECONDS_BETWEEN_FRAMES) {

                String outputFilename = dumpImageToFile(event.getImage());

                // indicate file written
                double seconds = ((double) event.getTimeStamp())
                        / Global.DEFAULT_PTS_PER_SECOND;
                //System.out.printf("at elapsed time of %6.3f seconds wrote: %s\n",seconds, outputFilename);

                // update last write time
                mLastPtsWrite += MICRO_SECONDS_BETWEEN_FRAMES;
            }

        }

        private String dumpImageToFile(BufferedImage image) {
            try {
                framecnt++;
                String outputFilename = outputFilePrefix
                        + framecnt + ".jpg";
                ImageIO.write(image, "jpg", new File(outputFilename));
                return outputFilename;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
