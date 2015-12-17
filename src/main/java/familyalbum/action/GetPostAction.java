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

package familyalbum.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import familyalbum.bean.FamilyAlbumBean;
import familyalbum.model.FamilyAlbum;

public class GetPostAction extends Action
{

    // -------------------- [[Static Attributes]] --------------------

    // -------------------- [[Attributes]] --------------------

    // -------------------- [[Static Methods]] --------------------

    public static int getIntParameter(HttpServletRequest request, String name, int defaultValue)
    {
        String value = request.getParameter(name);
        if (value == null) {
            return defaultValue;
        } else {

            try {
                return Integer.parseInt(value);
            } catch (Exception e) {
                return defaultValue;
            }
        }
    }

    public static Integer getIntegerParameter(HttpServletRequest request, String name)
    {
        String value = request.getParameter(name);
        if ((value == null) || (value.equals("0"))) {
            return null;
        } else {

            try {
                return new Integer(Integer.parseInt(value));
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static boolean getBoolParameter(HttpServletRequest request, String name)
    {
        if (request.getParameter(name) == null) {
            return false;
        } else {
            return !("0".equals(request.getParameter(name)));
        }
    }

    // -------------------- [[Constructors]] --------------------

    /**
  */
    public GetPostAction()
    {
        super();
    }

    // -------------------- [[Methods]] --------------------

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        try {

            initRequest(mapping, form, request, response);

            if (isCancelled(request)) {
                return cancelled(mapping, form, request, response);
            } else {

                if (request.getMethod().equals("POST")) {
                    return post(mapping, form, request, response);
                } else if (request.getMethod().equals("GET")) {
                    return get(mapping, form, request, response);
                } else {
                    return unknownMethod(mapping, form, request, response);
                }

            }

        } catch (Exception e) {
            request.setAttribute("familyAlbum_error", e);
            return mapping.findForward("error");
        }

    }

    protected void initRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response)
    {
        // FamilyAlbum.instance().setBaseDirectory( new File(
        // servletContext.getRealPath( "/" ) ) );
        FamilyAlbum.instance().setBaseDirectory(new File("/gidea/images/photos/Family Album"));
        FamilyAlbum.instance().setBaseUrl("/images/photos/Family%20Album");

        // This is here to initialise the FamilyAlbumBean to values based on
        // parameters passed
        // in the request. Later on I would like to write a piece of code that
        // does this in a generic
        // way - similar to JSF's managed beans.
        FamilyAlbumBean fab = new FamilyAlbumBean();
        request.setAttribute("familyAlbum", fab);

        fab.initialise(request);
    }

    protected FamilyAlbumBean getFamilyAlbumBean(HttpServletRequest request)
    {
        return (FamilyAlbumBean) request.getAttribute("familyAlbum");
    }

    protected ActionForward cancelled(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        return mapping.findForward("cancelled");
    }

    protected ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        throw new Exception("post is not supported");
    }

    protected ActionForward get(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        throw new Exception("get is not suppoerted");
    }

    protected ActionForward unknownMethod(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                    HttpServletResponse response) throws Exception
    {
        throw new Exception("unknown method is not suppoerted");
    }

    protected ActionForward parameterRedirect(ActionMapping mapping, String mappingName, String parameterName,
                    Object parameterValue)
    {
        if (parameterValue == null) {
            return mapping.findForward(mappingName);

        } else {
            ActionRedirect actionRedirect = new ActionRedirect(mapping.findForward(mappingName));
            actionRedirect.addParameter(parameterName, parameterValue);
            return actionRedirect;
        }

    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class GetPostAction ----------

