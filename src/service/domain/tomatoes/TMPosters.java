/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain.tomatoes;

/**
 *
 * @author kavan
 */
public class TMPosters {
  
    String thumbnail;
    String profile;
    String detailed;
    String original;

    public TMPosters() {
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
    
}
/*
posters: {
thumbnail: "http://content8.flixster.com/movie/11/16/39/11163966_mob.jpg",
profile: "http://content8.flixster.com/movie/11/16/39/11163966_pro.jpg",
detailed: "http://content8.flixster.com/movie/11/16/39/11163966_det.jpg",
original: "http://content8.flixster.com/movie/11/16/39/11163966_ori.jpg"
}
*/