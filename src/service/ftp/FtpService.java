/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.ftp;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import service.DataService;
import service.HostService;
import service.filesystem.MediaFile;
import service.filesystem.TypeX;
import service.ftp.domain.FTPMediaFile;
import service.domain.DataObject;

/**
 *
 * @author kavan
 */
public class FtpService implements HostService {

    private FTPFileManager fileManager;
   

    public FtpService(FTPFileManager manager) {
        this.fileManager = manager;
    }
    
    

    public List<MediaFile> listMovies() {
        List<MediaFile> movies = new LinkedList<MediaFile>();
        FTPMediaFile[] fileSystem = fileManager.listCategories();
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
        List<MediaFile> tvs = new LinkedList<MediaFile>();
        FTPMediaFile[] fileSystem = fileManager.listCategories();
        for (FTPMediaFile fold : fileSystem) {
            if (fold.getName().equalsIgnoreCase("TvShows") && fold.getFTPFile().isDirectory()) {
                fileSystem = fold.listFTPMediaFiles();
                break;
            }
        }
        tvs.addAll(Arrays.asList(fileSystem)); 
        
        return removeNoneVideoFiles(tvs);
    }

    public List<MediaFile> listDocumentaries() {
        List<MediaFile> docs = new LinkedList<MediaFile>();
        FTPMediaFile[] fileSystem = fileManager.listCategories();
        for (FTPMediaFile fold : fileSystem) {
            if (fold.getName().equalsIgnoreCase("Documentaries") && fold.getFTPFile().isDirectory()) {
                fileSystem = fold.listFTPMediaFiles();
                break;
            }
        }
        docs.addAll(Arrays.asList(fileSystem)); 
        
        return removeNoneVideoFiles(docs);
    }

    public List<MediaFile> listPersianItems() {
        List<MediaFile> peritems = new LinkedList<MediaFile>();
        FTPMediaFile[] fileSystem = fileManager.listCategories();
        for (FTPMediaFile fold : fileSystem) {
            if (fold.getName().equalsIgnoreCase("Movies-Persian") && fold.getFTPFile().isDirectory()) {
                fileSystem = fold.listFTPMediaFiles();
                break;
            }
        }
        peritems.addAll(Arrays.asList(fileSystem)); 
        
        return removeNoneVideoFiles(peritems);
    }
    private List<MediaFile> removeNoneVideoFiles(List<MediaFile> list){
        List<MediaFile> list2 = new LinkedList<MediaFile>();
        list2.addAll(list);
        for(MediaFile f:list){
            if(!(f.isMediaType(TypeX.VIDEO)|| f.isMediaType(TypeX.MEDIA_DIR)
                    || f.isMediaType(TypeX.SEASON_DIR)
                    || f.isMediaType(TypeX.TVS_DIR)) ){
                list2.remove(f);
            }
        }
        return list2;
    }
}
