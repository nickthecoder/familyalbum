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

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;


public class ThumbnailMaker implements JobQueueEntry
{

    private static final float THUMBNAIL_QUALITY = (float) 0.75;

    private static final int DEFAULT_THUMBNAIL_WIDTH = 150;

    private static final int DEFAULT_THUMBNAIL_HEIGHT = 150;

    private String _source;

    private String _destination;

    private int _maxThumbnailWidth;

    private int _maxThumbnailHeight;

    public ThumbnailMaker(String source, String destination)
    {
        this(source, destination, DEFAULT_THUMBNAIL_WIDTH, DEFAULT_THUMBNAIL_HEIGHT);
    }

    public ThumbnailMaker(String source, String destination, int maxWidth, int maxHeight)
    {
        _source = source;
        _destination = destination;
        _maxThumbnailWidth = maxWidth;
        _maxThumbnailHeight = maxHeight;
    }

    public void run()
    {

        try {
            // System.out.println( "ThumbnailMaker : " + _destination );

            Image image = Toolkit.getDefaultToolkit().createImage(_source);

            MediaTracker mediaTracker = new MediaTracker(new Container());
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
            // System.out.println( "ThumbnailMaker loaded image" );

            // determine thumbnail size from WIDTH and HEIGHT
            int thumbWidth = _maxThumbnailWidth;
            int thumbHeight = _maxThumbnailHeight;
            double thumbRatio = (double) thumbWidth / (double) thumbHeight;
            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);
            double imageRatio = (double) imageWidth / (double) imageHeight;
            if (thumbRatio < imageRatio) {
                thumbHeight = (int) (thumbWidth / imageRatio);
            } else {
                thumbWidth = (int) (thumbHeight * imageRatio);
            }

            // System.out.println( "ThumbnailMaker size : " + thumbWidth + "," +
            // thumbHeight );

            // draw original image to thumbnail image object and
            // scale it to the new size on-the-fly
            BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);

            Graphics2D graphics2D = thumbImage.createGraphics();

            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            // System.out.println( "About to draw image" );
            graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
            // System.out.println( "drawn image" );

            save(thumbImage);

        } catch (Exception e) {
            System.err.println("ThumbnailMaker failed");
            e.printStackTrace();
        }

    }

    private void save(BufferedImage image) throws Exception
    {
        FileImageOutputStream out = new FileImageOutputStream(new File(_destination));
        ImageWriter encoder = (ImageWriter) ImageIO.getImageWritersByFormatName("JPEG").next();

        try {
            JPEGImageWriteParam param = new JPEGImageWriteParam(null);
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(THUMBNAIL_QUALITY);

            encoder.setOutput(out);
            IIOImage iioImage = new IIOImage(image, null, null);
            encoder.write((IIOMetadata) null, iioImage, param);

        } finally {
            encoder.dispose();
            out.close();
        }
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof ThumbnailMaker)) {
            return false;
        }
        return ((ThumbnailMaker) obj)._destination.equals(_destination);
    }

}
