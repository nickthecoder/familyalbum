    <ww:script src="/ww_resources/ww_eventNotifier.js"/>
    <ww:script src="/photo/script.js"/>

    <div style="text-align: center">

      <!-- circles over the image -->
      <div id="subjectsInPhotoContainer">

        <!-- the red one -->
        <div id="sip1" class="subjectInPhotoContainer">
          <img alt="red circle" width="100%" src="<ww:contextPath/>/resources/redCircle.gif"/ >
        </div>

        <!-- the grey ones -->
        <c:forEach items="${familyAlbum.photo.subjectsInPhoto}" var="sip" varStatus="varStatus" >

          <div class="subjectInPhotoContainer" id="sip<c:out value="${varStatus.count + 1}"/>"
            style="<c:out value="left: ${sip.x - sip.radius}px; top: ${sip.y - sip.radius}px; width: ${sip.radius * 2}px;"/>">

            <img alt="circle" id="fixedCircle<c:out value="${varStatus.count}"/>" width="100%" src="<ww:contextPath/>/resources/${sip.useHalo ? "circle.gif" : "dashedCircle.gif"} "/>

          </div>
        </c:forEach>

      <div id="subjectsInPhotoMouse"  onmouseup="return onMouseUp(event);" onmousedown="return onMouseDown(event);" onmousemove="onMouseMove(event);">
        <!-- mouse layer -->
      </div>

      </div>
      <!-- MAIN IMAGE -->
      <img alt="photo" id="photo" src="<c:out value="${familyAlbum.photoUrl}"/>" />

    </div>

    <br/>

    <%@ include file="tools.inc" %>

    <script type="text/javascript">
      photoWidth = <c:out value="${familyAlbum.photo.width}"/>;
      photoHeight = <c:out value="${familyAlbum.photo.height}"/>;
      photoFullSizeUrl = "<c:out value="${familyAlbum.photo.fullUrl}"/>";
      ww_onloadNotifier.add( positionSIPLinks );
    </script>

