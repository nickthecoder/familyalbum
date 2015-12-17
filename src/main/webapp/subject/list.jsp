<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="true">
  <tiles:putAttribute name="title" type="string" value="${familyAlbum.subjectType.categoryLabel}" />

  <tiles:putAttribute name="main" type="string" >
    <tiles:insertTemplate template="/subject/listContents.jsp" flush="false">
      <tiles:putAttribute name="action" type="string" value="/action/halo" />
    </tiles:insertTemplate>
  </tiles:putAttribute>

</tiles:insertTemplate>

