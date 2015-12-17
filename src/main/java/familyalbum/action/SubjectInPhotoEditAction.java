/*
----------------------------------------------------------------------

 Author        :  (nick)
 Creation Date : 2004-03-23

----------------------------------------------------------------------

 History
 2004-03-23 : nick : Initial Development

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

package familyalbum.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import familyalbum.model.Photo;
import familyalbum.model.Subject;
import familyalbum.model.SubjectInPhoto;
import familyalbum.util.HibernateUtil;

public class SubjectInPhotoEditAction extends PhotoAction
{

    // -------------------- [[Static Attributes]] --------------------

    // -------------------- [[Attributes]] --------------------

    // -------------------- [[Static Methods]] --------------------

    // -------------------- [[Constructors]] --------------------

    /**
  */
    public SubjectInPhotoEditAction()
    {
        super();
    }

    // -------------------- [[Methods]] --------------------

    protected ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        int photoId = getIntParameter(request, "photoId", 0);
        int subjectId = getIntParameter(request, "subjectId", 0);

        // System.out.println( "Photo Post : photoId=" + photoId +
        // ", subjectId=" + subjectId );

        Session session = HibernateUtil.currentSession();

        if (request.getParameter("deleteSubject") != null) {

            // Delete subject
            // System.out.println( "Deleting subject " + subjectId +
            // " from photo " + photoId );

            Transaction tx = session.beginTransaction();

            Integer subjectInPhotoId = getIntegerParameter(request, "subjectInPhotoId");
            SubjectInPhoto sip = (SubjectInPhoto) session.load(SubjectInPhoto.class, subjectInPhotoId);

            Subject subject = sip.getSubject();
            subject.getSubjectInPhotos().remove(sip);

            // If the current default has been removed, then replace it with the
            // first alternate
            if (subjectInPhotoId.equals(subject.getDefaultSubjectInPhotoId())) {

                subject.setDefaultSubjectInPhotoId(null);
                for (Iterator i = subject.getSubjectInPhotos().iterator(); i.hasNext();) {
                    SubjectInPhoto other = (SubjectInPhoto) i.next();
                    subject.setDefaultSubjectInPhotoId(other.getSubjectInPhotoId());
                }

                session.save(subject);
            }

            sip.deleteImages();
            session.delete(sip);

            tx.commit();

            return parameterRedirect(mapping, "deletedSubject", photoId);

        } else if (request.getParameter("newSubject") != null) {

            // new subject
            // System.out.println( "Adding subject " + subjectId +
            // " from photo " + photoId );

            Transaction tx = session.beginTransaction();

            Photo photo = (Photo) session.load(Photo.class, new Integer(photoId));
            Subject subject = (Subject) session.load(Subject.class, new Integer(subjectId));

            // System.out.println( "Adding " + subject.getLabel() + " to photo "
            // + photo.getPhotoId() );
            SubjectInPhoto sip = new SubjectInPhoto(subject, photo);

            sip.setX(getIntParameter(request, "x", 0));
            sip.setY(getIntParameter(request, "y", 0));
            sip.setRadius(getIntParameter(request, "radius", 0));
            sip.setUseHalo(getBoolParameter(request, "useHalo"));

            // System.out.println( "Subjects ? " +
            // sip.getSubject().getSubjectInPhotos().size() );
            // System.out.println( "Photos ? " +
            // sip.getPhoto().getSubjectsInPhoto().size() );

            // session.save( photo );
            session.save(sip);

            // System.out.println( "Checking for first" );
            // System.out.println( "sip.sipid = " + sip.getSubjectInPhotoId() );

            if (subject.getDefaultSubjectInPhotoId() == null) {
                subject.setDefaultSubjectInPhotoId(sip.getSubjectInPhotoId());
                session.save(subject);
            }

            tx.commit();
            // System.out.println( "Saved" );

            sip.updateImages();

            return parameterRedirect(mapping, "savedSubject", photo);

        } else {
            // Hmm, shouldn't get here!
            return mapping.findForward("cancelled");
        }

    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class SubjectInPhotoEditAction ----------

