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

package familyalbum.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PngHaloImageMaker extends HaloImageMaker
{

    private static final int THUMBNAIL_SIZE = 80;

    public PngHaloImageMaker(String source, String destination, int x, int y, int radius)
    {
        super(source, destination, x, y, radius, THUMBNAIL_SIZE);
    }

    protected BufferedImage createImage()
    {
        return new BufferedImage(getSize(), getSize(), BufferedImage.TYPE_INT_ARGB);
    }

    /*
     * protected void prepareHalo( Graphics2D g ) throws InterruptedException {
     * g.setComposite( AlphaComposite.getInstance( AlphaComposite.DST_OUT ) );
     * g.drawImage( getHaloTemplate(), 0, 0, getSize(), getSize(), null ); }
     */
    protected void prepareHalo(Graphics2D g) throws InterruptedException
    {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OUT));
    }

    protected void saveImage(BufferedImage image) throws IOException
    {
        ImageIO.write(image, "png", new File(getDestination()));
    }

}
