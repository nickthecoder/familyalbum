<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>

<div class="breadcrumbNavigation">
  <ww:link href="/">Welcome</ww:link>
  <ww:link href="/action/families"><img src="<ww:contextPath/>/resources/find.png" alt=""/>Find a Family</ww:link>
  <ww:link href="/action/viewFamily"><img src="<ww:contextPath/>/resources/details.png" alt=""/>Family Details</ww:link>
  <ww:link href="/action/viewSubjects?familyId=${familyAlbum.family.familyId}&amp;subjectTypeId=1"><img src="<ww:contextPath/>/resources/family.png" alt=""/>Family Members</ww:link>
  <ww:link href="/action/viewSubjects?familyId=${familyAlbum.family.familyId}&amp;subjectTypeId=3"><img src="<ww:contextPath/>/resources/friends.png" alt=""/>Friends</ww:link>
  <ww:link href="/action/viewSubjects?familyId=${familyAlbum.family.familyId}&amp;subjectTypeId=2"><img src="<ww:contextPath/>/resources/pets.png" alt=""/>Pets</ww:link>
  <ww:link href="/action/viewAlbums?familyId=${familyAlbum.family.familyId}&amp;"><img src="<ww:contextPath/>/resources/albums.png" alt=""/>Albums</ww:link>

  <ww:linkInfo href="/action/edit">
    <ww:linkParameter name="albumId" value="${familyAlbum.album.albumId}"/>
    <ww:linkParameter name="subjectId" value="${familyAlbum.subject.subjectId}"/>
    <ww:linkParameter name="photoId" value="${familyAlbum.photo.photoId}"/>
    <ww:link><img src="<ww:contextPath/>/resources/edit.png" alt=""/>Edit</ww:link>
  </ww:linkInfo>

</div>

