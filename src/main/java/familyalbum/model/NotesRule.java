/*
----------------------------------------------------------------------

 Author        :  (nick)
 Creation Date : 2004-03-23

----------------------------------------------------------------------

 History
 2004-03-23 : nick : Initial Development

----------------------------------------------------------------------

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

----------------------------------------------------------------------
 */

package familyalbum.model;

import java.util.regex.Pattern;

/**
 * A rule for rendering the notes. It uses simple regular expressions to get
 * very basic wiki style mark up within notes.
 */

public class NotesRule
{
    private Integer _notesRuleId;

    private String _label;

    private int _priority;

    private String _pattern;

    private String _replacement;

    private Pattern _compiledPattern;

    public NotesRule()
    {
        super();
        _priority = 50;
    }

    public Integer getNotesRuleId()
    {
        return _notesRuleId;
    }

    public void setNotesRuleId(Integer value)
    {
        _notesRuleId = value;
    }

    public String getLabel()
    {
        return _label;
    }

    public void setLabel(String value)
    {
        _label = value;
    }

    public int getPriority()
    {
        return _priority;
    }

    public void setPriority(int value)
    {
        _priority = value;
    }

    public String getPattern()
    {
        return _pattern;
    }

    public void setPattern(String value)
    {
        _compiledPattern = Pattern.compile(value);
        _pattern = value;
    }

    public String getReplacement()
    {
        return _replacement;
    }

    public void setReplacement(String value)
    {
        _replacement = value;
    }

    public String replaceAll(String value)
    {
        return _compiledPattern.matcher(value).replaceAll(_replacement);
    }
}
