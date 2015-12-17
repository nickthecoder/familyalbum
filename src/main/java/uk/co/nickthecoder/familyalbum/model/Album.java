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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Album
{
    public static final String THUMBNAILS_DIR = ".thumbnails";

    public static final String QUICK_DIR = ".quick";

    public static final String SUBJECTS_DIR = "subjects";

    private Integer _albumId;

    private Family _family;

    private String _label;

    private String _directoryName;

    private int _albumPhotoId;

    private String _notes;

    private Set _photos = new HashSet();

    public Album()
    {
        super();
    }

    public Integer getAlbumId()
    {
        return _albumId;
    }

    public void setAlbumId(Integer value)
    {
        _albumId = value;
    }

    public boolean isNew()
    {
        return _albumId == null;
    }

    public void setFamily(Family family)
    {
        _family = family;
    }

    public Family getFamily()
    {
        return _family;
    }

    public String getLabel()
    {
        return _label;
    }

    public void setLabel(String value)
    {
        _label = value;
    }

    public String getDirectoryName()
    {
        return _directoryName;
    }

    public void setDirectoryName(String value)
    {
        _directoryName = value;
    }

    public File getDirectory()
    {
        return FamilyAlbum.instance().getFile(getDirectoryName());
    }

    public int getAlbumPhotoId()
    {
        return _albumPhotoId;
    }

    public void setAlbumPhotoId(int value)
    {
        _albumPhotoId = value;
    }

    public String getNotes()
    {
        return _notes;
    }

    public void setNotes(String value)
    {
        _notes = value;
    }

    public Set getPhotos()
    {
        return _photos;
    }

    public void setPhotos(Set photos)
    {
        _photos = photos;
    }

    public void addPhoto(Photo photo)
    {
        if (photo.getAlbum() != null) {
            throw new IllegalArgumentException("The photo is already in an album");
        }
        photo.setAlbum(this);
        getPhotos().add(photo);
    }

    /**
     * Updates each of the photos thumbnails, and the halos, and the halos
     * thumbnails.
     */
    public void updateImages()
    {
        if (FamilyAlbum.instance().getBaseDirectory() != null) {
            for (Iterator i = getPhotos().iterator(); i.hasNext();) {
                Photo photo = (Photo) i.next();
                photo.updateImages();
            }
        }
    }

    public boolean containsPhoto(String photoName)
    {
        for (Iterator i = getPhotos().iterator(); i.hasNext();) {
            Photo photo = (Photo) i.next();

            if (photo.getFilename().equals(photoName)) {
                return true;
            }
        }

        return false;
    }

    public void createDirectories()
    {
        File directory = FamilyAlbum.instance().getFile(getDirectoryName());
        directory.mkdir();
        File subjectsDir = new File(directory, SUBJECTS_DIR);
        subjectsDir.mkdir();
        (new File(subjectsDir, THUMBNAILS_DIR)).mkdir();
        (new File(directory, THUMBNAILS_DIR)).mkdir();
        (new File(directory, QUICK_DIR)).mkdir();
    }

}

