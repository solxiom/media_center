/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import service.filesystem.MediaFile;
import service.domain.DataObject;

/**
 *
 * @author kavan
 */
public interface HostService {

    public List<MediaFile> listMovies();

    public List<MediaFile> listTvShows();

    public List<MediaFile> listDocumentaries();

    public List<MediaFile> listPersianItems();
}
