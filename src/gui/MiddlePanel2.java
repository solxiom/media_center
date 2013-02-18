/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.logic.ListItemContainer;
import gui.logic.ListItemMouseAdapter;
import gui.logic.XGUI_Controller;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author kavan
 */
public class MiddlePanel2 extends JPanel {

    //tools
    private XGUI_Controller controller;
    // Variables declaration - do not modify
//    private JScrollPane leftScrollPane;
    private ResultPanel leftScrollPane;
    private JPanel leftSP_Panel;
    private HashMap<Integer, Color> itemStyleCach;
    private JPanel rightPanel;
    private InfoPanel infoPanel;
    private ListItem selectedItem;
    private String inProcess_msg;

    public MiddlePanel2(XGUI_Controller controller) {
        itemStyleCach = new HashMap<Integer, Color>();
        this.controller = controller;
        initMiddlePanel();       
        this.setDoubleBuffered(true);
        inProcess_msg = "";
    }

    public void setInProcessMessage(String msg) {
        this.inProcess_msg = msg;
    }

    public String getInProcessMessage() {
        return this.inProcess_msg;
    }

    public InfoPanel getInfoPanel() {
        return this.infoPanel;
    }

    public void setResults(List<ListItem> items) {
//        leftSP_Panel.removeAll();
//        boolean binary = true;
//        for (ListItem item : items) {
//            item.setOpaque(true);
//            if (binary) {
//                item.setBackground(Color.LIGHT_GRAY);
//                binary = false;
//            } else {
//                item.setBackground(Color.WHITE);
//                binary = true;
//            }
//            itemStyleCach.put(new Integer(item.getXGUI_Item().getKey()), item.getBackground());
//            setItemHandlers(item);
//            leftSP_Panel.add(item);
//        }
//        this.revalidate();
//        this.repaint();
        leftScrollPane.setResults(items);


    }



    private void initMiddlePanel() {
        leftSP_Panel = new JPanel();
//        leftScrollPane = new JScrollPane(leftSP_Panel);
        leftScrollPane = new ResultPanel(controller);
        rightPanel = new JPanel();
        infoPanel = new InfoPanel();
        this.setBackground(new java.awt.Color(204, 204, 204));
//        leftScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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

   

    

   
}
