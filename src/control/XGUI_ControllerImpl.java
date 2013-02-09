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
import service.DataConverter;
import service.DataService;
import service.HostService;
import service.JsonSearcher;
import service.JsonServer;
import service.dataService.DataObjectConverterImpl;
import service.dataService.OmdbDataService;
import service.dataService.OmdbSearcher;
import service.dataService.OmdbServer;
import service.filesystem.MediaFile;
import service.ftp.FTPFileManager;
import service.ftp.FtpService;
import service.dataService.TMDataService;
import service.dataService.TMSearcher;
import service.dataService.TMServer;
import service.domain.DataObject;
import service.domain.TitleSearchOptions;

/**
 *
 * @author kavan
 */
public class XGUI_ControllerImpl implements XGUI_Controller {

    //tools
    private List<XGUI_Observer> observers;
    private HashMap<Integer, MediaFile> activeResultMap;
    private XGUI_Item_Converter converter;
    private HostService hostService;
    private DataService dataService;
    private DataConverter data_converter;
    private JsonServer json_server;
    private JsonSearcher json_searcher;
    private String dataServiceUrl;
    private String[] dataApiKey;
    private Thread infoInProc, itemInfo;

    public XGUI_ControllerImpl() {
        this.observers = new LinkedList<XGUI_Observer>();
        activeResultMap = new HashMap<Integer, MediaFile>();
        converter = new XGUI_Item_Converter();
        hostService = new FtpService(new FTPFileManager());

//        setUpAndConfig_tomatoes();
        setUpAndConfig_omdb();

    }

    private void setUpAndConfig_omdb() {

        dataServiceUrl = "http://omdbapi.com";
        data_converter = new DataObjectConverterImpl();
        json_server = new OmdbServer();
        json_searcher = new OmdbSearcher(json_server, dataServiceUrl);
        dataService = new OmdbDataService(json_server, data_converter, json_searcher);
    }

    private void setUpAndConfig_tomatoes() {
        dataApiKey = new String[2];
        dataApiKey[0] = "4qcmmmshcx94zrh76gc2eyez";// active with solxiom account
        dataApiKey[1] = "rquwhx4xrfss7vxuc6bje64h";//active with ali.doori account
        dataServiceUrl = "http://api.rottentomatoes.com/api/public/v1.0";
        //        dataServiceUrl = "http://imdbapi.org";


        data_converter = new DataObjectConverterImpl();
        json_server = new TMServer();
        json_searcher = new TMSearcher(json_server, dataServiceUrl, dataApiKey);
//        dataService = new ImdbDataService(json_server,data_converter);

        dataService = new TMDataService(dataApiKey, json_server, json_searcher, data_converter, true);
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

    public void notifyObserversWithItemInfo(DataObject info) {
        for (XGUI_Observer obs : observers) {
            obs.updateInfo(info);
        }
    }

    public void putObserversInInfoProcessState() {

        infoInProc = new InfoInProcess();
        infoInProc.start();

    }

    public void putObserversInResultsProcessState() {
        for (XGUI_Observer obs : observers) {
            obs.setResultsInProcess();
        }
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

    public void findItemInfo(int itemCode) {

        putObserversInInfoProcessState();
        itemInfo = new ItemInfo(5000, itemCode);
        itemInfo.start();

    }

    public void listMovies() {

        List<MediaFile> files = hostService.listMovies();
        List<XGUI_Item> movies = converter.convertAll(files);
        updateActiveResultMap(files);
        notifyObserversWithResults(movies);

    }

    public void listTvShows() {
        List<MediaFile> files = hostService.listTvShows();
        List<XGUI_Item> tvs = converter.convertAll(files);
        updateActiveResultMap(files);
        notifyObserversWithResults(tvs);
    }

    public void listPersianMedia() {
        List<MediaFile> files = hostService.listPersianItems();
        List<XGUI_Item> perItems = converter.convertAll(files);
        updateActiveResultMap(files);
        notifyObserversWithResults(perItems);
    }

    public void listDocumentaries() {
        List<MediaFile> files = hostService.listDocumentaries();
        List<XGUI_Item> docs = converter.convertAll(files);
        updateActiveResultMap(files);
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

    private String getMovieYear(String name) {
        String year = "Unknown";
        if (name.contains("(y")) {

            year = name.substring(name.indexOf("(y") + 2, name.indexOf("(y") + 6);
        }

        return year;
    }

    class InfoInProcess extends Thread {

        InfoInProcess() {
            if (infoInProc != null) {
                infoInProc.interrupt();
                infoInProc = null;
            }
        }

        @Override
        public void run() {
            boolean go = true;
            long timer = 0;
            while (go) {

                try {
                    sleep(400);
                    timer += 400;
                    for (XGUI_Observer obs : observers) {
                        obs.setInfoInProcess();
                    }

                } catch (InterruptedException ie) {
                    go = false;
                }
            }

        }
    }

    class ItemInfo extends Thread {

        long mills;
        int itemCode;

        ItemInfo(long mills, int itemCode) {
            if (itemInfo != null) {
                itemInfo.interrupt();
                itemInfo = null;
            }
            this.mills = mills;
            this.itemCode = itemCode;


        }

        @Override
        public void run() {

            long timer = 0;


            try {
                sleep(mills);
                MediaFile item = activeResultMap.get(itemCode);
                String itemYear = getMovieYear(item.getName());
                if (itemYear.equalsIgnoreCase("Unknown")) {
                    itemYear = "";
                }
                TitleSearchOptions options = new TitleSearchOptions(item.getMediaName(), itemYear);
                DataObject info = dataService.getDataByTitle(dataServiceUrl, options);
                if (infoInProc != null) {
                    infoInProc.interrupt();
                    infoInProc = null;
                }
                notifyObserversWithItemInfo(info);

            } catch (InterruptedException ie) {
            }

            return;
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
    }
}
