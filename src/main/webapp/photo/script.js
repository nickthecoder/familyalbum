dragging = false;
zoom = 1;
dragX = 0;
dragY = 0;
centerX = 0;
centerY = 0;
radius = 0;
NEAR_ENOUGH = 10;
LARGE_ENOUGH = 10;
dragType = "";

function zoomFit()
{
  var zoom1 = ww_innerWidth() / ww_findObj( "photo" ).clientWidth;
  var zoom2 = ww_innerHeight() / ww_findObj( "photo" ).clientHeight;

  // alert( "zooms " + zoom1 + ", " + zoom2 + " :  " + (zoom1 > zoom2 ? zoom2 : zoom1) );
  if ( (zoom1 < 1) || (zoom2 < 1) ) {
    doZoom( zoom1 > zoom2 ? zoom2 : zoom1 );
  }
}
ww_onloadNotifier.add( zoomFit );

function zoomIn()
{
  doZoom( 1.25 );
  return false;
}

function zoomOut()
{
  doZoom( 1/1.25 );
  return false;
}

function doZoom( scale )
{
  var oldZoom = zoom;
  zoom = zoom * scale;
  var photo = ww_findObj( "photo" );

  if ( (scale > 1) && (zoom > 1.1) && ( photo.src.toString().indexOf( "/.quick" ) > 0 ) ) {
    // alert( "Zooming into larger version of the image : " + photoFullSizeUrl + " was " + photo.src );
    photo.src = photoFullSizeUrl;
  } else {
    // alert( "normal zoom" );
  }

  //alert( "doZoom scale: " + scale + " zoom : " + zoom + " was " + oldZoom );

  if ( photo.normalWidth == null ) {
    photo.normalWidth = photo.width;
  }
  photo.width = Math.floor( photo.normalWidth * zoom );

  positionSIPLinks();

  return false;
}

function positionSIPLinks()
{
  // alert( "positionSIPLinks" )
  var i = 1;
  var sip = ww_findObj( "sip" + i );

  var photo = ww_findObj( "photo" );
  var subjectsInPhotoContainer = ww_findObj( "subjectsInPhotoContainer" );
  var subjectsInPhotoMouse = ww_findObj( "subjectsInPhotoMouse" );
  var ratio = photoWidth / photo.width;

  // alert( subjectsInPhotoContainer.style.width );
  subjectsInPhotoContainer.style.width = photo.width + "px";
  if ( subjectsInPhotoMouse != null ) {
    subjectsInPhotoMouse.style.width = photo.width + "px";
    subjectsInPhotoMouse.style.height = photo.height + "px";
  }

  while ( sip != null ) {
    // alert( sip );
    // sip.style.left = i * 100 + "px";
    if ( sip.realX == null ) {
      sip.realX = sip.style.left.toString().replace( "px", "" );
      sip.realY = sip.style.top.toString().replace( "px", "" );
      sip.realWidth = sip.style.width.toString().replace( "px", "" );
    }

    sip.style.left = Math.round( sip.realX / ratio ) + "px";
    sip.style.top = Math.round( sip.realY / ratio ) + "px";
    sip.style.width = Math.round( sip.realWidth / ratio ) + "px";
    sip.style.height = sip.style.width;
    sip.style.visibility = "visible";

    i ++;
    sip = ww_findObj( "sip" + i );
  }
}


function onMouseDown( event )
{
  event = fixMouseEvent( event );

  if ( ! dragging ) {
    var circle = ww_findObj( 'sip1' );

    dragging = true;

    dragX = event.containerX;
    dragY = event.containerY;

    var distFromCenter = Math.sqrt( (dragX-centerX) * (dragX-centerX) + (dragY-centerY) * (dragY-centerY) );

    // alert ( distFromCenter - radius );

    // Close enough to the exising center?
    if ( ( distFromCenter < NEAR_ENOUGH ) && ( radius > LARGE_ENOUGH ) ) {
      // alert( "moving" );
      dragType = "moving";

    // Close enough to the exising edge?
    } else if ( (distFromCenter - radius < NEAR_ENOUGH) && (radius - distFromCenter > - NEAR_ENOUGH) ) {
      // alert( "re-sizing" );
      // Use the existing center
      dragX = centerX;
      dragY = centerY;
      dragType = "sizing";

    } else {
      //alert( "new " + dragX + "," + dragY );
      circle.style.width = "0px";
      circle.style.height = "0px";
      centerX = dragX;
      centerY = dragY;
      radius = 0;
      dragType = "sizing";
    }

  }
  return false;
}

function onMouseUp( event )
{
  if ( dragging ) {
    dragging = false;
  }

  return false;
}


function onMouseMove( event )
{
  event = fixMouseEvent( event );

  if (dragging) {

    if ( dragType == "moving" ) {

      centerX = event.containerX;
      centerY = event.containerY;

    } else {

      var diffX = event.containerX - dragX;
      var diffY = event.containerY - dragY;

      radius = Math.floor( Math.sqrt(diffX*diffX + diffY*diffY) );
    }

    var circle = ww_findObj( 'sip1' );
    // alert( "move " + circle );
    circle.style.visibility = "visible";
    circle.style.left = centerX - radius + "px";
    circle.style.top = centerY - radius + "px";
    circle.style.width = radius * 2 + "px";
    circle.style.height = radius * 2 + "px";

    var photo = ww_findObj( "photo" );
    var ratio = photoWidth / photo.width;

    circle.realX = Math.floor( (centerX  - radius) * ratio );
    circle.realY = Math.floor( (centerY - radius) * ratio );
    circle.realWidth = Math.floor( radius * 2 * ratio );


    ww_findObj( "x" ).value = Math.floor( centerX * ratio );
    ww_findObj( "y" ).value = Math.floor( centerY * ratio );
    ww_findObj( "radius" ).value = Math.floor( radius * ratio );
  }
  return false;
}


function deleteSubjectInPhoto( subjectInPhotoId )
{

  var selectBox = ww_findObj( "subjectInPhotoId" );
  for ( i = 0; i < selectBox.length; i ++ ) {
    if ( selectBox.options[ i ].value == subjectInPhotoId ) {
      selectBox.selectedIndex = i;
      if ( confirm( "Are you sure you want to delete the subject from the photograph?" ) ) {
        selectBox.form.submit();
      }
      return false;
    }
  }



  return false;
}

/*
  Returns the event object, which now has two new attributes:
  documentX and documentY, which is the

  See : http://evolt.org/article/Mission_Impossible_mouse_position/17/23335/index.html
*/

function fixMouseEvent(e)
{
	if (!e) var e = window.event;

	if (e.pageX || e.pageY)	{

		e.documentX = e.pageX;
		e.documentY = e.pageY;

  }	else if (e.clientX || e.clientY) {

		e.documentX = e.clientX + document.body.scrollLeft;
		e.documentY = e.clientY + document.body.scrollTop;
	}

  if ( e.layerX ) {
    e.containerX = e.layerX;
    e.containerY = e.layerY;
  } else if ( e.offsetX ) {
    e.containerX = e.offsetX;
    e.containerY = w.offsetY;
  }

	// documentX and documentY contain the mouse position relative to the document
	// containerX and containerY contain the mouse position relative to the containing element.

  return e;
}

shortcutListener.add( [","], function() { ww_followLink( "previousLink" ); } );
shortcutListener.add( ["."], function() { ww_followLink( "nextLink" ); } );
shortcutListener.add( ["u"], function() { ww_followLink( "upLink" ); } );

shortcutListener.add( ["+"], zoomIn );
shortcutListener.add( ["="], zoomIn );
shortcutListener.add( ["-"], zoomOut );
shortcutListener.add( ["_"], zoomOut );


