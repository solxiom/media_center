/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imdb.service;

import imdb.domain.DataObject;
import imdb.domain.IdSearchOptions;
import imdb.domain.TitleSearchOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kavan
 */
public class DataServiceTest {
    
    public DataServiceTest() {
    }   
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDataById method, of class DataService.
     */
    @Test
    public void testGetDataById() {
//        tt1129445
        System.out.println("getDataById");
       String serverURL = "http://imdbapi.org";
        String expResultStr = "[Hilary Swank, Richard Gere,"
                + " Ewan McGregor, Christopher Eccleston, Joe Anderson, Cherry "
                + "Jones, Mia Wasikowska, Aaron Abrams, Dylan Roberts, Scott Yaphe,"
                + " Tom Fairfoot, Ryann Shane, William Cuddy, Elizabeth Shepherd, "
                + "Richard Donat]";
        IdSearchOptions options = new IdSearchOptions("tt1129445");
      
        DataService instance = new DataServiceImpl();
        DataObject result = instance.getDataById(serverURL, options);
        assertEquals(expResultStr, result.getActors().toString());
    }

    /**
     * Test of getDataByTitle method, of class DataService.
     */
    @Test
    public void testGetDataByTitle() {

        System.out.println("getDataByTitle");
        String serverURL = "http://imdbapi.org";
        String expResultStr = "[Hilary Swank, Richard Gere,"
                + " Ewan McGregor, Christopher Eccleston, Joe Anderson, Cherry "
                + "Jones, Mia Wasikowska, Aaron Abrams, Dylan Roberts, Scott Yaphe,"
                + " Tom Fairfoot, Ryann Shane, William Cuddy, Elizabeth Shepherd, "
                + "Richard Donat]";
        TitleSearchOptions options = new TitleSearchOptions("amelia","2009");
        options.setOffset("0");
        DataService instance = new DataServiceImpl();
        DataObject result = instance.getDataByTitle(serverURL, options);
        assertEquals(expResultStr, result.getActors().toString());
      
    }

   
}
