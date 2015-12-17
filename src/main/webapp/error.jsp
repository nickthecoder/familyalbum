<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Family Album" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <p>
      Sorry, and unexpected error has occurred.
      Please try again, or <a href="http://nickthecoder.co.uk/pinkwino/view/Contact">contact me</a> (Nick),
      and I'll do what I can to fix it.
    </p>

    <br/>

    <c:if test="${! empty familyAlbum_error}">
      <ww:script src="/ww_resources/ww_minimizable.js"/>
      <ww:box minimized="true">
        <ww:boxTitle title="Details..." clickable="true"/>

        <c:out value="${familyAlbum_error}"/>
        <ww:boxContent>
          <c:forEach items="${familyAlbum_error.stackTrace}" var="item">
            <c:out value="${item}"/><br/>
          </c:forEach>
        </ww:boxContent>
      </ww:box>
    </c:if>

  </tiles:putAttribute>
</tiles:insertTemplate>


