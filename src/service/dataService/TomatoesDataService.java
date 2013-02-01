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
public class TomatoesDataService implements DataService<TMDataObject> {

    final String[] apiKeys;
    private JsonServer<TMDataObject> server;
    private DataConverter converter;
    private TMDataObject server_data;

    public TomatoesDataService(String[] apiKeys, JsonServer<TMDataObject> server, DataConverter converter) {
        this.apiKeys = apiKeys;
        this.server = server;
        this.converter = converter;
        this.server_data = null;
    }

    public DataObject getDataById(String serverURL, IdSearchOptions options) {

        String parameters = getIdParameters(options);
        
        return getData(serverURL, parameters);
    }

    public DataObject getDataByTitle(String serverURL, TitleSearchOptions options) {
        String parameters = getTitleParameters(options);
        return getData(serverURL, parameters);
    }

    public TMDataObject getServerData() {
        return this.server_data;
    }

    

    private String getIdParameters(IdSearchOptions options){
        String parameters = options.getId() + ".json?";
        return parameters;
    }
    private String getTitleParameters(TitleSearchOptions options){
        String parameters = "movies.json" + options.toString();
        return parameters;
    }
    private String getApiKey() {
        int index = 0;
        index = (int) (Math.random() * (apiKeys.length - 1));

        return apiKeys[index];
    }

    private DataObject getData(String serverURL, String options) {

        try {
            return converter.convert(getServerData(serverURL, options));
        } catch (Exception e) {

            return null;
        }
    }

    private TMDataObject getServerData(String serverURL, String options) {
        String jsonstr = "";
        DataObject dataObject;
        String requestString = serverURL + options + "&apikey=" + getApiKey();
        jsonstr = server.requestToServer(requestString);
        try {
            server_data = server.jsonToServerObject(jsonstr);
            return server_data;
        } catch (Exception e) {

            return null;
        }
    }
}
