/*
 * To changeLabelIcon this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.logic;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author kavan
 */
public class XGUI_IconChanger {

    public XGUI_IconChanger() {
    }

    public void changeLabelIcon(JLabel lab, String iconUrl) {
        lab.setIcon(new ImageIcon(getClass().getResource(iconUrl)));
    }

    public void changeLabelFontColor(Component[] comps, Component target, Color col, Color defaultCol) {

        JLabel l = null;

        for (Component c : comps) {
            try {
                l = (JLabel) c;
            } catch (Exception e) {
                l = null;
            }


            if (c.equals(target)) {
//                if (target.getForeground() != col) {
                target.setForeground(col);
//                } else {
//                    target.setForeground(defaultCol);
//                }

            } else if (l != null && !l.equals(target)) {
                l.setForeground(defaultCol);
            }

        }


    }
}
