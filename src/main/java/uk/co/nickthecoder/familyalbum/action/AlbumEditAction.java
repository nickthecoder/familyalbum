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
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import uk.co.nickthecoder.familyalbum.form.AlbumForm;
import uk.co.nickthecoder.familyalbum.model.Album;
import uk.co.nickthecoder.familyalbum.model.Family;
import uk.co.nickthecoder.familyalbum.util.HibernateUtil;

public class AlbumEditAction extends AlbumAction
{
    public AlbumEditAction()
    {
        super();
    }

    protected ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        // System.out.println( "Need to Save album" );

        AlbumForm albumForm = (AlbumForm) form;

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Album album;

        try {
            album = getAlbum(request);

            album.setLabel(albumForm.getLabel());
            album.setNotes(albumForm.getNotes());
            Family family = getFamilyAlbumBean(request).getFamily();

            if (album.getAlbumId() == null) {
                family.addAlbum(album);

                album.setDirectoryName(family.getDirectoryName() + File.separator + "TEMP");

                session.save(album);

                DecimalFormat numberFormat = new DecimalFormat("00000");

                String albumNumber = numberFormat.format(album.getAlbumId());
                album.setDirectoryName(family.getDirectoryName() + File.separator + "album" + albumNumber);

                album.createDirectories();
            }

            session.save(album);
            tx.commit();
            // System.out.println( "Saved album : " + album.getLabel() );

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

        return parameterRedirect(mapping, "saved", album.getAlbumId());
    }

}
