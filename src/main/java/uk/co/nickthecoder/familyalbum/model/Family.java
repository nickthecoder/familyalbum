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

package uk.co.nickthecoder.familyalbum.model;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class Family
{

  private Integer _familyId;

  private String _label;

  private String _subLabel;

  private String _notes;

  private Set _albums = new HashSet();

  private Set _subjects = new HashSet();

  public Family()
  {
    super();
  }

    public Integer getFamilyId()
  {
    return _familyId;
  }

  public Integer getFamilyIdInt()
  {
    return _familyId == null ? 0 : _familyId.intValue();
  }

  public void setFamilyId( Integer value )
  {
    _familyId = value;
  }

  public boolean isNew()
  {
    return _familyId == null;
  }

  public String getLabel()
  {
    return _label;
  }

  public void setLabel( String value )
  {
    _label = value;
  }

  public String getSubLabel()
  {
    return _subLabel;
  }

  public void setSubLabel( String value )
  {
    _subLabel = value;
  }

  public String getNotes()
  {
    return _notes;
  }

  public void setNotes( String value )
  {
    _notes = value;
  }

  public Set getSubjects()
  {
    return _subjects;
  }

  public void setSubjects( Set subjects )
  {
    _subjects = subjects;
  }


  public Set getAlbums()
  {
    return _albums;
  }

  public void setAlbums( Set albums )
  {
    _albums = albums;
  }

  public void addSubject( Subject subject )
  {
    if ( subject.getFamily() != null ) {
      throw new IllegalArgumentException( "The subject is already in a family" );
    }
    subject.setFamily( this );
    getSubjects().add( subject );
  }

  public void addAlbum( Album album )
  {
    if ( album.getFamily() != null ) {
      throw new IllegalArgumentException( "The album is already in a family" );
    }
    album.setFamily( this );
    getAlbums().add( album );
  }

  public String getDirectoryName()
  {
    DecimalFormat numberFormat = new DecimalFormat( "00000" );
    String familyNumber = numberFormat.format( getFamilyIdInt() );

    return "family" + familyNumber;
  }

  public File getDirectory()
  {
    return FamilyAlbum.instance().getFile( getDirectoryName() );
  }

  public String toString()
  {
    return getFamilyId() + " : " + getLabel();
  }

}

