/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import GUI.ListItem;

/**
 *
 * @author kavan
 */
public interface XGUI_Controller extends XGUI_Subjet {

    public String[] getSearchGeneres();

    public String[] getSearchCategories();

    public String[] getSearchYears();

    public void listMovies();

    public void listTvShows();

    public void listPersianMedia();

    public void listDocumentaries();
    
    public void findItemInfo(int itemCode);
}
