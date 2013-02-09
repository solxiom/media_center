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
public class OmdbSearcher implements JsonSearcher {

    private JsonServer server;
    private String serverUrl;

    public OmdbSearcher(JsonServer server, String serverUrl) {
        this.server = server;
        this.serverUrl = serverUrl;

    }

    public String findItemId(String title, String year) {
        String url = Tools.bindUrlwithParameters(serverUrl, "?s=" + title.replace(" ", "+") + "&r=json", "/");
        String json_response = server.requestToServer(url);
        System.out.println(""+url);
        System.out.println(""+json_response);
        String result = searchWithYear(json_response, title, year);
       /**
        * if still not found try only with the first word of the title
        */
        if(result.equalsIgnoreCase("not_found")){
            url = Tools.bindUrlwithParameters(serverUrl, "?s=" + title.split(" ")[0] + "&r=json", "/");
            json_response = server.requestToServer(url);          
            result = searchWithYear(json_response, title, year);
        }

        return result;
    }
    private String searchWithYear(String response,String title,String year){
         if (year == null || year.equals(" ")) {
            return searchInJsonText(response, title);
        } else {
            return searchInJsonText(response, title, year);
        }
    }
    private String searchInJsonText(String json_str, String title) {

        return searchInJsonText(json_str, title, null);
    }

    private String searchInJsonText(String json_str, String title, String year) {
        String res_id = "not_found";
        try {
            JsonParser parser = new JsonParser();
            JsonElement el = parser.parse(json_str);
            JsonObject job = null;
            JsonArray movies = null;

            int total = 0;
            if (el.isJsonObject()) {
                job = el.getAsJsonObject();
                if (job.has("Search")) {
                    movies = job.get("Search").getAsJsonArray();
                    for (int i = 0; i < movies.size(); i++) {
                        job = movies.get(i).getAsJsonObject();
                        System.out.println("---"+job.get("Title").getAsString());
                        if (year == null || !job.has("Year")) {
                            if (isSameTitle(title, job.get("Title").getAsString())) {
                                res_id = job.get("imdbID").getAsString();
                            }
                        } else {
                            if ((year != null && job.has("Year"))
                                    && job.get("Year").getAsString().equalsIgnoreCase(year)) {
                                if (isSameTitle(title, job.get("Title").getAsString())) {
                                    return job.get("imdbID").getAsString();
                                }

                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
        }

        return res_id;
    }

    private boolean isSameTitle(String f_title, String s_title) {

        if (s_title.equalsIgnoreCase(f_title) 
                || s_title.replace(".", "").equalsIgnoreCase(f_title)
                || s_title.replace(":", "").equalsIgnoreCase(f_title)
                || s_title.replace("/", "").equalsIgnoreCase(f_title)) {
            return true;
        }

        return false;
    }
}
