/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import GUI.beans.XGUI_Item;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import service.HostService;
import service.filesystem.MediaFile;
import service.ftp.FTPFileManager;
import service.ftp.FtpService;

/**
 *
 * @author kavan
 */
public class XGUI_ControllerImpl implements XGUI_Controller {

    //tools
    private List<XGUI_Observer> observers;
    private HashMap<Integer, MediaFile> activeResultMap;
    XGUI_Item_Converter converter;
    HostService hostService;

    public XGUI_ControllerImpl() {
        this.observers = new LinkedList<XGUI_Observer>();
        activeResultMap = new HashMap<Integer, MediaFile>();
        converter = new XGUI_Item_Converter();
        hostService = new FtpService(new FTPFileManager());
    }

    public void registerObserver(XGUI_Observer obs) {
        observers.add(obs);
    }

    public void unregisterObserver(XGUI_Observer obs) {
        observers.remove(obs);
    }

    public void notifyObserversWithResults(List<XGUI_Item> results) {
        for (XGUI_Observer obs : observers) {
            obs.updateResults(results);
        }
    }

    public void notifyObserversWithItemInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] getSearchGeneres() {
        String[] genres = {"<--All-->", "Action", "Drama", "Romance", "Thriller", "SciFi", "Horror", "Comedy", "Biography", "History", "Crime"};
        return genres;
    }

    public String[] getSearchCategories() {
        String[] categories = {"<--All-->", "Movies", "TvShows", "Documentaries", "Persian Media"};
        return categories;
    }

    public String[] getSearchYears() {
        List<String> years = new ArrayList<String>();
        years.add("<--All-->");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year; i >= 1900; i--) {
            years.add(i + "");
        }
        return years.toArray(new String[]{});
    }

    public void listMovies() {

        List<MediaFile> files = hostService.listMovies();
        List<XGUI_Item> movies = converter.convertAll(files);
        updateActiveResultMap(files);
        notifyObserversWithResults(movies);

    }

    public void listTvShows() {
        List<XGUI_Item> tvs = new LinkedList<XGUI_Item>();

        notifyObserversWithResults(tvs);
    }

    public void listPersianMedia() {
        List<XGUI_Item> perItems = new LinkedList<XGUI_Item>();

        notifyObserversWithResults(perItems);
    }

    public void listDocumentaries() {
        List<XGUI_Item> docs = new LinkedList<XGUI_Item>();

        notifyObserversWithResults(docs);
    }

    /**
     * populating activeResultMap with new results
     *
     * @param results
     */
    private void updateActiveResultMap(List<MediaFile> results) {
        activeResultMap.clear();
        for (MediaFile f : results) {
            activeResultMap.put(new Integer(f.getName().hashCode()), f);
        }
    }
}

class XGUI_Item_Converter {

    XGUI_Item_Converter() {
    }

    XGUI_Item convert(MediaFile file) {
        XGUI_Item item = new XGUI_Item();
        item.setKey(file.getName().hashCode());
        item.setName(file.getMediaName());
        item.setYear(getMovieYear(file.getName()));
        return item;
    }

    List<XGUI_Item> convertAll(List<MediaFile> files) {
        List<XGUI_Item> items = new LinkedList<XGUI_Item>();
        for (MediaFile f : files) {
            items.add(convert(f));
        }
        return items;
    }

    String getMovieYear(String name) {
        String year = "Unknown";
        if (name.contains("(y")) {

            year = name.substring(name.indexOf("(y") + 2, name.indexOf("(y") + 6);
        }

        return year;
    }
}
