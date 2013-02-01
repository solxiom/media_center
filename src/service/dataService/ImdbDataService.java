/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import service.JsonServer;
import service.DataService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import service.domain.DataObject;
import service.domain.IdSearchOptions;
import service.domain.TitleSearchOptions;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author kavan
 */
public class ImdbDataService implements DataService {

    HttpClient client_globe;
    HttpGet req_globe;
    String jsonstr_globe = "";
    DataObject dataObject_globe;
    JsonServer server;
    

    public ImdbDataService(JsonServer server) {
        this.server = server;
    }

    @Override
    public DataObject getDataByTitle(String serverURL, TitleSearchOptions options) {
        return getData(serverURL, options.toString());
    }

    @Override
    public DataObject getDataById(String serverURL, IdSearchOptions options) {
        return getData(serverURL, options.toString());
    }

    private DataObject getData(String serverURL, String options) {
        String jsonstr = "";
        DataObject dataObject;
        String requestString = serverURL + "/" + options;
        jsonstr = server.requestToServer(requestString);
        try {
            return server.jsonToDataObject(jsonstr);
        } catch (Exception e) {

            return null;
        }
    }  
}
