<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<tiles:importAttribute name="main" />
<tiles:importAttribute name="title" ignore="true"/>
<tiles:importAttribute name="tab" ignore="true"/>
<tiles:importAttribute name="extraHead" ignore="true"/>
<tiles:importAttribute name="width" ignore="true"/>

<tiles:insertTemplate template="/layouts/columnLayout.jsp" flush="false">

  <c:if test="${! empty title}">
    <tiles:putAttribute name="title" type="string" value="${title}"/>
  </c:if>

  <c:if test="${! empty width}">
    <tiles:putAttribute name="width" type="string" value="${width}"/>
  </c:if>

  <tiles:putAttribute name="tab" type="string" value="familyalbum"/>
  <tiles:putAttribute name="breadcrumbs">
    <%@include file="breadcrumbs.jsp"%>
  </tiles:putAttribute>


  <tiles:putAttribute name="extraHead" type="string">
    <ww:styleSheet href="/resources/style.css" />
    ${extraHead}
  </tiles:putAttribute>

  <tiles:putAttribute name="main" type="string" value="${main}"/>

</tiles:insertTemplate>

