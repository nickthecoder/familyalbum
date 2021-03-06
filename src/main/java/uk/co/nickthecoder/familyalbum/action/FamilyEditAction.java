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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import uk.co.nickthecoder.familyalbum.form.FamilyForm;
import uk.co.nickthecoder.familyalbum.model.Family;
import uk.co.nickthecoder.familyalbum.util.HibernateUtil;


public class FamilyEditAction extends FamilyAction
{

    public FamilyEditAction()
    {
        super();
    }

    protected ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        FamilyForm familyForm = (FamilyForm) form;

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Family family;

        try {
            family = getFamily(request);

            family.setLabel(familyForm.getLabel());
            family.setSubLabel(familyForm.getSubLabel());
            family.setNotes(familyForm.getNotes());

            session.save(family);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            throw e;
        }

        // Attempt to create the family's directory
        try {
            File directory = family.getDirectory();
            directory.mkdirs();
        } catch (Exception e) {
            // Don't worry
        }

        return parameterRedirect(mapping, "saved", family.getFamilyId());
    }

}

