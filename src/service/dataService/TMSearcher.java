/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import service.JsonSearcher;
import service.JsonServer;
import service.Tools;

/**
 *
 * @author kavan
 */
public class TMSearcher implements JsonSearcher {

    private JsonServer server;
    private String serverUrl;
    private String[] apiKeys;

    public TMSearcher(JsonServer server, String serverUrl, String[] apiKey) {
        this.server = server;
        this.serverUrl = serverUrl;
        this.apiKeys = apiKey;
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
    public String findItemId(String title, String year) {
        String api_key = Tools.getRandomMember(apiKeys);
        if (api_key.equalsIgnoreCase("no-key")) {
            return null;
        }
        String url = serverUrl + "/movies.json?apikey=" + api_key + "&q=" + title + "&page_limit=50";
        url = url.replace(" ", "+");
        String json_str = "";
        String result_id = "";
        int page = 1;
        int total = 0;
        do {
            json_str = server.requestToServer(url + "&page=" + page);
            result_id = searchInJsonText(json_str, title, year);
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
