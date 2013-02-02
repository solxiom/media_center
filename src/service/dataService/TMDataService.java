/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import service.DataConverter;
import service.DataService;
import service.JsonServer;
import service.domain.IdSearchOptions;
import service.domain.DataObject;
import service.domain.ImdbDataObject;
import service.domain.TitleSearchOptions;
import service.domain.tomatoes.TMDataObject;

/**
 *
 * @author kavan
 */
public class TMDataService implements DataService<TMDataObject> {

    /**
     * rotten tomatoes data service service api: http://api.rottentomatoes.com/
     *
     */
    final String[] apiKeys;
    private JsonServer<TMDataObject> server;
    private DataConverter converter;
    private TMDataObject server_data;

    public TMDataService(String[] apiKeys, JsonServer<TMDataObject> server, DataConverter converter) {
        this.apiKeys = apiKeys;
        this.server = server;
        this.converter = converter;
        this.server_data = null;
    }

    public DataObject getDataById(String serverURL, IdSearchOptions options) {

        String parameters = options.getTomatoesParameters(getApiKey());

        return getData(serverURL, parameters);
    }

    public DataObject getDataByTitle(String serverURL, TitleSearchOptions options) {
        //needs adouble search for detailed info
        String parameters = options.getTomatoesParameters(getApiKey());
        TMDataObject d = getServerData(serverURL, parameters);
        if (d == null) {
            return null;
        }
        IdSearchOptions o = new IdSearchOptions(d.getId());
        DataObject dob = getDataById(serverURL, o);
       
        return dob;
    }

    public TMDataObject getServerData() {
        return this.server_data;
    }

    private String getApiKey() {
        int index = 0;
        index = (int) (Math.random() * (apiKeys.length - 1));

        return apiKeys[index];
    }

    private DataObject getData(String serverURL, String options) {

//        try {
        return converter.convert(getServerData(serverURL, options));
//        } catch (Exception e) {
//            System.out.println("Hello.... here: " + e);
//            return null;
//        }
    }

    private TMDataObject getServerData(String serverURL, String options) {
        String jsonstr = "";
        DataObject dataObject;
        String requestString = serverURL + options;
        jsonstr = server.requestToServer(requestString);

        try {
            server_data = server.jsonToServerObject(jsonstr);
            return server_data;
        } catch (Exception e) {

            return null;
        }
    }
}
