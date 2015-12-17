<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="${familyAlbum.subject.label}" />

  <tiles:putAttribute name="extraHead" type="string">
    <ww:script src="/ww_resources/ww_eventNotifier.js"/>
    <ww:script src="/subject/halo.js" />
  </tiles:putAttribute>

  <tiles:putAttribute name="main" type="string" >

    <ww:sort items="${familyAlbum.subject.subjectInPhotos}" var="allSips" comparator="${familyAlbum.sipAgeComparator}"/>
    <ww:pager items="${allSips}" subsetVar="sips" itemsPerPage="20" currentItem="${familyAlbum.subjectInPhoto}">


    <!-- The table, which lays out the rest of the page, behind the main image -->
    <a name="main"></a>
    <table class="haloTable">

      <tr>
        <!-- The main image -->
        <td class="main" colspan="2">

          <%@ include file="haloCircle.jsp" %>

        </td>
      </tr>

      <tr>

        <!-- Photo Thumbnail -->
        <td class="photo">
          <c:if test="${familyAlbum.subjectInPhoto.photo.photoId != null}">
            <ww:link href="/action/photoBySubject?subjectInPhotoId=${familyAlbum.subjectInPhoto.subjectInPhotoId}"><img id="photo" src="<c:out value="${familyAlbum.subjectInPhoto.photo.thumbnailUrl}"/>" /></ww:link><br/>

            <!-- year-->
            <img src="<ww:contextPath/>/resources/blankIcon.png" alt="edit"/>
            <fa:photoDate photo="${familyAlbum.photo}"/>
            <ww:link href="/action/editPhoto?photoId=${familyAlbum.subjectInPhoto.photo.photoId}" title="edit photo info"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link>
          </c:if>
        </td>

        <!-- relatives -->
        <td class="relatives">
          <!-- relatives -->
          <c:forEach items="${familyAlbum.subject.relationships}" var="relationship">
            <fa:halo subject="${relationship.toSubject}" />
          </c:forEach>
        </td>

      </tr>

    </table>

    </ww:pager>

    <div class="wikiLinkHere">
      [<c:out value="${familyAlbum.subject.label}"/>|subject:${familyAlbum.subject.subjectId}]
    </div>

  </tiles:putAttribute>
</tiles:insertTemplate>

