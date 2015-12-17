/*
 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
*/

package familyalbum.bean;

import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import familyalbum.action.GetPostAction;
import familyalbum.model.Album;
import familyalbum.model.Family;
import familyalbum.model.FamilyAlbum;
import familyalbum.model.Photo;
import familyalbum.model.SipAgeComparator;
import familyalbum.model.Subject;
import familyalbum.model.SubjectInPhoto;
import familyalbum.model.SubjectType;
import familyalbum.util.HibernateUtil;

/**
*/

public class FamilyAlbumBean
{

  // -------------------- [[Static Attributes]] --------------------

  public static final String SIZE_NORMAL = "normal";

  public static final Integer DEFAULT_FAMILY_ID = new Integer( 1 );

  // -------------------- [[Attributes]] --------------------

  private Integer _familyId;

  private Family _family;

  private Integer _subjectId;

  private Subject _subject;

  private Integer _subjectTypeId;

  private SubjectType _subjectType;

  private Integer _albumId;

  private Album _album;

  private Integer _photoId;

  private Photo _photo;

  private SubjectInPhoto _subjectInPhoto;

  private Integer _subjectInPhotoId;

  private String _size;




  // -------------------- [[Static Methods]] --------------------

  // -------------------- [[Constructors]] --------------------

  /**
  */
  public FamilyAlbumBean()
  {
    _size = SIZE_NORMAL;
  }

  public void initialise( HttpServletRequest request )
  {
    HttpSession session = request.getSession();

    // Persist the familyId throught a sesgesion.
    if ( GetPostAction.getIntegerParameter( request, "familyId" ) == null ) {
      Integer sessionFamilyId = (Integer) session.getAttribute( "familyId" );
      setFamilyId( sessionFamilyId );
    } else {
      setFamilyId( GetPostAction.getIntegerParameter( request, "familyId" ) );
      session.setAttribute( "familyId", getFamilyId() );
    }

    setAlbumId( GetPostAction.getIntegerParameter( request, "albumId" ) );
    setSubjectId( GetPostAction.getIntegerParameter( request, "subjectId" ) );
    setPhotoId( GetPostAction.getIntegerParameter( request, "photoId" ) );
    setSubjectInPhotoId( GetPostAction.getIntegerParameter( request, "subjectInPhotoId" ) );
    setSize( request.getParameter( "size" ) );
    setSubjectTypeId( GetPostAction.getIntegerParameter( request, "subjectTypeId" ) );
  }

  // -------------------- [[Methods]] --------------------


  public FamilyAlbum getFamilyAlbum()
  {
    return FamilyAlbum.instance();
  }

  public String getBaseUrl()
  {
    return FamilyAlbum.instance().getBaseUrl();
  }

  public Integer getFamilyId()
  {
    return _familyId;
  }

  public void setFamilyId( Integer value )
  {
    _familyId = value;

    if ( (value != null) && (value.intValue() == 0) ) {
      _family = new Family();
    } else {
      // will be evaluated lazily
      _family = null;
    }
  }

  public Integer getSubjectId()
  {
    return _subjectId;
  }

  public void setSubjectId( Integer value )
  {
    _subjectId = value;
    _subject = null;
  }

  public Integer getSubjectTypeId()
  {
    return _subjectTypeId;
  }

  public SubjectType getSubjectType()
  {
    return _subjectType;
  }

  public void setSubjectTypeId( Integer value )
  {
    _subjectTypeId = value;
    _subjectType = SubjectType.get( _subjectTypeId );
    if ( _subjectType == null ) {
      _subjectType = SubjectType.FAMILY_MEMBER;
    }
  }


  public Integer getAlbumId()
  {
    return _albumId;
  }

  public void setAlbumId( Integer value )
  {
    _albumId = value;
  }



  public Integer getPhotoId()
  {
    return _photoId;
  }

  public void setPhotoId( Integer value )
  {
    _photoId = value;
    _photo = null;
  }


  public Integer getSubjectInPhotoId()
  {
    return _subjectInPhotoId;
  }

  public void setSubjectInPhotoId( Integer value )
  {
    _subjectInPhotoId = value;
  }


  public Family getFamily()
  {
    if ( _family == null ) {

      if ( _familyId == null ) {
        // System.out.println( "Using default" );
        _family = getFamily( DEFAULT_FAMILY_ID );

      } else {
        // System.out.println( "Using id " + _familyId );
        _family = getFamily( _familyId );
        if ( _family == null ) {
          _family = getFamily( DEFAULT_FAMILY_ID );
        }
      }
    }

    return _family;
  }

  private Family getFamily( Integer familyId )
  {
    try {
      return (Family) HibernateUtil.currentSession().load( Family.class, familyId );
    } catch (Exception e) {
      // System.out.println( "Failed to find family : " + familyId + " Error : " + e );
      return null;
    }
  }

  public Subject getSubject()
  {
    if ( _subject == null ) {
      // System.out.println( "Finding subject : " + getSubjectId() );

      try {
        if ( getSubjectId() == null ) {
          _subject = new Subject();
        } else {
          _subject = (Subject) HibernateUtil.currentSession().load( Subject.class, getSubjectId() );
        }

      } catch (Exception e) {
        // System.out.println( "Failed to get subject : " + getSubjectId() );
        return null;
      }

    }

    return _subject;
  }


  public Album getAlbum()
  {
    if ( _album == null ) {
      // System.out.println( "Finding album : " + getAlbumId() );

      try {
        if ( getAlbumId() == null ) {
          _album = new Album();
          if ( getPhotoId() != null ) {
            if ( getPhoto() != null ) {
              _album = getPhoto().getAlbum();
            }
          }
        } else {
          _album = (Album) HibernateUtil.currentSession().load( Album.class, getAlbumId() );
        }
        // System.out.println( "Found : " + _album.getLabel() );

      } catch (Exception e) {
        // System.out.println( "Failed to get album : " + getAlbumId() );
        return null;
      }
    }

    return _album;
  }


  private Integer guessPhotoId()
  {
    if ( _photoId == null ) {

      if ( getSubjectInPhoto().getPhoto() == null ) {
        // System.out.println( "Failed to guess p" );
        return null;
      } else {
        // System.out.println( "guessed p " + getSubjectInPhoto().getPhoto().getPhotoId() );
        return getSubjectInPhoto().getPhoto().getPhotoId();
      }

    } else {
      return _photoId;
    }

  }


  public Photo getPhoto()
  {
    if ( _photo == null ) {
      // System.out.println( "No photo yet" );

      try {
        if ( guessPhotoId() == null ) {
          // System.out.println( "No pohot" );
          _photo = new Photo();
        } else {
          // System.out.println( "Got photo" );
          _photo = (Photo) HibernateUtil.currentSession().load( Photo.class, guessPhotoId() );
        }

      } catch (Exception e) {
        // System.out.println( "Failed to get photo : " + guessPhotoId() );
        return null;
      }

    }
    return _photo;

  }

  private Integer guessSubjectInPhotoId()
  {
    if ( _subjectInPhotoId == null ) {
      if ( getSubject().getDefaultSubjectInPhoto() == null ) {
        // System.out.println( "Failed to guess sip" );
        return null;
      } else {
        // System.out.println("Guessed sip : " + getSubject().getDefaultSubjectInPhoto().getSubjectInPhotoId() );
        return getSubject().getDefaultSubjectInPhoto().getSubjectInPhotoId();
      }

    } else {
      // System.out.println( "Sent sip id" );
      return _subjectInPhotoId;
    }
  }

  public SubjectInPhoto getSubjectInPhoto()
  {
    if ( _subjectInPhoto == null ) {

      Integer subjectInPhotoId = null;
      try {

        subjectInPhotoId = guessSubjectInPhotoId();

        if ( subjectInPhotoId == null ) {
          _subjectInPhoto = new SubjectInPhoto();
        } else {

          try {
            _subjectInPhoto = (SubjectInPhoto)
              HibernateUtil.currentSession().get( SubjectInPhoto.class, subjectInPhotoId );
          } catch (Exception e) {
            //System.out.println( "inner try failed" );
          }

          // if not found, then try to use the default one from subject
          if ( _subjectInPhoto == null ) {
            _subjectInPhoto = getSubject().getDefaultSubjectInPhoto();
          }

        }

      } catch (Exception e) {
        // System.out.println( "Failed to get subjectInPhoto" );
        return null;
      }

    }

    // System.out.println( "Returning sip  : " + _subjectInPhoto );
    return _subjectInPhoto;
  }




  public List getSubjects()
  {
    Integer familyId = getFamily().getFamilyId();

    if ( getSubjectTypeId() == null ) {

      List result = HibernateUtil.currentSession()
        .createQuery( "from Subject where familyId=:familyId order by name2, name1" )
        .setParameter( "familyId", familyId )
        .list();
      return result;

    } else {

      List result = HibernateUtil.currentSession()
        .createQuery( "from Subject where familyId=:familyId and subjectTypeId=:subjectTypeId order by name2, name1" )
        .setParameter( "familyId", familyId )
        .setParameter( "subjectTypeId", getSubjectType().getSubjectTypeId() )
        .list();

      return result;

    }
  }


  public List getAlbums()
  {
    List result = HibernateUtil.currentSession()
      .createQuery( "from Album where familyId=:familyId order by label" )
      .setParameter( "familyId", getFamily().getFamilyId() )
      .list();

    return result;
  }

  public List getFamilies()
  {
    List result = HibernateUtil.currentSession()
      .createQuery( "from Family order by label" )
      .list();

    return result;
  }

  public List getRelationshipTypes()
  {
    List result = HibernateUtil.currentSession().createQuery( "from RelationshipType" ).list();

    return result;
  }

  public String getSize()
  {
    return _size;
  }

  public void setSize( String value )
  {
    _size = value;
  }


  public String getPhotoUrl()
  {
    // System.out.println( "Size :  " + getSize() );

    if ( SIZE_NORMAL.equals( getSize() ) ) {
      return getPhoto().getFullUrl();
    } else {
      if ( getPhoto().getQuickFile().exists() ) {
        return getPhoto().getQuickUrl();
      } else {
        return getPhoto().getFullUrl();
      }
    }
  }

  public Comparator getSipAgeComparator()
  {
    // System.out.println( "Requested sipagecomparator" );
    return new SipAgeComparator();
  }


  // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class FamilyAlbum ----------

