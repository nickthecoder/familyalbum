<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Album : ${familyAlbum.album.label}" />

  <tiles:putAttribute name="width" type="string" value="780" />

  <tiles:putAttribute name="main" type="string" >

    <br/>
    <div style="text-align: right">
      <ww:link href="/action/uploadPhoto?albumId=${familyAlbum.album.albumId}">
        Add Photo <img class="icon" alt="add" src="<ww:contextPath/>/resources/add.png" />
      </ww:link>

      <ww:link href="/action/scanAlbum?albumId=${familyAlbum.album.albumId}">
        Scan Directory
        <img alt="scan directory" src="<ww:contextPath/>/resources/scanDirectory.png" />
      </ww:link>
    </div>
    <br/>

    <c:if test="${empty familyAlbum.album.photos}">
      <div class="tips">
        <h3>Tip</h3>
        <ul>
          <li>
            This album has no photos in it yet. Click the
            <ww:link href="/action/uploadPhoto?albumId=${familyAlbum.album.albumId}">
              Add Photo <img class="icon" alt="add" src="<ww:contextPath/>/resources/add.png" />
            </ww:link>
            link to begin adding photos.
          </li>
        </ul>
      </div>
      <br/>
    </c:if>

    <!-- Album Notes -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        Notes
        <ww:boxIcon>
          <ww:link href="/action/editAlbum?albumId=${familyAlbum.album.albumId}" title="edit album info"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/> Edit</ww:link>
        </ww:boxIcon>
      </ww:boxTitle>

      <ww:boxContent>
        <div class="notes">
          <c:if test="${empty familyAlbum.album.notes}">
            None
          </c:if>
          <pw:wikiText>${familyAlbum.album.notes}</pw:wikiText>
        </div>
     </ww:boxContent>
    </ww:box>


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
                        <img class="icon" alt="note" src="<ww:contextPath/>/resources/note.png"/>
                      </c:when>
                      <c:otherwise>
                        <img class="icon" src="<ww:contextPath/>/resources/blankIcon.png" alt=""/>
                      </c:otherwise>
                    </c:choose>
                    <fa:photoDate photo="${photo}" unknown="date ?"/>
                    <ww:link href="/action/editPhoto?photoId=${photo.photoId}" title="edit photo info"><img class="icon" src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link>

                    <br/>

                    <img class="icon" src="<ww:contextPath/>/resources/blankIcon.png" alt=""/>
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

    <br/>

    <div class="tips">
      <h3>Tips</h3>
      <ul>
        <li>
          Underneath each photo, there is date the photo was taken, and then the number of people in the
          photograph. If either of these are wrong, please correct it by clicking on the
          'edit' icon to the right of the date.
        </li>
        <li>
          Photos with notes attached to them, and are marked like this :
          <img alt="note" src="<ww:contextPath/>/resources/note.png"/>.
        </li>
        <li>
          Under each photo are two edit links.
          <br/>
          The first one
          (<img alt="" src="<ww:contextPath/>/resources/editIcon.png"/>)
          edits the information about the photo, such as the date.
          <br/>
          The second one
          (<img alt="" src="<ww:contextPath/>/resources/editPeopleIcon.png"/>)
          lets you tag who is in the photo.
        </li>
      </ul>

    </div>

    <div class="wikiLinkHere">
      [<c:out value="${familyAlbum.album.label}"/>|album:${familyAlbum.album.albumId}]
    </div>

  </tiles:putAttribute>
</tiles:insertTemplate>

