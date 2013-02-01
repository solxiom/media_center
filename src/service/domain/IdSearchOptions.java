/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

/**
 *
 * @author kavan
 */
public class IdSearchOptions {
//Parameter	Required	Valid options	  DefaultValue	Description

//id	        Yes	       /^tt\d+$/		        The IMDB ID of the movie you wish to get the info for (e.g. tt0120689)
String id;
//type   	No	       json, jsonp, xml	   json	        The data type you wish the API to return.
String type;
//plot	        No	       none, simple, full simple	The plot type you wish the API to return.
String plot;
//episode	No	       1,0	            1	        When the parameter is 0, "episodes" is not included in the result.
String episode;
//lang	        No	       en-US, zh-CN        en-US	The data language you wish the API to reutrn.
String lang;
//aka	        No	       simple,full        simple	The aka type you wish the API to return.
String aka;
//release	No	       simple,full        simple	The release date type you wish the API to return.
String release;

/**
 * some default values changed here
 */
    public IdSearchOptions(String id) {
        this.id = id;
        this.type = "json";
        this.plot = "full";
        this.episode = "0";
        this.lang = "en-US";
        this.aka = "simple";
        this.release = "simple";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
/**
     * 
     * @return the correct syntax for using in http get request in OMDBAPI.COM 
     */
    public String getOmdbParameters(){
        String str = "?";
        str += "i=" + this.id;
        str += "&plot=" + this.plot;
        str += "&r=JSON";
        str += "&tomatoes=true";
        return str;
    }
    /**
     * 
     * @return the correct syntax for using in http get request in IMDBAPI.ORG 
     */
    @Override
    public String toString() {
// sample:
//        ?id=tt1129445&type=json&plot=simple&episode=1&lang=en-US&aka=simple&release=simple
        String str ="?";
        str+="id=" + id;
        str+="&type=" + type;
        str+="&plot=" + plot;
        str+="&episode=" + episode;
        str+="&lang=" + lang;
        str+="&aka=" + aka;
        str+="&release=" + release;
        
     
        return    str;
    }

}
