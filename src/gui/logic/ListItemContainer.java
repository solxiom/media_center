/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.logic;

import gui.ListItem;
import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author kavan
 */
public interface ListItemContainer {
    
    public XGUI_Controller getController();
    
    public ListItem getSelectedItem();
    
    public void setSelectedItem(ListItem item);
    
    public HashMap<Integer, Color> getStyleCache();
}
