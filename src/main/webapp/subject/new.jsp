<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Add New Person" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <ww:box className="mainbox">
      <ww:boxTitle>
        Add New Person
      </ww:boxTitle>

      <ww:boxContent>

        <!-- {{{ form -->
        <html:form action="newSubject">

          You are about to add a new person into the family : <b><c:out value="${familyAlbum.family.label}"/></b>

          <input type="hidden" name="familyId" value="${familyAlbum.family.familyId}"/>
          <table class="plainForm">

            <%@ include file="form.inc" %>

            <!-- buttons -->
            <tr>
              <td class="buttons" colspan="2">
                <html:submit>Ok</html:submit>
                <html:cancel>Cancel</html:cancel>
              </td>
            </tr>

          </table>

        </html:form>
        <!-- end form }}} -->

      </ww:boxContent>
    </ww:box>

  </tiles:putAttribute>
</tiles:insertTemplate>

