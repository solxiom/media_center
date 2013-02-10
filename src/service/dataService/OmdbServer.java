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
import service.JsonServer;
import service.domain.OmdbDataObject;
import service.domain.tomatoes.TMDataObject;

/**
 *
 * @author kavan
 */
public class OmdbServer extends JsonServer<OmdbDataObject> {

    @Override
    public OmdbDataObject jsonToServerObject(String jsonstr) throws Exception {
        JsonObject resultObj = getResultAsJsonObject(jsonstr);
        OmdbDataObject omdb_object = new Gson().fromJson(resultObj, OmdbDataObject.class);

        return omdb_object;
    }

    @Override
    public JsonObject getResultAsJsonObject(String jsonstr) throws Exception {
        JsonParser parser = new JsonParser();
        JsonElement element;
        JsonObject resultObject = null;

        try {

            element = parser.parse(jsonstr);

            if (element.isJsonObject()) {

                resultObject = element.getAsJsonObject();
                if (resultObject.has("Error")) {

                    return null;

                }
                if (resultObject.has("Title")) {

                    return resultObject;



                } else {

                    throw new Exception("The element is not "
                            + " a JsonObject - method [jsonToDataObject]");
                }

            }
        } catch (Exception e) {

            throw new JsonSyntaxException("Throwed after an Exception in method[jsonToDataObject]" + e.getMessage(), e.getCause());
        }

        return null;
    }
}
