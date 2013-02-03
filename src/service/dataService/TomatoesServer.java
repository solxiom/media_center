/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import service.DataConverter;
import service.JsonServer;
import service.domain.DataObject;
import service.domain.tomatoes.TMDataObject;

/**
 *
 * @author kavan
 */
public class TomatoesServer extends JsonServer<TMDataObject> {

    public TomatoesServer() {
    }

    @Override
    public TMDataObject jsonToServerObject(String jsonstr) throws Exception {

        JsonObject resultObj = getResultAsJsonObject(jsonstr);
        TMDataObject tm_object = new Gson().fromJson(resultObj, TMDataObject.class);

        return tm_object;
    }

    @Override
    public JsonObject getResultAsJsonObject(String jsonstr) throws Exception {
        JsonParser parser = new JsonParser();
        JsonElement element;
        JsonObject resultObject = null;
        JsonArray resultArray = null;
        String total_found = null;//if the serach has offset then response will have this property
        String error = null;//property for result coms with an error message 

        try {

            element = parser.parse(jsonstr);

            if (element.isJsonObject()) {

                resultObject = element.getAsJsonObject();
                if (resultObject.has("title") && resultObject.has("id")) {

                    return resultObject;

                }
                if (resultObject.has("movies")) {

                    resultArray = resultObject.get("movies").getAsJsonArray();

                    if (resultArray.size() > 0) {
                        resultObject = resultArray.get(0).getAsJsonObject();


                    } else {

                        return null;
                    }
                }
                if (resultObject.has("error")) {
                    error = resultObject.get("error").getAsString();
                }
                if (resultObject.has("total")) {
                    total_found = resultObject.get("total").getAsString();
                }

            } else {

                throw new Exception("The element is not "
                        + " a JsonObject - method [jsonToDataObject]");
            }

        } catch (Exception e) {

            throw new JsonSyntaxException("Throwed after an Exception in method[jsonToDataObject]" + e.getMessage(), e.getCause());
        }


        if (total_found != null) {
            resultObject.addProperty("total_found", total_found);
        }
        if (error != null) {
            resultObject.addProperty("error", error);
        }

        return resultObject;
    }

    /**
     * search for a movie by it's title and return the id of the movie or
     * not_found
     *
     * @param title
     * @param year can be null
     * @param apiKey
     * @param serverUrl
     * @return the id of movie or not_found
     */
    public String searchMovie(String title, String year, String apiKey, String serverUrl) {
        String url = serverUrl + "/movies.json?apikey=" + apiKey + "&q=" + title + "&page_limit=50";
        url = url.replace(" ", "+");
        String json_str = "";
        String result_id = "";
        int page = 1;
        int total = 0;
        do {
            json_str = requestToServer(url + "&page=" + page);
            result_id = searchInJsonText(json_str, title,year);
            if (!result_id.equalsIgnoreCase("not_found") && !result_id.equals("")) {
                return result_id;
            }
            page = getNextPageNumber(json_str, page);
            if (page == -1) {
                break;
            }
        } while (true);

        return "not_found";
    }

    private String searchInJsonText(String json_str, String title) {

        return searchInJsonText(json_str, title, null);
    }

    private String searchInJsonText(String json_str, String title, String year) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement el = parser.parse(json_str);
            JsonObject job = null;
            JsonArray movies = null;
            int total = 0;
            if (el.isJsonObject()) {
                job = el.getAsJsonObject();
                if (job.has("movies")) {
                    movies = job.get("movies").getAsJsonArray();
                    for (int i = 0; i < movies.size(); i++) {
                        job = movies.get(i).getAsJsonObject();
                        if (year == null || !job.has("year")) {
                            if (job.get("title").getAsString().equalsIgnoreCase(title)) {
                                return job.get("id").getAsString();
                            }
                        } else {
                            if (job.has("year")
                                    && job.get("title").getAsString().equalsIgnoreCase(title)
                                    && job.get("year").getAsString().equalsIgnoreCase(year)) {
                                return job.get("id").getAsString();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
        }

        return "not_found";
    }

    private int getNextPageNumber(String json_str, int current_page) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement el = parser.parse(json_str);
            JsonObject job = null;
            int total = 0;
            if (el.isJsonObject()) {
                job = el.getAsJsonObject();
                if (job.has("total")) {
                    total = job.get("total").getAsInt();
                    if (current_page * 50 < total) {
                        return (current_page + 1);
                    }
                }
            }

        } catch (Exception e) {
        }
        return -1;
    }
}
