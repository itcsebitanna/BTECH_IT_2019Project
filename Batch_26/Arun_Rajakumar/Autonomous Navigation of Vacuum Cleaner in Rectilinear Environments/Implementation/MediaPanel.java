package garbagedetection;

// Fig 21.6: MediaPanel.java
// A JPanel the plays media from a URL
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JPanel;

public class MediaPanel extends JPanel
{
   public MediaPanel( URL mediaURL )
   {
      setLayout( new BorderLayout() ); // use a BorderLayout
      
      // Use lightweight components for Swing compatibility
      Manager.setHint( Manager.LIGHTWEIGHT_RENDERER, true );
      
      try
      {
         // create a player to play the media specified in the URL
         Player mediaPlayer = Manager.createRealizedPlayer( mediaURL );
         
         // get the components for the video and the playback controls
         Component video = mediaPlayer.getVisualComponent();
         Component controls = mediaPlayer.getControlPanelComponent();
         
         if ( video != null ) 
            add( video, BorderLayout.CENTER ); // add video component

         if ( controls != null ) 
            add( controls, BorderLayout.SOUTH ); // add controls
         
         mediaPlayer.start(); // start playing the media clip
      } // end try
      catch ( NoPlayerException noPlayerException )
      {
         System.err.println( "No media player found" );
      } // end catch
      catch ( CannotRealizeException cannotRealizeException )
      {
         System.err.println( "Could not realize media player" );
      } // end catch
      catch ( IOException iOException )
      {
         System.err.println( "Error reading from the source" );
      } // end catch
   } // end MediaPanel constructor
} // end class MediaPanel
