<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>

<tiles:importAttribute name="action" />

<ww:groupByInitial var="letters" items="${familyAlbum.subjects}" expression="sortedLabel">

  <div style="text-align: right">
    <ww:link href="/action/newSubject">Add Person / Pet <img class="icon" src="<ww:contextPath/>/resources/add.png" alt="add"/></ww:link>
  </div>

  <!-- Initial Letters -->
  <div class="boxContainer" style="text-align: center;">
    <p>
      Jump to the people who's surname begins with ...
    </p>

    <div class="chooseLetter">
      <c:forEach var="letter" items="${letters}">
        <c:if test="${letter.count > 0}">
          <a class="button" href="#<c:out value="${letter}"/>"><c:out value="${letter}"/></a>
        </c:if>
        <c:if test="${letter.count == 0}">
          <c:out value="${letter}"/>
        </c:if>
      </c:forEach>
    </div>

  </div>

  <!-- People -->
  <c:forEach var="letter" items="${letters}">
  <c:if test="${letter.count > 0}">

    <a name="<c:out value="${letter}"/>"></a>
    <ww:box className="mainbox">
      <ww:boxTitle>
        <c:out value="${letter}"/>
        <ww:boxIcon>
          [ <a href="#top">top</a> ]
        </ww:boxIcon>
      </ww:boxTitle>
      <ww:boxContent>

        <table width="100%">
        <ww:portion items="${letter.items}" var="portions" portionSize="6" >

            <c:forEach items="${portions}" var="portion">
            <tr>

              <c:forEach items="${portion}" var="subject">

                <c:if test="${subject != null}">

                <!-- subject image -->
                <td align="center">

                  <ww:linkInfo href="${action}">
                    <fa:halo subject="${subject}" label="true"editable="true" />
                  </ww:linkInfo>

                </td>
                </c:if>

              </c:forEach>

            </tr>
            </c:forEach>

        </ww:portion>
        </table>

      </ww:boxContent>
    </ww:box>

  </c:if>
  </c:forEach>

</ww:groupByInitial>

