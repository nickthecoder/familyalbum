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

import java.util.HashSet;
import java.util.Set;

public class RelationshipType
{

    public static final Set PARENTS_SET = createSet(new int[] { 1, 2 });

    public static final Set SIBLINGS_SET = createSet(new int[] { 5, 6 });

    public static final Set CHILDREN_SET = createSet(new int[] { 3, 4 });

    public static final Set PARTNERS_SET = createSet(new int[] { 20, 21, 22, 23 });

    public static final Set OTHERS_SET = createSet(new int[] { 101, 102, 201 });

    private Integer _relationshipTypeId;

    private String _label;

    private Character _sex;

    private RelationshipType _reverseMale;

    private RelationshipType _reverseFemale;

    private static Set createSet(int[] values)
    {
        Set result = new HashSet();
        for (int i = 0; i < values.length; i++) {
            Integer key = new Integer(values[i]);
            result.add(key);
        }
        return result;
    }

    public RelationshipType()
    {
        super();
    }

    public Integer getRelationshipTypeId()
    {
        return _relationshipTypeId;
    }

    public void setRelationshipTypeId(Integer value)
    {
        _relationshipTypeId = value;
    }

    public String getLabel()
    {
        return _label;
    }

    public void setLabel(String value)
    {
        _label = value;
    }

    public Character getSex()
    {
        return _sex;
    }

    public void setSex(Character value)
    {
        _sex = value;
    }

    public void setSex(String value)
    {
        String upper = value.toUpperCase();
        if ("M".equals(upper)) {
            setSex(Subject.MALE);
        } else if ("F".equals(upper)) {
            setSex(Subject.FEMALE);
        } else {
            setSex(Subject.SEX_UNKNOWN);
        }
    }

    public boolean isMale()
    {
        return (_sex != null) && _sex.equals(Subject.MALE);
    }

    public boolean isFemale()
    {
        return (_sex != null) && _sex.equals(Subject.FEMALE);
    }

    public RelationshipType getReverseMale()
    {
        return _reverseMale;
    }

    public void setReverseMale(RelationshipType value)
    {
        _reverseMale = value;
    }

    public RelationshipType getReverseFemale()
    {
        return _reverseFemale;
    }

    public void setReverseFemale(RelationshipType value)
    {
        _reverseFemale = value;
    }

    public RelationshipType getReverse(Subject subject)
    {
        if (subject.isMale()) {
            if (getReverseMale() != null) {
                return getReverseMale();
            }
        } else if (subject.isFemale()) {
            if (getReverseFemale() != null) {
                return getReverseFemale();
            }
        } else {
            if (getReverseMale() == getReverseFemale()) {
                return getReverseMale();
            }
        }

        return null;
    }

}
