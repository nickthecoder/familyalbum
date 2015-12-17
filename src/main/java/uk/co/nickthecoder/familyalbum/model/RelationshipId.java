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

import java.io.Serializable;

public class RelationshipId implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Integer _fromSubjectId;

    private Integer _toSubjectId;

    public RelationshipId()
    {
        super();
    }

    public RelationshipId(Integer from, Integer to)
    {
        _fromSubjectId = from;
        _toSubjectId = to;
    }

    public void setFromSubjectId(Integer value)
    {
        _fromSubjectId = value;
    }

    public Integer getFromSubjectId()
    {
        return _fromSubjectId;
    }

    public void setToSubjectId(Integer value)
    {
        _toSubjectId = value;
    }

    public Integer getToSubjectId()
    {
        return _toSubjectId;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof RelationshipId) {
            RelationshipId other = (RelationshipId) obj;

            return (other.getFromSubjectId().equals(getFromSubjectId()))
                            && (other.getToSubjectId().equals(getToSubjectId()));
        } else {
            return false;
        }
    }

    public int hashCode()
    {
        return (getFromSubjectId().hashCode()) + 17 * (getToSubjectId().hashCode());
    }

    public String toString()
    {
        return "(" + getFromSubjectId() + "," + getToSubjectId() + ")";
    }

}
