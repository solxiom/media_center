/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imdb.domain;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

/**
 *
 * @author kavan
 */
public class TitleSearchOptions {

//    Parameter	Required   Valid options	Default Value	Description
//      title	Yes                             <empty>         The name of the movie you wish to search for (e.g. Finding Nemo, The Green Mile)
    String title;
//      type	No	json, jsonp, xml	json            The data type you wish the API to return.
    String type;
//      year	No                              2013            The year you wish to search for. If you omit the year it will automatically use the current year when searching.
    String year;
//      yg	No	1,0                     0               When this parameter is 0, the year parameter is disabled.
    String yg;
//      mt	No	none, M, TV, TVS, V, VG	none            The movie type you wish to search for.
    String mt;
//      plot	No	none, simple, full	simple          The plot type you wish the API to return.
    String plot;
//      episode	No	1,0                     1               When the parameter is 0, "episodes" is not included in the result.
    String episode;
//      offset	No                              0               The API to return to starting from that offset, specify the offset will enable paging, please try it.
    String offset;
//      limit	No	1 - 5                   1               The number of records you wish to API to return.
    String limit;
//      lang	No	en-US, zh-CN            en-US           The data language you wish the API to reutrn.
    String lang;
//      aka	No	simple,full             simple          The aka type you wish the API to return.
    String aka;
//      release	No	simple,full             simple          The release date type you wish the API to return.
    String release;

    /**
     * some default values changed here
     *
     * @param title the movie title
     */
    public TitleSearchOptions(String title) {

        this.title = title;
        this.yg = "0";
        setDefaultValues();

    }

    public TitleSearchOptions(String title, String year) {

        this.title = title;
        this.yg = "1";
        this.year = year;
        setDefaultValues();

    }

    private void setDefaultValues() {
        this.type = "json";
        this.plot = "full";
        this.episode = "0";
        this.limit = "1";
        this.mt = "none";
        this.lang = "en-US";
        this.aka = "simple";
        this.release = "simple";
      
       
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getYg() {
        return yg;
    }

    public void setYg(String yg) {
        this.yg = yg;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
//        sample: 
//        ?title=amelia&type=json&plot=full&episode=1&limit=1&year=2009
//        &yg=1&mt=none&lang=en-US&offset=&aka=simple&release=simple
        if (this.year != null) {
            this.yg = "1";
        }
        if (this.offset == null) {
            this.offset = "";
        }
        String str = "?";
        str += "title=" + this.title;
        str += "&type=" + this.type;
        str += "&plot=" + this.plot;
        str += "&episode=" + this.episode;
        str += "&limit=" + this.limit;
        str += "&yg=" + this.yg;
        str += "&mt=" + this.mt;
        str += "&lang=" + this.lang;
        str += "&offset=" + this.offset;
        str += "&aka=" + this.aka;
        str += "&release=" + this.release;
        str += "&year=" + this.year;
        return str;
    }
}
