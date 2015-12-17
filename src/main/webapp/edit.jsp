<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>
<%@ taglib uri="/WEB-INF/familyalbum.tld" prefix="fa" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="true">

  <tiles:putAttribute name="title" type="string" value="Edit" />

  <tiles:putAttribute name="main" type="string" >

    <h1>Edit</h1>

    <table width="100%" align="center">
      <tr>
        <td valign="top" width="48%">

          <!-- {{{ Photo -->
          <ww:box className="mainbox">
            <ww:boxTitle>
              Photo
            </ww:boxTitle>
            <ww:boxContent>

              <c:if test="${familyAlbum.photo.photoId != null }">
                <img src="<c:out value="${familyAlbum.photo.thumbnailUrl}"/>" />
              </c:if>

              <!-- add photo -->
              <c:if test="${familyAlbum.album.albumId != null }">
                <h2>
                  <ww:link href="/action/uploadPhoto?albumId=${familyAlbum.album.albumId}">Add a Photo to Album
                  : '<i><c:out value="${familyAlbum.album.label}"/></ww:link></i>'
                </h2>
                <p>
                  If you want to add the photos to a different album (not '<i><c:out value="${familyAlbum.album.label}"/></i>'),
                  then you must <ww:link href="/action/viewAlbums">choose an album</ww:link>, and then return to this
                  page, by clicking the 'Edit' link near the top right of the page.
                </p>
                <p>
                  If you want to begin a new album, then you should <ww:link href="/action/newAlbum">create one</ww:link>
                </p>
              </c:if>

              <c:if test="${familyAlbum.album.albumId == null }">
                <h2>Add a Photo to an Album</h2>

                <p>
                  Before you can add photos, you must have an <i>album</i> to put them in. Either :
                </p>
                <ol>
                  <li><ww:link href="/action/newAlbum">Create a new album</ww:link></li>
                  <li><ww:link href="/action/viewAlbums">Select an existing album</ww:link></li>
                </ol>
              </c:if>

              <!-- more... -->
              <c:if test="${familyAlbum.photo.photoId == null }">
               <h2>More...</h2>
                <p>
                  If you want to edit a photo,
                  the first thing you need to do is find the photo,
                  and then return to this page, by clicking the 'Edit' link near the top right of the page.
                </p>
                <p>
                  There are two ways to find a photo :
                </p>
                <ol>
                  <li><ww:link href="/action/viewAlbums">Look for photos within an album</ww:link></li>
                  <li><ww:link href="/action/viewSubjects">Look for a photo of a particular person</ww:link></li>
                </ol>

              </c:if>

              <c:if test="${familyAlbum.photo.photoId != null }">

                <!-- edit photo -->
                <h2><ww:link href="/action/editPhoto?photoId=${familyAlbum.photo.photoId}">Edit Photo Information</ww:link></h2>
                <p>
                  You can enter the date that the photo was take, and add any notes that you wish.
                  For example, you may want to describe where the photo was taken.
                </p>

                <!-- tag people in photo -->
                <h2><ww:link href="/action/editSubjectsInPhoto?photoId=${familyAlbum.photo.photoId}">Tag People in the Photo</ww:link></h2>
                <p>
                  Draw circles around each person in the photo, and tag who it is.
                  You need to do this for every photograph that you add. If you miss out this
                  step, the the family album won't be as good, because the photo won't appear
                  on the person's page.
                </p>

                <!-- delete phopto -->
                <h2><ww:link href="/action/deletePhoto?photoId=${familyAlbum.photo.photoId}">Delete the Photo</ww:link></h2>
                <p>
                  Please don't delete other people's photos!
                  This is a publicly editable web site, which depends on trust. Please don't abuse it :-)
                </p>

              </c:if>

            </ww:boxContent>
          </ww:box>
          <!-- end photo }}} -->

          <br/>

          <!-- {{{ Album -->
          <ww:box className="mainbox">
            <ww:boxTitle>
              Photo Album
            </ww:boxTitle>
            <ww:boxContent>

              <!-- new album -->
              <h2><ww:link href="/action/newAlbum">Add a New Album</ww:link></h2>
              <p>
                If you want to add photos, this is the place to start. Photo are placed into albums,
                so you won't be able to add photos until you have an album to put them in.
              </p>

              <!-- more... -->
              <c:if test="${familyAlbum.album.albumId == null }">
                <h2>More...</h2>
                <p>
                  If you want to edit an existing photo album,
                  <ww:link href="/action/viewAlbums">select an album</ww:link>,
                  and then return to this page, by clicking the 'Edit' link near the top right of the page.
                </p>
              </c:if>

              <c:if test="${familyAlbum.album.albumId != null }">

               <!-- edit album -->
               <h2><ww:link href="/action/editAlbum?albumId=${familyAlbum.album.albumId}">Edit Album Information</ww:link></h2>
                <p>
                  You can write notes about each photo album. It is useful to include who's
                  photo album it is, as well what photos are in the album.
                </p>

                <!-- delete album -->
                <h2><ww:link href="/action/deleteAlbum?albumId=${familyAlbum.album.albumId}">Delete Entire Photo Album : '<i><c:out value="${familyAlbum.album.label}"/></i>'</ww:link></h2>
                <p>
                  Deleting an album also deletes all of the photos within it. Don't use this
                  option unless you are really sure that you want to delete a whole photo album
                  and all of its photos.
                </p>

              </c:if>

            </ww:boxContent>
          </ww:box>
          <!-- end Album }}} -->

        </td>

        <td valign="top" width="4%">
        </td>

        <td valign="top" width="48%">

          <!-- {{{ Person -->
          <ww:box className="mainbox">
            <ww:boxTitle>
              Person
            </ww:boxTitle>
            <ww:boxContent>

              <!-- new person -->
              <h2><ww:link href="/action/newSubject">Add a Person</ww:link></h2>
              <p>
                Add a new person to the database. It is easier to add all of your family into the database
                before adding the photos. If you add the photos first, then you won't be able to tag
                <b>who</b> is in the photographs.
              </p>

              <!-- more... -->
              <c:if test="${familyAlbum.subject.subjectId == null }">
               <h2>More...</h2>
                <p>
                  If you want to edit an existing person,
                  <ww:link href="/action/viewSubjects">select a person</ww:link>
                  and then return to this page, by clicking the 'Edit' link near the top right of the page.
                </p>
              </c:if>

              <c:if test="${familyAlbum.subject.subjectId != null }">

                <!-- edit person -->
                <h2><ww:link href="/action/editSubject?subjectId=${familyAlbum.subject.subjectId}">Edit : '<i><c:out value="${familyAlbum.subject.label}"/></i>'</ww:link></h2>
                <p>
                  You can alter a person's name, sex, and their relationships to other family members.
                  You can also choose the person's best photo.
                </p>

                <!-- delete person -->
                <h2><ww:link href="/action/deleteSubject?subjectId=${familyAlbum.subject.subjectId}">Delete : '<i><c:out value="${familyAlbum.subject.label}"/></i>'</ww:link></h2>
                <p>
                  Please don't remove people for fun (or spite).
                  This is a publicly editable web site, which depends on trust. Please don't abuse it :-)
                </p>
              </c:if>

            </ww:boxContent>
          </ww:box>
          <!-- end Person }}} -->

          <br/>

          <!-- {{{ Family -->
          <ww:box className="mainbox">
            <ww:boxTitle>
              Family Album
            </ww:boxTitle>
            <ww:boxContent>

              <!-- New Family -->
              <h2><ww:link href="/action/newFamily">Start a new Family Album</ww:link></h2>
              <p>
                Start a new Family Album if your family are in not the database yet, or if some
                of them are already here, but there are so many people in that Family Album
                that it is getting tricky to use.
              </p>

              <!-- Edit Family -->
              <h2><ww:link href="/action/editFamily?familyId=${familyAlbum.family.familyId}">Edit : '<i><c:out value="${familyAlbum.family.label}"/></i>'</ww:link></h2>
              <p>
                You can alter the label and notes for this family.
              </p>

              <!-- delete family -->
              <h2><ww:link href="/action/deleteFamily?familyId=${familyAlbum.family.familyId}">Delete : '<i><c:out value="${familyAlbum.family.label}"/></i>'</ww:link></h2>
              <p>
                WARNING : This will delete the entire family from the database.
                Only use this option if you are the main person in charge of this family album
                and you no longer want anybody to be able see any of the photos or people
                within the family album that you created.
              </p>
              <p>
                This is a publicly editable web site, which depends on trust. Please don't abuse it :-)
              </p>

            </ww:boxContent>
          </ww:box>
          <!-- end Family }}} -->

        </td>
      </tr>
    </table>


  </tiles:putAttribute>
</tiles:insertTemplate>


