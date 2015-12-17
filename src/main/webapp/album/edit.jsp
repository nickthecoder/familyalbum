<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Edit Album : ${familyAlbum.album.label}" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <ww:box className="mainbox">
      <ww:boxTitle>
        Album : <c:out value="${familyAlbum.album.label}"/>
      </ww:boxTitle>

      <ww:boxContent>

        <!-- form -->
        <html:form action="/editAlbum">

          <%@ include file="form.inc" %>

        </html:form>
        <!-- END form -->

      </ww:boxContent>
    </ww:box>

  </tiles:putAttribute>
</tiles:insertTemplate>


