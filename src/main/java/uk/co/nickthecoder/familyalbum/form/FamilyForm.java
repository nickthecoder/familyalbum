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

package uk.co.nickthecoder.familyalbum.form;

import org.apache.struts.action.ActionForm;

public class FamilyForm extends ActionForm
{

    private static final long serialVersionUID = 1L;

    private Integer _familyId;

    private String _label;

    private String _subLabel;

    private String _notes;

    public FamilyForm()
    {
        super();
    }

    public Integer getFamilyId()
    {
        return _familyId;
    }

    public void setFamilyId(Integer value)
    {
        _familyId = value;
    }

    public String getLabel()
    {
        return _label;
    }

    public void setLabel(String value)
    {
        _label = value;
    }

    public String getSubLabel()
    {
        return _subLabel;
    }

    public void setSubLabel(String value)
    {
        _subLabel = value;
    }

    public String getNotes()
    {
        return _notes;
    }

    public void setNotes(String value)
    {
        _notes = value;
    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class AlbumForm ----------

