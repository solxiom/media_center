/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import GUI.beans.XGUI_Item;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kavan
 */
public class XGUI_ControllerImpl implements XGUI_Controller{

    List<XGUI_Observer> observers;

    
    public XGUI_ControllerImpl() {
        this.observers = new LinkedList<XGUI_Observer>();
    }
       
    
    public void registerObserver(XGUI_Observer obs) {
        observers.add(obs);
    }

    public void unregisterObserver(XGUI_Observer obs) {
        observers.remove(obs);
    }

    public void notifyObserversWithResults(List<XGUI_Item> results) {
        for(XGUI_Observer obs: observers)
        {
            obs.updateResults(results);
        }
    }

    public void notifyObserversWithItemInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] getSearchGeneres() {
        String[] genres = {"<--All-->","Action","Drama","Romance","Thriller","SciFi","Horror","Comedy","Biography","History","Crime"};
        return genres;
    }

    public String[] getSearchCategories() {
        String[] categories = {"<--All-->","Movies","TvShows","Documentaries","Persian Media"};
        return categories;
    }

    public String[] getSearchYears() {
        List<String> years = new ArrayList<String>();
        years.add("<--All-->");
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = year; i >= 1900; i--){
            years.add(i+"");
        }
        return years.toArray(new String[]{});
    }

    public void listMovies() {
        List<XGUI_Item> movies = new LinkedList<XGUI_Item>();
        
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
    
    
    
}
