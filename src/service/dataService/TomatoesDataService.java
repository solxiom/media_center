/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import service.DataService;
import service.domain.IdSearchOptions;
import service.domain.DataObject;
import service.domain.TitleSearchOptions;

/**
 *
 * @author kavan
 */
public class TomatoesDataService implements DataService{

    final String[] apiKeys;
    public TomatoesDataService(String[] apiKeys) {
        this.apiKeys = apiKeys;
    }

    public DataObject getDataById(String serverURL, IdSearchOptions options) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DataObject getDataByTitle(String serverURL, TitleSearchOptions options) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    private String getApiKey(){
        int index = 0;        
         index = (int)(Math.random() * (apiKeys.length -1));
        
        return apiKeys[index];
    }
    
}
