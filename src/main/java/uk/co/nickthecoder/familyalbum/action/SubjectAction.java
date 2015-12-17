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

package uk.co.nickthecoder.familyalbum.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import uk.co.nickthecoder.familyalbum.form.SubjectForm;
import uk.co.nickthecoder.familyalbum.model.Subject;
import uk.co.nickthecoder.familyalbum.util.HibernateUtil;

public class SubjectAction extends GetPostAction
{

    public SubjectAction()
    {
        super();
    }

    protected ActionForward cancelled(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        SubjectForm subjectForm = (SubjectForm) form;

        // System.out.println( "Cancelling action of subject : " +
        // subjectForm.getSubjectId() );
        return parameterRedirect(mapping, "cancelled", subjectForm.getSubjectId());
    }

    protected ActionForward get(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        int subjectId = getIntParameter(request, "subjectId", 0);

        Session session = HibernateUtil.currentSession();
        Subject subject;

        if (subjectId == 0) {
            subject = new Subject();
        } else {
            subject = (Subject) session.load(Subject.class, new Integer(subjectId));
        }

        SubjectForm subjectForm = (SubjectForm) form;
        subjectForm.initialise(subject);

        return findShowMapping(mapping, request);
    }

    public ActionForward findShowMapping(ActionMapping mapping, HttpServletRequest request)
    {
        String page = request.getParameter("page");
        if (page != null) {
            ActionForward af = mapping.findForward(page);
            if (af != null) {
                return af;
            }
        }

        return mapping.findForward("show");
    }

    public Subject getSubject(HttpServletRequest request)
    {
        int subjectId = getIntParameter(request, "subjectId", 0);
        if (subjectId == 0) {
            return new Subject();
        } else {
            // System.out.println( "Loading subject : " + subjectId );
            return (Subject) HibernateUtil.currentSession().load(Subject.class, new Integer(subjectId));
        }
    }

    protected ActionForward parameterRedirect(ActionMapping mapping, String mappingName, Subject subject)
    {
        return parameterRedirect(mapping, mappingName, "subjectId", subject.getSubjectId());
    }

    protected ActionForward parameterRedirect(ActionMapping mapping, String mappingName, Integer subjectId)
    {
        return parameterRedirect(mapping, mappingName, "subjectId", subjectId);
    }

}

