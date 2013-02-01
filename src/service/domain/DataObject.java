/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

import java.util.List;
import service.dataService.DataObjectConverterImpl;

/**
 *
 * @author kavan
 */
public class DataObject {

//Field	Type	Description
//actors	List	The movie's cast list.
    List<String> actors;
//also_known_as	List	 The movie's other name. Fields(full mode): title country remarks
    List<String> also_known_as;
    //country	List	The movie's country.
    List<String> country;
//directors	List	The movie's directors.
    List<String> directors;
//episodes	List	The TV series's episodes(only TV series). Fields: date season episode title.
    List<Episode> episodes;
//filming_locations	String	The movie's locations.
   String filming_locations;
//genres	List	The movie's genres.(e.g. Drama, War)
    List<String> genres;
//imdb_id	String	The movie's ID on IMDb.com.
    String imdb_id;
//imdb_url	String	The movie's url on IMDb.com.
    String imdb_url;
//language	List	The movie's audio language.
    List<String> language;
//plot	String	The movie's summary.
    String plot;
//plot_simple	String	The movie's summary.(short)
    String plot_simple;
//poster	String	The movie's poster.
    String poster;
//rated	String	The movie's classification and ratings.
    String rated;
//rating	Float	The score of the movie on IMDb.com.
    float rating;
//rating_count	Int	The number of voters on IMDb.com.
    int rating_count;
//release_date	Int (simple mode), 
    int release_date;
//List (full mode)	 The movie's release date. Fields(full mode): year month day country remarks
/* not implemented*/
//runtime	List	The movie's duration.
    List<String> runtime;
//title	String	The movie's name.
    String title;
//type	String	The movie's type. Below are all the type:
//
//M   - Movie
//TVS - TV Series
//TV  - TV Movie
//V   - Video
//VG  - Video Game
    String type;
//writers	List	The movie's writers.
    List<String> writers;
//year	Int	The movie's age.
    int year;
//if paging is used in the searchOptions see offset for paging in TitleSearchOptions class
    String total_found;
//error messae example film not found
    String error;

    public DataObject() {
    }
    
    public String getFilming_locations() {
        return filming_locations;
    }

    public void setFilming_locations(String filming_locations) {
        this.filming_locations = filming_locations;
    }   

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getAlso_known_as() {
        return also_known_as;
    }

    public void setAlso_known_as(List<String> also_known_as) {
        this.also_known_as = also_known_as;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
        this.country = country;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }


    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getImdb_url() {
        return imdb_url;
    }

    public void setImdb_url(String imdb_url) {
        this.imdb_url = imdb_url;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPlot_simple() {
        return plot_simple;
    }

    public void setPlot_simple(String plot_simple) {
        this.plot_simple = plot_simple;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public int getRelease_date() {
        return release_date;
    }

    public void setRelease_date(int release_date) {
        this.release_date = release_date;
    }

    public List<String> getRuntime() {
        return runtime;
    }

    public void setRuntime(List<String> runtime) {
        this.runtime = runtime;
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

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTotal_found() {
        return total_found;
    }

    public void setTotal_found(String total_found) {
        this.total_found = total_found;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "DataObject{" + "actors=" + actors + ", also_known_as=" + also_known_as
                + ", country=" + country + ", directors=" + directors + ", episodes=" + episodes
                + ", filming_locations=" + filming_locations + ", genres=" + genres + ", imdb_id=" + imdb_id
                + ", imdb_url=" + imdb_url + ", language=" + language + ", plot=" + plot
                + ", plot_simple=" + plot_simple + ", poster=" + poster + ", rated=" + rated
                + ", rating=" + rating + ", rating_count=" + rating_count + ", release_date=" + release_date
                + ", runtime=" + runtime + ", title=" + title + ", type=" + type + ", writers=" + writers + ", year=" + year
                + ", total_found=" + total_found + ", error=" + error + '}';
    }
}
