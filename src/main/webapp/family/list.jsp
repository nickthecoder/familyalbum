<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="true">
  <tiles:putAttribute name="title" type="string" value="Find a Family" />

  <tiles:putAttribute name="main" type="string" >

    <div style="text-align: right;">
      <ww:link href="/action/newFamily">Add a new Family Album <img class="icon" alt="add" src="<ww:contextPath/>/resources/add.png"/></ww:link>
    </div>

    <h1>Find a Family</h1>

    <c:forEach items="${familyAlbum.families}" var="family">

      <div class="boxContainer">

        <!-- each family -->
        <h2>
          <ww:link href="/action/viewFamily?familyId=${family.familyId}">
            <c:out value="${family.label}"/>
          </ww:link>
          <ww:link href="/action/editFamily?familyId=${family.familyId}" title="edit family info"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link>
        </h2>
        <c:out value="${family.subLabel}"/>


      </div>
    </c:forEach>

  </tiles:putAttribute>
</tiles:insertTemplate>


