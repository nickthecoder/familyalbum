<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">
  <tiles:putAttribute name="title" type="string" value="${familyAlbum.subject.label}" />

  <tiles:putAttribute name="extraHead" type="string" >

    <ww:script src="/ww_resources/ww_eventNotifier.js"/>

    <style type="text/css">
      #whole { width: 1000px; }
    </style>
    
    <!-- Redirect to a narrow version of this page if the screen isn't very wide -->
    <script type="text/javascript">
    <!--
      if (this.innerWidth < 950) {
        document.location = document.location.toString().replace( "/action/halo", "/action/haloNarrow" );
      }
    -->
    </script>

  </tiles:putAttribute>

  <tiles:putAttribute name="width" type="string" value="1000" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <ww:sort items="${familyAlbum.subject.subjectInPhotos}" var="sortedSips" comparator="${familyAlbum.sipAgeComparator}"/>
    <ww:filter items="${sortedSips}" var="filteredSips" itemName="sip" test="sip.useHalo"/>

    <ww:pager items="${filteredSips}" subsetVar="sips" itemsPerPage="20" currentItem="${familyAlbum.subjectInPhoto}">

      <a name="main"></a>
      <!-- {{{ The table, which lays out the rest of the page, behind the main image -->
      <table class="haloTable">

        <tr>
          <td class="edge"></td>
          <td class="center"></td>
          <td class="edge"></td>
        </tr>

        <tr>
          <!-- parents -->
          <td class="parents">
            <c:forEach items="${familyAlbum.subject.parents}" var="relationship">
              <fa:halo subject="${relationship.toSubject}" />
            </c:forEach>
          </td>

          <!-- The main image and circle of halos -->
          <td class="main" rowspan="3">
          
            <ww:edges className="whiteBox">
            <%@ include file="haloCircle.jsp" %>
            </ww:edges>
            
            <br/>
            
            <ww:edges className="whiteBox">
            
              <!-- edit details link -->
              <h1><img src="<ww:contextPath/>/resources/blankIcon.png" alt=""/><ww:link href="/action/editSubject?subjectId=${familyAlbum.subject.subjectId}"><c:out value="${familyAlbum.subject.label}"/><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit" /></ww:link></h1>

              <ww:linkInfo href="/action/halo" hash="main">
                <ww:linkParameter name="subjectId" value="${familyAlbum.subject.subjectId}"/>
                <ww:linkParameter name="subjectInPhotoId" value="${familyAlbum.subjectInPhoto.subjectInPhotoId}"/>
            
                <ww:pagerLinks type="before"><ww:link styleClass="button">${page}</ww:link> </ww:pagerLinks>
                <ww:pagerLinks type="current"><ww:link styleClass="onButton">Page ${page}</ww:link> </ww:pagerLinks>
                <ww:pagerLinks type="after"><ww:link styleClass="button">${page}</ww:link> </ww:pagerLinks>
              </ww:linkInfo>

              </ww:edges>
          </td>

          <!-- partners -->
          <td class="partners">
            <c:forEach items="${familyAlbum.subject.partners}" var="relationship">
              <fa:halo subject="${relationship.toSubject}" />
            </c:forEach>
          </td>
        </tr>

        <tr>

          <!-- siblings -->
          <td class="siblings">
            <c:forEach items="${familyAlbum.subject.siblings}" var="relationship">
              <fa:halo subject="${relationship.toSubject}" />
            </c:forEach>
          </td>

          <!-- photo -->
          <td class="photo">
            <c:if test="${familyAlbum.subjectInPhoto.photo.photoId != null}">
              <ww:link href="/action/photoBySubject?subjectInPhotoId=${familyAlbum.subjectInPhoto.subjectInPhotoId}"><img alt="photo" id="photo" src="<c:out value="${familyAlbum.subjectInPhoto.photo.thumbnailUrl}"/>" /></ww:link><br/>

              <!-- year-->
              <img src="<ww:contextPath/>/resources/blankIcon.png" alt="edit"/>
              <fa:photoDate photo="${familyAlbum.photo}"/>
              <ww:link href="/action/editPhoto?photoId=${familyAlbum.subjectInPhoto.photo.photoId}" title="edit photo info"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link>
            </c:if>
          </td>

        </tr>

        <tr>

          <!-- children -->
          <td class="children">
            <c:forEach items="${familyAlbum.subject.children}" var="relationship">
              <fa:halo subject="${relationship.toSubject}" />
            </c:forEach>
          </td>


          <!-- other -->
          <td class="other">
            <c:forEach items="${familyAlbum.subject.otherRelationships}" var="relationship" varStatus="status">
              <c:if test="${status.count < 5}">
                <fa:halo subject="${relationship.toSubject}" />
              </c:if>
            </c:forEach>
          </td>

        </tr>

      </table>
      <!-- end main table }}} -->

    </ww:pager>

    <br/>

    <!-- {{{ Notes -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        Notes
        <ww:boxIcon>
          <ww:link href="/action/editSubject?subjectId=${familyAlbum.subject.subjectId}" title="edit details"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/> Edit</ww:link>
        </ww:boxIcon>
      </ww:boxTitle>

      <ww:boxContent>
        <div class="notes">
          <c:if test="${empty familyAlbum.subject.notes}">
            None
          </c:if>
          <pw:wikiText>${familyAlbum.subject.notes}</pw:wikiText>
        </div>
      </ww:boxContent>
    </ww:box>
    <!-- end Notes }}} -->

    <!-- {{{ all other relationships -->
    <c:if test="${fn:length( familyAlbum.subject.otherRelationships ) > 4}">
      <ww:box className="mainbox">
        <ww:boxTitle>
          All Other Relationships
          <ww:boxIcon>
          </ww:boxIcon>
        </ww:boxTitle>

        <ww:boxContent>
          As <c:out value="${familyAlbum.subject.label}"/> has more than 4 "other" relatives,
          they couldn't all be shown above, so the complete list is shown here.
          <br/>
          <c:forEach items="${familyAlbum.subject.otherRelationships}" var="relationship" varStatus="status">
            <fa:halo subject="${relationship.toSubject}" />
          </c:forEach>

          </ww:boxContent>
      </ww:box>
    </c:if>
    <!-- end all other relationships }}} -->

        
    <ww:link href="/action/subjectThumbnails?subjectId=${familyAlbum.subject.subjectId}">Browse photos featuring
      <c:out value="${familyAlbum.subject.label}"/>
    </ww:link>

    <div class="wikiLinkHere">
      [<c:out value="${familyAlbum.subject.label}"/>|subject:${familyAlbum.subject.subjectId}]
    </div>

  </tiles:putAttribute>
</tiles:insertTemplate>

