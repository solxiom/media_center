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
import service.domain.ImdbDataObject;

/**
 *
 * @author kavan
 */
public class ImdbServer extends JsonServer<ImdbDataObject> {



    public ImdbServer() {
      
    }

    @Override
    public ImdbDataObject jsonToServerObject(String jsonstr) throws Exception {

        ImdbDataObject imdb_object;
        JsonObject resultObj = getResultAsJsonObject(jsonstr);
        imdb_object = new Gson().fromJson(resultObj, ImdbDataObject.class);
        return imdb_object;
    }

    @Override
    public JsonObject getResultAsJsonObject(String jsonstr) throws Exception {
        JsonParser parser = new JsonParser();
        JsonElement element;
        JsonArray jarr = null;
        JsonObject resultObj = null, jobj = null;
        String total_found = null;//if the serach has offset then response will have this property
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

        } catch (Exception e) {

            throw new JsonSyntaxException("Throwed after an Exception in method[jsonToDataObject]" + e.getMessage(), e.getCause());
        }
        if (singleResultObject && jobj != null) {
            resultObj = jobj;
        } else if (jarr != null && jarr.get(0).isJsonObject()) {
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

        return resultObj;
    }
}
