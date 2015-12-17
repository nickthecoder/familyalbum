<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Edit Relationships : ${familyAlbum.subject.label}" />

  <tiles:putAttribute name="extraHead" value="string">
    <ww:script src="/subject/script.js"/>
  </tiles:putAttribute>

  <tiles:putAttribute name="main" type="string" >

    <h1><ww:link href="/action/halo?subjectId=${familyAlbum.subject.subjectId}"><c:out value="${familyAlbum.subject.label}"/></ww:link></h1>

    <!-- {{{ tabs -->
    <div class="hMenu tertiaryNavigation">
      <ul>
        <li><ww:link href="editSubject?subjectId=${familyAlbum.subject.subjectId}">Details</ww:link></li>
        <li class="on"><ww:link href="editRelationships?subjectId=${familyAlbum.subject.subjectId}">Relationships</ww:link></li>
        <li><ww:link href="editHalos?subjectId=${familyAlbum.subject.subjectId}">Halos</ww:link></li>
      </ul>
    </div>
    <!-- end tabs }}} -->

    <ww:box className="mainbox">
      <ww:boxTitle>
        Relationships : <c:out value="${familyAlbum.subject.label}"/>
      </ww:boxTitle>

      <ww:boxContent>

        <!-- {{{ form -->
        <html:form action="editRelationships" onsubmit="return validateRelationships()">

          <html:hidden property="subjectId" />
          <input type="hidden" name="page" value="relationships"/>

          <table class="plainForm">

            <tr>
              <td class="buttons">... is the</td>
              <td class="buttons">... of <c:out value="${familyAlbum.subject.label}"/></td>
              <td></td>
              <td class="buttons">Relationships</td>
            </tr>

            <tr>

              <td>
                <!-- all people -->
                <select size="15" name="allPeople" onchange="changedSubject( 'allPeople' );">
                  <option value="-1">----------------------------------------</option>
                  <c:forEach items="${familyAlbum.subjects}" var="asubject">
                    <c:if test="${asubject != familyAlbum.subject}">
                      <option value="${asubject.subjectId}:${asubject.sex}"><c:out value="${asubject.sortedLabel}"/></option>
                    </c:if>
                  </c:forEach>
                </select>
                <!-- family members -->
                <select size="15" name="familyMembers" style="display: none" onchange="changedSubject( 'familyMembers' );">
                  <option value="-1">----------------------------------------</option>
                  <c:forEach items="${familyAlbum.subjects}" var="asubject">
                    <c:if test="${asubject.subjectType.subjectTypeId == 1}">
                      <c:if test="${asubject != familyAlbum.subject}">
                        <option value="${asubject.subjectId}:${asubject.sex}"><c:out value="${asubject.sortedLabel}"/></option>
                      </c:if>
                    </c:if>
                  </c:forEach>
                </select>
                <!-- friends -->
                <select size="15" name="friends" style="display: none" onchange="changedSubject( 'friends' );">
                  <option value="-1">----------------------------------------</option>
                  <c:forEach items="${familyAlbum.subjects}" var="asubject">
                    <c:if test="${asubject.subjectType.subjectTypeId == 3}">
                      <c:if test="${asubject != familyAlbum.subject}">
                        <option value="${asubject.subjectId}:${asubject.sex}"><c:out value="${asubject.sortedLabel}"/></option>
                      </c:if>
                    </c:if>
                  </c:forEach>
                </select>
                <!-- pets -->
                <select size="15" name="pets" style="display: none" onchange="changedSubject( 'pets' );">
                  <option value="-1">----------------------------------------</option>
                  <c:forEach items="${familyAlbum.subjects}" var="asubject">
                    <c:if test="${asubject.subjectType.subjectTypeId == 2}">
                      <c:if test="${asubject != familyAlbum.subject}">
                        <option value="${asubject.subjectId}:"><c:out value="${asubject.sortedLabel}"/></option>
                      </c:if>
                    </c:if>
                  </c:forEach>
                </select>
              </td>

              <td>
                <!-- all relationship type -->
                <select size="15" name="allRelationshipTypes">
                  <option value="-1">------------------------</option>
                  <c:forEach items="${familyAlbum.relationshipTypes}" var="relationshipType">
                    <option value="<c:out value="${relationshipType.relationshipTypeId}"/>"><c:out value="${relationshipType.label}"/></option>
                  </c:forEach>
                </select>
                <!-- exclude female relationship types -->
                <select size="15" name="maleRelationshipTypes" style="display: none;">
                  <option value="-1">------------------------</option>
                  <c:forEach items="${familyAlbum.relationshipTypes}" var="relationshipType">
                    <c:if test="${relationshipType.sex > 70}">
                      <option value="<c:out value="${relationshipType.relationshipTypeId}"/>"><c:out value="${relationshipType.label}"/></option>
                    </c:if>
                  </c:forEach>
                  <c:forEach items="${familyAlbum.relationshipTypes}" var="relationshipType">
                    <c:if test="${empty relationshipType.sex}">
                      <option value="<c:out value="${relationshipType.relationshipTypeId}"/>"><c:out value="${relationshipType.label}"/></option>
                    </c:if>
                  </c:forEach>
                </select>
                <!-- exclude male relationship types -->
                <select size="15" name="femaleRelationshipTypes" style="display: none;">
                  <option value="-1">------------------------</option>
                  <c:forEach items="${familyAlbum.relationshipTypes}" var="relationshipType">
                    <c:if test="${relationshipType.sex <= 70}">
                      <option value="<c:out value="${relationshipType.relationshipTypeId}"/>"><c:out value="${relationshipType.label}"/></option>
                    </c:if>
                  </c:forEach>
                  <c:forEach items="${familyAlbum.relationshipTypes}" var="relationshipType">
                    <c:if test="${empty relationshipType.sex}">
                      <option value="<c:out value="${relationshipType.relationshipTypeId}"/>"><c:out value="${relationshipType.label}"/></option>
                    </c:if>
                  </c:forEach>
                </select>
              </td>

              <td>
                <input type="button" value="Add &gt;&gt;" onclick="addRelationship();" />
              </td>

              <td>
                <select name="relationships" size="15" multiple="true">
                  <option value="-1">--------------------------------------------------</option>
                  <c:forEach items="${familyAlbum.subject.relationships}" var="relationship">
                    <option value="<c:out value="${relationship.relationshipType.relationshipTypeId}:${relationship.toSubject.subjectId}"/>">
                      <c:out value="${relationship.toSubject.sortedLabel} (${relationship.relationshipType.label})"/>
                    </option>
                  </c:forEach>
                </select>

              </td>
            </tr>

            <tr>

              <td colspan="3" style="white-space: nowrap;">
                <input type="radio" name="subjectType" value="all" checked onclick="setRelatives('all')">&nbsp;<a href="#" onclick="return setRelatives('all');">All</a><br/>
                <input type="radio" name="subjectType" value="familyMembers" onclick="setRelatives('familyMembers')">&nbsp;<a href="#" onclick="return setRelatives('familyMembers');">Family</a><br/>
                <input type="radio" name="subjectType" value="friends" onclick="setRelatives('friends')">&nbsp;<a href="#" onclick="return setRelatives('friends');">Friends</a><br/>
                <input type="radio" name="subjectType" value="pets" onclick="setRelatives('pets')">&nbsp;<a href="#" onclick="return setRelatives('pets');">Pets</a><br/>
              </td>

              <td class="buttons">
                <input type="button" value="Delete" onclick="deleteRelationship();"/>
                <br/>
                <br/>
                <html:submit>Ok</html:submit>
                <html:cancel>Cancel</html:cancel>
              </td>
            </tr>

          </table>

        </html:form>
        <!--- end form }}} -->

      </ww:boxContent>
    </ww:box>


    <br/>

    <!-- {{{ tips -->
    <div class="tips">
      <h3>Tips</h3>
      <ul>
        <li>
          To add new relationships for
          <i><c:out value="${familyAlbum.subject.label}"/></i>
          choose one of his/her immediate relatives from the list, choose how that person is related to
          <i><c:out value="${familyAlbum.subject.label}"/></i> and then click <i>Add &gt;&gt;</i>
          <br/>
          Do this for each of his/her relatives, and then click <i>Ok</i>
        </li>

        <li>
          To correct mistakes, click on the incorrect relationship in the right
          hand box, then click the <i>Delete</i> button. Now add the correct relation (as
          descibed above), and click <i>Ok</i>.
        </li>

        <li>
          You only have to enter one half or the relationship, for example, if you say that Joe Blogs
          is the father of Jane Blogs, then you do <b>not</b> need to say that Jane is the daughter of
          Joe (its done for you automatically).
        </li>

        <li>
          To help get the relationship the right way round, there is a phase above the list. Read it
          replacing the '...' with the values you selected.
          <br/>
          For example, if you choose <i>"Joe Blogs"</i> and <i>"father"</i>, then the phrase will read:
          "<i>Joe Blogs is the father of <c:out value="${familyAlbum.subject.label}"/></i>"
        </li>

        <li>
          If a relative is not in the list of people, don't attempt to add the relationship yet.
          Instead, finish what you are doing, and then add the missing person later.
        </li>

      </ul>

    </div>
    <!-- end tips }}} -->

  </tiles:putAttribute>
</tiles:insertTemplate>

