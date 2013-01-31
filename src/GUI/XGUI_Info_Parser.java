/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.HashMap;
import service.imdb.domain.ImdbDataObject;

/**
 *
 * @author kavan
 */
public class XGUI_Info_Parser {

    public HashMap<String, String> getValues(ImdbDataObject info) {

        HashMap<String, String> values = new HashMap<String, String>();
        String title = "No information found for this Item";
        String genre = "";
        String actors = "";
        String directors = "";
        String type = "";
        String plot = "";
        String language = "";
        String rateInfo = "";
        String poster = "";
        if (info.getError() == null) {

            title = info.getTitle() + " [" + info.getYear() + "]";
            title = getStyledTitle(title);
            genre = "Genre: " + info.getGenres().toString().replace("[", "").replace("]", "");
            directors = "Director(s): " + info.getDirectors().toString().replace("[", "").replace("]", "");
            actors = "Actors: "
                    + parseActors(info.getActors().toArray(new String[info.getActors().size()]), 6, 3)
                    + "";
            type = "Type: " + getItemType(info.getType());
            plot = "" + parsePlot(info.getPlot(), 90) + "";
            language = "Language(s): " + info.getLanguage().toString().replace("[", "").replace("]", "");
            rateInfo = info.getRating() + "/ 10 Rated by " + info.getRating_count() + " users";
            if (info.getPoster().equals(" ")) {
                poster = "";
            } else {
                poster = "<img src='" + info.getPoster() + "' width='300' height='400' />";
            }

        }

        values.put("title", title);

        values.put("genre", genre);

        values.put("director", directors);

        values.put("actors", actors);

        values.put("type", type);

        values.put("plot", plot);

        values.put("language", language);

        values.put("rateInfo", rateInfo);

        values.put("poster", poster);
        
        putIntoHTMLTag(values);
        return values;
    }
    private String getStyledTitle(String tit){
        String css ="background-color:black;color:white;font-size:x-large;"
                + "border: solid white 2px;padding:2px;"
                + "font-weight:bold;text-wrap:none;"
                + "";
        String styled ="<div style='"+css+"'>"+tit+"</div>";
        
        return styled;
        
    }
    private void putIntoHTMLTag(HashMap<String,String> vals){
        for(String k: vals.keySet()){
            String newVal = "<html><body style='padding:5px;'>"+ vals.get(k) +"</body></html>";
            vals.put(k, newVal);
        }
    }
    public String getItemType(String code) {
        if (code.equalsIgnoreCase("M")) {
            return "Movie";
        }
        if (code.equalsIgnoreCase("tvs")) {
            return "TV Series";
        }
        if (code.equalsIgnoreCase("tv")) {
            return "TV Movie";
        }
        if (code.equalsIgnoreCase("V")) {
            return "Video";
        }
        if (code.equalsIgnoreCase("vg")) {
            return "Video Game";
        }

        return code;
    }

    public String parseActors(String[] ar, int total, int perLine) {
        String res = "";
        int count = 0;
        for (String a : ar) {
            if (count > 0 && count < total) {
                res += ", ";
            }
            if (count != 0 && count % perLine == 0) {

                res += "<br/>";
                
            }
            if (count >= total) {
                res += "...";
                break;
            }
            res += a;
            count++;
        }

        return res;
    }

    public String parsePlot(String str, int line_size) {
        String res = "";
        int x = 0;
        for (int i = 0; i < str.length(); i++) {

            if (i > 0 && i % line_size == 0) {
                if (i > 0 && ((str.charAt(i - 1) != ' ' && str.charAt(i) != ' '))) {
                    res += "-";
                }
                res += "<br/>";
            }
            res += str.charAt(i);
        }

        return res;
    }
}
