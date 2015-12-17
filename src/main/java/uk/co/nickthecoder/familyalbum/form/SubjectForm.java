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

import uk.co.nickthecoder.familyalbum.model.Subject;

public class SubjectForm extends ActionForm
{
    private static final long serialVersionUID = 1L;

    private Integer _subjectId;

    private String _name1;

    private String _name2;

    private String _notes;

    private Integer _defaultSubjectInPhotoId;

    private String _sex;

    private Integer _subjectTypeId;

    public SubjectForm()
    {
        super();
    }

    public void initialise(Subject subject)
    {
        setSubjectId(subject.getSubjectId());
        setName1(subject.getName1());
        setName2(subject.getName2());
        setNotes(subject.getNotes());
        setSex(subject.getSex());
        setDefaultSubjectInPhotoId(subject.getDefaultSubjectInPhotoId());
        setSubjectTypeId(subject.getSubjectTypeId());
    }

    // -------------------- [[Methods]] --------------------

    public Integer getSubjectId()
    {
        return _subjectId;
    }

    public void setSubjectId(Integer value)
    {
        _subjectId = value;
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

    public String getNotes()
    {
        return _notes;
    }

    public void setNotes(String value)
    {
        _notes = value;
    }

    public Integer getDefaultSubjectInPhotoId()
    {
        return _defaultSubjectInPhotoId;
    }

    public void setDefaultSubjectInPhotoId(Integer value)
    {
        _defaultSubjectInPhotoId = value;
    }

    public String getSex()
    {
        return _sex;
    }

    public void setSex(String value)
    {
        _sex = value;
    }

    public Integer getSubjectTypeId()
    {
        return _subjectTypeId;
    }

    public void setSubjectTypeId(Integer value)
    {
        _subjectTypeId = value;
    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class SubjectForm ----------

