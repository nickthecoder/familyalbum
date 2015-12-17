<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="${familyAlbum.subjectInPhoto.subject.label} - Photo # ${familyAlbum.subjectInPhoto.photo.photoId}" />

  <tiles:putAttribute name="main" type="string">

    <a name="image"></a>
    <!-- Navigation -->
    <div class="photoNavigation">

      <ww:link id="previousLink" title="previous photo" href="photoBySubject?subjectInPhotoId=${familyAlbum.subjectInPhoto.subjectInPhotoId}&amp;navigation=prev#image">
        <img src="<ww:contextPath/>/resources/prev.png" />
      </ww:link>

      <ww:link id="nextLink" title="next photo" href="photoBySubject?subjectInPhotoId=${familyAlbum.subjectInPhoto.subjectInPhotoId}&amp;navigation=next#image">
        <img src="<ww:contextPath/>/resources/next.png" />
      </ww:link>

      <a title="zoom out" href="#" onclick="return zoomOut();">
        <img src="<ww:contextPath/>/resources/zoomOut.png" />
      </a>

      <a title="zoom in" href="#" onclick="return zoomIn();">
        <img src="<ww:contextPath/>/resources/zoomIn.png" />
      </a>

    </div>

    <%@include file="viewContent.inc" %>

  </tiles:putAttribute>
</tiles:insertTemplate>

