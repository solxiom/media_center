/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.logic.XGUI_Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author kavan
 */
public class MiddlePanel extends JPanel {

    //tools
    private XGUI_Controller controller;
    // Variables declaration - do not modify
    private javax.swing.JScrollPane leftScrollPane;
    private JPanel leftSP_Panel;
    private HashMap<Integer, Color> itemStyleCach;
    private javax.swing.JPanel rightPanel;
    private InfoPanel infoPanel;
    private ListItem selectedItem;

    public MiddlePanel(XGUI_Controller controller) {
        itemStyleCach = new HashMap<Integer, Color>();
        initMiddlePanel();
        this.controller = controller;

    }

    public InfoPanel getInfoPanel() {
        return this.infoPanel;
    }

    public void setResults(List<ListItem> items) {
        leftSP_Panel.removeAll();
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
            itemStyleCach.put(new Integer(item.getXGUI_Item().getKey()), item.getBackground());
            setItemHandlers(item);
            leftSP_Panel.add(item);
        }
        this.updateUI();

    }

    private void setItemHandlers(final ListItem item) {

        MouseAdapter adapter = new ItemMouseAdapter(item, itemStyleCach);
        item.addMouseListener(adapter);
        item.getNameLabel().addMouseListener(adapter);
        item.getYearLabel().addMouseListener(adapter);
    }

    private void initMiddlePanel() {

        leftSP_Panel = new JPanel();
        leftScrollPane = new JScrollPane(leftSP_Panel);
        rightPanel = new JPanel();
        infoPanel = new InfoPanel();

        this.setBackground(new java.awt.Color(204, 204, 204));

        leftScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        leftSP_Panel.setLayout(new BoxLayout(leftSP_Panel, BoxLayout.Y_AXIS));


        setRightPanelLayout();
        setMiddlePanelLayout();
    }

    private void setMiddlePanelLayout() {
        javax.swing.GroupLayout middlePanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(middlePanelLayout);
        middlePanelLayout.setHorizontalGroup(
                middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(middlePanelLayout.createSequentialGroup()
                .addComponent(leftScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()));
        middlePanelLayout.setVerticalGroup(
                middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(leftScrollPane)
                .addGroup(middlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()));
    }

    private void setRightPanelLayout() {
        BoxLayout boxLay = new BoxLayout(rightPanel, BoxLayout.LINE_AXIS);
        rightPanel.setLayout(boxLay);
        rightPanel.add(Box.createRigidArea(new Dimension(20, rightPanel.getHeight())));

        rightPanel.add(infoPanel);
        rightPanel.add(Box.createRigidArea(new Dimension(20, rightPanel.getHeight())));
    }

    class ItemMouseAdapter extends MouseAdapter {

        final ListItem item;
        final HashMap<Integer, Color> colorCach;

        ItemMouseAdapter(ListItem item, HashMap<Integer, Color> colorCach) {
            this.item = item;
            this.colorCach = colorCach;

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
            controller.findItemInfo(item.getXGUI_Item().getKey());
            if (selectedItem != null) {
                selectedItem.setBackground(colorCach.get(selectedItem.getXGUI_Item().getKey()));
            }
            selectedItem = item;
            selectedItem.setBackground(Color.green);


        }

        @Override
        public void mouseEntered(MouseEvent evt) {
            evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
            if (item != selectedItem) {
                item.setBackground(Color.YELLOW);
            }
            item.getNameLabel().setForeground(Color.red);
            item.getYearLabel().setForeground(Color.red);
        }

        @Override
        public void mouseExited(MouseEvent evt) {
            evt.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            if (item != selectedItem) {
                item.setBackground(colorCach.get(item.getXGUI_Item().getKey()));
            }
            item.getNameLabel().setForeground(Color.black);
            item.getYearLabel().setForeground(Color.black);
        }
    }
}
