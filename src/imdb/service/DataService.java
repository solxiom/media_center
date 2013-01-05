/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imdb.service;

import imdb.domain.TitleSearchOptions;
import imdb.domain.IdSearchOptions;
import imdb.domain.DataObject;

/**
 *
 * @author kavan
 */
public interface DataService {

   public DataObject getDataById(String serverURL, IdSearchOptions options);

   public DataObject getDataByTitle(String serverURL, TitleSearchOptions options);
    
}
