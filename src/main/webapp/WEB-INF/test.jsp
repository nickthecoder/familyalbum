<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>

Hello World

<tiles:insertTemplate template="/layouts/columnLayout.jsp" flush="true">

  <tiles:putAttribute name="title" type="string" value="Family Album" />
  <tiles:putAttribute name="tab" value="familyalbum" />

  <tiles:putAttribute name="main" type="string" >

    Testing.

  </tiles:putAttribute>
</tiles:insertTemplate>


