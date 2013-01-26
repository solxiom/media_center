/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kavan
 */
public class CategoryPanel extends JPanel {

    // Variables declaration - do not modify
    private javax.swing.JLabel docIconLb;
    private javax.swing.JLabel docLb;
    private javax.swing.JLabel mvIconLb;
    private javax.swing.JLabel mvLb;
    private javax.swing.JLabel perIconLb;
    private javax.swing.JLabel perLb;
    private javax.swing.JLabel tvsIconLb;
    private javax.swing.JLabel tvsLb;

    public CategoryPanel() {
        initCategoryPanel();


    }

    private void initCategoryPanel() {

        mvIconLb = new javax.swing.JLabel();
        perIconLb = new javax.swing.JLabel();
        docIconLb = new javax.swing.JLabel();
        tvsIconLb = new javax.swing.JLabel();
        tvsLb = new javax.swing.JLabel();
        mvLb = new javax.swing.JLabel();
        perLb = new javax.swing.JLabel();
        docLb = new javax.swing.JLabel();

        mvIconLb.setBackground(new java.awt.Color(204, 204, 204));
        mvIconLb.setForeground(new java.awt.Color(255, 102, 102));
        mvIconLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mvIconLb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xmv_128.png")));
        mvIconLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                new IconChanger().change((JLabel)evt.getSource(),"/img/xmv_128_sw.png");
            }
            @Override
            public void mouseReleased(MouseEvent evt) {
                new IconChanger().change((JLabel)evt.getSource(),"/img/xmv_128.png");
            }
        });


        perIconLb.setBackground(new java.awt.Color(204, 204, 204));
        perIconLb.setForeground(new java.awt.Color(255, 102, 102));
        perIconLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        perIconLb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xayatollah.png")));
        perIconLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                new IconChanger().change((JLabel)evt.getSource(),"/img/xayatollah_sw.png");
            }
            @Override
            public void mouseReleased(MouseEvent evt) {
                new IconChanger().change((JLabel)evt.getSource(),"/img/xayatollah.png");
            }
        });

        docIconLb.setBackground(new java.awt.Color(204, 204, 204));
        docIconLb.setForeground(new java.awt.Color(255, 102, 102));
        docIconLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        docIconLb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xdocu_128.png")));
        docIconLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                new IconChanger().change((JLabel)evt.getSource(),"/img/xdocu_128_sw.png");
            }
            @Override
            public void mouseReleased(MouseEvent evt) {
                new IconChanger().change((JLabel)evt.getSource(),"/img/xdocu_128.png");
            }
        });

        tvsIconLb.setBackground(new java.awt.Color(204, 204, 204));
        tvsIconLb.setForeground(new java.awt.Color(255, 51, 51));
        tvsIconLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tvsIconLb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/xtvs_128.png")));
        tvsIconLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                new IconChanger().change((JLabel)evt.getSource(),"/img/xtvs_128_sw.png");
            }
            @Override
            public void mouseReleased(MouseEvent evt) {
                new IconChanger().change((JLabel)evt.getSource(),"/img/xtvs_128.png");
            }
        });

        tvsLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tvsLb.setText("Tv Shows");
        tvsLb.setFont(new Font("Serif", Font.BOLD, 14));

        mvLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mvLb.setText("Movies");
        mvLb.setFont(new Font("Serif", Font.BOLD, 14));

        perLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        perLb.setText("Persian Media");
        perLb.setFont(new Font("Serif", Font.BOLD, 14));

        docLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        docLb.setText("Documentaries");
        docLb.setFont(new Font("Serif", Font.BOLD, 14));

        setCategoryPanelLayout();
    }

    private void setCategoryPanelLayout() {
        javax.swing.GroupLayout categoryPanelLayout = new javax.swing.GroupLayout(this);
        this.setLayout(categoryPanelLayout);
        categoryPanelLayout.setHorizontalGroup(
                categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoryPanelLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mvIconLb, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(mvLb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(categoryPanelLayout.createSequentialGroup()
                .addComponent(tvsLb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(perLb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(docLb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(categoryPanelLayout.createSequentialGroup()
                .addComponent(tvsIconLb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(perIconLb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(docIconLb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));
        categoryPanelLayout.setVerticalGroup(
                categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(categoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(perIconLb, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addComponent(mvIconLb)
                .addComponent(docIconLb, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(tvsIconLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(categoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(mvLb, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(tvsLb, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(perLb, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(docLb, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE)));
    }
}
