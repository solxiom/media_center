/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filesystem;

/**
 *
 * @author kavan
 */
public enum TypeX {

    CATEGORY_DIR, VIDEO, AUDIO, DOCUMENT, OPTIONS_DIR, MEDIA_DIR, UNKNOWN, IMAGE,DOCUMENT_DIR;

    @Override
    public String toString() {

        return this.name().toString().charAt(0) + this.name().toString().substring(1).toLowerCase();
    }
}
