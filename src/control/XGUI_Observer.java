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
public interface XGUI_Observer {

    public void updateResults(List<XGUI_Item> results);

    public void updateInfo(DataObject info);
}
