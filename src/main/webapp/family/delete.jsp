<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="true">

  <tiles:putAttribute name="title" type="string" value="Delete Family" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <!-- {{{ delete form -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        Delete
      </ww:boxTitle>
      <ww:boxContent>

        <!-- form -->
        <html:form action="/deleteFamily">

          <html:hidden property="familyId"/>

          <h2><ww:link href="/action/deleteFamily?familyId=${familyAlbum.family.familyId}">Delete : '<i><c:out value="${familyAlbum.family.label}"/></i>'</ww:link></h2>
          <p>
            WARNING : This will delete the entire family from the database.
            Only use this option if you are the main person in charge of this family album
            and you no longer want anybody to be able see any of the photos or people
            within the family album that you created.
          </p>
          <p>
            This is a publicly editable web site, which depends on trust. Please don't abuse it :-)
          </p>

          <br/>

          <table class="alertForm">
            <tr>
              <td>
                Are you sure you want to delete this family, and all of its people and photos?
              </td>
            </tr>

            <tr>
              <td class="buttons">

                <html:submit>Delete</html:submit>
                <html:cancel>Cancel</html:cancel>

              </td>
            </tr>
          </table>

        </html:form>
        <!-- END form -->

      </ww:boxContent>
    </ww:box>
    <!-- end delete form }}} -->

    <!-- {{{ family details -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        Family Details
        <ww:link href="/action/editFamily?familyId=${familyAlbum.family.familyId}"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link>
      </ww:boxTitle>
      <ww:boxContent>

        <h1><c:out value="${familyAlbum.family.label}"/></h1>
        <c:out value="${familyAlbum.family.subLabel}"/>

        <div class="narrow">

          <!-- Photos -->
          <h2><ww:link href="/action/viewAlbums?familyId=${familyAlbum.family.familyId}">Photo Albums</ww:link></h2>
          <div class="boxContainer">

            <c:if test="${fn:length( familyAlbum.family.albums ) == 0}">
              <p>
                You haven't added any photos yet.
              </p>
            </c:if>

            <c:if test="${fn:length( familyAlbum.family.albums ) > 0}">
              <p>
                There are
                ${fn:length( familyAlbum.family.albums )}
                photo albums within this family.
              </p>
            </c:if>

        </div>

          <!-- People -->
          <h2><ww:link href="/action/viewSubjects?familyId=${familyAlbum.family.familyId}">People</ww:link></h2>

          <div class="boxContainer">

            <c:if test="${fn:length( familyAlbum.family.subjects ) == 0}">
              <p>
                You haven't aded any people yet.
              </p>
            </c:if>

            <c:if test="${fn:length( familyAlbum.family.subjects ) > 0}">
              <p>
                There are
                ${fn:length( familyAlbum.family.subjects )}
                people within this family.
              </p>
            </c:if>

          </div>


          <!-- Notes -->
          <h2>Notes</h2>
          <div class="boxContainer">
            <div class="notes">
              <c:if test="${empty familyAlbum.family.notes}">
                None
              </c:if>
              <pw:wikiText>${familyAlbum.family.notes}</pw:wikiText>
            </div>
           </div>


        </div>

      </ww:boxContent>
    </ww:box>
    <!-- end family details }}} -->

  </tiles:putAttribute>
</tiles:insertTemplate>

