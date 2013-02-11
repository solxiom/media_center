/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import service.DataConverter;
import service.DataService;
import service.JsonSearcher;
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
    private JsonSearcher searcher;

    public OmdbDataService(JsonServer<OmdbDataObject> server, DataConverter converter, JsonSearcher searcher) {
        this.server = server;
        this.converter = converter;
        this.searcher = searcher;
    }

    public DataObject getDataById(String serverURL, IdSearchOptions options) {
        return converter.convert(getServerObject(serverURL, options.getOmdbParameters()));
    }

    public DataObject getDataByTitle(String serverURL, TitleSearchOptions options) {
        OmdbDataObject obj = getServerObject(serverURL, options.getOmdbParameters());
        String item_id = "not_found";
        IdSearchOptions id_options;
        if (obj == null || !obj.getTitle().equalsIgnoreCase(options.getTitle())) {
            item_id = searcher.findItemId(options.getTitle(), options.getYear());
            if (!item_id.equalsIgnoreCase("not_found")) {
                id_options = new IdSearchOptions(item_id);
                return getDataById(serverURL, id_options);
            }

        }

        return converter.convert(obj);
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
        jsonstr = server.requestToServer(requestString);
        try {

            server_data = server.jsonToServerObject(jsonstr);

            return server_data;
        } catch (Exception e) {
            System.out.println("error" + e);
            return null;
        }
    }
}
