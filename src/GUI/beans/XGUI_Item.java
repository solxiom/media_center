/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.beans;

/**
 *
 * @author kavan
 */
public class XGUI_Item {

    private int key;
    private String name;
    private String year;

    public XGUI_Item() {
    }

    public XGUI_Item(int key, String name, String year) {
        this.key = key;
        this.name = name;
        this.year = year;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        return this.key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().isInstance(this)) {
            return (obj.hashCode() == this.hashCode());
        }
        return false;
    }
}
