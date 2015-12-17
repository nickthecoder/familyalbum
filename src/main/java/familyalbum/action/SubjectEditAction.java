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

import familyalbum.model.Family;
import familyalbum.model.Relationship;
import familyalbum.model.RelationshipType;
import familyalbum.model.Subject;
import familyalbum.model.SubjectInPhoto;
import familyalbum.util.HibernateUtil;

public class SubjectEditAction extends SubjectAction
{

    public SubjectEditAction()
    {
        super();
    }

    protected ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        Subject subject = getSubject(request);

        if ("relationships".equals(request.getParameter("page"))) {

            // System.out.println( "saving relationships : " +
            // subject.getSubjectId() );

            // Delete the old relationships
            for (Iterator i = subject.getRelationships().iterator(); i.hasNext();) {
                Relationship relationship = (Relationship) i.next();
                i.remove();
                session.delete(relationship);
                Relationship inverse = (Relationship) session.get(Relationship.class, relationship.getReverseId());
                if (inverse != null) {
                    session.delete(inverse);
                }
            }

            // Create the relationships
            String[] stringRelationships = request.getParameterValues("relationships");

            if (stringRelationships == null) {
                // System.out.println( "There are no relationships to save" );

            } else {
                // System.out.println( "Going to process " +
                // stringRelationships.length + " relationships" );

                for (int i = 0; i < stringRelationships.length; i++) {
                    int relationshipTypeId = Integer.parseInt(stringRelationships[i].replaceFirst(":.*", ""));
                    int relativeSubjectId = Integer.parseInt(stringRelationships[i].replaceFirst(".*:", ""));

                    // System.out.println( "Relationship : " +
                    // relationshipTypeId + " : " + relativeSubjectId );

                    Subject otherSubject = (Subject) session.load(Subject.class, new Integer(relativeSubjectId));
                    // System.out.println( "otherSubject : " + otherSubject );

                    RelationshipType relationshipType = (RelationshipType) session.load(RelationshipType.class,
                                    new Integer(relationshipTypeId));
                    // System.out.println( relationshipType );

                    Relationship relationship = new Relationship();
                    relationship.setFromSubject(subject);
                    relationship.setToSubject(otherSubject);
                    relationship.setRelationshipType(relationshipType);
                    // System.out.println( "created relationship in mem" );

                    session.save(relationship);
                    // System.out.println( "saved relationship : " +
                    // relationship );

                    RelationshipType reverseType = relationshipType.getReverse(subject);
                    // System.out.println( "reverseType = " + reverseType );

                    if (reverseType != null) {
                        // System.out.println( "saving reverse relationship : "
                        // + otherSubject.getLabel() + " > " +
                        // reverseType.getLabel() );
                        Relationship inverse = new Relationship();
                        inverse.setId(relationship.getReverseId());
                        inverse.setRelationshipType(reverseType);
                        session.save(inverse);
                    }

                }
            }

            tx.commit();
            return parameterRedirect(mapping, "saved", subject);

        } else if ("halos".equals(request.getParameter("page"))) {

            // Halos

            Integer subjectInPhotoId = getIntegerParameter(request, "subjectInPhotoId");
            SubjectInPhoto subjectInPhoto = (SubjectInPhoto) session.load(SubjectInPhoto.class, subjectInPhotoId);
            subjectInPhoto.setUseHalo(getBoolParameter(request, "useHalo"));

            session.save(subjectInPhoto);
            tx.commit();

            return parameterRedirect(mapping, "saved", subject);

        } else {
            // Subject details

            // System.out.println( "saving : " + subject.getSubjectId() );

            if (subject.isNew()) {
                Family family = getFamilyAlbumBean(request).getFamily();
                family.addSubject(subject);
            }

            subject.setName1(request.getParameter("name1"));
            subject.setName2(request.getParameter("name2"));
            subject.setNotes(request.getParameter("notes"));
            subject.setDefaultSubjectInPhotoId(getIntegerParameter(request, "defaultSubjectInPhotoId"));
            // System.out.println(
            // "AubjectEditAction : Set defaultSubjectInPhotoId: " +
            // subject.getDefaultSubjectInPhotoId() );
            subject.setSex2(request.getParameter("sex"));
            subject.setSubjectTypeId(getIntegerParameter(request, "subjectTypeId"));

            session.save(subject);
            tx.commit();

            return parameterRedirect(mapping, "saved", subject);
        }

    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class SubjectEditAction ----------

