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
    private boolean titId_doubleSearch;

    public TMDataService(String[] apiKeys, JsonServer<TMDataObject> server, DataConverter converter) {
        this(apiKeys, server, converter, false);
    }

    public TMDataService(String[] apiKeys, JsonServer<TMDataObject> server, DataConverter converter, boolean doubleSearch) {
        this.apiKeys = apiKeys;
        this.server = server;
        this.converter = converter;
        this.server_data = null;
        titId_doubleSearch = doubleSearch;
    }

    public DataObject getDataById(String serverURL, IdSearchOptions options) {

        String parameters = options.getTomatoesParameters(getApiKey());
        TMDataObject dob = getServerData(serverURL, parameters);

        return converter.convert(dob);
    }

    /**
     * in rotten tomatoes API, search by id will return more detailed
     * information about a movie. So if full available info is desired then we
     * should use idSearch instead, but in situations where the rotten tomatoes
     * Id for movies are unknown(as this is usually the case) we must use the
     * double search option. In double search we first make one search by title
     * to retrieve the id of the movie then we continue with another search this
     * time using the id of the movie
     *
     * @param serverURL
     * @param options
     * @return DataObject
     */
    public DataObject getDataByTitle(String serverURL, TitleSearchOptions options) {
        //needs adouble search for detailed info
        String parameters = options.getTomatoesParameters(getApiKey());
        TMDataObject server_d = getServerData(serverURL, parameters);
        int year = -1;
        try{
         year = Integer.parseInt(options.getYear());
        }catch(Exception e){
            
        }
        /**
         * if it was wrong movie search for more available movies
         *
         */
        if (server_d == null) {
            return null;
        }
        if (!server_d.getTitle().equalsIgnoreCase(options.getTitle()) || 
                (year != -1 && server_d.getYear() != year) ) {

            String mv_id = "";
            if (options.getYear().equals(" ") || options.getYear() == null) {
                mv_id = new TomatoesServer().searchMovie(options.getTitle(), null, getApiKey(), serverURL);
      
            } else {
                mv_id = new TomatoesServer().searchMovie(options.getTitle(), options.getYear(), getApiKey(), serverURL);
    
            }

            if (mv_id.equalsIgnoreCase("not_found")) {
                return null;
            }
            return getDataById(serverURL, new IdSearchOptions(mv_id));
        }
        if (!titId_doubleSearch) {
            return converter.convert(server_d);
        }

        /*
         * if double search needed for more detailed info 
         */
        IdSearchOptions opt2 = new IdSearchOptions(server_d.getId());
        DataObject dob = getDataById(serverURL, opt2);

        return dob;
    }

    public TMDataObject getServerData() {
        return this.server_data;
    }

    private String getApiKey() {
        if (apiKeys.length == 0) {
            return "no-key";
        }
        int index = 0;
        index = (int) (Math.random() * (apiKeys.length));
        if (index == apiKeys.length && apiKeys.length > 0) {
            index -= 1;
        }


        return apiKeys[index];
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
