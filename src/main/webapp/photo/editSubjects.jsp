<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Edit : ${familyAlbum.photo.label}" />

  <tiles:putAttribute name="main" type="string">

    <a name="image"></a>
    <!-- Navigation -->
    <div class="photoNavigation">

      <ww:link title="done" href="photo?photoId=${familyAlbum.photo.photoId}">
        <img alt="done" src="<ww:contextPath/>/resources/done.png" />
      </ww:link>

      <ww:link id="previousLink" title="previous photo" href="editSubjectsInPhoto?photoId=${familyAlbum.photo.photoId}&amp;navigation=prev#image">
        <img src="<ww:contextPath/>/resources/prev.png" />
      </ww:link>

      <ww:link id="nextLink" title="next photo" href="editSubjectsInPhoto?photoId=${familyAlbum.photo.photoId}&amp;navigation=next#image">
        <img src="<ww:contextPath/>/resources/next.png" />
      </ww:link>

      <a title="zoom out" href="#" onclick="return zoomOut();">
        <img src="<ww:contextPath/>/resources/zoomOut.png" />
      </a>

      <a title="zoom in" href="#" onclick="return zoomIn();">
        <img src="<ww:contextPath/>/resources/zoomIn.png" />
      </a>

    </div>

    <%@ include file="editSubjectsContent.inc" %>

  </tiles:putAttribute>
</tiles:insertTemplate>

