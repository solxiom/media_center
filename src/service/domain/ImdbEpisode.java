/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

/**
 *
 * @author kavan
 */
public class ImdbEpisode {
//    Fields: date season episode title.

    String date;
    String season;
    String episode;
    String title;

    public ImdbEpisode() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
