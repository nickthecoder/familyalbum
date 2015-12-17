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
import java.util.Iterator;
import java.util.Set;

import familyalbum.util.JpegHaloImageMaker;
import familyalbum.util.PngHaloImageMaker;

public class SubjectInPhoto
{
    private Integer _subjectInPhotoId;

    private Integer _subjectId;

    private Integer _photoId;

    private int _x;

    private int _y;

    private int _radius;

    private String _notes;

    private boolean _useHalo;

    private Photo _photo;

    private Subject _subject;

    public SubjectInPhoto()
    {
    }

    public SubjectInPhoto(Subject subject, Photo photo)
    {
        _subject = subject;
        _photo = photo;

        _photo.getSubjectsInPhoto().add(this);
        _subject.getSubjectInPhotos().add(this);

        _subjectId = subject.getSubjectId();
        _photoId = photo.getPhotoId();
    }

    public Integer getSubjectInPhotoId()
    {
        return _subjectInPhotoId;
    }

    public void setSubjectInPhotoId(Integer value)
    {
        _subjectInPhotoId = value;
    }

    public Integer getSubjectId()
    {
        return _subjectId;
    }

    public void setSubjectId(Integer value)
    {
        _subjectId = value;
    }

    public Integer getPhotoId()
    {
        return _photoId;
    }

    public void setPhotoId(Integer value)
    {
        _photoId = value;
    }

    public Photo getPhoto()
    {
        return _photo;
    }

    public void setPhoto(Photo value)
    {
        _photo = value;
    }

    public void setSubject(Subject value)
    {
        _subject = value;
    }

    public Subject getSubject()
    {
        return _subject;
    }

    public int getX()
    {
        return _x;
    }

    public void setX(int value)
    {
        _x = value;
    }

    public int getY()
    {
        return _y;
    }

    public void setY(int value)
    {
        _y = value;
    }

    public int getRadius()
    {
        return _radius;
    }

    public void setRadius(int value)
    {
        _radius = value;
    }

    public String getNotes()
    {
        return _notes;
    }

    public void setNotes(String value)
    {
        _notes = value;
    }

    public void setUseHalo(boolean value)
    {
        _useHalo = value;
    }

    public boolean getUseHalo()
    {
        return _useHalo;
    }

    public String getHaloUrl()
    {
        String filename = getHaloFilename();
        if (filename == null) {
            return null;
        } else {
            return Photo.makeUrlFromFilename(filename);
        }
    }

    private String getHaloFilename()
    {
        if (getPhoto() == null) {
            return null;
        }

        File file = new File(getPhoto().getFullFilename());
        String dir = file.getParent() + File.separator + "subjects" + File.separator;

        return dir + file.getName().replaceFirst("\\.jpg", "-subject" + getSubject().getSubjectId() + ".jpg");
    }

    public File getHaloFile()
    {
        return FamilyAlbum.instance().getFile(getHaloFilename());
    }

    public String getHaloThumbnailUrl()
    {
        String filename = getHaloThumbnailFilename();
        if (filename == null) {
            return null;
        } else {
            return Photo.makeUrlFromFilename(filename);
        }
    }

    private String getHaloThumbnailFilename()
    {
        if (getPhoto() == null) {
            return null;
        }

        File file = new File(getPhoto().getFullFilename());
        String dir = file.getParent() + "/subjects/.thumbnails/";

        return dir + file.getName().replaceFirst("\\.jpg", "-subject" + getSubject().getSubjectId() + ".png");
    }

    public File getHaloThumbnailFile()
    {
        return FamilyAlbum.instance().getFile(getHaloThumbnailFilename());
    }

    public void deleteImages()
    {
        getHaloFile().delete();
        getHaloThumbnailFile().delete();
    }

    public void updateImages()
    {
        getImagesUpToDate();
    }

    public boolean getImagesUpToDate()
    {
        boolean result = true;

        File source = getPhoto().getFile();
        File halo = getHaloFile();
        File haloThumbnail = getHaloThumbnailFile();

        if (source.lastModified() > halo.lastModified()) {
            JpegHaloImageMaker maker = new JpegHaloImageMaker(source.getPath(), halo.getPath(), getX(), getY(),
                            getRadius());
            Photo.getImageJobQueue().add(maker);
            result = false;
        }

        if (source.lastModified() > haloThumbnail.lastModified()) {
            PngHaloImageMaker maker = new PngHaloImageMaker(source.getPath(), haloThumbnail.getPath(), getX(), getY(),
                            getRadius());
            Photo.getImageJobQueue().add(maker);
            result = false;
        }

        return result;
    }

    public SubjectInPhoto getNextPhotoBySubject()
    {
        Set subjectInPhotos = getSubject().getSubjectInPhotos();

        for (Iterator i = subjectInPhotos.iterator(); i.hasNext();) {
            SubjectInPhoto subjectInPhoto = (SubjectInPhoto) i.next();
            // System.out.println( "Comparing : " +
            // subjectInPhoto.getSubjectInPhotoId() + " vs " +
            // getSubjectInPhotoId() );
            if (subjectInPhoto.getSubjectInPhotoId().equals(getSubjectInPhotoId())) {
                // System.out.println( "Matched" );
                if (i.hasNext()) {
                    return ((SubjectInPhoto) i.next());
                }
            }
        }

        // If all else fails, return the same photo.
        return this;
    }

    public SubjectInPhoto getPreviousPhotoBySubject()
    {
        Set subjectInPhotos = getSubject().getSubjectInPhotos();
        SubjectInPhoto prevSubjectInPhoto = null;

        for (Iterator i = subjectInPhotos.iterator(); i.hasNext();) {
            SubjectInPhoto subjectInPhoto = (SubjectInPhoto) i.next();
            // System.out.println( "Comparing : " +
            // subjectInPhoto.getSubjectInPhotoId() + " vs " +
            // getSubjectInPhotoId() );
            if (subjectInPhoto.getSubjectInPhotoId().equals(getSubjectInPhotoId())) {
                // System.out.println( "Matched" );
                if (prevSubjectInPhoto != null) {
                    return prevSubjectInPhoto;
                } else {
                    return this;
                }
            }
            prevSubjectInPhoto = subjectInPhoto;
        }

        // If all else fails, return the same photo
        return this;
    }

}
