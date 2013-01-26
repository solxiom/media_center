/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author kavan
 */
public class XGUI_ControllerImpl implements XGUI_Controller{

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
    
}
