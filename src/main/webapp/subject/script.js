var relationshipTypeListName = "allRelationshipTypes";

function setDefaultPhoto( subjectInPhotoId )
{
  ww_findObj( 'defaultPhoto' ).src = ww_findObj( 'halo' + subjectInPhotoId ).src;
  ww_findObj( 'defaultSubjectInPhotoId' ).value = subjectInPhotoId;
  // alert( "Default id : " + ww_findObj( 'defaultSubjectInPhotoId' ).value );
  return false;
}

function addRelationship()
{
  var relationshipTypeList = ww_findObj( relationshipTypeListName );
  var possibleRelativeList = getRelatives();
  var relationshipsList = ww_findObj( "relationships" );

  if ( relationshipTypeList.selectedIndex < 0 ) {
    // alert( "no type" );
    return;
  }
  if ( possibleRelativeList.selectedIndex < 0 ) {
    // alert( "no subject" );
    return;
  }

  var relationshipType = relationshipTypeList.options[ relationshipTypeList.selectedIndex ];
  var relative = possibleRelativeList.options[ possibleRelativeList.selectedIndex ];
  var relativeId = relative.value.replace( /:.*/, "");

  // alert ( "relative's id : " + relativeId );

  if ( relationshipType.value <= 0 ) {
    //alert( "not valid type" );
    return;
  }
  if ( relativeId.value <= 0 ) {
    //alert( "not value subject" );
    return;
  }

  var newRelationship = new Option();
  newRelationship.value = relationshipType.value + ":" + relativeId;
  newRelationship.text = relative.text + " (" + relationshipType.text + ")" ;

  relationshipsList.options[ relationshipsList.options.length ] = newRelationship;

}

function deleteRelationship()
{
  var relationshipsList = ww_findObj( "relationships" );
  var i;

  for( i = relationshipsList.options.length - 1; i > 0; i -- ) {
    if ( relationshipsList.options[ i ].selected ) {
      relationshipsList.options[ i ] = null;
    }
  }
}

function validateRelationships()
{
  var relationshipsList = ww_findObj( "relationships" );
  var i;

  relationshipsList.options[0].selected = false;

  for ( i = 1; i < relationshipsList.options.length; i ++ ) {
    relationshipsList.options[i].selected = true;
  }

  return true;
}

function getRelatives()
{
  if ( ww_findObj( "familyMembers" ).style.display != "none" ) {
    return ww_findObj( "familyMembers" );
  } else if ( ww_findObj( "friends" ).style.display != "none" ) {
    return ww_findObj( "friends" );
  } else if ( ww_findObj( "pets" ).style.display != "none" ) {
    return ww_findObj( "pets" );
  } else {
    return ww_findObj( "allPeople" );
  }
}

function setRelatives( type )
{
  ww_findObj( "allPeople" ).style.display = (type == "all") ? "block" : "none";
  ww_findObj( "familyMembers" ).style.display = (type == "familyMembers") ? "block" : "none";
  ww_findObj( "friends" ).style.display = (type == "friends") ? "block" : "none";
  ww_findObj( "pets" ).style.display = (type == "pets") ? "block" : "none";

  ww_findObj( "subjectType" )[0].checked = (type == "all");
  ww_findObj( "subjectType" )[1].checked = (type == "familyMembers");
  ww_findObj( "subjectType" )[2].checked = (type == "friends");
  ww_findObj( "subjectType" )[3].checked = (type == "pets");

  return false;
}

function changedSubject( listName )
{
  var relatives = ww_findObj( listName );
  var possibleRelativeList = getRelatives();

  var relativeValue = possibleRelativeList.options[ possibleRelativeList.selectedIndex ].value;
  var sex = relativeValue.replace( /.*:/, "" );
  // alert( "relativeSex = " + relativeSex );

  if ( sex == "M" ) {
    relationshipTypeListName = "maleRelationshipTypes";
  } else if ( sex == "F" ) {
    relationshipTypeListName = "femaleRelationshipTypes";
  } else {
    relationshipTypeListName = "allRelationshipTypes";
  }

  ww_findObj( "maleRelationshipTypes" ).style.display = (relationshipTypeListName == "maleRelationshipTypes") ? "block" : "none";
  ww_findObj( "femaleRelationshipTypes" ).style.display = (relationshipTypeListName == "femaleRelationshipTypes") ? "block" : "none";
  ww_findObj( "allRelationshipTypes" ).style.display = (relationshipTypeListName == "allRelationshipTypes") ? "block" : "none";
}

