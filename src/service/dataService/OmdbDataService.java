/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import service.DataConverter;
import service.DataService;
import service.JsonServer;
import service.Tools;
import service.domain.IdSearchOptions;
import service.domain.DataObject;
import service.domain.ImdbDataObject;
import service.domain.OmdbDataObject;
import service.domain.TitleSearchOptions;
import service.domain.tomatoes.TMDataObject;

/**
 *
 * @author kavan
 */
public class OmdbDataService implements DataService<OmdbDataObject> {

    /**
     * OMDB API data service service api: http://omdbapi.com/
     *
     */
    private JsonServer<OmdbDataObject> server;
    private DataConverter converter;
    private OmdbDataObject server_data;

    public OmdbDataService(JsonServer<OmdbDataObject> server, DataConverter converter) {
        this.server = server;
        this.converter = converter;
    }

    public DataObject getDataById(String serverURL, IdSearchOptions options) {
        return converter.convert(getServerObject(serverURL, options.getOmdbParameters()));
    }

    public DataObject getDataByTitle(String serverURL, TitleSearchOptions options) {
        return converter.convert(getServerObject(serverURL, options.getOmdbParameters()));
    }

    public OmdbDataObject getServerData() {
        if (server_data != null) {
            return server_data;
        }
        return null;
    }

    private OmdbDataObject getServerObject(String serverURL, String options) {
        String jsonstr = "";
        DataObject dataObject;
        String requestString = Tools.bindUrlwithParameters(serverURL, options, "/");//serverURL + "/" + options;
        System.out.println(""+requestString);
        jsonstr = server.requestToServer(requestString);
        System.out.println("str: "+ jsonstr);
        try {
            
            server_data = server.jsonToServerObject(jsonstr);
            System.out.println("object: "+server_data);
            return server_data;
        } catch (Exception e) {
            System.out.println("error"+ e);
            return null;
        }
    }
}
