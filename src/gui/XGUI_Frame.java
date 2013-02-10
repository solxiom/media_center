/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.logic.XGUI_Info_Parser;
import gui.beans.XGUI_Item;
import gui.logic.XGUI_Controller;
import control.XGUI_ControllerImpl;
import gui.beans.XProcessType;
import gui.logic.XGUI_Observer;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import service.domain.DataObject;

/**
 *
 * @author kavan
 */
public class XGUI_Frame extends JFrame implements XGUI_Observer {

    // Variables declaration - do not modify
    //tools
    private XGUI_Controller controller;
    private XGUI_Info_Parser parser;
    //components
    private CategoryPanel categoryPanel;
    private SearchPanel searchPanel;
    private MiddlePanel middlePanel;
    private BottomPanel bottomPanel;
    private Thread info_inProc;

    // End of variables declaration
    public XGUI_Frame() {
        super("Media Center v1.0");
        controller = new XGUI_ControllerImpl();
        parser = new XGUI_Info_Parser();
        controller.registerObserver(this);
        initXFrame();

    }

    public void updateResults(List<XGUI_Item> results) {
        List<ListItem> items = new LinkedList<ListItem>();
        for (XGUI_Item bean : results) {
            ListItem item = new ListItem(bean);
            items.add(item);
        }
        if (middlePanel != null) {
            middlePanel.setResults(items);
        }
    }

    public void updateInfo(DataObject info) {

        middlePanel.getInfoPanel().setInfo(info, parser);

    }

    public void startInProcessState(XProcessType type) {
        stopInProcessState(type);
        info_inProc = inProcessAnime(500, type);
        info_inProc.start();
    }

    public void stopInProcessState(XProcessType type) {
        if (type == XProcessType.RETRIEVE_INFO
                && info_inProc != null) {
            info_inProc.interrupt();
            info_inProc = null;
        }
    }

    private Thread inProcessAnime(final long cycle_sleep, final XProcessType proc_type) {
        Runnable info_inProcess = new Runnable() {
            @Override
            public void run() {

                while (true) {

                    try {
                        Thread.sleep(cycle_sleep);
                        sendInProcessPulse(proc_type);

                    } catch (InterruptedException ie) {
                        break;
                    }
                }

            }
        };
        return new Thread(info_inProcess, "info_inProcess");
    }

    private void sendInProcessPulse(XProcessType type) {
        if (type == XProcessType.RETRIEVE_INFO) {
            middlePanel.getInfoPanel().changeInProcessIcon();
        } else if (type == XProcessType.LIST_MEDIA) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initXFrame() {


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        categoryPanel = new CategoryPanel(controller);//initCategoryPanel();
        middlePanel = new MiddlePanel(controller);//initMiddlePanel();
        bottomPanel = new BottomPanel();//initBottomPanel();
        searchPanel = new SearchPanel(controller);//initSearchPanel();


        setXFrameLayout();

        pack();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DesignFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DesignFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DesignFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DesignFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XGUI_Frame().setVisible(true);
            }
        });
    }

    private void setXFrameLayout() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(categoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(middlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(categoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(middlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
    }
}
