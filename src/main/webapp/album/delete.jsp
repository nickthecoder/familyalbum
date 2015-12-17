<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">
  <tiles:putAttribute name="title" type="string" value="Delete Album : ${familyAlbum.album.label}" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <!-- form -->
    <html:form action="/deleteAlbum">

      <html:hidden property="albumId"/>

      <table class="alertForm">
        <tr>
          <td>
            Are you sure you want to delete this album, and all of its images?
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

        <!-- Album's Photos (thumbnails) -->
    <div class="whiteBox2">
      <table class="thumbnails">

        <ww:portion var="portions" items="${familyAlbum.album.photos}" portionSize="4" pad="normal">
          <c:forEach var="portion" items="${portions}">

            <tr>
              <c:forEach var="photo" items="${portion}">
              <td width="25%" class="thumbnailCell">
                <c:if test="${photo != null}" >
                  <div>

                    <ww:linkInfo href="/action/photo">
                      <ww:linkParameter name="photoId" value="${photo.photoId}"/>

                      <ww:link><img alt="" src="<c:out value="${photo.thumbnailUrl}"/>" /></ww:link>

                    </ww:linkInfo>
                  </div>
                </c:if>
              </td>
              </c:forEach>
            </tr>

            <tr>
              <c:forEach var="photo" items="${portion}">
              <td width="25%" class="labelCell">
                <c:if test="${photo != null}" >
                  <div>

                    <c:choose>
                      <c:when test="${ ! empty photo.notes}">
                        <img alt="note" src="<ww:contextPath/>/resources/note.png"/>
                      </c:when>
                      <c:otherwise>
                        <img src="<ww:contextPath/>/resources/blankIcon.png" alt=""/>
                      </c:otherwise>
                    </c:choose>
                    <fa:photoDate photo="${photo}" unknown="date ?"/>
                    <ww:link href="/action/editPhoto?photoId=${photo.photoId}" title="edit photo info"><img class="icon" src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link>

                    <br/>

                    <img src="<ww:contextPath/>/resources/blankIcon.png" alt=""/>
                    (&nbsp;<c:out value="${photo.subjectCount}"/>&nbsp;)
                    <ww:link href="/action/editSubjectsInPhoto?photoId=${photo.photoId}" title="tag people in photo"><img class="icon" src="<ww:contextPath/>/resources/editPeopleIcon.png" alt="edit"/></ww:link>

                    <c:if test="${! photo.imagesUpToDate }">
                      <br/>
                      updating
                    </c:if>

                  </div>
                </c:if>

              </td>
              </c:forEach>
            </tr>

          </c:forEach>
        </ww:portion>

      </table>
    </div>
    <!-- END Album's Photos (thumbnails) -->

  </tiles:putAttribute>
</tiles:insertTemplate>

