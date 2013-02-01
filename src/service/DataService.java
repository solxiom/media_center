/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.domain.TitleSearchOptions;
import service.domain.IdSearchOptions;
import service.domain.DataObject;

/**
 *
 * @author kavan
 */
public interface DataService {

   public DataObject getDataById(String serverURL, IdSearchOptions options);

   public DataObject getDataByTitle(String serverURL, TitleSearchOptions options);
    
}
