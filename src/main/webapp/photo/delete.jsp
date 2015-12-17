<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Delete : ${familyAlbum.photo.label}" />
  <tiles:putAttribute name="main" type="string" >

    <br/>

    <!-- form -->
    <html:form action="/deletePhoto">

      <html:hidden property="photoId"/>

      <table class="alertForm">
        <tr>
          <td>
            Are you sure you want to delete this photo?
          </td>
        </tr>

        <tr>
          <td class="buttons">

            <html:submit>Ok</html:submit>
            <html:cancel>Cancel</html:cancel>

          </td>
        </tr>
      </table>

    </html:form>
    <!-- END form -->

    <br/>

    <!-- main image -->
    <div class="center">
      <img id="photo" src="<c:out value="${familyAlbum.photoUrl}"/>" />
    </div>

  </tiles:putAttribute>

</tiles:insertTemplate>

