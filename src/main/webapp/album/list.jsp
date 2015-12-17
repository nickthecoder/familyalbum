<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">
  <tiles:putAttribute name="title" type="string" value="Albums" />

  <tiles:putAttribute name="main" type="string" >


    <c:choose>
      <c:when test="${fn:length( familyAlbum.albums ) == 0}">
        <p>
          There are no photos albums yet. Why not
          <ww:link href="/action/newAlbum?familyAlbumId=${familyAlbum.family.familyId}">Add a Photo Album <img class="icon" alt="add" src="<ww:contextPath/>/resources/add.png"/></ww:link>
        </p>
      </c:when>
      <c:otherwise>
        <div style="text-align: right;">
          <ww:link href="/action/newAlbum?familyAlbumId=${familyAlbum.family.familyId}">Add Photo Album <img class="icon" alt="add" src="<ww:contextPath/>/resources/add.png"/></ww:link>
        </div>
      </c:otherwise>
      </c:choose>

    <c:forEach items="${familyAlbum.albums}" var="album">

      <div class="boxContainer">

        <!-- album heading -->
        <h2>
          <ww:link href="/action/album?albumId=${album.albumId}">
            <c:out value="${album.label}"/>
          </ww:link>
          <ww:link href="/action/editAlbum?albumId=${album.albumId}" title="edit album info"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link>
        </h2>

        <!-- first few photos of each album -->
        <table align="center" width="100%">
          <tr>
            <c:forEach items="${album.photos}" var="photo" varStatus="status">
              <c:if test="${status.count < 6}">
                <td width="100" align="center">
                  <ww:link href="/action/album?albumId=${album.albumId}">
                    <img height="56px" src="<c:out value="${photo.thumbnailUrl}"/>" />
                  </ww:link>
                </td>
              </c:if>
            </c:forEach>
          </tr>
        </table>

        <!-- album notes -->
        <div class="notes">
          <pw:wikiText summary="true" summaryLength="100">${album.notes}</pw:wikiText>
        </div>

      </div>

      <br/>
      <br/>

    </c:forEach>

  </tiles:putAttribute>
</tiles:insertTemplate>

