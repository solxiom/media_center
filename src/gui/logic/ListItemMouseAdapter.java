/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.logic;

import gui.ListItem;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 *
 * @author kavan
 */
public class ListItemMouseAdapter extends MouseAdapter {

    final ListItem item;
    final HashMap<Integer, Color> colorCache;
    private ListItemContainer container;

    public ListItemMouseAdapter(ListItemContainer container, ListItem item) {
        this.item = item;
        this.colorCache = container.getStyleCache();
        this.container = container;


    }

    @Override
    public void mousePressed(MouseEvent evt) {
        item.getNameLabel().setFont(new Font("serif", 1, 16));
        item.getYearLabel().setFont(new Font("serif", 0, 12));
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        item.getNameLabel().setFont(new Font("serif", 2, 16));
        item.getYearLabel().setFont(new Font("serif", 1, 12));
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        container.getController().findItemInfo(item.getXGUI_Item().getKey());
        if (container.getSelectedItem() != null) {
            container.getSelectedItem().setBackground(colorCache.get(
                    container.getSelectedItem().getXGUI_Item().getKey()));
        }
        container.setSelectedItem(item);
        container.getSelectedItem().setBackground(Color.green);


    }

    @Override
    public void mouseEntered(MouseEvent evt) {
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));     
            if (container.getSelectedItem() == null 
                    || item != container.getSelectedItem()) {
                item.setBackground(Color.YELLOW);
            }
      
        item.getNameLabel().setForeground(Color.red);
        item.getYearLabel().setForeground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent evt) {
        evt.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            if (container.getSelectedItem() == null 
                    || item != container.getSelectedItem()) {
                item.setBackground(colorCache.get(item.getXGUI_Item().getKey()));
            }
      
        item.getNameLabel().setForeground(Color.black);
        item.getYearLabel().setForeground(Color.black);
    }
}
