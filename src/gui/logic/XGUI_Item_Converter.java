/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.logic;

import gui.beans.XGUI_Item;
import java.util.LinkedList;
import java.util.List;
import service.Tools;
import service.filesystem.MediaFile;

/**
 *
 * @author kavan
 */
public class XGUI_Item_Converter {
     public XGUI_Item_Converter() {
        }

       public XGUI_Item convert(MediaFile file) {
            XGUI_Item item = new XGUI_Item();
            item.setKey(file.getName().hashCode());
            item.setName(file.getMediaName());
            item.setYear(Tools.getMovieYear(file.getName()));
            return item;
        }

       public List<XGUI_Item> convertAll(List<MediaFile> files) {
            List<XGUI_Item> items = new LinkedList<XGUI_Item>();
            for (MediaFile f : files) {
                items.add(convert(f));
            }
            return items;
        }
}
