/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author kavan
 */
public class MiddlePanel extends JPanel {

    // Variables declaration - do not modify
    private javax.swing.JScrollPane leftScrollPane;
    private JPanel leftSP_Panel;
    private HashMap<Integer, Color> itemStyleCach;
    private javax.swing.JPanel rightInfoPanel;

    public MiddlePanel() {
        itemStyleCach = new HashMap<Integer, Color>();
        initMiddlePanel();

    }

    public void setResults(List<ListItem> items) {
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
        rightInfoPanel = new JPanel();

        this.setBackground(new java.awt.Color(204, 204, 204));

        leftScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        rightInfoPanel.setBackground(new java.awt.Color(204, 204, 255));
        leftSP_Panel.setLayout(new BoxLayout(leftSP_Panel, BoxLayout.Y_AXIS));


        setRightInfoPanelLayout();
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
                .addComponent(rightInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()));
        middlePanelLayout.setVerticalGroup(
                middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(leftScrollPane)
                .addGroup(middlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rightInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()));
    }

    private void setRightInfoPanelLayout() {
        javax.swing.GroupLayout rightInfoPanelLayout = new javax.swing.GroupLayout(rightInfoPanel);
        rightInfoPanel.setLayout(rightInfoPanelLayout);
        rightInfoPanelLayout.setHorizontalGroup(
                rightInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 511, Short.MAX_VALUE));
        rightInfoPanelLayout.setVerticalGroup(
                rightInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 521, Short.MAX_VALUE));
    }
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
    }

    @Override
    public void mouseEntered(MouseEvent evt) {
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
        item.setBackground(Color.YELLOW);
        item.getNameLabel().setForeground(Color.red);
        item.getYearLabel().setForeground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent evt) {
        evt.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        item.setBackground(colorCach.get(item.getXGUI_Item().getKey()));
        item.getNameLabel().setForeground(Color.black);
        item.getYearLabel().setForeground(Color.black);
    }
}
