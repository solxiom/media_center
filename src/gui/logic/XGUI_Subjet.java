/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.logic;

import gui.beans.XProcessType;
import gui.beans.XGUI_Item;
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
    
    public void putObserversInProcessState(XProcessType processType);
    
    public void removeObserversInProcessState(XProcessType processType);
    
}
