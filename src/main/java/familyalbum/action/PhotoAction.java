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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import familyalbum.form.PhotoForm;
import familyalbum.model.Album;
import familyalbum.model.Photo;
import familyalbum.util.HibernateUtil;

/**
*/

public class PhotoAction extends GetPostAction
{

    // -------------------- [[Static Attributes]] --------------------

    // -------------------- [[Attributes]] --------------------

    // -------------------- [[Static Methods]] --------------------

    // -------------------- [[Constructors]] --------------------

    /**
  */
    public PhotoAction()
    {
        super();
    }

    // -------------------- [[Methods]] --------------------

    protected ActionForward get(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        Photo photo = getPhoto(request);

        // Redirect if navgation=next or navigation=prev
        String navigation = request.getParameter("navigation");
        if ("prev".equals(navigation)) {

            return parameterRedirect(mapping, "jump", photo.getPreviousPhotoInAlbum());

        } else if ("next".equals(navigation)) {

            return parameterRedirect(mapping, "jump", photo.getNextPhotoInAlbum());
        }

        if (form instanceof PhotoForm) {
            ((PhotoForm) form).initialise(photo);
        }

        return mapping.findForward("show");
    }

    protected Photo getPhoto(HttpServletRequest request)
    {
        Session session = HibernateUtil.currentSession();

        Integer photoId = getIntegerParameter(request, "photoId");
        Integer albumId = getIntegerParameter(request, "albumId");

        Photo photo;

        if (photoId == null) {
            photo = new Photo();
            if (albumId != null) {
                photo.setAlbum((Album) session.load(Album.class, albumId));
            }
        } else {
            photo = (Photo) session.load(Photo.class, photoId);
        }

        return photo;
    }

    protected ActionForward cancelled(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        Photo photo = getPhoto(request);
        if (photo.getPhotoId() == null) {
            return parameterRedirect(mapping, "newCancelled", "albumId", photo.getAlbum().getAlbumId());
        } else {
            return parameterRedirect(mapping, "cancelled", photo);
        }
    }

    protected ActionForward parameterRedirect(ActionMapping mapping, String mappingName, Photo photo)
    {
        return parameterRedirect(mapping, mappingName, "photoId", photo.getPhotoId());
    }

    protected ActionForward parameterRedirect(ActionMapping mapping, String mappingName, Album album)
    {
        return parameterRedirect(mapping, mappingName, "albumId", album.getAlbumId());
    }

    protected ActionForward parameterRedirect(ActionMapping mapping, String mappingName, int photoId)
    {
        return parameterRedirect(mapping, mappingName, "photoId", new Integer(photoId));
    }

    protected ActionForward parameterRedirect(ActionMapping mapping, String mappingName, Integer photoId)
    {
        return parameterRedirect(mapping, mappingName, "photoId", photoId);
    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class PhotoAction ----------

