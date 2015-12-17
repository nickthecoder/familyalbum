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

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import familyalbum.model.Album;
import familyalbum.model.Photo;
import familyalbum.model.SubjectInPhoto;
import familyalbum.util.HibernateUtil;

public class PhotoDeleteAction extends PhotoAction
{

    public PhotoDeleteAction()
    {
        super();
    }

    protected ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        // System.out.println( "Need to Delete album" );

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Photo photo;
        Album album;

        try {
            photo = getPhoto(request);
            album = photo.getAlbum();

            // System.out.println( "Photo : " + photo );
            // System.out.println( "Album : " + album );

            for (Iterator i = photo.getSubjectsInPhoto().iterator(); i.hasNext();) {

                SubjectInPhoto sip = (SubjectInPhoto) i.next();

                // System.out.println( "Sip : " + sip );
                // System.out.println( "Sip.subject : " + sip.getSubject() );
                // System.out.println( "Sip.subject.dsipid : " +
                // sip.getSubject().getDefaultSubjectInPhotoId() );

                if (sip.getSubjectInPhotoId().equals(sip.getSubject().getDefaultSubjectInPhotoId())) {
                    sip.getSubject().setDefaultSubjectInPhotoId(null);
                    session.save(sip.getSubject());
                }

            }

            session.delete(photo);
            tx.commit();
            // System.out.println( "Deleted photo : " + photo.getFilename() );

            return parameterRedirect(mapping, "deleted", album);

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

    }

}
