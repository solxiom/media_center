/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author kavan
 */
public class IconChanger {

    public IconChanger() {
        
    }
      
    
    public void change(JLabel lab, String iconUrl){
        lab.setIcon(new ImageIcon(getClass().getResource(iconUrl)));
    }
}
