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
    List<String> cast;
//country	List	The movie's country.
    List<String> country;
//directors	List	The movie's directors.
    List<String> directors;
//episodes	List	The TV series's episodes(only TV series). Fields: date season episode title.
    List<ImdbEpisode> episodes;
//genres	List	The movie's genres.(e.g. Drama, War)
    List<String> genres;
//imdb_id	String	The movie's ID on IMDb.com.
    String imdb_id;
//rotten tomatoes id	
    String tm_id;
//language	List	The movie's audio language.
    List<String> language;
//plot	String	The movie's summary.
    String storyLine;
//poster	String	The movie's poster.
    List<String> posters;
//rated	String	The movie's classification and ratings.
    String rated;
//precentage value of rotten tomatoes user votes
    int tm_user_meter;
    float tm_user_rating;
    float tm_votes_count;
//rating_count	Int	The number of voters if avalaible.
    int imdb_votes_count;
    float imdb_user_rating;
    String teather_release_date;
    String dvd_release_date;
//runtime	List	The movie's duration.
    String runtime;
//title	String	The movie's name.
    String title;
    String studio;
//type	String	The movie's type. Below are all the type:
//
//M   - Movie
//TVS - TV Series
//TV  - TV Movie
//V   - Video
//VG  - Video Game
    String type;
//year	Int	The movie's age.
    int year;
//error messae example film not found
    String error;

    public DataObject() {
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
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

    public List<ImdbEpisode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<ImdbEpisode> episodes) {
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

    public String getTm_id() {
        return tm_id;
    }

    public void setTm_id(String tm_id) {
        this.tm_id = tm_id;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public String getStoryLine() {
        return storyLine;
    }

    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
    }

    public List<String> getPosters() {
        return posters;
    }

    public void setPosters(List<String> posters) {
        this.posters = posters;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public int getTm_user_meter() {
        return tm_user_meter;
    }

    public void setTm_user_meter(int tm_user_meter) {
        this.tm_user_meter = tm_user_meter;
    }

    public float getTm_user_rating() {
        return tm_user_rating;
    }

    public void setTm_user_rating(float tm_user_rating) {
        this.tm_user_rating = tm_user_rating;
    }

    public float getTm_votes_count() {
        return tm_votes_count;
    }

    public void setTm_votes_count(float tm_votes_count) {
        this.tm_votes_count = tm_votes_count;
    }

    public int getImdb_votes_count() {
        return imdb_votes_count;
    }

    public void setImdb_votes_count(int imdb_votes_count) {
        this.imdb_votes_count = imdb_votes_count;
    }

    public float getImdb_user_rating() {
        return imdb_user_rating;
    }

    public void setImdb_user_rating(float imdb_user_rating) {
        this.imdb_user_rating = imdb_user_rating;
    }

    public String getTeather_release_date() {
        return teather_release_date;
    }

    public void setTeather_release_date(String teather_release_date) {
        this.teather_release_date = teather_release_date;
    }

    public String getDvd_release_date() {
        return dvd_release_date;
    }

    public void setDvd_release_date(String dvd_release_date) {
        this.dvd_release_date = dvd_release_date;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
