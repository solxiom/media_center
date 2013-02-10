/*
 * To changeLabelIcon this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.logic.XGUI_IconChanger;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kavan
 */
public class BottomPanel extends JPanel {

    // Variables declaration - do not modify
    private javax.swing.JLabel settingsLb;

    public BottomPanel() {
        initBottomPanel();
    }

    private void initBottomPanel() {

        settingsLb = new javax.swing.JLabel();

        settingsLb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xsettings_64.png")));
        settingsLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                new XGUI_IconChanger().changeLabelIcon((JLabel) evt.getSource(), "/img/xsettings_64_sw.png");
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                new XGUI_IconChanger().changeLabelIcon((JLabel) evt.getSource(), "/img/xsettings_64.png");
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                evt.getComponent().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        setBottomPanelLayout();
    }

    private void setBottomPanelLayout() {
        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
                bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingsLb, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(983, Short.MAX_VALUE)));
        bottomPanelLayout.setVerticalGroup(
                bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingsLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }
}
