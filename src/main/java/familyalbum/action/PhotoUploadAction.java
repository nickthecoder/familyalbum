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

package familyalbum.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;

import uk.co.nickthecoder.webwidgets.util.MultipartHelper;
import uk.co.nickthecoder.webwidgets.util.MultipartHelper.FileInfo;
import uk.co.nickthecoder.webwidgets.util.MultipartServletRequestWrapper;
import familyalbum.model.Album;
import familyalbum.model.Photo;
import familyalbum.util.HibernateUtil;


public class PhotoUploadAction extends PhotoAction
{

    protected static final DecimalFormat ID_FORMAT = new DecimalFormat("000000");

    private static final File DOWNLOAD_DIR = new File("/tmp/FamilyAlbmum-PhotoUpload");

    public static final int MAX_FILES = 10;

    /**
     * The maximum number of bytes that can be uploaded in one go.
     */
    public static int MAX_CONTENT_LENGTH = 10000000; // About 10 MB

    public PhotoUploadAction()
    {
        super();
    }

    protected ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest unwrappedRequest,
                    HttpServletResponse response) throws Exception
    {
        MultipartHelper multipartHelper = new MultipartHelper();
        HttpServletRequest request = new MultipartServletRequestWrapper(unwrappedRequest, multipartHelper);

        try {
            multipartHelper.setCharacterEncoding("UTF-8");
            DOWNLOAD_DIR.mkdirs();
            multipartHelper.setOutputDirectory(DOWNLOAD_DIR);
            multipartHelper.setMaximumContentLength(MAX_CONTENT_LENGTH);

            multipartHelper.go(request);

            Session session = HibernateUtil.currentSession();
            Integer albumId = getIntegerParameter(request, "albumId");
            Integer year = getIntegerParameter(request, "year");
            int yearAccuracy = getIntParameter(request, "yearAccuracy", 1);

            System.out.println("albumId " + albumId);
            System.out.println("year " + year);
            System.out.println("yearAccuracy " + yearAccuracy);

            Album album = (Album) session.load(Album.class, albumId);

            for (int i = 0; i < MAX_FILES; i++) {
                String fileId = "upload" + i;
                MultipartHelper.FileInfo fileInfo = multipartHelper.getFile(fileId);

                if (fileInfo != null) {
                    processFile(session, album, fileInfo.getFile(), fileInfo.getFilename(), year, yearAccuracy);
                }
            }

            return parameterRedirect(mapping, "saved", album);

        } finally {

            // Delete all of the temp files created during the upload process.
            for (Iterator<FileInfo> i = multipartHelper.getFiles().values().iterator(); i.hasNext();) {
                MultipartHelper.FileInfo fileInfo = (MultipartHelper.FileInfo) i.next();

                if (fileInfo.getFile() != null) {
                    fileInfo.getFile().delete();
                }
            }

        }

    }

    protected void processFile(Session session, Album album, File file, String label, Integer year, int yearAccuracy)
                    throws Exception
    {
        Transaction tx = session.beginTransaction();

        Photo photo = new Photo();

        photo.setAlbum(album);
        photo.setLabel(label);
        photo.setYear(year);
        photo.setYearAccuracy(yearAccuracy);
        photo.setFilename("uploading");

        session.save(photo);

        photo.setFilename("image" + ID_FORMAT.format(photo.getPhotoId().intValue()) + ".jpg");

        File photoFile = photo.getFile(); // FamilyAlbum.instance().getFile(
                                          // photo.getFilename() );
        rename(file, photoFile);

        photo.findSize();

        session.save(photo);
        tx.commit();

        photo.updateImages();

    }

    // {{{ rename
    /**
     * It seems that java doesn't like renaming files across partitions (i.e. if
     * a rename actually requires a copy and a delete, it just returns false).
     * This method attempts to use java's rename, and if that fails, it does its
     * own copy and delete.
     */
    public static void rename(File source, File dest) throws IOException
    {
        if (!source.renameTo(dest)) {

            FileInputStream from = null;
            FileOutputStream to = null;

            try {
                from = new FileInputStream(source);
                to = new FileOutputStream(dest);

                stream(from, to);

            } finally {
                if (from != null) {
                    try {
                        from.close();
                    } catch (IOException e) {
                        // nothing
                    }
                }

                if (to != null) {
                    try {
                        to.close();
                    } catch (IOException e) {
                        // nothing
                    }
                }
            }

            source.delete();
        }

    }

    // }}}

    // {{{ stream
    public static void stream(InputStream from, OutputStream to) throws IOException
    {

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = from.read(buffer)) != -1) {
            to.write(buffer, 0, bytesRead); // write
        }

        to.flush();
    }
    // }}}

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class PhotoUploadAction ----------

