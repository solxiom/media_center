/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain.tomatoes;

/**
 *
 * @author kavan
 */
public class TMLinks {

    String self;
    String alternate;
    String cast;
    String clips;
    String reviews;
    String similar;

    public TMLinks() {
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getAlternate() {
        return alternate;
    }

    public void setAlternate(String alternate) {
        this.alternate = alternate;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getClips() {
        return clips;
    }

    public void setClips(String clips) {
        this.clips = clips;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getSimilar() {
        return similar;
    }

    public void setSimilar(String similar) {
        this.similar = similar;
    }
}
/*
 *links: {
self: "http://api.rottentomatoes.com/api/public/v1.0/movies/771205893.json",
alternate: "http://www.rottentomatoes.com/m/jack_and_jill_2011/",
cast: "http://api.rottentomatoes.com/api/public/v1.0/movies/771205893/cast.json",
clips: "http://api.rottentomatoes.com/api/public/v1.0/movies/771205893/clips.json",
reviews: "http://api.rottentomatoes.com/api/public/v1.0/movies/771205893/reviews.json",
similar: "http://api.rottentomatoes.com/api/public/v1.0/movies/771205893/similar.json"
}
 */