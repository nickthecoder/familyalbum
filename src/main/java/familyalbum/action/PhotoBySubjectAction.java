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

import familyalbum.model.SubjectInPhoto;
import familyalbum.util.HibernateUtil;

public class PhotoBySubjectAction extends GetPostAction
{

    public PhotoBySubjectAction()
    {
        super();
    }

    protected ActionForward get(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        Integer subjectInPhotoId = getIntegerParameter(request, "subjectInPhotoId");

        Session session = HibernateUtil.currentSession();

        SubjectInPhoto sip = (SubjectInPhoto) session.load(SubjectInPhoto.class, subjectInPhotoId);

        // Redirect if navgation=next or navigation=prev
        String navigation = request.getParameter("navigation");
        if ("prev".equals(navigation)) {

            Integer prev = sip.getPreviousPhotoBySubject().getSubjectInPhotoId();
            // System.out.println( "PhotoBySubjectAction : Prev = " + prev );
            return parameterRedirect(mapping, "jump", "subjectInPhotoId", prev);

        } else if ("next".equals(navigation)) {

            Integer next = sip.getNextPhotoBySubject().getSubjectInPhotoId();
            // System.out.println( "PhotoBySubjectAction : Next = " + next );
            return parameterRedirect(mapping, "jump", "subjectInPhotoId", next);
        }

        return mapping.findForward("show");
    }

}

