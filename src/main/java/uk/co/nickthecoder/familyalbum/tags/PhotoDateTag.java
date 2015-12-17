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

package uk.co.nickthecoder.familyalbum.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.familyalbum.model.Photo;

/**
 * Redirects to a page, usually used after a POST.
 */

public class PhotoDateTag extends TagSupport
{

    private static final long serialVersionUID = 1L;

    private Photo _photo;

    private String _unknown;

    public PhotoDateTag()
    {
        super();
        initialise();
    }

    private void initialise()
    {
        _photo = null;
        _unknown = "date unknown";
    }

    public void release()
    {
        super.release();
        initialise();
    }

    public void setPhoto(Photo value)
    {
        _photo = value;
    }

    public void setUnknown(String value)
    {
        _unknown = value;
    }

    public int doEndTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            if ((_photo.getYear() == null) || (_photo.getYear().intValue() == 0)) {
                out.print(_unknown);
            } else {
                out.print(_photo.getYear());
                if (_photo.getYearAccuracy() > 1) {
                    out.print(" ish");
                }
            }

        } catch (Exception e) {
            throw new JspException(e);
        }

        return EVAL_PAGE;
    }

}
