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

package familyalbum.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import uk.co.nickthecoder.webwidgets.util.TagUtil;
import familyalbum.model.NotesRule;
import familyalbum.util.HibernateUtil;

/**
  Redirects to a page, usually used after a POST.
*/

public class NotesTag
  extends BodyTagSupport
{

  // -------------------- [[Static Attributes]] --------------------

  private static List _rules;

  // -------------------- [[Attributes]] --------------------

  private boolean _escape;

  // -------------------- [[Static Methods]] --------------------

  protected synchronized List getRules()
  {
    if ( _rules == null ) {

      _rules = HibernateUtil.currentSession()
        .createQuery( "from NotesRule order by priority" )
        .list();
    }

    return _rules;
  }


  // -------------------- [[Constructors]] --------------------

  /**
  */
  public NotesTag()
  {
    super();
    initialise();
  }


  private void initialise()
  {
    _escape = true;
  }

  public void release()
  {
    super.release();
    initialise();
  }

  // -------------------- [[Methods]] --------------------


  public void setEscape( boolean value )
  {
    _escape = value;
  }

  public boolean getEscape()
  {
    return _escape;
  }

  public int doStartTag()
    throws JspException
  {
    return EVAL_BODY_BUFFERED;
  }


  public int doAfterBody()
    throws JspException
  {
    try {

      String body = getBodyContent().getString();
      JspWriter out = getPreviousOut();

      if ( getEscape() ) {
        body = TagUtil.safeText( body );
      }

      List rules = getRules();
      for ( Iterator i = rules.iterator(); i.hasNext(); ) {
        NotesRule rule = (NotesRule) i.next();
        body = rule.replaceAll( body );
      }

      out.println( body );

      return SKIP_BODY;

    } catch (IOException e) {
      // @MORE@
      e.printStackTrace();
      throw new JspException( "Unexpected IO Exception." );
    }
  }

  public int doEndTag()
    throws JspException
  {
    System.out.println( "end tag" );
    return EVAL_PAGE;
  }


  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class NotesTag ----------

