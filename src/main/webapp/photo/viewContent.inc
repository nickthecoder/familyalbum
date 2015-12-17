    <ww:script src="/ww_resources/ww_eventNotifier.js"/>
    <ww:script src="/photo/script.js"/>

    <!-- {{{ photo and details -->
    <div style="text-align: center">

      <!-- links over the image -->
      <div id="subjectsInPhotoContainer">
        <c:forEach items="${familyAlbum.photo.subjectsInPhoto}" var="sip" varStatus="varStatus" >
          <div class="subjectInPhotoContainer" id="sip<c:out value="${varStatus.count}"/>"
            style="<c:out value="left: ${sip.x - sip.radius / 2}px; top: ${sip.y - sip.radius / 2}px; width: ${sip.radius}px; height: ${sip.radius}px;"/>">

            <ww:link title="${sip.subject.label}" styleClass="subjectInPhoto" href="/action/halo?subjectId=${sip.subject.subjectId}&amp;subjectInPhotoId=${sip.subjectInPhotoId}#main"> </ww:link>
          </div>
        </c:forEach>
      </div>

      <!-- main image -->
      <img id="photo" alt="photo" src="<c:out value="${familyAlbum.photoUrl}"/>" />
      <br/>

      <!-- date -->
      <img src="<ww:contextPath/>/resources/blankIcon.png" alt=""/>
      <fa:photoDate photo="${familyAlbum.photo}"/>
      <ww:link href="/action/editPhoto?photoId=${familyAlbum.photo.photoId}" title="edit photo info"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/></ww:link>

      <br/>

      <img src="<ww:contextPath/>/resources/blankIcon.png" alt=""/>
      (&nbsp;<c:out value="${familyAlbum.photo.subjectCount}"/>&nbsp;)
      <ww:link href="/action/editSubjectsInPhoto?photoId=${familyAlbum.photo.photoId}" title="tag people in photo"><img src="<ww:contextPath/>/resources/editPeopleIcon.png" alt="tag people in photo"/></ww:link>

      <br/>
      Photo Album :
      <ww:link href="album?albumId=${familyAlbum.photo.album.albumId}">
        <c:out value="${familyAlbum.photo.album.label}"/>
      </ww:link>

      <br/>
      [<a href="${familyAlbum.photo.fullUrl}" alt="fullsize">Full Size</a>]

    </div>

    <!-- end photo and details }}} -->

    <br/>

    <!-- {{{ notes -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        Notes
        <ww:boxIcon>
          <ww:link href="/action/editPhoto?photoId=${familyAlbum.photo.photoId}" title="edit photo info"><img src="<ww:contextPath/>/resources/editIcon.png" alt="edit"/> Edit</ww:link>
        </ww:boxIcon>
      </ww:boxTitle>

      <ww:boxContent>
        <div class="notes">
          <c:if test="${empty familyAlbum.photo.notes}">
            None
          </c:if>
          <pw:wikiText>${familyAlbum.photo.notes}</pw:wikiText>
        </div>
      </ww:boxContent>
    </ww:box>
    <!-- end notes }}} -- >

    <!-- {{{ People in Photo -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        People in the Photo
        <ww:boxIcon>
          <ww:link href="/action/editSubjectsInPhoto?photoId=${familyAlbum.photo.photoId}" title="tag people in photo"><img src="<ww:contextPath/>/resources/editPeopleIcon.png" alt="edit"/> Edit</ww:link>
        </ww:boxIcon>
      </ww:boxTitle>

      <ww:boxContent>

        <c:if test="${ empty familyAlbum.photo.subjectsInPhoto }">
          Nobody!
          <c:if test="${fn:length( familyAlbum.subjects ) > 0 }">
            To tag people this photo, click the <i>Edit</i> link at the top right of this box.
          </c:if>
        </c:if>

        <ww:portion items="${familyAlbum.photo.subjectsInPhoto}" var="rows" portionSize="6">

          <table width="100%">
            <c:forEach items="${rows}" var ="row">
            <tr>
            <c:forEach items="${row}" var="subjectInPhoto">
              <td align="center">
                <fa:halo subjectInPhoto="${subjectInPhoto}" label="true" />
              </td>
            </c:forEach>
            </tr>
            </c:forEach>

          </table>

        </ww:portion>

      </ww:boxContent>

    </ww:box>
    <!-- end people in photo }}} -->

    <!-- {{{ tips -->
    <div class="tips">
      <h3>Tips</h3>
      <ul>

        <c:if test="${fn:length( familyAlbum.photo.subjectsInPhoto ) > 0 }">

          <li>See what happens when you click on one of the faces in the photo above.</li>

          <li>Move the mouse cursor over a person's face in the photo above to reveal thier name.</li>

        </c:if>

        <li>
          To tag the people in this photo - use the "Edit" link in the
          "People in Photo" box above.
        </li>

        <li>
          If you want to see the other photos in the same album, click the '<i>Photo Album</i>' link
          underneath the photo.
        </li>

      </ul>

    </div>
    <br/>
    <!-- end tips }}} -->

    <c:if test="${familyAlbum.photo.imagesUpToDate == false}">
      Updating images
    </c:if>

    <script type="text/javascript">
      photoWidth = <c:out value="${familyAlbum.photo.width}"/>;
      photoHeight = <c:out value="${familyAlbum.photo.height}"/>;
      photoFullSizeUrl = "<c:out value="${familyAlbum.photo.fullUrl}"/>";
      ww_onloadNotifier.add( positionSIPLinks );
    </script>

    <div class="wikiLinkHere">
      [<c:out value="${familyAlbum.photo.label}"/>|photo:${familyAlbum.photo.photoId}]
    </div>

