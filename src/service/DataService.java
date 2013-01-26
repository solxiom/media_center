/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.imdb.domain.TitleSearchOptions;
import service.imdb.domain.IdSearchOptions;
import service.imdb.domain.ImdbDataObject;

/**
 *
 * @author kavan
 */
public interface DataService {

   public ImdbDataObject getDataById(String serverURL, IdSearchOptions options);

   public ImdbDataObject getDataByTitle(String serverURL, TitleSearchOptions options);
    
}
