/*
 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
*/

package uk.co.nickthecoder.familyalbum.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;

/**
*/

public class JpegHaloImageMaker
  extends HaloImageMaker
{

  // -------------------- [[Static Attributes]] --------------------

  private static final float JPEG_QUALITY = (float)0.75;

  private static final int FULL_SIZE = 400;

  // -------------------- [[Attributes]] --------------------

  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

 /**
  */
  public JpegHaloImageMaker( String source, String destination, int x, int y, int radius )
  {
    super( source, destination, x, y, radius, FULL_SIZE );
  }

  // -------------------- [[Methods]] --------------------

  protected BufferedImage createImage()
  {
    return new BufferedImage( getSize(), getSize(), BufferedImage.TYPE_INT_RGB );
  }

  /*
  protected void superimposeHalo( Graphics2D g )
    throws InterruptedException
  {
    g.drawImage( getHaloTemplate(), 0, 0, getSize(), getSize(), null );
  }
  */

  protected void saveImage( BufferedImage image )
    throws IOException
  {
    FileImageOutputStream out = new FileImageOutputStream( new File( getDestination() ) );
    ImageWriter encoder = (ImageWriter) ImageIO.getImageWritersByFormatName( "JPEG" ).next();

    try {
      JPEGImageWriteParam param = new JPEGImageWriteParam( null );
      param.setCompressionMode( ImageWriteParam.MODE_EXPLICIT );
      param.setCompressionQuality( JPEG_QUALITY );

      encoder.setOutput( out );
      IIOImage iioImage = new IIOImage( image, null, null );
      encoder.write( (IIOMetadata) null, iioImage, param );

    } finally {
      out.close();
      encoder.dispose();
    }
  }


}

// ---------- End Of Class JpegHaloImageMaker ----------

