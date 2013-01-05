/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imdb.service;

import imdb.domain.TitleSearchOptions;
import imdb.domain.IdSearchOptions;
import imdb.domain.DataObject;
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

/**
 *
 * @author kavan
 */
public class DataServiceImpl implements DataService {

    HttpClient client_globe;
    HttpGet req_globe;
    String jsonstr_globe = "";
    DataObject dataObject_globe;

    public DataServiceImpl() {
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
        jsonstr = connectToServer(requestString);
        try {
            return jsonToDataObject(jsonstr);
        } catch (Exception e) {

            return null;
        }
    }

    private String connectToServer(String requestString) {
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
        System.out.println("" + requestString);
        System.out.println("out: " + jsonResponse);
        return jsonResponse;
    }

    private DataObject jsonToDataObject(String jsonstr) throws Exception {
        DataObject dataObject;
        JsonObject resultObj = getResultAsJsonObject(jsonstr);
        dataObject = new Gson().fromJson(resultObj, DataObject.class);
        return dataObject;
    }

    private JsonObject getResultAsJsonObject(String jsonstr) throws Exception {
        JsonParser parser = new JsonParser();
        JsonElement element;
        JsonArray jarr = null;
        JsonObject resultObj = null, jobj = null;
        String total_found = null;//if the result is an object it will have this property
        String error = null;//property for result coms with an error message 
        boolean singleResultObject = false;//when search is made by id
        try {

            element = parser.parse(jsonstr);

            if (element.isJsonArray()) {
                jarr = element.getAsJsonArray();

            } else if (element.isJsonObject()) {

                jobj = element.getAsJsonObject();
                if (jobj.has("result")) {
                    jarr = (JsonArray) jobj.get("result");
                }
                if (jobj.has("total_found")) {
                    total_found = jobj.get("total_found").getAsString();
                }
                if (jobj.has("error")) {
                    error = jobj.get("error").getAsString();
                }
                if (jobj.has("imdb_id")) {
                    singleResultObject = true;
                }

            } else {

                throw new Exception("The element is not a JsonArray nor a JsonObject - method [jsonToDataObject]");
            }
            if (singleResultObject && jobj != null) {
                resultObj = jobj;
            } else if (jarr != null) {
                resultObj = jarr.get(0).getAsJsonObject();
            } else {
                resultObj = new JsonObject();
            }
            if (total_found != null) {
                resultObj.addProperty("total_found", total_found);
            }
            if (error != null) {
                resultObj.addProperty("error", error);
            }
        } catch (Exception e) {
            System.out.println("täällä ollaan edelleen" + e.toString());
            throw new JsonSyntaxException("Throwed after an Exception in method[jsonToDataObject]" + e.getMessage(), e.getCause());
        }

        return resultObj;
    }
}
