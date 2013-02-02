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
}
