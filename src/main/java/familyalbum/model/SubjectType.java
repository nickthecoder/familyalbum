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

import java.util.HashMap;

/**
 * For effeiciency, this lookup table is not retrieved from the database, the
 * rows are fixed (hard-coded).
 */
public class SubjectType
{

    public static final SubjectType FAMILY_MEMBER = new SubjectType(1, "Family Member", "Family Members");

    public static final SubjectType PET = new SubjectType(2, "Pet", "Pets");

    public static final SubjectType FRIEND = new SubjectType(3, "Friend", "Friends");

    private static HashMap _subjectTypes;

    private Integer _subjectTypeId;

    private String _label;

    private String _categoryLabel;

    // -------------------- [[Static Methods]] --------------------

    public static SubjectType get(Integer subjectTypeId)
    {
        if (_subjectTypes == null) {
            _subjectTypes = new HashMap();
            addSubjectType(FAMILY_MEMBER);
            addSubjectType(PET);
            addSubjectType(FRIEND);
        }

        return (SubjectType) _subjectTypes.get(subjectTypeId);
    }

    private static void addSubjectType(SubjectType subjectType)
    {
        _subjectTypes.put(subjectType.getSubjectTypeId(), subjectType);
    }

    public SubjectType()
    {
        super();
    }

    public SubjectType(int subjectTypeId, String label, String categoryLabel)
    {
        super();
        _subjectTypeId = new Integer(subjectTypeId);
        _label = label;
        _categoryLabel = categoryLabel;
    }

    public Integer getSubjectTypeId()
    {
        return _subjectTypeId;
    }

    public void setSubjectTypeId(Integer value)
    {
        _subjectTypeId = value;
    }

    public String getLabel()
    {
        return _label;
    }

    public void setLabel(String value)
    {
        _label = value;
    }

    public String getCategoryLabel()
    {
        return _categoryLabel;
    }

    public void setCategoryLabel(String value)
    {
        _categoryLabel = value;
    }

}
