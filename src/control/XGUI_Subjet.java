/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import GUI.beans.XGUI_Item;
import java.util.List;
import service.domain.DataObject;

/**
 *
 * @author kavan
 */
public interface XGUI_Subjet {
    
    public void registerObserver(XGUI_Observer obs);
    
    public void unregisterObserver(XGUI_Observer obs);
    
    public void notifyObserversWithResults(List<XGUI_Item> results);
    
    public void notifyObserversWithItemInfo(DataObject info);
    
    public void putInProcessState_info();
    
     public void putInProcessState_results();
    
   
    
}
