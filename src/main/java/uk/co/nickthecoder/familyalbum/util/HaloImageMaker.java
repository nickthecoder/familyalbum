/*
----------------------------------------------------------------------

 Author        :  (nick)
 Creation Date : 2004-03-22

----------------------------------------------------------------------

 History
 2004-03-22 : nick : Initial Development

----------------------------------------------------------------------

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

----------------------------------------------------------------------
 */

package uk.co.nickthecoder.familyalbum.util;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
*/

public abstract class HaloImageMaker implements JobQueueEntry
{

    private static final String HALO_TEMPLATE = "/familyalbum/resources/halo.png";

    private static final String SQUARE_TEMPLATE = "/familyalbum/resources/haloSquare.png";

    private static final double RADIUS_GROWTH = 1.2;

    private Image _haloTemplate;

    private Image _squareTemplate;

    // -------------------- [[Attributes]] --------------------

    private String _source;

    private String _destination;

    private int _x;

    private int _y;

    private int _radius;

    private int _size;

    private int _squareMargin;

    // -------------------- [[Static Methods]] --------------------

    public Image getHaloTemplate() throws InterruptedException
    {
        if (_haloTemplate == null) {
            URL haloURL = getClass().getResource(HALO_TEMPLATE);
            _haloTemplate = Toolkit.getDefaultToolkit().createImage(haloURL);
            MediaTracker mediaTracker = new MediaTracker(new Container());
            mediaTracker.addImage(_haloTemplate, 0);
            mediaTracker.waitForID(0);
        }
        return _haloTemplate;
    }

    public Image getSquareTemplate() throws InterruptedException
    {
        if (_squareTemplate == null) {
            URL squareURL = getClass().getResource(SQUARE_TEMPLATE);
            _squareTemplate = Toolkit.getDefaultToolkit().createImage(squareURL);
            MediaTracker mediaTracker = new MediaTracker(new Container());
            mediaTracker.addImage(_squareTemplate, 0);
            mediaTracker.waitForID(0);
        }
        return _squareTemplate;
    }

    // -------------------- [[Constructors]] --------------------

    /**
  */
    public HaloImageMaker(String source, String destination, int x, int y, int radius, int size)
    {
        _source = source;
        _destination = destination;
        _x = x;
        _y = y;
        _radius = radius;
        _size = size;
        _squareMargin = 1; // _size / 20;
    }

    // -------------------- [[Methods]] --------------------

    public String getDestination()
    {
        return _destination;
    }

    public int getSize()
    {
        return _size;
    }

    protected abstract BufferedImage createImage();

    /*
     * protected abstract void superimposeHalo( Graphics2D g ) throws
     * InterruptedException;
     */

    protected abstract void saveImage(BufferedImage image) throws IOException;

    protected void prepareHalo(Graphics2D g) throws InterruptedException
    {
    }

    public void run()
    {

        try {
            // System.out.println( "HaloImageMaker : " + _destination );

            // Load the images
            Image sourceImage = Toolkit.getDefaultToolkit().createImage(_source);
            MediaTracker mediaTracker = new MediaTracker(new Container());
            mediaTracker.addImage(sourceImage, 0);
            mediaTracker.waitForID(0);

            // Create the destination image.

            BufferedImage image = createImage();
            Graphics2D g = image.createGraphics();

            g.setColor(Color.white);
            g.fillRect(0, 0, getSize(), getSize());

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            // Draw the jpg to the new image, scaling as it does it.
            int radius = (int) (_radius * RADIUS_GROWTH);

            int x1 = _x - radius;
            int y1 = _y - radius;
            int x2 = _x + radius;
            int y2 = _y + radius;

            int dx1 = 0;
            int dy1 = 0;
            int dx2 = _size;
            int dy2 = _size;

            if (x1 < 0) {
                dx1 = -x1 * _size / (2 * radius);
                x1 = 0;
            }

            if (y1 < 0) {
                dy1 = -y1 * _size / (2 * radius);
                y1 = 0;
            }

            g.drawImage(sourceImage, dx1, dy1, dx2, dy2, x1, y1, x2, y2, null);

            prepareHalo(g);

            // If the circle is outside of the photo, then blur the edges using
            // a SQUARE mask.
            int rightEdge = (sourceImage.getWidth(null) - (_x - radius)) * _size / (2 * radius);
            int bottomEdge = (sourceImage.getHeight(null) - (_y - radius)) * _size / (2 * radius);
            if (rightEdge > _size)
                rightEdge = _size;
            if (bottomEdge > _size)
                bottomEdge = _size;

            g.drawImage(getSquareTemplate(), rightEdge - _size - _squareMargin, bottomEdge - _size - _squareMargin,
                            _size + _squareMargin * 2, _size + _squareMargin * 2, null);
            g.drawImage(getSquareTemplate(), dx1 - _squareMargin, dy1 - _squareMargin, _size + _squareMargin * 2, _size
                            + _squareMargin * 2, null);

            // Now draw the circular mask ( halo ).
            g.drawImage(getHaloTemplate(), 0, 0, getSize(), getSize(), null);
            g.dispose();

            saveImage(image);

        } catch (Exception e) {
            System.err.println("HaloImageMaker failed : " + e);
            e.printStackTrace();
        }

    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof HaloImageMaker)) {
            return false;
        }
        return ((HaloImageMaker) obj).getDestination().equals(getDestination());
    }

}

// ---------- End Of Class HaloImageMaker ----------

