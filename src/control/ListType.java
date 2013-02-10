/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author kavan
 */
public enum ListType {

    MOVIES_LIST, TVS_LIST, DOC_LIST, PERSIAN_LIST;

    @Override
    public String toString() {
        return this.name();
    }
}
