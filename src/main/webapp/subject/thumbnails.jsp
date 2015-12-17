<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Photos Featuring ${familyAlbum.subject.label}" />

  <tiles:putAttribute name="main" type="string" >

    <h1>Photos Featuring <c:out value="${familyAlbum.subject.label}"/></h1>
  
    <a name="image"></a>
  
    <ww:pager items="${familyAlbum.subject.subjectInPhotos}" subsetVar="sips" itemsPerPage="28">        
    <c:choose>
      
      <c:when test="${fn:length( familyAlbum.subject.subjectInPhotos ) > 0}">
        
        <!-- Subjects's Photos (thumbnails) -->        
        <div class="whiteBox2">
          <table class="thumbnails">

            <ww:portion var="portions" items="${sips}" portionSize="4" pad="normal">
              <c:forEach var="portion" items="${portions}">

                <tr>
                  <c:forEach var="sip" items="${portion}">
                  <td width="25%" class="thumbnailCell">
                    <c:if test="${sip != null}" >
                      <div>
    
                        <ww:linkInfo href="/action/photo">
                          <ww:linkParameter name="photoId" value="${sip.photo.photoId}"/>
                          <ww:link><img alt="" src="<c:out value="${sip.photo.thumbnailUrl}"/>" /></ww:link>
                        </ww:linkInfo>                      
                        
                      </div>
                    </c:if>
                  </td>
                  </c:forEach>
                </tr>
    
                <tr>
                  <c:forEach var="sip" items="${portion}">
                  <td width="25%" class="labelCell">
                    <c:if test="${sip != null}" >
                      <div>
    
                        <c:choose>
                          <c:when test="${ ! empty sip.photo.notes}">
                            <img class="icon" alt="note" src="<ww:contextPath/>/resources/note.png"/>
                          </c:when>
                          <c:otherwise>
                            <img class="icon" src="<ww:contextPath/>/resources/blankIcon.png" alt=""/>
                          </c:otherwise>
                        </c:choose>
                        <fa:photoDate photo="${sip.photo}" unknown="date ?"/>
                        <ww:link href="/action/editPhoto?photoId=${sip.photoId}" title="edit photo info"><img class="icon" src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link>
    
                        <br/>
    
                        <img class="icon" src="<ww:contextPath/>/resources/blankIcon.png" alt=""/>
                        (&nbsp;<c:out value="${sip.photo.subjectCount}"/>&nbsp;)
                        <ww:link href="/action/editSubjectsInPhoto?photoId=${sip.photoId}" title="tag people in photo"><img class="icon" src="<ww:contextPath/>/resources/editPeopleIcon.png" alt="edit"/></ww:link>
                                                          photo
                        <c:if test="${! sip.photo.imagesUpToDate }">
                          <br/>
                          updating
                        </c:if>
    
                      </div>
                    </c:if>
    
                  </td>
                  </c:forEach>
                </tr>

              </c:forEach>
            </ww:portion>
            
          </table>
        </div>
  
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
    
    <div style="text-align: center">
      <ww:linkInfo href="/action/subjectThumbnails">
        <ww:linkParameter name="subjectId" value="${familyAlbum.subject.subjectId}"/>
    
        <ww:pagerLinks type="before"><ww:link styleClass="button">${page}</ww:link> </ww:pagerLinks>
        <ww:pagerLinks type="current"><ww:link styleClass="onButton">Page ${page}</ww:link> </ww:pagerLinks>
        <ww:pagerLinks type="after"><ww:link styleClass="button">${page}</ww:link> </ww:pagerLinks>
      </ww:linkInfo>
    </div>
    
    </ww:pager>
  
  </tiles:putAttribute>
</tiles:insertTemplate>

