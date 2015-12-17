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

package familyalbum.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import familyalbum.model.Album;
import familyalbum.util.HibernateUtil;

public class AlbumDeleteAction extends AlbumAction
{

    public AlbumDeleteAction()
    {
        super();
    }

    protected ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        // System.out.println( "Need to Delete album" );

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Album album;

        try {
            album = getAlbum(request);

            File dir = album.getDirectory();

            session.delete(album);
            tx.commit();
            // System.out.println( "Deleted album : " + album.getLabel() );

            try {
                dir.renameTo(new File(dir.getPath() + ".deleted"));
            } catch (Exception e) {
                System.err.println("Failed to rename deleted album : " + dir);
            }

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

        return parameterRedirect(mapping, "deleted", album.getAlbumId());
    }

}

