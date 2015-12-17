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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import uk.co.nickthecoder.familyalbum.form.AlbumForm;
import uk.co.nickthecoder.familyalbum.model.Album;
import uk.co.nickthecoder.familyalbum.util.HibernateUtil;

/**
*/

public class AlbumAction extends GetPostAction
{

    public AlbumAction()
    {
        super();
    }

    protected ActionForward cancelled(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        AlbumForm albumForm = (AlbumForm) form;

        // System.out.println( "Cancelling action of album : " +
        // albumForm.getAlbumId() );
        return parameterRedirect(mapping, "cancelled", albumForm.getAlbumId());
    }

    protected ActionForward get(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        Album album = getAlbum(request);

        if (album == null) {

        } else {
            AlbumForm albumForm = (AlbumForm) form;

            albumForm.setAlbumId(album.getAlbumId());
            albumForm.setLabel(album.getLabel());
            albumForm.setNotes(album.getNotes());
        }

        return mapping.findForward("show");
    }

    protected Album getAlbum(HttpServletRequest request)
    {
        int albumId = getIntParameter(request, "albumId", 0);

        if (albumId == 0) {
            return new Album();
        }

        Session session = HibernateUtil.currentSession();
        Album album = (Album) session.load(Album.class, new Integer(albumId));
        // System.out.println( "Loaded album : " + album.getLabel() );

        return album;
    }

    protected ActionForward parameterRedirect(ActionMapping mapping, String mappingName, Integer albumId)
    {
        return parameterRedirect(mapping, mappingName, "albumId", albumId);
    }

}
