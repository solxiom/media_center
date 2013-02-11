/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import gui.logic.XGUI_Controller;
import gui.logic.XGUI_Item_Converter;
import gui.logic.XGUI_Observer;
import gui.beans.XGUI_Item;
import gui.beans.XProcessType;
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
import service.Tools;
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
    private Thread info_thread, listMedia_thread;

    public XGUI_ControllerImpl(){
        this.observers = new LinkedList<XGUI_Observer>();
        activeResultMap = new HashMap<Integer, MediaFile>();
        converter = new XGUI_Item_Converter();
        hostService = new FtpService(new FTPFileManager());

        setUpAndConfig_omdb();
    }

    private void setUpAndConfig_omdb(){

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

    public void putObserversInProcessState(XProcessType procType) {
        for (XGUI_Observer obs : observers) {
            obs.startInProcessState(procType);
        }
    }

    public void removeObserversInProcessState(XProcessType procType) {
        for (XGUI_Observer obs : observers) {
            obs.stopInProcessState(procType);
        }
    }

    public String[] getSearchGeneres() {
        String[] genres = {"<--All-->", "Action", "Drama",
            "Romance", "Thriller", "SciFi", "Horror", "Comedy",
            "Biography", "History", "Crime"};
        return genres;
    }

    public String[] getSearchCategories() {
        String[] categories = {"<--All-->", "Movies",
            "TvShows", "Documentaries", "Persian Media"};
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
        putObserversInProcessState(XProcessType.RETRIEVE_INFO);
        stopProcessThread(XProcessType.RETRIEVE_INFO);
        info_thread = async_searchInfo(itemCode, 5000);
        info_thread.start();

    }

    public void listMovies() {
        startListMediaThread(ListType.MOVIES_LIST);
    }

    public void listTvShows() {
        startListMediaThread(ListType.TVS_LIST);
    }

    public void listPersianMedia() {
        startListMediaThread(ListType.PERSIAN_LIST);
    }

    public void listDocumentaries() {
        startListMediaThread(ListType.DOC_LIST);
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

    private void startListMediaThread(ListType listType){
        putObserversInProcessState(XProcessType.LIST_MEDIA);
        stopProcessThread(XProcessType.LIST_MEDIA);
        stopProcessThread(XProcessType.RETRIEVE_INFO);//we should also kill the info_thread if it's alive
        removeObserversInProcessState(XProcessType.RETRIEVE_INFO);
        listMedia_thread = async_listMedia(listType);
        listMedia_thread.start();
    }

    private void stopProcessThread(XProcessType procType) {

        if (procType == XProcessType.RETRIEVE_INFO
                && info_thread != null) {
            info_thread.interrupt();
            info_thread = null;
        } else if (procType == XProcessType.LIST_MEDIA
                && listMedia_thread != null) {
            listMedia_thread.interrupt();
            listMedia_thread = null;
        }
    }

    private List<MediaFile> listHostFiles(ListType type) {
        switch (type) {
            case MOVIES_LIST:
                return hostService.listMovies();

            case TVS_LIST:
                return hostService.listTvShows();

            case DOC_LIST:
                return hostService.listDocumentaries();

            case PERSIAN_LIST:
                return hostService.listPersianItems();
            default:
                return null;

        }

    }

    private Thread async_searchInfo(final int item_code, final long w) {
        Runnable info_th = new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(w);
                    MediaFile item = activeResultMap.get(item_code);
                    String itemYear = Tools.getMovieYear(item.getName());
                    if (itemYear.equalsIgnoreCase("Unknown")) {
                        itemYear = "";
                    }
                    TitleSearchOptions options = new TitleSearchOptions(item.getMediaName(), itemYear);
                    DataObject info = dataService.getDataByTitle(dataServiceUrl, options);
                    removeObserversInProcessState(XProcessType.RETRIEVE_INFO);
                    notifyObserversWithItemInfo(info);

                } catch (InterruptedException ie) {
                }
            }
        };

        return new Thread(info_th, "info_thread");

    }

    private Thread async_listMedia(final ListType type) {

        Runnable media_th = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    List<MediaFile> files = listHostFiles(type);
                    List<XGUI_Item> items = converter.convertAll(files);
                    updateActiveResultMap(files);
                    removeObserversInProcessState(XProcessType.LIST_MEDIA);
                    notifyObserversWithResults(items);
                } catch (InterruptedException ix) {
                }
            }
        };



        return new Thread(media_th, "list_media_thread");
    }
}
