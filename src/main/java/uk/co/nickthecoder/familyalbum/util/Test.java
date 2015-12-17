package uk.co.nickthecoder.familyalbum.util;

import java.awt.*;
import java.net.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Test
{
  public static void main(String[] args)
    throws Exception
  {
    new Test().test();
  }

  public void test()
    throws Exception
  {
    URL haloURL = getClass().getResource( "/familyalbum/resources/halo.png" );
    // System.out.println( haloURL );

    // Load the jpg image
    Image sourceImage = Toolkit.getDefaultToolkit().createImage( "source.jpg" );
    Image haloImage = Toolkit.getDefaultToolkit().createImage( haloURL );
    MediaTracker mediaTracker = new MediaTracker(new Container());
    mediaTracker.addImage( sourceImage, 0 );
    mediaTracker.waitForID(0);
    mediaTracker.addImage( haloImage, 0 );
    mediaTracker.waitForID(0);

    int s = 200;
    int thumbWidth = s;
    int thumbHeight = s;

    // Create the destination image.
    BufferedImage image = new BufferedImage(s, s, BufferedImage.TYPE_INT_ARGB); //A!
    Graphics2D g = image.createGraphics();

    g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
    g.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );

    // Draw the jpg to the new image, scaling as it does it.
    int x = 884;
    int y = 412;
    int radius = (int) ( 1.2 * 193 );

    int x1 = x - radius;
    int y1 = y - radius;
    int x2 = x + radius;
    int y2 = y + radius;

    int dx1 = 0;
    int dy1 = 0;
    int dx2 = s;
    int dy2 = s;

    if ( x1 < 0 ) {
      dx1 =  -x1 * s / (2 * radius);
      x1 = 0;
    }

    if ( y1 < 0 ) {
      dy1 =  -y1 * s / (2 * radius);
      y1 = 0;
    }

    g.drawImage( sourceImage, dx1,dy1, dx2,dy2, x1,y1, x2,y2, null );

    g.setComposite( AlphaComposite.getInstance( AlphaComposite.DST_OUT ) );

    g.drawImage( haloImage, 0, 0, thumbWidth, thumbHeight, null );


    g.dispose();

    // Write the image to disk.
    ImageIO.write(image, "png", new File("temp.png"));
  }
}

