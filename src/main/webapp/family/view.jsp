<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="true">

  <tiles:putAttribute name="title" type="string" value="Family Details" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <ww:box className="mainbox">
      <ww:boxTitle>
        Family Details
        <ww:link href="/action/editFamily?familyId=${familyAlbum.family.familyId}"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link>
      </ww:boxTitle>
      <ww:boxContent>

        <h1><c:out value="${familyAlbum.family.label}"/></h1>
        <c:out value="${familyAlbum.family.subLabel}"/>

        <div class="easyReading">

          <!-- Photos -->
          <h2><ww:link href="/action/viewAlbums?familyId=${familyAlbum.family.familyId}">Photo Albums</ww:link></h2>
          <div class="boxContainer">

            <c:if test="${fn:length( familyAlbum.family.albums ) == 0}">
              <p>
                You haven't added any photos yet.
              </p>
              <p>
                To add photos, you first need to create a photo album.
                You can create a new photo album and add photos at any time, by clicking the
                <i>edit</i> link near the top right of the page.
              </p>
            </c:if>

            <c:if test="${fn:length( familyAlbum.family.albums ) == 1}">
              <p>
                <c:forEach items="${familyAlbum.family.albums}" var="album">
                  There is
                  <ww:link href="/action/album?albumId=${album.albumId}">1 photo album</ww:link>
                  within this family.
                </c:forEach>
              </p>
            </c:if>

            <c:if test="${fn:length( familyAlbum.family.albums ) > 1}">
              <p>
                There are
                <ww:link href="/action/viewAlbums?familyId=${familyAlbum.family.familyId}">${fn:length( familyAlbum.family.albums )} photo albums</ww:link>
                within this family.
              </p>
            </c:if>

            <p>
              Why not
              <ww:link href="/action/newAlbum?familyAlbumId=${familyAlbum.family.familyId}">add a photo album</ww:link> now?
            </p>

          </div>

          <!-- People -->
          <h2><ww:link href="/action/viewSubjects?familyId=${familyAlbum.family.familyId}">People</ww:link></h2>

          <div class="boxContainer">

            <c:if test="${fn:length( familyAlbum.family.subjects ) == 0}">

              <p>
                You haven't aded any people yet.
              </p>
              <p>
                You can add people to your family at any time, by clicking the
                <i>edit</i> link near the top right of the page.
              </p>

            </c:if>

            <c:if test="${fn:length( familyAlbum.family.subjects ) > 0}">
              <p>
                There are
                <ww:link href="/action/viewSubjects?familyId=${familyAlbum.family.familyId}">${fn:length( familyAlbum.family.subjects )} people</ww:link>
                within this family.
              </p>
            </c:if>

            <p>
              Why not
              <ww:link href="/action/newSubject?familyAlbumId=${familyAlbum.family.familyId}">add a person</ww:link> now?
            </p>

            </div>


          <!-- Notes -->
          <h2>Notes <ww:link href="/action/editFamily?familyId=${familyAlbum.family.familyId}"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link></h2>
          <div class="boxContainer">

            <div class="notes">
              <c:if test="${empty familyAlbum.family.notes}">
                None
              </c:if>
              <pw:wikiText>${familyAlbum.family.notes}</pw:wikiText>
            </div>
          </div>

        </div>

      </ww:boxContent>
    </ww:box>

    <div class="wikiLinkHere">
      [<c:out value="${familyAlbum.family.label}"/>|family:${familyAlbum.family.familyId}]
    </div>

  </tiles:putAttribute>
</tiles:insertTemplate>

