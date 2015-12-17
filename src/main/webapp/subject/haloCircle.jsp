<%!

public static final int HALO_RADIUS = 220;
public static final double ANGLE_OFFSET = Math.PI * 2 / 17; //  Math.PI / 4.0;
public static final int ADD_LEFT = -55;
public static final int ADD_TOP = -60;

public String haloPosition( HttpServletRequest request, PageContext pageContext )
{
  java.util.Collection sips = (java.util.Collection) request.getAttribute( "sips" );
  javax.servlet.jsp.jstl.core.LoopTagStatus loop = (javax.servlet.jsp.jstl.core.LoopTagStatus) pageContext.getAttribute( "loop" );

  int haloCount = sips.size();
  int index = loop.getCount() - 1;

  double angle = (Math.PI/2) + ANGLE_OFFSET + ((2 * Math.PI) - (2 * ANGLE_OFFSET)) * index / (haloCount - 1);
  long x = Math.round( (Math.cos( angle ) + 1) * HALO_RADIUS ) + ADD_LEFT;
  long y = Math.round( (Math.sin( angle ) + 1) * HALO_RADIUS ) + ADD_TOP;

  return "left: " + x + "px; top: " + y + "px";
}

public static final int SQUARE_SIZE = 440;

public String alternateHaloPosition( HttpServletRequest request, PageContext pageContext )
{
  java.util.Collection sips = (java.util.Collection) request.getAttribute( "sips" );
  javax.servlet.jsp.jstl.core.LoopTagStatus loop = (javax.servlet.jsp.jstl.core.LoopTagStatus) pageContext.getAttribute( "loop" );

  int haloCount = sips.size();
  int index = loop.getCount() - 1;

  int nth;
  int from;
  int x;
  int y;

  if ( haloCount < 5 ) {
    int dx = index % 2;
    int dy = index / 2;
    return "left: " + (ADD_LEFT + dx * HALO_RADIUS * 2) + "px; top: " + (ADD_TOP + dy * HALO_RADIUS * 2) + "px;";
  }

  if ( index - 1 < haloCount / 2 ) {

    nth = index;
    from = haloCount - haloCount / 2;

    if ( nth <= from / 2 )  {
      from = from - from / 2;

      x = 0;
      y = SQUARE_SIZE - ( SQUARE_SIZE * nth / from );

    } else {
      nth = nth - ( from - from / 2 );
      from = from / 2;
      x = SQUARE_SIZE * nth / from;
      y = 0;

    }

  } else {

    nth = index - (haloCount - haloCount / 2);
    from = haloCount / 2;

    if ( nth <= from / 2 ) {
      from = from - from / 2;

      x = SQUARE_SIZE;
      y = SQUARE_SIZE * nth / from;

    } else {
      nth = nth - ( from - from / 2 );
      from = from / 2;
      x = SQUARE_SIZE - SQUARE_SIZE * nth / from;
      y = SQUARE_SIZE;
    }
  }

  return "left: " + ( x + ADD_LEFT ) + "px; top: " + ( y + ADD_TOP ) + "px";
}

%>
  <ww:browser supportsPng="supportsPng"/>

  <div id="mainContainer">

    <div class="haloContainer">

      <!-- halos -->
      <c:forEach items="${sips}" var="subjectInPhoto" varStatus="loop">

        <c:if test="${supportsPng}">
          <div id="halo${loop.count - 1}" class="halo" style="<%= haloPosition( request, pageContext ) %>">
            <fa:halo subjectInPhoto="${subjectInPhoto}" />
          </div>
        </c:if>
        <c:if test="${! supportsPng}">
          <div id="halo${loop.count - 1}" class="halo" style="<%= alternateHaloPosition( request, pageContext ) %>">
            <fa:halo subjectInPhoto="${subjectInPhoto}" />
          </div>
        </c:if>

      </c:forEach>
    </div>

    <!-- Main Halo -->
    <c:if test="${familyAlbum.subjectInPhoto.haloUrl == null}">
      <img id="mainHaloImage" width="400" height="400" alt="no image" src="<ww:contextPath/>/resources/questionMarkHalo.png"/>
    </c:if>
    <c:if test="${familyAlbum.subjectInPhoto.haloUrl != null}">
      <img width="400" height="400" id="mainHaloImage" alt="main image" src="<c:out value="${familyAlbum.subjectInPhoto.haloUrl}"/>"/>
    </c:if>
  </div>


