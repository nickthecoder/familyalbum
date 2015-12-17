<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Photo : ${familyAlbum.photo.label}" />

  <tiles:putAttribute name="main" type="string">

    <a name="image"></a>
    <!-- Navigation -->
    <div class="photoNavigation">

      <ww:link title="edit" href="editSubjectsInPhoto?photoId=${familyAlbum.photo.photoId}">
        <img alt="edit" src="<ww:contextPath/>/resources/edit.png" />
      </ww:link>

      <ww:link id="previousLink" title="previous photo" href="photo?photoId=${familyAlbum.photo.photoId}&amp;navigation=prev#image">
        <img src="<ww:contextPath/>/resources/prev.png" />
      </ww:link>

      <ww:link id="nextLink" title="next photo" href="photo?photoId=${familyAlbum.photo.photoId}&amp;navigation=next#image">
        <img src="<ww:contextPath/>/resources/next.png" />
      </ww:link>

      <a title="full size" href="${familyAlbum.photo.fullUrl}">
        <img src="<ww:contextPath/>/resources/zoomIn.png" />
      </a>
      
    </div>

    <%@include file="viewContent.inc" %>

  </tiles:putAttribute>

</tiles:insertTemplate>

