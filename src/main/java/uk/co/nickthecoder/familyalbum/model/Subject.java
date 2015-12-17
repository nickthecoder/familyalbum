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

package uk.co.nickthecoder.familyalbum.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import uk.co.nickthecoder.familyalbum.util.HibernateUtil;

public class Subject
{

    public static final String MALE = "M";

    public static final String FEMALE = "F";

    public static final String SEX_UNKNOWN = null;

    private Integer _subjectId;

    private Family _family;

    private String _name1;

    private String _name2;

    private String _notes;

    private String _sex;

    private Set<SubjectInPhoto> _subjectInPhotos = new HashSet<SubjectInPhoto>();

    private Set<Relationship> _relationships = new HashSet<Relationship>();

    private Integer _subjectTypeId;

    /*
     * private Photo _defaultPhoto;
     */
    private Integer _defaultSubjectInPhotoId;

    public Subject()
    {
        super();
        _subjectTypeId = SubjectType.FAMILY_MEMBER.getSubjectTypeId();
    }

    public Integer getSubjectId()
    {
        return _subjectId;
    }

    public void setSubjectId(Integer value)
    {
        _subjectId = value;
    }

    public boolean isNew()
    {
        return _subjectId == null;
    }

    public Family getFamily()
    {
        return _family;
    }

    public void setFamily(Family family)
    {
        _family = family;
    }

    public Integer getSubjectTypeId()
    {
        return _subjectTypeId;
    }

    public void setSubjectTypeId(Integer value)
    {
        _subjectTypeId = value;
    }

    public SubjectType getSubjectType()
    {
        return SubjectType.get(getSubjectTypeId());
    }

    public String getName1()
    {
        return _name1;
    }

    public void setName1(String value)
    {
        _name1 = value;
    }

    public String getName2()
    {
        return _name2;
    }

    public void setName2(String value)
    {
        _name2 = value;
    }

    public String getLabel()
    {
        if (getName2() == null) {
            return getName1();
        } else {
            return getName1() + " " + getName2();
        }
    }

    public String getSortedLabel()
    {
        if ((getName2() == null) || (getName2().equals(""))) {
            return getName1();
        } else {
            return getName2() + ", " + getName1();
        }
    }

    public String getSex()
    {
        return _sex;
    }

    public void setSex(String value)
    {
        _sex = value;
    }

    public void setSex2(String value)
    {
        if (value == null) {
            setSex(SEX_UNKNOWN);
        } else {

            String upper = value.toUpperCase();
            if ("M".equals(upper)) {
                setSex(MALE);
            } else if ("F".equals(upper)) {
                setSex(FEMALE);
            } else {
                setSex(SEX_UNKNOWN);
            }
        }
    }

    public boolean isMale()
    {
        return (_sex != null) && _sex.equals(MALE);
    }

    public boolean isFemale()
    {
        return (_sex != null) && _sex.equals(FEMALE);
    }

    public String getNotes()
    {
        return _notes;
    }

    public void setNotes(String value)
    {
        _notes = value;
    }

    public Set<SubjectInPhoto> getSubjectInPhotos()
    {
        return _subjectInPhotos;
    }

    public void setSubjectInPhotos(Set<SubjectInPhoto> value)
    {
        _subjectInPhotos = value;
    }

    public Set<Relationship> getRelationships()
    {
        return _relationships;
    }

    public void setRelationships(Set<Relationship> value)
    {
        _relationships = value;
    }

    public Set<Relationship> getRelationships(Set<Integer> relationshipTypesIds)
    {
        Set<Relationship> result = new HashSet<Relationship>();
        for (Iterator<Relationship> i = getRelationships().iterator(); i.hasNext();) {
            Relationship relationship = i.next();

            if (relationshipTypesIds.contains(relationship.getRelationshipType().getRelationshipTypeId())) {
                result.add(relationship);
            }
        }

        return result;
    }

    public Set<Relationship> getParents()
    {
        return getRelationships(RelationshipType.PARENTS_SET);
    }

    public Set<Relationship> getSiblings()
    {
        return getRelationships(RelationshipType.SIBLINGS_SET);
    }

    public Set<Relationship> getChildren()
    {
        return getRelationships(RelationshipType.CHILDREN_SET);
    }

    public Set<Relationship> getPartners()
    {
        return getRelationships(RelationshipType.PARTNERS_SET);
    }

    public Set<Relationship> getOtherRelationships()
    {
        return getRelationships(RelationshipType.OTHERS_SET);
    }

    /*
     * public void setDefaultPhoto( Photo value ) { _defaultPhoto = value; }
     * 
     * public Photo getDefaultPhoto() { return _defaultPhoto; }
     */

    public void setDefaultSubjectInPhotoId(Integer value)
    {
        _defaultSubjectInPhotoId = value;
    }

    public Integer getDefaultSubjectInPhotoId()
    {
        return _defaultSubjectInPhotoId;
    }

    // Remove this, and replace with a proper hibernate implementation.
    public SubjectInPhoto getDefaultSubjectInPhoto()
    {
        if (getDefaultSubjectInPhotoId() == null) {
            return null;
        }

        return (SubjectInPhoto) HibernateUtil.currentSession().load(SubjectInPhoto.class, getDefaultSubjectInPhotoId());
    }

}
