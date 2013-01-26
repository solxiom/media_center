/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JPanel;

/**
 *
 * @author kavan
 */
public class MiddlePanel extends JPanel {
    
    // Variables declaration - do not modify
    private javax.swing.JScrollPane leftScrollPane;

    private javax.swing.JPanel rightInfoPanel;

    public MiddlePanel() {
        initMiddlePanel();
    }

    private void initMiddlePanel() {
     
        leftScrollPane = new javax.swing.JScrollPane();
        rightInfoPanel = new javax.swing.JPanel();

        this.setBackground(new java.awt.Color(204, 204, 204));

        leftScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        rightInfoPanel.setBackground(new java.awt.Color(204, 204, 255));

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
