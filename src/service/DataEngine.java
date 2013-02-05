/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.domain.DataObject;
import service.filesystem.MediaFile;

/**
 *
 * @author kavan
 */
public interface DataEngine {

    public DataObject findMovie(MediaFile file);

    public DataObject findTvSeries(MediaFile file);

    public DataObject findDocumentary(MediaFile file);

    public DataObject findPersianItem(MediaFile file);
}
