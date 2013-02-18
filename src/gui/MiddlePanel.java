/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.logic.XGUI_Controller;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author kavan
 */
public class MiddlePanel extends JPanel {

    //tools
    private XGUI_Controller controller;
    // Variables declaration - do not modify
    private ResultPanel resultPanel;
    private JPanel rightPanel;
    private InfoPanel infoPanel;

    public MiddlePanel(XGUI_Controller controller) {
        this.controller = controller;
        resultPanel = new ResultPanel(controller);
        rightPanel = new JPanel();
        infoPanel = new InfoPanel();
        this.setBackground(new java.awt.Color(204, 204, 204));
        setRightPanelLayout();
        setMiddlePanelLayout();
        this.setDoubleBuffered(true);

    }

    public InfoPanel getInfoPanel() {
        return this.infoPanel;
    }

    public ResultPanel getResultPanel() {
        return this.resultPanel;
    }

    private void setMiddlePanelLayout() {
        javax.swing.GroupLayout middlePanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(middlePanelLayout);
        middlePanelLayout.setHorizontalGroup(
                middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(middlePanelLayout.createSequentialGroup()
                .addComponent(resultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()));
        middlePanelLayout.setVerticalGroup(
                middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(resultPanel)
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
