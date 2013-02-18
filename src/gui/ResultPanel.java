/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.logic.ListItemContainer;
import gui.logic.ListItemMouseAdapter;
import gui.logic.XGUI_Controller;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.util.HashMap;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author kavan
 */
public class ResultPanel extends JScrollPane implements ListItemContainer{

        private JPanel leftSP_Panel;
        private HashMap<Integer, Color> itemStyleCache;
        private ListItem selectedItem;
        private XGUI_Controller controller;

        public ResultPanel(XGUI_Controller controller) {
            super();
            initComponents();
            this.controller = controller;
            this.selectedItem = null;
        }

        public XGUI_Controller getController() {
            return controller;
        }

        public ListItem getSelectedItem() {
            return this.selectedItem;
        }

        public void setSelectedItem(ListItem item) {
            this.selectedItem = item;
        }

        public HashMap<Integer, Color> getStyleCache() {
            return this.itemStyleCache;
        }
        
        
        

        private void initComponents() {
            leftSP_Panel = new JPanel();
            leftSP_Panel.setLayout(new BoxLayout(leftSP_Panel, BoxLayout.Y_AXIS));
            this.setViewportView(leftSP_Panel);
            this.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        }

        public void setResults(List<ListItem> items) {
            leftSP_Panel.removeAll();
            itemStyleCache = new HashMap<Integer, Color>();
            boolean binary = true;
            for (ListItem item : items) {
                item.setOpaque(true);
                if (binary) {
                    item.setBackground(Color.LIGHT_GRAY);
                    binary = false;
                } else {
                    item.setBackground(Color.WHITE);
                    binary = true;
                }
                itemStyleCache.put(new Integer(item.getXGUI_Item().getKey()), item.getBackground());

                setItemHandlers(item);
                leftSP_Panel.add(item);
            }
            this.revalidate();
            this.repaint();

        }

        private void setItemHandlers(final ListItem item) {

//            MouseAdapter adapter = new ItemMouseAdapter(item, itemStyleCache);
            MouseAdapter adapter = new ListItemMouseAdapter(this,item);
            item.addMouseListener(adapter);
            item.getNameLabel().addMouseListener(adapter);
            item.getYearLabel().addMouseListener(adapter);
        }
    }
