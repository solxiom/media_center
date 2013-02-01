/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain.tomatoes;

import java.util.List;

/**
 *
 * @author kavan
 */
public class TMDataObject {

    String id;
    String title;
    String mpaa_rating;
    String runtime;
    String critics_consensus;
    TMRealeses release_dates;
    String synopsis;
    TMPosters posters;
    List<TMCast> abridged_cast;
    TMAlternate_ids alternate_ids;
    TMLinks links;
    TMDirector abridged_directors;
    String studio;

    public TMDataObject() {
    }

    public TMDirector getAbridged_directors() {
        return abridged_directors;
    }

    public void setAbridged_directors(TMDirector abridged_directors) {
        this.abridged_directors = abridged_directors;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMpaa_rating() {
        return mpaa_rating;
    }

    public void setMpaa_rating(String mpaa_rating) {
        this.mpaa_rating = mpaa_rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getCritics_consensus() {
        return critics_consensus;
    }

    public void setCritics_consensus(String critics_consensus) {
        this.critics_consensus = critics_consensus;
    }

    public TMRealeses getRelease_dates() {
        return release_dates;
    }

    public void setRelease_dates(TMRealeses release_dates) {
        this.release_dates = release_dates;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public TMPosters getPosters() {
        return posters;
    }

    public void setPosters(TMPosters posters) {
        this.posters = posters;
    }

    public List<TMCast> getAbridged_cast() {
        return abridged_cast;
    }

    public void setAbridged_cast(List<TMCast> abridged_cast) {
        this.abridged_cast = abridged_cast;
    }

    public TMAlternate_ids getAlternate_ids() {
        return alternate_ids;
    }

    public void setAlternate_ids(TMAlternate_ids alternate_ids) {
        this.alternate_ids = alternate_ids;
    }

    public TMLinks getLinks() {
        return links;
    }

    public void setLinks(TMLinks links) {
        this.links = links;
    }
//    abridged_directors: [
//{
//name: "Dennis Dugan"
//}
//]
}
/*
id: "771205893",
title: "Jack and Jill",
year: 2011,
mpaa_rating: "PG",
runtime: 93,
critics_consensus: "Although it features an inexplicably committed performance from Al Pacino, Jack and Jill is impossible to recommend on any level whatsoever.",
release_dates: {
theater: "2011-11-11",
dvd: "2012-03-06"
},
ratings: {
critics_rating: "Rotten",
critics_score: 3,
audience_rating: "Spilled",
audience_score: 39
},
synopsis: "Jack and Jill is a comedy focusing on Jack Sadelstein (Adam Sandler), a successful advertising executive in Los Angeles with a beautiful wife and kids, who dreads one event each year: the Thanksgiving visit of his identical twin sister Jill (also Adam Sandler). Jill's neediness and passive-aggressiveness is maddening to Jack, turning his normally tranquil life upside down. Katie Holmes plays Erin, Jack's wife. -- (C) Sony Pictures",
posters: {
thumbnail: "http://content8.flixster.com/movie/11/16/39/11163966_mob.jpg",
profile: "http://content8.flixster.com/movie/11/16/39/11163966_pro.jpg",
detailed: "http://content8.flixster.com/movie/11/16/39/11163966_det.jpg",
original: "http://content8.flixster.com/movie/11/16/39/11163966_ori.jpg"
},
abridged_cast: [
{
name: "Adam Sandler",
id: "162652550",
characters: [
"Jack Sadelstein",
"Jill Sadelstein"
]
},
{
name: "Katie Holmes",
id: "162652648",
characters: [
"Erin"
]
},
{
name: "Al Pacino",
id: "162654461",
characters: [
"Himself"
]
},
{
name: "Elodie Tougne",
id: "771386204",
characters: [
"Sophia"
]
},
{
name: "Rohan Chand",
id: "771386205",
characters: [
"Gary"
]
}
],
alternate_ids: {
imdb: "0810913"
},
links: {
self: "http://api.rottentomatoes.com/api/public/v1.0/movies/771205893.json",
alternate: "http://www.rottentomatoes.com/m/jack_and_jill_2011/",
cast: "http://api.rottentomatoes.com/api/public/v1.0/movies/771205893/cast.json",
clips: "http://api.rottentomatoes.com/api/public/v1.0/movies/771205893/clips.json",
reviews: "http://api.rottentomatoes.com/api/public/v1.0/movies/771205893/reviews.json",
similar: "http://api.rottentomatoes.com/api/public/v1.0/movies/771205893/similar.json"
}
}
*/