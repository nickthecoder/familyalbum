<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/webwidgets.tld" prefix="ww" %>
<%@ taglib uri="/WEB-INF/pinkwino.tld" prefix="pw" %>

<tiles:insertTemplate template="/resources/layout.jsp" flush="true">

  <tiles:putAttribute name="title" type="string" value="Family Album" />
  <tiles:putAttribute name="tab" value="familyalbum" />

  <tiles:putAttribute name="main" type="string" >

    <br/>

    <!-- {{{ Welcome -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        <h1>Welcome to the Family Album</h1>
      </ww:boxTitle>
      <ww:boxContent>


        <div class="wiki_image_float_right">
          <br/>
          <ww:link href="/action/halo?subjectId=1">
            <img src="resources/screenshot.jpg" alt="screenshot"/>
          </ww:link>
        </div>

        <p>
          Welcome to the Family Album, a place to store pictures of your loved ones.
        </a>

        <h2>Getting Started</h2>

        <p>
          Do you want to see a typical Family Album page, then click on the image on the
          right.
          It will take you to my page, and from there you can explore
          the rest of my family.
        </p>

        <div class="clear">
        </div>

      </ww:boxContent>
    </ww:box>
    <!-- end welcome }}} -->

    <!-- {{{ Navigating the Family Album -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        <h2>Navigating the Family Album</h2>
      </ww:boxTitle>
      <ww:boxContent>

        <div class="easyReading">

          <h2><img style="vertical-align:middle;" src="resources/find.png" alt=""/> Find a Family</h2>
          <p>
            This web site can store many family albums. Use the "Find a Family" link
            to pick the family you are interesting in. When you first enter this
            web site, a <i>default</i> family is used (my family!).
          </p>

          <h2><img style="vertical-align:middle;" src="resources/details.png" alt=""/> Family Details</h2>
          <p>
            Gives various details about the family you have chosen. It isn't
            the most interesting page, and most people will want to skip straight
            to the Family Members instead.
          </p>

          <h2><img style="vertical-align:middle;" src="resources/family.png" alt=""/> Family Members</h2>
          <p>
            This is the most popular page, it lists all of the family members, and
            from there, you can click through to each person's page.
          </p>

          <h2><img style="vertical-align:middle;" src="resources/friends.png" alt=""/> Friends</h2>
          <p>
            Does exactly the same as the Family Members page, except it lists friends
            only. The friends are kept seperate from the family members to ensure that
            the number of friends doesn't ovverwhelm the family (and this is site is
            aimed primarily at family, not friends).
          </p>

          <h2><img style="vertical-align:middle;" src="resources/pets.png" alt=""/> Pets</h2>
          <p>
            Does exactly the same as the Family Members page, except it lists just
            the pets. Pets are given their own page, so that they don't have to appear
            on the main Family Members page.
          </p>

          <h2><img style="vertical-align:middle;" src="resources/albums.png" alt=""/> Albums</h2>
          <p>
            An album is a collection of photos, and each family may have lots of
            photo albums. This page lists all of the albums. It is especially useful
            for those people who are active members of the family, who upload photos.\
          </p>

          <h2><img style="vertical-align:middle;" src="resources/edit.png" alt=""/> Editing</h2>
          <p>
            Everything within the family album is editable by <i>you</i>. Wherever you
            see the screwdriver icon, you can edit something. If you aren't sure how to
            get to the thing you want to edit, use the large edit button near the
            top right of each page.
          </p>

        </div>

      </ww:boxContent>
    </ww:box>
    <!-- end Navigating }}} -->

    <!-- {{{ Feedback -->
    <ww:box className="mainbox">
      <ww:boxTitle>
        <h2>Feedback</h2>
      </ww:boxTitle>
      <ww:boxContent>

        <div class="easyReading">

          <p>
            Very few people have used the Family Album, so I need
            <a href="http://nickthecoder.co.uk/pinkwino/view/Contact">your feedback</a>
            on how to make it better.
            I'd be very grateful for a quick
            <a href="http://nickthecoder.co.uk/pinkwino/view/Contact">e-mail</a>;
            telling me which bits are good, and which aren't so good.
          </p>
          <p>
            I am frequently updating the software, and your feedback will make the next version
            better. Thanks.
          </p>
          <p>
            For those using <i>Internet Explorer</i> (especially version 6 and lower), I strongly recommend
            upgrading to a good web browser such as <a href="http://www.mozilla.com/firefox/">Firefox</a>.
            Internet Explorer does not display
            <a href="http://nickthecoder.co.uk/pinkwino/view/Transparent+Images">transparent images</a>
            correctly, and these images are heavily used in this Family Album.
            <a href="http://nickthecoder.co.uk/pinkwino/view/Transparent+Images">more info...</a>
          </p>

        </div>

      </ww:boxContent>
    </ww:box>
    <!-- end feedback }}} -->


  </tiles:putAttribute>
</tiles:insertTemplate>


