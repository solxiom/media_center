/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.HashMap;
import service.domain.DataObject;

/**
 *
 * @author kavan
 */
public class XGUI_Info_Parser {

    public HashMap<String, String> getValues(DataObject info) {

        HashMap<String, String> values = new HashMap<String, String>();
        String title = getStyledCaution("Sorry!<br/> No information found for this Item");
        String genre = "";
        String actors = "";
        String directors = "";
        String type = "";
        String plot = "";
        String mpaa = "";
        String rateInfo = "";
        String poster = "sad-face";
        if (info != null && info.getError() == null) {

            title = info.getTitle() + " [" + info.getYear() + "]";
            title = getStyledTitle(title);
            genre = "Genre: ";
            if (info.getGenres() != null) {
                genre += info.getGenres().toString().replace("[", "").replace("]", "");
            }
            directors = "Director(s): " + info.getDirectors().toString().replace("[", "").replace("]", "");
            actors = "Actors: "
                    + parseActors(info.getCast().toArray(new String[info.getCast().size()]), 6, 3)
                    + "";
            type = "Type: " + getItemType(info.getType());
            plot = "" + parsePlot(info.getStoryLine(), 90) + "";

            mpaa = "";
            if (info.getRated() != null) {
                mpaa += info.getRated();
            }
            rateInfo = info.getImdb_user_rating() + "/ 10 Rated by " + info.getImdb_votes_count() + " users";
            if (info.getPosters().size() == 0) {
                poster = "";
            } else {
                poster = "<img src='" + info.getPosters().get(1) + "' width='300' height='400' />";
            }

        }
        /**
         * TESTING posters
         */
        if (info != null && info.getPosters() != null) {
            System.out.println("poster[0]: " + info.getPosters().get(0));
            System.out.println("poster[1]: " + info.getPosters().get(1));
            System.out.println("poster[2]: " + info.getPosters().get(2));
            System.out.println("poster[3]: " + info.getPosters().get(3));
        }

        values.put("title", title);

        values.put("genre", genre);

        values.put("director", directors);

        values.put("actors", actors);

        values.put("type", type);

        values.put("plot", plot);

        values.put("mpaa", mpaa);

        values.put("rateInfo", rateInfo);

        values.put("poster", poster);

        putIntoHTMLTag(values);
        return values;
    }

    private String getStyledTitle(String tit) {
        String css = "background-color:black;color:white;font-size:x-large;"
                + "border: solid white 2px;padding:2px;"
                + "font-weight:bold;text-wrap:none;"
                + "";
        String styled = "<div style='" + css + "'>" + tit + "</div>";

        return styled;

    }

    private String getStyledCaution(String tit) {
        String css = "background-color:red;color:white;font-size:x-large;"
                + "border: solid white 2px;padding:2px;"
                + "font-weight:bold;text-wrap:none;"
                + "";
        String styled = "<div style='" + css + "'>" + tit + "</div>";

        return styled;

    }

    private void putIntoHTMLTag(HashMap<String, String> vals) {
        for (String k : vals.keySet()) {
            String newVal = inDefaultHtmlTag(vals.get(k));
            vals.put(k, newVal);
        }
    }

    public String inDefaultHtmlTag(String str) {
        String newVal = "<html><body style='padding:5px;'>" + str + "</body></html>";
        return newVal;
    }

    public String getItemType(String code) {
        if (code == null || code.equals(" ")) {
            return "Uknown";
        }
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
