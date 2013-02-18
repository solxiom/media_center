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
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
        super("Media Center V-1.0");
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
        if (type == XProcessType.RETRIEVE_INFO) {
            info_inProc = new InProcessAnime(500, type);
            info_inProc.start();
        }
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
                        clearInProcessPulse(proc_type);
                        break;
                    }
                }

            }
        };
        return new Thread(info_inProcess, "info_inProcess");
    }

    private void sendInProcessPulse(XProcessType type) {
        if (type == XProcessType.RETRIEVE_INFO) {
            middlePanel.getInfoPanel().setInProcessPulse();
        } else if (type == XProcessType.LIST_MEDIA) {
        }
    }
    private void sendInProcessMessage(XProcessType type, String msg){
         if (type == XProcessType.RETRIEVE_INFO) {
            middlePanel.getInfoPanel().setInProcessMessage(msg);
        } else if (type == XProcessType.LIST_MEDIA) {
        }
    }
    private void clearInProcessPulse(XProcessType type){
         if (type == XProcessType.RETRIEVE_INFO) {
            middlePanel.getInfoPanel().clearInfoPanel();        
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
    private void setXFrameLayout(){
//        BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        BorderLayout layout = new BorderLayout();
//        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        
        JPanel categoryContainer = new JPanel();
        categoryContainer.setLayout(new BorderLayout());
        categoryContainer.add(categoryPanel, BorderLayout.WEST);
        
        JPanel searchContainer = new JPanel();
        searchContainer.setLayout(new BorderLayout());
       
        searchContainer.add(Box.createRigidArea(new Dimension(10, 30)), BorderLayout.NORTH);
        searchContainer.add(searchPanel, BorderLayout.WEST);
        
        JPanel topPanel = new JPanel();                
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(categoryContainer);
        topPanel.add(Box.createRigidArea(new Dimension(100, 5)));       
        topPanel.add(searchContainer);
       
                
        this.getContentPane().setLayout(layout);
        this.add(topPanel,BorderLayout.NORTH);
        
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }
    private void setXFrameLayout_auto_generated() {
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
    class InProcessAnime extends Thread{
        private final XProcessType proc_type;
        private final long cycle_sleep;
        String[] tst ={"Searching...","connecting to data server","searching with id","Parsing JSON Response","Searching in JSON"};
        int timec, msg_index;
        InProcessAnime(final long cycle_sleep, final XProcessType proc_type){
            this.cycle_sleep = cycle_sleep;
            this.proc_type = proc_type;
            timec=0;
            msg_index = 0;
        }
       
            @Override
            public void run() {

                while (true) {

                    try {
                        this.sleep(cycle_sleep);
                        timec +=cycle_sleep;
                        if(timec > 2000){
                            msg_index++;
                            timec=0;
                            
                        }
                        if(msg_index >= tst.length){
                            msg_index =0;
                        }
                        sendInProcessPulse(proc_type);
                        sendInProcessMessage(proc_type, tst[msg_index]);

                    } catch (InterruptedException ie) {
                        clearInProcessPulse(proc_type);
                        break;
                    }
                }

            }
   
    }
}
