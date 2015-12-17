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

import familyalbum.form.FamilyForm;
import familyalbum.model.Family;
import familyalbum.util.HibernateUtil;

public class FamilyAction extends GetPostAction
{
    public FamilyAction()
    {
        super();
    }

    // -------------------- [[Methods]] --------------------

    protected ActionForward cancelled(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        FamilyForm familyForm = (FamilyForm) form;

        // System.out.println( "Cancelling action of family : " +
        // familyForm.getFamilyId() );
        return parameterRedirect(mapping, "cancelled", familyForm.getFamilyId());
    }

    protected ActionForward get(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        Family family = getFamily(request);

        if (family == null) {

        } else {
            FamilyForm familyForm = (FamilyForm) form;

            familyForm.setFamilyId(family.getFamilyId());
            familyForm.setLabel(family.getLabel());
            familyForm.setSubLabel(family.getSubLabel());
            familyForm.setNotes(family.getNotes());
        }

        return mapping.findForward("show");
    }

    protected Family getFamily(HttpServletRequest request)
    {
        int familyId = getIntParameter(request, "familyId", 0);

        if (familyId == 0) {
            return new Family();
        }

        Session session = HibernateUtil.currentSession();
        Family family = (Family) session.load(Family.class, new Integer(familyId));
        // System.out.println( "Loaded family : " + family.getLabel() );

        return family;
    }

    protected ActionForward parameterRedirect(ActionMapping mapping, String mappingName, Integer familyId)
    {
        return parameterRedirect(mapping, mappingName, "familyId", familyId);
    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class FamilyAction ----------

