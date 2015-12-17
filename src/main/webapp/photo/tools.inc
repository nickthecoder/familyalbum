<html:xhtml/>

  <table >
    <tr>

      <td width="50%" valign="top">

        <!-- innerBox NEW -->
        <ww:box  className="mainbox">
          <ww:boxTitle>
            Add Person
          </ww:boxTitle>

          <ww:boxContent>

            <!-- New Subject In Photo Form -->
            <form name="newSubjectInPhotoForm" method="post" action="editSubjectsInPhoto">

              <input type="hidden" name="photoId" value="<c:out value="${familyAlbum.photo.photoId}"/>" />
              <input type="hidden" name="x" value="0" />
              <input type="hidden" name="y" value="0" />
              <input type="hidden" name="radius" value="0" />

              <p>
                Click and drag a circle around the face of a person in the photo,
                select their name from the list below, then click Save.
              </p>

              <table class="plainForm">

                <tr>
                  <td>Subject</td>
                  <td>
                    <select name="subjectId">
                      <option value="-1" >--select--</option>

                      <optgroup label="Family Members">
                        <c:forEach items="${familyAlbum.subjects}" var="subject">
                          <c:if test="${subject.subjectType.subjectTypeId == 1}">
                            <option value="<c:out value="${subject.subjectId}"/>"><c:out value="${subject.sortedLabel}"/></option>
                          </c:if>
                        </c:forEach>
                      </optgroup>

                      <optgroup label="Friends">
                        <c:forEach items="${familyAlbum.subjects}" var="subject">
                          <c:if test="${subject.subjectType.subjectTypeId == 3}">
                            <option value="<c:out value="${subject.subjectId}"/>"><c:out value="${subject.sortedLabel}"/></option>
                          </c:if>
                        </c:forEach>
                      </optgroup>

                      <optgroup label="Pets">
                        <c:forEach items="${familyAlbum.subjects}" var="subject">
                          <c:if test="${subject.subjectType.subjectTypeId == 2}">
                            <option value="<c:out value="${subject.subjectId}"/>"><c:out value="${subject.sortedLabel}"/></option>
                          </c:if>
                        </c:forEach>
                      </optgroup>

                    </select>
                  </td>
                </tr>

                <tr>
                  <td></td>
                  <td>
                    <p>
                      If the person you want to add is not in the list, then
                      <ww:link href="/action/newSubject?familyId=${familyAlbum.family.familyId}"> add&nbsp;him/her</ww:link>.
                    </p>

                  </td>
                </tr>

                <tr>
                  <td>Good Quality?</td>
                  <td>
                    <input type="checkbox" name="useHalo" checked="true" value="true"/>
                    Is the photo of this person good enough to show on their page? For example, if the person is
                    looking away then <i>remove</i> the tick from the box.
                  </td>
                </tr>

                <tr>
                  <td class="buttons" colspan="2" >
                    <input type="submit" name="newSubject" value="Save" />
                  </td>
                </tr>
              </table>

            </form>
            <!-- end new subject in photo form -->

          </ww:boxContent>
        </ww:box>

      </td>

      <td width="50%" valign="top">

        <!-- box DELETE -->
        <ww:box className="mainbox">
          <ww:boxTitle>
            Remove Person
          </ww:boxTitle>

          <ww:boxContent>

            <!-- delete subject in photo form -->
            <form name="deleteSubjectInPhotoForm" method="post" action="editSubjectsInPhoto">

              <input type="hidden" name="photoId" value="<c:out value="${familyAlbum.photo.photoId}"/>" />
              <input type="hidden" name="deleteSubject" value="true" />

              <p>
                Choose the person to remove from the photo, then click delete.
              </p>

              <table class="plainForm">
                <tr>
                  <td>Subject</td>
                  <td>
                    <select name="subjectInPhotoId">
                      <option value="-1">--select--</option>
                      <c:forEach items="${familyAlbum.photo.subjectsInPhoto}" var="subjectInPhoto">
                        <option value="<c:out value="${subjectInPhoto.subjectInPhotoId}"/>"><c:out value="${subjectInPhoto.subject.sortedLabel}"/></option>
                      </c:forEach>
                    </select>
                  </td>
                </tr>

                <tr>
                  <td class="buttons" colspan="2">
                    <input type="submit" name="deleteSubjectButton" value="Remove" />
                  </td>
                </tr>

              </table>

            </form>
            <!-- end delete subject in photo form -->

          </ww:boxContent>
        </ww:box>

      </td>

    </tr>

  </table>

  <div class="tips">
    <h3>Tips</h3>
    <ul>
      <c:choose>

        <c:when test="${empty familyAlbum.subjects}">
          <li>
            This Family Album doesn't have any people in it yet.
          </li>
          <li>
            Before edting the photos, you should add your family. Click the "Edit" link near
            the top right of the page and follow the instructions.
          </li>
        </c:when>

        <c:otherwise>
          <li>
            To tag a person to in the photo, click and drag a circle around the face of a person,
            select their name from the list below, then click Save.
          </li>

          <li>
            When you have finished tagging the people in this photo, you can tag the next photo using the
            <i>'forward'</i> button
            (<img height="20" alt="done" src="<ww:contextPath/>/resources/next.png" />)
            at the top of the page, or you can stop tagging, by clicking the '<i>done</i>' button
            (<ww:link title="done" href="photo?photoId=${familyAlbum.photo.photoId}"><img height="20" alt="done" src="<ww:contextPath/>/resources/done.png" /></ww:link>)
           </li>

          <li>
            If you want to <i>change</i> the position or size of a circle after it has been saved, then
            remove it and recreate it.
          </li>

          <li>
            You can zoom into the picture using the icons at the top right of the page. However,
            zooming in requires the full size photo to be downloaded, which can be a little
            slow.
          </li>

        </c:otherwise>

      </c:choose>
    </ul>
  </div>

