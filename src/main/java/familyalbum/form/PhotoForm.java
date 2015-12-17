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

package familyalbum.form;

import org.apache.struts.action.ActionForm;

import familyalbum.model.Photo;

/**
*/

public class PhotoForm extends ActionForm
{
    private static final long serialVersionUID = 1L;

    private Integer _photoId;

    private Integer _albumId;

    private String _notes;

    private Integer _year;

    private int _yearAccuracy;

    public PhotoForm()
    {
        super();
    }

    public void initialise(Photo photo)
    {
        _photoId = photo.getPhotoId();
        _albumId = photo.getAlbum().getAlbumId();
        _notes = photo.getNotes();
        _year = photo.getYear();
        _yearAccuracy = photo.getYearAccuracy();
    }

    // -------------------- [[Methods]] --------------------

    public Integer getPhotoId()
    {
        return _photoId;
    }

    public void setPhotoId(Integer value)
    {
        _photoId = value;
    }

    public Integer getAlbumId()
    {
        return _albumId;
    }

    public void setAlbumId(Integer value)
    {
        _albumId = value;
    }

    public Integer getYear()
    {
        return _year;
    }

    public void setYear(Integer value)
    {
        _year = value;
    }

    public int getYearAccuracy()
    {
        return _yearAccuracy;
    }

    public void setYearAccuracy(int value)
    {
        _yearAccuracy = value;
    }

    public String getNotes()
    {
        return _notes;
    }

    public void setNotes(String value)
    {
        _notes = value;
    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class PhotoForm ----------

