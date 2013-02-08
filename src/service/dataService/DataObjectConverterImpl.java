/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import java.lang.String;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import service.DataConverter;
import service.domain.DataObject;
import service.domain.ImdbDataObject;
import service.domain.OmdbDataObject;
import service.domain.tomatoes.TMCast;
import service.domain.tomatoes.TMDataObject;
import service.domain.tomatoes.TMDirector;

/**
 *
 * @author kavan
 */
public class DataObjectConverterImpl implements DataConverter {

    DataObject data;

    public DataObjectConverterImpl() {
    }

    @Override
    public DataObject convert(OmdbDataObject object) {
        String[] poster = new String[1];
        if (object == null) {
            return null;
        }
        if (object.getPoster() != null) {
            poster[0] = object.getPoster();
        }


        data = new DataObject();
        data.setImdb_id(object.getImdbID());
        data.setTitle(object.getTitle());
        data.setPosters(Arrays.asList(poster));
        data.setCast(Arrays.asList(object.getActors().split(",")));

        data.setStoryLine(object.getPlot());

        data.setDirectors(Arrays.asList(object.getDirector().split(",")));
        data.setTeather_release_date(object.getReleased());
        data.setGenres(Arrays.asList(object.getGenre().split(",")));
        data.setRated(object.getRated());
        data.setRuntime(object.getRuntime());
        data.setImdb_user_rating(object.getImdbRating());
        data.setImdb_votes_count(object.getImdbVotes());
        data.setYear(object.getYear());

        return data;
    }

    @Override
    public DataObject convert(ImdbDataObject object) {
        String[] poster = new String[1];
        poster[0] = object.getPoster();
        data = new DataObject();
        data.setImdb_id(object.getImdb_id());
        data.setTitle(object.getTitle());
        data.setPosters(Arrays.asList(poster));
        data.setCast(object.getActors());
        if (object.getPlot() == null || object.getPlot().equals("")) {
            data.setStoryLine(object.getPlot_simple());
        } else {
            data.setStoryLine(object.getPlot());
        }
        data.setCountry(object.getCountry());
        data.setEpisodes(object.getEpisodes());
        data.setDirectors(object.getDirectors());
        data.setTeather_release_date(object.getRelease_date() + "");
        data.setGenres(object.getGenres());
        data.setError(object.getError());
        data.setLanguage(object.getLanguage());
        data.setRated(object.getRated());
        data.setRuntime(object.getRuntime() + "");
        data.setImdb_user_rating(object.getRating());
        data.setImdb_votes_count(object.getRating_count());
        data.setYear(object.getYear());
        data.setType(object.getType());

        return data;
    }

    @Override
    public DataObject convert(TMDataObject object) {
        String[] posters = new String[4];
        if (object == null) {
            return null;
        }
        if (object.getPosters() != null) {

            posters[0] = object.getPosters().getOriginal();
            posters[1] = object.getPosters().getDetailed();
            posters[2] = object.getPosters().getProfile();
            posters[3] = object.getPosters().getThumbnail();
        }

        List<String> cast = new LinkedList<String>();
        if (object.getAbridged_cast() != null) {
            for (TMCast actor : object.getAbridged_cast()) {
                cast.add(actor.getName());
            }
        }
        List<String> directs = new LinkedList<String>();

        if (object.getAbridged_directors() != null) {
            for (TMDirector d : object.getAbridged_directors()) {
                directs.add(d.getName());
            }
        }
        data = new DataObject();
        data.setTm_id(object.getId());
        data.setTitle(object.getTitle());
        data.setPosters(Arrays.asList(posters));
        data.setCast(cast);
        data.setStoryLine(object.getSynopsis());
        data.setDirectors(directs);
        if (object.getRelease_dates() != null) {
            data.setTeather_release_date(object.getRelease_dates().getTheater());
            data.setDvd_release_date(object.getRelease_dates().getDvd());
        }
        data.setGenres(object.getGenres());
        data.setError(object.getError());
        data.setRated(object.getMpaa_rating());
        data.setRuntime(object.getRuntime() + "");
        data.setYear(object.getYear());
        if (object.getRatings() != null) {
            data.setTm_user_meter(Integer.parseInt(object.getRatings().getAudience_score()));
        }
        data.setStudio(object.getStudio());

        return data;
    }
}
