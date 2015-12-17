<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">
  <tiles:putAttribute name="title" type="string" value="Edit Person : ${familyAlbum.subject.label}" />

  <tiles:putAttribute name="extraHead" value="string">
    <ww:script src="/subject/script.js"/>
  </tiles:putAttribute>

  <tiles:putAttribute name="main" type="string" >

    <h1><ww:link href="/action/halo?subjectId=${familyAlbum.subject.subjectId}"><c:out value="${familyAlbum.subject.label}"/></ww:link></h1>

    <!-- {{{ tabs -->
    <div class="hMenu tertiaryNavigation">
      <ul>
        <li class="on"><ww:link href="editSubject?subjectId=${familyAlbum.subject.subjectId}">Details</ww:link></li>
        <li><ww:link href="editRelationships?subjectId=${familyAlbum.subject.subjectId}">Relationships</ww:link></li>
        <li><ww:link href="editHalos?subjectId=${familyAlbum.subject.subjectId}">Halos</ww:link></li>
      </ul>
    </div>
    <!-- end tabs }}} -->

    <ww:box className="mainbox">
      <ww:boxTitle>
        Details : <c:out value="${familyAlbum.subject.label}"/>
      </ww:boxTitle>

      <ww:boxContent>

      <!-- {{{ form -->
      <html:form action="editSubject">

        <html:hidden property="subjectId" />
        <input type="hidden" name="familyId" value="${familyAlbum.family.familyId}" />
        <input type="hidden" name="page" value="edit"/>

        <table class="plainForm">

          <%@ include file="form.inc" %>

          <c:if test="${fn:length( familyAlbum.subject.subjectInPhotos ) > 0}">
          <!-- defaultPhoto -->
          <tr>
            <th>Default Photo</th>
            <td>
              <fa:halo id="defaultPhoto" subject="${familyAlbum.subject}" title="Default Photo" />

              <span class="help">Click one of the halos below to set the default image</span>
              <html:hidden property="defaultSubjectInPhotoId" />
            </td>
          </tr>

          </c:if>

          <!-- buttons -->
          <tr>
            <td colspan="2" class="buttons">
              <html:submit>Ok</html:submit>
              <html:cancel>Cancel</html:cancel>
            </td>
          </tr>

        </table>

      </html:form>
      <!-- end form }}} -->

      </ww:boxContent>

    </ww:box>

    <!-- {{{ halos -->
    <c:choose>

      <c:when test="${fn:length( familyAlbum.subject.subjectInPhotos ) > 0}">

        <ww:box className="mainbox">
          <ww:boxTitle>
            Choose a photo below as the default photo for <c:out value="${familyAlbum.subject.label}"/>
          </ww:boxTitle>

          <ww:boxContent>
            <c:forEach var="subjectInPhoto" items="${familyAlbum.subject.subjectInPhotos}">
              <c:if test="${subjectInPhoto.useHalo}">
                <fa:halo id="halo${subjectInPhoto.subjectInPhotoId}" onclick="return setDefaultPhoto( ${subjectInPhoto.subjectInPhotoId} );" subjectInPhoto="${subjectInPhoto}" title="Click to set as default image" />
              </c:if>
            </c:forEach>

          </ww:boxContent>
        </ww:box>
      </c:when>

      <c:otherwise>
        <div class="tips">
        <h3>Tips</h3>

          <ul>
            <li>
              There are no photos tagged with this person yet. Come back here, when you have
              added some photos, and tagged this person with the photos.
            </li>
          </ul>
        </div>
      </c:otherwise>

    </c:choose>
    <!-- end halos }}} -->


  </tiles:putAttribute>
</tiles:insertTemplate>

