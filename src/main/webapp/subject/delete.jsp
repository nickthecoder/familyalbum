<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Delete Person : ${familyAlbum.subject.label}" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <!-- {{{ delete form -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        Delete <c:out value="${familyAlbum.subject.label}"/>
      </ww:boxTitle>
      <ww:boxContent>

        <table class="alertForm">
          <tr>
            <td style="text-align: center;">
              Are you sure you want to delete this person?
            </td>

          </tr>

          <tr>
            <td class="buttons">
              <!-- form -->
              <html:form action="/deleteSubject">

                <html:hidden property="subjectId"/>

                <html:submit>Ok</html:submit>
                <html:cancel>Cancel</html:cancel>

              </html:form>
              <!-- END form -->
            </td>
          </tr>

        </table>

      </ww:boxContent>
    </ww:box>
    <!-- end delete form }}} -->

    <div style="text-align: center;">
      <!-- default halo -->
      <c:if test="${familyAlbum.subject.defaultSubjectInPhoto.haloUrl != null}">
        <img width="400" height="400" id="mainHaloImage" alt="main" src="<c:out value="${familyAlbum.subject.defaultSubjectInPhoto.haloUrl}"/>"/>
        <br/>
      </c:if>
    </div>

    <br/>

    <!-- {{{ subject details -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        <ww:link href="/action/halo?subjectId=${familyAlbum.subject.subjectId}"><c:out value="${familyAlbum.subject.label}"/></ww:link>
      </ww:boxTitle>

      <ww:boxContent>

        <!-- sex -->
        <c:if test="${familyAlbum.subject.sex=='M'}">
          Male
        </c:if>
        <c:if test="${familyAlbum.subject.sex=='F'}">
          Female
        </c:if>

        <h2>Relationships</h2>
        <ul>
          <c:forEach items="${familyAlbum.subject.relationships}" var="relationship">
            <li>
              <ww:link href="/action/subject?subjectId=${relationship.toSubject.subjectId}"><c:out value="${relationship.toSubject.sortedLabel}"/></ww:link>
              (<c:out value="${relationship.relationshipType.label}"/>)
            </li>
          </c:forEach>
        </ul>


        <!-- notes -->
        <h2>Notes</h2>
        <div class="notes">
          <c:if test="${empty familyAlbum.subject.notes}">
            None
          </c:if>
          <pw:wikiText>${familyAlbum.subject.notes}</pw:wikiText>
        </div>

        <!-- halos -->
        <h2>Images</h2>
        <c:forEach var="subjectInPhoto" items="${familyAlbum.subject.subjectInPhotos}">
          <fa:halo subjectInPhoto="${subjectInPhoto}" />
        </c:forEach>

        <!-- Photos -->
        <h2>Photos</h2>
        <ww:portion var="portions" items="${familyAlbum.subject.subjectInPhotos}" portionSize="4" >
        <table class="thumbnails" width="100%">
          <c:forEach var="portion" items="${portions}">
          <tr>
            <c:forEach var="subjectInPhoto" items="${portion}">
              <td>
                <ww:link href="/action/photo?photoId=${subjectInPhoto.photo.photoId}">
                  <img src="<c:out value="${subjectInPhoto.photo.thumbnailUrl}"/>" />
                </ww:link>
              </td>
            </c:forEach>
          </tr>
          </c:forEach>
        </table>
        </ww:portion>

      </ww:boxContent>
    </ww:box>
    <!-- end subject deatil }}} -->

  </tiles:putAttribute>
</tiles:insertTemplate>

