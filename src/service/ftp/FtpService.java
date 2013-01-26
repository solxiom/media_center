/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.ftp;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import service.HostService;
import service.filesystem.MediaFile;
import service.filesystem.TypeX;
import service.ftp.domain.FTPMediaFile;

/**
 *
 * @author kavan
 */
public class FtpService implements HostService {

    private FTPFileManager manager;

    public FtpService(FTPFileManager manager) {
        this.manager = manager;
    }

    public List<MediaFile> listMovies() {
        List<MediaFile> movies = new LinkedList<MediaFile>();
        FTPMediaFile[] fileSystem = manager.listCategories();
        for (FTPMediaFile fold : fileSystem) {
            if (fold.getName().equalsIgnoreCase("movies") && fold.getFTPFile().isDirectory()) {
                fileSystem = fold.listFTPMediaFiles();
                break;
            }
        }
        movies.addAll(Arrays.asList(fileSystem)); 
        
        return removeNoneVideoFiles(movies);
    }

    public List<MediaFile> listTvShows() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<MediaFile> listDocumentaries() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<MediaFile> listPersianItems() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private List<MediaFile> removeNoneVideoFiles(List<MediaFile> list){
        List<MediaFile> list2 = new LinkedList<MediaFile>();
        list2.addAll(list);
        for(MediaFile f:list){
            if(!(f.isMediaType(TypeX.VIDEO)|| f.isMediaType(TypeX.MEDIA_DIR) ) ){
                list2.remove(f);
            }
        }
        return list2;
    }
}
