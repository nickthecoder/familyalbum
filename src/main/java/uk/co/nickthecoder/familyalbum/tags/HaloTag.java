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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import uk.co.nickthecoder.familyalbum.model.Subject;
import uk.co.nickthecoder.familyalbum.model.SubjectInPhoto;
import uk.co.nickthecoder.webwidgets.tags.LinkInfo;
import uk.co.nickthecoder.webwidgets.tags.LinkInfoTag;
import uk.co.nickthecoder.webwidgets.util.TagUtil;

/**
 * Redirects to a page, usually used after a POST.
 */

public class HaloTag extends TagSupport
{

    private static final long serialVersionUID = 1L;

    public static final String NO_IMAGE = "/resources/questionMarkHalo.png";

    public static final String DEFAULT_ACTION = "/action/halo";

    public static final String EDIT_URL = "/action/editSubject";

    public static final String EDIT_LABEL = "edit"; // not I18N

    public static final String EDIT_IMAGE = "/resources/editIcon.png";

    private SubjectInPhoto _subjectInPhoto;

    private Subject _subject;

    private String _action;

    private boolean _label;

    private String _title;

    private String _onclick;

    private String _id;

    private boolean _editable;

    public HaloTag()
    {
        super();
        initialise();
    }

    private void initialise()
    {
        _subject = null;
        _subjectInPhoto = null;
        _action = DEFAULT_ACTION;
        _label = false;
        _title = null;
        _onclick = null;
        _id = null;
        _editable = false;
    }

    public void release()
    {
        super.release();
        initialise();
    }

    public void setSubject(Subject value)
    {
        _subject = value;
    }

    public Subject getSubject()
    {
        if (_subject == null) {
            if (_subjectInPhoto != null) {
                return _subjectInPhoto.getSubject();
            }
        }

        return _subject;
    }

    public void setSubjectInPhoto(SubjectInPhoto value)
    {
        _subjectInPhoto = value;
    }

    public SubjectInPhoto getSubjectInPhoto()
    {
        if ((_subjectInPhoto == null) && (_subject != null)) {
            return _subject.getDefaultSubjectInPhoto();
        }

        return _subjectInPhoto;
    }

    public String getAction()
    {
        return _action;
    }

    public void setAction(String value)
    {
        _action = value;
    }

    public void setLabel(boolean value)
    {
        _label = value;
    }

    public boolean getLabel()
    {
        return _label;
    }

    public void setOnclick(String value)
    {
        _onclick = value;
    }

    public String getOnclick()
    {
        return _onclick;
    }

    public void setTitle(String title)
    {
        _title = title;
    }

    public String getTitle()
    {
        return _title;
    }

    public void setId(String value)
    {
        _id = value;
    }

    public String getId()
    {
        return _id;
    }

    public void setEditable(boolean value)
    {
        _editable = value;
    }

    public boolean getEditable()
    {
        return _editable;
    }

    private LinkInfo getLinkInfo() throws JspException
    {
        if (getAction() == null) {
            return LinkInfoTag.getAncestorLinkInfo(this);

        } else {
            return new LinkInfo(getAction());
        }
    }

    public String getContextPath()
    {
        return ((HttpServletRequest) pageContext.getRequest()).getContextPath();
    }

    public int doEndTag() throws JspException
    {
        try {

            JspWriter out = pageContext.getOut();

            Subject subject = getSubject();
            SubjectInPhoto sip = getSubjectInPhoto();

            if (subject == null) {
                subject = new Subject();
            }

            // Work out the intomation for the a tag href
            LinkInfo linkInfo = getLinkInfo();

            linkInfo.addParameter("subjectId", subject.getSubjectId());
            if (sip != null) {
                linkInfo.addParameter("subjectInPhotoId", sip.getSubjectInPhotoId());
            }
            String href = TagUtil.resolveUrl(pageContext, linkInfo.getLinkHref(), true);

            String imageUrl = (sip == null) ? getContextPath() + "/" + NO_IMAGE : sip.getHaloThumbnailUrl();

            /* a tag */
            out.print("<a");
            if (getTitle() == null) {
                TagUtil.printSafeAttribute(out, "title", subject.getLabel());
            } else {
                TagUtil.printSafeAttribute(out, "title", getTitle());
            }
            TagUtil.printAttribute(out, "href", href + "#main");

            if (getOnclick() != null) {
                TagUtil.printAttribute(out, "onclick", getOnclick());
            }
            out.print(">");

            /* img tag */
            out.print("<img");
            TagUtil.printSafeAttribute(out, "alt", subject.getLabel());
            TagUtil.printSafeAttribute(out, "src", imageUrl);
            if (getId() != null) {
                TagUtil.printSafeAttribute(out, "id", getId());
            }
            out.print("/>");

            /* label */
            if (getLabel()) {
                out.print("<br/>");
                out.print(TagUtil.safeText(subject.getLabel()));
            }

            /* edit link */
            if (getEditable()) {
                String editHref = TagUtil.resolveUrl(pageContext, EDIT_URL + "?subjectId=" + subject.getSubjectId(),
                                true);
                out.print("<a");
                TagUtil.printAttribute(out, "href", editHref);
                TagUtil.printSafeAttribute(out, "title", EDIT_LABEL);
                out.print(">");
                out.print("<img");
                TagUtil.printAttribute(out, "src", getContextPath() + EDIT_IMAGE);
                TagUtil.printAttribute(out, "alt", EDIT_LABEL);
                out.print("/>");
                out.print("</a>");
            }

            /* end a tag */
            out.print("</a>");

            // initialise();

        } catch (Exception e) {
            throw new JspException(e);
        }

        return EVAL_PAGE;
    }

}
