<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">
  <tiles:putAttribute name="title" type="string" value="Edit Photo : ${familyAlbum.photo.label}" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <ww:box className="mainbox">
      <ww:boxTitle>
        Photo : <c:out value="${familyAlbum.photo.label}"/>
      </ww:boxTitle>

      <ww:boxContent>

        <!-- form -->
        <html:form action="/editPhoto">

          <input type="hidden" name="photoId" value="<c:out value="${familyAlbum.photo.photoId}"/>" />
          <table class="plainForm">

            <tr>
              <th>Year (yyyy)</th>
              <td><html:text property="year" maxlength="4" size="5"/></td>
            </tr>

            <tr>

              <th>Year Accuracy</th>
              <td>
                <html:select property="yearAccuracy">
                  <c:forEach items="${familyAlbum.familyAlbum.yearAccuracyLabels}" var="yal">
                    <html:option value="${yal.key}"><c:out value="${yal.value}"/></html:option>
                  </c:forEach>
                </html:select>
              </td>
            </tr>
            <tr>

              <th>Notes</th>
              <td>
                <html:textarea property="notes" rows="10" cols="60"/>
              </td>
            </tr>

            <tr>
              <td class="buttons" colspan="2">
                <html:submit>Ok</html:submit>
                <html:cancel>Cancel</html:cancel>
              </td>
            </tr>
          </table>

        </html:form>
        <!-- END form -->

      </ww:boxContent>
    </ww:box>

    <br/>

    <div class="center">
      <img id="photo" src="<c:out value="${familyAlbum.photoUrl}"/>" />
    </div>

  </tiles:putAttribute>
</tiles:insertTemplate>


