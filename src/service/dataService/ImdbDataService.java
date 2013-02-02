/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import service.JsonServer;
import service.DataService;
import service.domain.DataObject;
import service.domain.IdSearchOptions;
import service.domain.TitleSearchOptions;
import service.DataConverter;
import service.domain.ImdbDataObject;

/**
 *
 * @author kavan
 */
public class ImdbDataService implements DataService<ImdbDataObject> {

    /**
     * IMDB API data service service api: http://imdbapi.org/
     *
     */
//   
    private JsonServer<ImdbDataObject> server;
    private DataConverter converter;
    private ImdbDataObject server_data;

    public ImdbDataService(JsonServer<ImdbDataObject> server, DataConverter converter) {
        this.server = server;
        this.converter = converter;
    }

    @Override
    public DataObject getDataByTitle(String serverURL, TitleSearchOptions options) {
        return getData(serverURL, options.getImdbParameters());
    }

    @Override
    public DataObject getDataById(String serverURL, IdSearchOptions options) {
        return getData(serverURL, options.getImdbParameters());
    }

    public ImdbDataObject getServerData() {
        return this.getServerData();
    }

    private DataObject getData(String serverURL, String options) {

        try {

            return converter.convert(this.getServerData(serverURL, options));
        } catch (Exception e) {

            return null;
        }
    }

    private ImdbDataObject getServerData(String serverURL, String options) {
        String jsonstr = "";
        DataObject dataObject;
        String requestString = serverURL + "/" + options;
        
        if (!serverURL.endsWith("/") && !options.startsWith("/")) {
            requestString = serverURL + "/" + options;
        } else if ((serverURL.endsWith("/") && !options.endsWith("/"))
                || (!serverURL.endsWith("/") && options.endsWith("/"))) {
            requestString = serverURL + options;
        }
        else if(serverURL.endsWith("/") && options.endsWith("/")){
            requestString = serverURL + options.substring(1);
        }

        jsonstr = server.requestToServer(requestString);
        try {
            server_data = server.jsonToServerObject(jsonstr);
            return server_data;
        } catch (Exception e) {

            return null;
        }
    }
}
