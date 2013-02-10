/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.beans;

/**
 *
 * @author kavan
 */
public enum XProcessType {
    LIST_MEDIA, RETRIEVE_INFO;
        @Override
        public String toString() {
            return this.name();
        }
}
