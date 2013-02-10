/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.logic;

import gui.beans.XGUI_Item;
import gui.beans.XProcessType;
import java.util.List;
import service.domain.DataObject;

/**
 *
 * @author kavan
 */
public interface XGUI_Observer {

    public void updateResults(List<XGUI_Item> results);

    public void updateInfo(DataObject info);
    
    public void startInProcessState(XProcessType type);
    
    public void stopInProcessState(XProcessType type);
    

}
