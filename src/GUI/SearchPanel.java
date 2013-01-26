/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import control.XGUI_Controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author kavan
 */
public class SearchPanel extends JPanel {

    // Variables declaration - do not modify
    //tools
    XGUI_Controller controller;
    //components
    private javax.swing.JComboBox categoryCombo;
    private javax.swing.JLabel categoryLb;
    private javax.swing.JLabel searchBt;
    private javax.swing.JCheckBox searchCheck;
    private RoundedJTextField searchField;
    private javax.swing.JComboBox yearCombo;
    private javax.swing.JLabel yearLb;
    private javax.swing.JComboBox genreCombo;
    private javax.swing.JLabel genreLb;
    //other variables
    final String searchFieldTip;

    public SearchPanel(XGUI_Controller controller) {
        searchFieldTip = "Search with keyword!";
        this.controller = controller;
        initSearchPanel();
    }

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void initSearchPanel() {


        searchField = new RoundedJTextField();
        searchField.setFont(null);
        searchField.setFont(new Font("Serif", Font.BOLD, 14));
        searchField.setForeground(Color.GRAY);

        searchField.setText(searchFieldTip);
        searchField.setEditable(false);
        searchField.setFocusable(false);
        searchField.addFocusListener(new FocusListener() {
            

            public void focusGained(FocusEvent e) {
                JTextField field = (JTextField) e.getSource();
                if (field.getText().equals(searchFieldTip)) {
                    field.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                JTextField field = (JTextField) e.getSource();
                if (field.getText().equals("")) {
                    field.setText(searchFieldTip);
                }
            }
        });
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        searchCheck = new javax.swing.JCheckBox();
        searchCheck.setSelected(false);
        searchCheck.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JCheckBox box = (JCheckBox) e.getSource();
                if (box.isSelected()) {
                    searchField.setEditable(true);
                    searchField.setFocusable(true);
                    
                } else {
                    searchField.setEditable(false);
                    searchField.setFocusable(false);
                    searchField.setText(searchFieldTip);
                    
                }
            }
        });

        yearLb = new javax.swing.JLabel();
        categoryLb = new javax.swing.JLabel();
        genreLb = new javax.swing.JLabel();
        yearCombo = new javax.swing.JComboBox();
        categoryCombo = new javax.swing.JComboBox();
        genreCombo = new javax.swing.JComboBox();


        searchBt = new javax.swing.JLabel();



        yearLb.setText("Year:");
        yearLb.setFont(new Font("Serif", Font.BOLD, 11));

        categoryLb.setText("Category:");
        categoryLb.setFont(new Font("Serif", Font.BOLD, 11));

        genreLb.setText("Genre:");
        genreLb.setFont(new Font("Serif", Font.BOLD, 11));
        
        
        yearCombo.setModel(new javax.swing.DefaultComboBoxModel(controller.getSearchYears()));
        
        categoryCombo.setModel(new javax.swing.DefaultComboBoxModel(controller.getSearchCategories()));

        genreCombo.setModel(new javax.swing.DefaultComboBoxModel(controller.getSearchGeneres()));

        searchBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xsearch4_64.png")));
        searchBt.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                new IconChanger().change((JLabel) evt.getSource(), "/img/xsearch4_64_sw.png");
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                new IconChanger().change((JLabel) evt.getSource(), "/img/xsearch4_64.png");
            }
        });

        setSearchPanelLayout();
    }

    private void setSearchPanelLayout() {
        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
                searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchPanelLayout.createSequentialGroup()
                .addComponent(searchCheck)
                .addGap(30, 30, 30)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(searchPanelLayout.createSequentialGroup()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                .addComponent(genreLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(genreCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                .addComponent(yearLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchPanelLayout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(searchBt)))))
                .addGap(249, 249, 249)));
        searchPanelLayout.setVerticalGroup(
                searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchPanelLayout.createSequentialGroup()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(searchCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(genreLb)
                .addComponent(genreCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(categoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(categoryLb))
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(yearLb)
                .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(searchPanelLayout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBt)))
                .addGap(15, 15, 15)));
    }
}
