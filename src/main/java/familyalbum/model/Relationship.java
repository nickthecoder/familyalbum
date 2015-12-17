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

package familyalbum.model;


public class Relationship
{

    private RelationshipId _id;

    private RelationshipType _relationshipType;

    private Subject _fromSubject;

    private Subject _toSubject;

    public Relationship()
    {
        super();
        _id = new RelationshipId();
    }

    public void setId(RelationshipId value)
    {
        _id = value;
    }

    public RelationshipId getId()
    {
        return _id;
    }

    public RelationshipId getReverseId()
    {
        return new RelationshipId(getId().getToSubjectId(), getId().getFromSubjectId());
    }

    public void setRelationshipType(RelationshipType value)
    {
        _relationshipType = value;
    }

    public RelationshipType getRelationshipType()
    {
        return _relationshipType;
    }

    public void setFromSubject(Subject value)
    {
        _fromSubject = value;
        _id.setFromSubjectId(value.getSubjectId());
    }

    public Subject getFromSubject()
    {
        return _fromSubject;
    }

    public void setToSubject(Subject value)
    {
        _toSubject = value;
        _id.setToSubjectId(value.getSubjectId());
    }

    public Subject getToSubject()
    {
        return _toSubject;
    }

    public String getIdString()
    {
        return getRelationshipType().getRelationshipTypeId().toString() + ":"
                        + getToSubject().getSubjectId().toString();
    }

    public String getLabel()
    {
        return getToSubject().getLabel() + "(" + getRelationshipType().getLabel() + ")";
    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class Relationship ----------

