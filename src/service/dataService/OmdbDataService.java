/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import service.DataService;
import service.domain.IdSearchOptions;
import service.domain.DataObject;
import service.domain.OmdbDataObject;
import service.domain.TitleSearchOptions;

/**
 *
 * @author kavan
 */
public class OmdbDataService implements DataService<OmdbDataObject>{

    public DataObject getDataById(String serverURL, IdSearchOptions options) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DataObject getDataByTitle(String serverURL, TitleSearchOptions options) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public OmdbDataObject getServerData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
