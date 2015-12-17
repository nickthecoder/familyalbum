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
import java.util.Map;
import java.util.TreeMap;

public class FamilyAlbum
{

  /**
    Singleton design pattern.
    <br><br>
    See Gamma, Helm, Johnson, Vlissides. Design Patterns,
    pages 127-134. Addison-Wesley
  */
  private static FamilyAlbum _instance;

  public static Map _yearAccuracyLabels;


  static {
    _yearAccuracyLabels = new TreeMap();

    _yearAccuracyLabels.put( new Integer( 0 ), "Exact" );
    _yearAccuracyLabels.put( new Integer( 1 ), "Within a year or two" );
    _yearAccuracyLabels.put( new Integer( 10 ), "Within 10 Years" );
    _yearAccuracyLabels.put( new Integer( 100 ), "Complete Guess" );

  }

  private File _baseDirectory;

  private String _baseUrl;


  /**
    Singleton design pattern.
    <br><br>
    See Gamma, Helm, Johnson, Vlissides. Design Patterns,
    pages 127-134. Addison-Wesley
  */
  public static FamilyAlbum instance()
  {
    if ( _instance == null ) {
      _instance = new FamilyAlbum();
    }

    return _instance;
  }


  public Map getYearAccuracyLabels()
  {
    return _yearAccuracyLabels;
  }

  /**
    Constructor must be protected to ensure that the singleton pattern
    is not broken.
  */
  protected FamilyAlbum()
  {
  }

  public File getBaseDirectory()
  {
    return _baseDirectory;
  }

  public void setBaseDirectory( File value )
  {
    _baseDirectory = value;
  }


  public String getBaseUrl()
  {
    return _baseUrl;
  }

  public void setBaseUrl( String value )
  {
    _baseUrl = value;
  }


  public File getFile( String modelsPath )
  {
    return new File( _baseDirectory, modelsPath );
  }

  public String getURL( String modelsPath )
  {
    throw new RuntimeException( "Not coded yet" );
  }

}
