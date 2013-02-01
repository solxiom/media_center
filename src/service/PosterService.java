/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author kavan
 */
public interface PosterService {

    public String getPoster(String title, String year);

    public String getPosterById(String id);
}
