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
import org.hibernate.Transaction;

import uk.co.nickthecoder.familyalbum.form.PhotoForm;
import uk.co.nickthecoder.familyalbum.model.Photo;
import uk.co.nickthecoder.familyalbum.util.HibernateUtil;


public class PhotoEditAction extends PhotoAction
{

    // -------------------- [[Static Attributes]] --------------------

    // -------------------- [[Attributes]] --------------------

    // -------------------- [[Static Methods]] --------------------

    // -------------------- [[Constructors]] --------------------

    /**
  */
    public PhotoEditAction()
    {
        super();
    }

    // -------------------- [[Methods]] --------------------

    protected ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        int photoId = getIntParameter(request, "photoId", 0);

        Session session = HibernateUtil.currentSession();

        PhotoForm photoForm = (PhotoForm) form;

        Transaction tx = session.beginTransaction();
        Photo photo = (Photo) session.load(Photo.class, new Integer(photoId));

        photo.setYear(photoForm.getYear());
        photo.setYearAccuracy(photoForm.getYearAccuracy());
        photo.setNotes(photoForm.getNotes());

        session.save(photo);
        tx.commit();

        return parameterRedirect(mapping, "saved", photo.getPhotoId());

    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class PhotoEditAction ----------

