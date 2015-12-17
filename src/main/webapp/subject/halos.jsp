<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Halos : ${familyAlbum.subject.label}" />

  <tiles:putAttribute name="extraHead" value="string">
    <ww:script src="/subject/script.js"/>

    <script type="text/javascript">
    function changeCategory( subjectInPhotoId, useHalo )
    {
      ww_findObj( "subjectInPhotoId" ).value = subjectInPhotoId;
      ww_findObj( "useHalo" ).value = useHalo;

      ww_findObj( "useHalo" ).form.submit();

      return false;
    }
    </script>
  </tiles:putAttribute>

  <tiles:putAttribute name="main" type="string" >

    <h1><ww:link href="/action/halo?subjectId=${familyAlbum.subject.subjectId}"><c:out value="${familyAlbum.subject.label}"/></ww:link></h1>

    <!-- {{{ tabs -->
    <div class="hMenu tertiaryNavigation">
      <ul>
        <li><ww:link href="editSubject?subjectId=${familyAlbum.subject.subjectId}">Details</ww:link></li>
        <li><ww:link href="editRelationships?subjectId=${familyAlbum.subject.subjectId}">Relationships</ww:link></li>
        <li class="on"><ww:link href="editHalos?subjectId=${familyAlbum.subject.subjectId}">Halos</ww:link></li>
      </ul>
    </div>
    <!-- end tabs }}} -->

    <form name="theForm" method="post" action="editHalos">

      <input type="hidden" name="subjectId" value="${familyAlbum.subject.subjectId}"/>
      <input type="hidden" name="subjectInPhotoId" value="0"/>
      <input type="hidden" name="page" value="halos"/>
      <input type="hidden" name="useHalo" value=""/>

    </form>


    <c:choose>

      <c:when test="${fn:length( familyAlbum.subject.subjectInPhotos ) > 0}">

        <ww:box className="mainbox">
          <ww:boxTitle>Good Quality Photos (use them)</ww:boxTitle>
          <ww:boxContent>
            <c:forEach var="subjectInPhoto" items="${familyAlbum.subject.subjectInPhotos}">
              <c:if test="${subjectInPhoto.useHalo}">
                <fa:halo subjectInPhoto="${subjectInPhoto}" title="Click tag as BAD quality" onclick="return changeCategory(${subjectInPhoto.subjectInPhotoId}, 0);" />
              </c:if>
            </c:forEach>
            <p>
              Click an image to move it into the "Bad Photo" category
            </p>

          </ww:boxContent>
        </ww:box>

        <ww:box className="mainbox">
          <ww:boxTitle>Bad Quality Photos (don't use them)</ww:boxTitle>
          <ww:boxContent>

            <p>
              These images won't normally be used when viewing a person.
            </p>

            <c:forEach var="subjectInPhoto" items="${familyAlbum.subject.subjectInPhotos}">
              <c:if test="${! subjectInPhoto.useHalo}">
                <fa:halo subjectInPhoto="${subjectInPhoto}" title="Click tag as GOOD quality" onclick="return changeCategory(${subjectInPhoto.subjectInPhotoId}, 1);" />
              </c:if>
            </c:forEach>
            <p>
              Click an image move it into the "Good Photo" category
            </p>

          </ww:boxContent>
        </ww:box>

      </c:when>

      <c:otherwise>

        <br/>

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

  </tiles:putAttribute>
</tiles:insertTemplate>

