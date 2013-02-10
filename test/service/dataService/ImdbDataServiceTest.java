/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service.dataService;

import service.dataService.ImdbDataService;
import service.DataService;
import service.domain.DataObject;
import service.domain.IdSearchOptions;
import service.domain.TitleSearchOptions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.DataConverter;

/**
 *
 * @author kavan
 */
public class ImdbDataServiceTest {

    public ImdbDataServiceTest() {
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
        DataConverter converter = new DataObjectConverterImpl();
        DataService instance = new ImdbDataService(new ImdbServer(), converter);
        DataObject result = instance.getDataById(serverURL, options);
        assertEquals(expResultStr, result.getCast().toString());
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
        TitleSearchOptions options = new TitleSearchOptions("amelia", "2009");
        options.setOffset("0");
        DataConverter converter = new DataObjectConverterImpl();
        DataService instance = new ImdbDataService(new ImdbServer(), converter);
        DataObject result = instance.getDataByTitle(serverURL, options);
        assertEquals(expResultStr, result.getCast().toString());

    }
    //Benton, Louisiana, USA

    /**
     * Test of getDataByTitle method, of class DataService.
     */
    @Test
    public void testGetDataByTitle2() {

        System.out.println("getDataByTitle");
        String serverURL = "http://imdbapi.org";
        String expResultStr = "";
        TitleSearchOptions options = new TitleSearchOptions("Beyond a Reasonable Doubt");
        options.setOffset("0");
        DataConverter converter = new DataObjectConverterImpl();
        DataService instance = new ImdbDataService(new ImdbServer(), converter);
        DataObject result = instance.getDataByTitle(serverURL, options);
        assertEquals(expResultStr, result.getRuntime().toString());

    }
}
