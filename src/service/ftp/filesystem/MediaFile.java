/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.ftp.filesystem;

/**
 *
 * @author kavan
 */
public interface MediaFile {

    String getAbsoluteFilePath();

    String getFilePath();

    String getFormat();

    String getFixedName();

    String getMediaName();

    String getName();
    
    TypeX getMediaType();

    FTPMediaFile getParentFolder();

    QualityX getQuality();


    boolean isDirContainsType(TypeX contentType);
    boolean isDirectory();
    boolean isDirEmpty();
    boolean isMediaType(TypeX t);

    MediaFile[] listMediaFiles();

}
