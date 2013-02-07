/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import com.google.gson.JsonObject;
import service.JsonServer;
import service.domain.OmdbDataObject;

/**
 *
 * @author kavan
 */
public class OmdbServer extends JsonServer<OmdbDataObject> {

    @Override
    public OmdbDataObject jsonToServerObject(String jsonstr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JsonObject getResultAsJsonObject(String jsonstr) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
