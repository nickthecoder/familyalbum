<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Person : ${familyAlbum.subject.label}" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <ww:box className="mainbox">
      <ww:boxTitle>
        <ww:link href="/action/halo?subjectId=${familyAlbum.subject.subjectId}"><c:out value="${familyAlbum.subject.label}"/></ww:link>
        <ww:boxIcon>
          <ww:link href="/action/editSubject?subjectId=${familyAlbum.subject.subjectId}" title="edit"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/> Edit</ww:link>
        </ww:boxIcon>
       </ww:boxTitle>

      <ww:boxContent>

        <!-- default image -->
        <fa:halo subject="${familyAlbum.subject}" />

        <br/>

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
              <ww:link href="/action/subject?subjectId=${relationship.toSubject.subjectId}"><c:out value="${relationship.toSubject.label}"/></ww:link>
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

      
      </ww:boxContent>

      <!-- halos -->
      <ww:innerBox>
        <ww:boxTitle>
          Images
        </ww:boxTitle>
        <ww:boxContent>

          <c:forEach var="subjectInPhoto" items="${familyAlbum.subject.subjectInPhotos}">
            <fa:halo subjectInPhoto="${subjectInPhoto}" />
          </c:forEach>

        </ww:boxContent>
      </ww:innerBox>

      <!-- Photos -->
      <ww:innerBox>
        <ww:boxTitle>
          Photos
        </ww:boxTitle>
        <ww:boxContent>

          <ww:portion var="portions" items="${familyAlbum.subject.subjectInPhotos}" portionSize="4" pad="true" >
          <table class="thumbnails" width="100%">
            <c:forEach var="portion" items="${portions}">
            <tr>
              <c:forEach var="subjectInPhoto" items="${portion}">
                <td>
                  <c:if test="${subjectInPhoto != null}">
                    <ww:link href="/action/photo?photoId=${subjectInPhoto.photo.photoId}">
                      <img src="<c:out value="${subjectInPhoto.photo.thumbnailUrl}"/>" />
                    </ww:link>
                  </c:if>
                </td>
              </c:forEach>
            </tr>
            </c:forEach>
          </table>
          </ww:portion>

        </ww:boxContent>
      </ww:innerBox>

    </ww:box>

  </tiles:putAttribute>
</tiles:insertTemplate>

