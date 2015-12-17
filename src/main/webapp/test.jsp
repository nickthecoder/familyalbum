<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="/layouts/columnLayout.jsp" flush="true">

  <tiles:putAttribute name="title" type="string" value="Family Album" />
  <tiles:putAttribute name="tab" value="familyalbum" />

  <tiles:putAttribute name="main" type="string" >

    Testing.

  </tiles:putAttribute>
</tiles:insertTemplate>


