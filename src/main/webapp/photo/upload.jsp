<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="false">

  <tiles:putAttribute name="title" type="string" value="Upload New Photo into Album : ${familyAlbum.album.label}" />

  <tiles:putAttribute name="main" type="string" >

    <form action="uploadPhoto" method="post" enctype="multipart/form-data">

    <br/>

      <div class="tips">
        <h2>Tips</h2>
        <ol>

        <li>Click <i>Browse...</i> (below), then select the photo you want to upload.</li>

          <li>You can choose up to 10 photos, one at a time.</li>

          <li>
            Click <i>Upload</i> (below).
            <br/>
            <b>Note</b>. Uploading large photos can be quite slow, so please be patient,
            especially if you are uploading more than one photo at a time.
          </li>

          <li>If you want to add more photos, repeat steps 1 to 3 as many times as necessay</li>

          <li>
            When you have finished uploading all of the photos, click
            <ww:link href="album?albumId=${familyAlbum.album.albumId}">Back to <c:out value="${familyAlbum.album.label}"/></ww:link>
          </li>

          <li>
            Afterwards, don't forget to <i>edit</i> each of the photos, specifiying
            a year and who is in each of the photos.
          </li>

        </ol>
      </div>

      <br/>

      <input type="hidden" name="albumId" value="${familyAlbum.album.albumId}"/>

      <table class="form">
        <tr>
          <td colspan="2">
            <p>
              Choose up to ten photos at a time, and then click "Upload" below.
            </p>
            <p>
              <b>Note: </b> You can only upload jpeg files.
            </p>
          </td>
        </tr>


        <tr>
          <td>Year (yyyy)</td>
          <td><input type="text" name="year" value=""/></td>
        </tr>

        <tr>
          <td>Year Accuracy</td>
          <td>
            <select name="yearAccuracy"><option value="0" selected="selected">Exact</option>
              <option value="1">Within a year or two</option>
              <option value="10">Within 10 Years</option>
              <option value="100">Complete Guess</option>
            </select>
          </td>
        </tr>


        <c:forEach begin="0" end="9" var="uploadNumber">
          <tr>
            <td>Image ${uploadNumber + 1}</td>
            <td>
              <input type="file" name="upload${uploadNumber}" size="60"/>
            </td>
          </tr>
        </c:forEach>



        <tr>
          <td class="buttons" colspan="2">
            <input type="submit" name="upload" value="Upload"/>
            <input type="submit" name="cancel" value="Cancel"/>
            <br/>
            <br/>
            <ww:link href="album?albumId=${familyAlbum.album.albumId}">Back to <c:out value="${familyAlbum.album.label}"/></ww:link>
          </td>
        </tr>
      </table>



    </html:form>

    <h2>Photos Uploaded</h2>


    <ww:sort items="${familyAlbum.album.photos}" var="photos" field="photoId" reverse="true"/>

    <!-- Album's Photos (thumbnails) -->
    <div class="whiteBox2">
      <table class="thumbnails">

        <ww:portion var="portions" items="${photos}" portionSize="4" pad="normal">
          <c:forEach var="portion" items="${portions}">

            <tr>
              <c:forEach var="photo" items="${portion}">
              <td width="25%" class="thumbnailCell">
                <c:if test="${photo != null}" >
                  <div>

                    <ww:linkInfo href="/action/photo">
                      <ww:linkParameter name="photoId" value="${photo.photoId}"/>

                      <ww:link><img alt="" src="<c:out value="${photo.thumbnailUrl}"/>" /></ww:link>

                    </ww:linkInfo>
                  </div>
                </c:if>
              </td>
              </c:forEach>
            </tr>

            <tr>
              <c:forEach var="photo" items="${portion}">
              <td width="25%" class="labelCell">
                <c:if test="${photo != null}" >
                  <div>

                    <c:out value="${photo.photoId}"/><br/>
                    <c:out value="${photo.label}"/>
                    <c:if test="${! photo.imagesUpToDate }">
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
    <!-- END Album's Photos (thumbnails) -->

  </tiles:putAttribute>
</tiles:insertTemplate>

