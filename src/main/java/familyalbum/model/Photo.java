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

package familyalbum.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import familyalbum.util.ImageInfo;
import familyalbum.util.JobQueue;
import familyalbum.util.ThumbnailMaker;

/**
*/

public class Photo
{

    // -------------------- [[Static Attributes]] --------------------

    public static final int QUICK_WIDTH = 640;

    public static final int QUICK_HEIGHT = 480;

    public static JobQueue _imageJobQueue = new JobQueue();

    // -------------------- [[Attributes]] --------------------

    private Integer _photoId;

    // private int _albumId;

    private String _label;

    private String _filename;

    private Integer _year;

    private int _yearAccuracy;

    private String _notes;

    private Album _album;

    private Set _subjectsInPhoto = new HashSet();

    private int _width;

    private int _height;

    // -------------------- [[Static Methods]] --------------------

    public static JobQueue getImageJobQueue()
    {
        return _imageJobQueue;
    }

    public static String makeUrlFromFilename(String filename)
    {
        return FamilyAlbum.instance().getBaseUrl() + "/" + filename.replaceAll(" ", "%20");
    }

    // -------------------- [[Constructors]] --------------------

    /**
  */
    public Photo()
    {
        super();
    }

    // -------------------- [[Methods]] --------------------

    public Album getAlbum()
    {
        return _album;
    }

    public void setAlbum(Album value)
    {
        _album = value;
    }

    public Integer getPhotoId()
    {
        return _photoId;
    }

    private void setPhotoId(Integer value)
    {
        _photoId = value;
    }

    public String getFilename()
    {
        return _filename;
    }

    public void setFilename(String value)
    {
        _filename = value;
    }

    String getFullFilename()
    {
        return getAlbum().getDirectoryName() + File.separator + getFilename();
    }

    public String getFullUrl()
    {
        return makeUrlFromFilename(getFullFilename());
    }

    public File getFile()
    {
        return FamilyAlbum.instance().getFile(getFullFilename());
    }

    public String getThumbnailUrl()
    {

        return makeUrlFromFilename(getThumbnailFilename());
    }

    private String getThumbnailFilename()
    {
        String filename = getFullFilename();
        int index = filename.lastIndexOf(File.separator);

        if (index < 0) {
            return null;
        }

        return filename.substring(0, index) + File.separator + ".thumbnails" + filename.substring(index);
    }

    public File getThumbnailFile()
    {
        return FamilyAlbum.instance().getFile(getThumbnailFilename());
    }

    private String getQuickFilename()
    {
        String filename = getFullFilename();
        int index = filename.lastIndexOf(File.separator);

        if (index < 0) {
            return null;
        }

        return filename.substring(0, index) + "/.quick" + filename.substring(index);
    }

    public String getQuickUrl()
    {
        return makeUrlFromFilename(getQuickFilename());
    }

    public File getQuickFile()
    {
        return FamilyAlbum.instance().getFile(getQuickFilename());
    }

    public void setLabel(String label)
    {
        _label = label;
    }

    public String getLabel()
    {
        return _label;
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

    public int getWidth()
    {
        return _width;
    }

    public void setWidth(int value)
    {
        _width = value;
    }

    public int getHeight()
    {
        return _height;
    }

    public void setHeight(int value)
    {
        _height = value;
    }

    public void updateImages()
    {
        getImagesUpToDate();
    }

    public boolean getImagesUpToDate()
    {
        boolean result = true;

        File full = getFile();
        File thumbnail = getThumbnailFile();
        File quick = getQuickFile();

        if (full.lastModified() > thumbnail.lastModified()) {
            ThumbnailMaker maker = new ThumbnailMaker(full.getPath(), thumbnail.getPath());
            getImageJobQueue().add(maker);
            result = false;
        }

        if (full.lastModified() > quick.lastModified()) {
            ThumbnailMaker maker = new ThumbnailMaker(full.getPath(), quick.getPath(), QUICK_WIDTH, QUICK_HEIGHT);
            getImageJobQueue().add(maker);
            result = false;
        }

        for (Iterator i = getSubjectsInPhoto().iterator(); i.hasNext();) {
            SubjectInPhoto sip = (SubjectInPhoto) i.next();
            result = result && sip.getImagesUpToDate();
        }

        return result;
    }

    public Set getSubjectsInPhoto()
    {
        return _subjectsInPhoto;
    }

    public void setSubjectsInPhoto(Set value)
    {
        _subjectsInPhoto = value;
    }

    public int getSubjectCount()
    {
        return getSubjectsInPhoto().size();
    }

    public Photo getNextPhotoInAlbum()
    {
        Set photos = getAlbum().getPhotos();

        for (Iterator i = photos.iterator(); i.hasNext();) {
            Photo photoInList = (Photo) i.next();
            if (photoInList.getPhotoId() == this.getPhotoId()) {
                if (i.hasNext()) {
                    return (Photo) i.next();
                }
            }
        }

        // If all else fails, return the same photo.
        return this;
    }

    public Photo getPreviousPhotoInAlbum()
    {
        Set photos = getAlbum().getPhotos();
        Photo prevPhoto = null;

        for (Iterator i = photos.iterator(); i.hasNext();) {
            Photo photoInList = (Photo) i.next();
            if (photoInList.getPhotoId() == this.getPhotoId()) {
                if (prevPhoto != null) {
                    return prevPhoto;
                }
            }
            prevPhoto = photoInList;
        }

        // If all else fails, return the same photo
        return this;
    }

    public void findSize()
    {
        File file = getFile();

        ImageInfo imageInfo = new ImageInfo();
        InputStream in = null;

        try {
            in = new FileInputStream(file);
            imageInfo.setInput(in);

            if (imageInfo.check()) {
                setHeight(imageInfo.getHeight());
                setWidth(imageInfo.getWidth());
                // System.out.println( "Done" );
            } else {
                // System.out.println( "Check failed" );
            }

        } catch (Exception e) {
            // System.out.println( "Failed to find size : " + e );
            // No nothing

        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class Photo ----------

