/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import service.DataEngine;
import service.domain.DataObject;
import service.filesystem.MediaFile;

/**
 *
 * @author kavan
 */
public class MultiServerDataEngine implements DataEngine {

    public MultiServerDataEngine() {
    
    }
    
    
    public DataObject findMovie(MediaFile file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DataObject findTvSeries(MediaFile file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DataObject findDocumentary(MediaFile file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DataObject findPersianItem(MediaFile file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
