/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import service.domain.DataObject;

/**
 *
 * @author kavan
 */
public abstract class JsonServer {
    
     public String requestToServer(String requestString) {
        String jsonResponse = "";
        HttpGet req = new HttpGet(requestString);

        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(req);

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                jsonResponse += line;
            }

        } catch (Exception e) {
            System.out.println("DataService  error:" + e);
        }

        return jsonResponse;
    }

    public abstract DataObject jsonToDataObject(String jsonstr) throws Exception;

    public abstract JsonObject getResultAsJsonObject(String jsonstr) throws Exception;
}
