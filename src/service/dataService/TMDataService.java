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
    private JsonSearcher searcher;
    private DataConverter converter;
    private TMDataObject server_data;
    private boolean titId_doubleSearch;

    public TMDataService(String[] apiKeys, JsonServer<TMDataObject> server, JsonSearcher searcher, DataConverter converter) {
        this(apiKeys, server, searcher, converter, false);
    }

    public TMDataService(String[] apiKeys, JsonServer<TMDataObject> server, JsonSearcher searcher, DataConverter converter, boolean doubleSearch) {
        this.apiKeys = apiKeys;
        this.server = server;
        this.searcher = searcher;
        this.converter = converter;
        this.server_data = null;
        titId_doubleSearch = doubleSearch;
    }

    public DataObject getDataById(String serverURL, IdSearchOptions options) {

        String parameters = options.getTomatoesParameters(Tools.getRandomMember(apiKeys));
        TMDataObject dob = getServerObject(serverURL, parameters);

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

        String parameters = options.getTomatoesParameters(Tools.getRandomMember(apiKeys));
        TMDataObject server_d = getServerObject(serverURL, parameters);

        if (server_d == null) {
            return null;
        }

        if (needsMoreSearch(options, server_d)) {

            String mv_id = "";

            if (options.getYear().equals(" ") || options.getYear() == null) {
                mv_id = searcher.findItemId(options.getTitle(), null);


            } else {
                mv_id = searcher.findItemId(options.getTitle(), options.getYear());


            }
            // if find a better result go for it otherwise continue with the old one
            if (!mv_id.equalsIgnoreCase("not_found")) {
                return getDataById(serverURL, new IdSearchOptions(mv_id));
            }

        }
        if (!titId_doubleSearch) {
            return converter.convert(server_d);
        }

        /*
         * when double search needed for more detailed info 
         */
        IdSearchOptions opt2 = new IdSearchOptions(server_d.getId());
        DataObject dob = getDataById(serverURL, opt2);

        return dob;
    }

    public TMDataObject getServerData() {
        return this.server_data;
    }

    @SuppressWarnings("empty-statement")
    private boolean needsMoreSearch(TitleSearchOptions options, TMDataObject server_d) {
        int o_year = -1, s_year = -1;
        String s_tit = server_d.getTitle(),
                o_tit = options.getTitle(),
                mode_1 = o_tit.replace(" and ", " & "),
                mode_2 = o_tit.replace(" ", "/");

        try {
            o_year = Integer.parseInt(options.getYear());
            s_year = server_d.getYear();

        } catch (Exception e) {
        }

        if (isYearsMatter(s_year, o_year)) {
            return true;
        }
        if (!s_tit.equalsIgnoreCase(o_tit)) {

            if (s_tit.equalsIgnoreCase(mode_1) || s_tit.equalsIgnoreCase(mode_2)) {

                return false;

            }
            return true;
        }



        return false;
    }

    private boolean isYearsMatter(int year_1, int year_2) {
        if ((year_1 != -1 || year_2 != -1)) {
            if (year_1 != year_2) {
                return true;
            }
        }
        return false;
    }

    private TMDataObject getServerObject(String serverURL, String options) {
        String jsonstr = "";
        DataObject dataObject;
        String requestString = Tools.bindUrlwithParameters(serverURL, options, null);
        jsonstr = server.requestToServer(requestString);

        try {
            server_data = server.jsonToServerObject(jsonstr);
            return server_data;
        } catch (Exception e) {

            return null;
        }
    }
}
