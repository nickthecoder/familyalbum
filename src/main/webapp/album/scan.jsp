<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Scan Directory for New Photos" />

  <tiles:putAttribute name="main" type="string" >

    <p>
      This page is only useful if you have direct access to server that stores the photographs.
      It looks at the directory where the photos are kept, and if there are any <b>new</b> ones,
      it adds them to the database.
    </p>

    <p>
      Scan for new photos complete.
    </p>

  </tiles:putAttribute>
</tiles:insertTemplate>

