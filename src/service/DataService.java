/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.imdb.domain.TitleSearchOptions;
import service.imdb.domain.IdSearchOptions;
import service.imdb.domain.DataObject;

/**
 *
 * @author kavan
 */
public interface DataService {

   public DataObject getDataById(String serverURL, IdSearchOptions options);

   public DataObject getDataByTitle(String serverURL, TitleSearchOptions options);
    
}
