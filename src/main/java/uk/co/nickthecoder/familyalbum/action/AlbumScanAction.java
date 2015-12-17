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

package uk.co.nickthecoder.familyalbum.action;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import uk.co.nickthecoder.familyalbum.form.AlbumForm;
import uk.co.nickthecoder.familyalbum.model.Album;
import uk.co.nickthecoder.familyalbum.model.Photo;
import uk.co.nickthecoder.familyalbum.util.HibernateUtil;



public class AlbumScanAction extends AlbumAction
{
    public AlbumScanAction()
    {
        super();
    }

    protected ActionForward get(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        // System.out.println( "Need to scan an album" );

        AlbumForm albumForm = (AlbumForm) form;

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Album album;

        try {
            album = getAlbum(request);
            album.createDirectories();

            int newPhotos = 0;

            File dir = album.getDirectory();

            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                String name = files[i].getName();

                if (name.endsWith(".jpg")) {

                    if (!album.containsPhoto(name)) {
                        // System.out.println(
                        // "AlbumScanAction : adding scanned photo : " + name );
                        newPhotos++;
                        Photo photo = new Photo();
                        photo.setFilename(album.getDirectoryName() + File.separator + name);
                        album.addPhoto(photo);
                        photo.updateImages();
                    }
                }
            }

            if (newPhotos > 0) {
                session.save(album);
                // System.out.println( "Scanned found " + newPhotos +
                // " new photos " );
            }

            for (Iterator i = album.getPhotos().iterator(); i.hasNext();) {
                Photo photo = (Photo) i.next();
                if ((photo.getWidth() == 0) || (photo.getHeight() == 0)) {
                    // System.out.println( "finding size for photo" );
                    photo.findSize();
                    session.save(photo);
                }
            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

        return mapping.findForward("show");
    }

}

