/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kavan
 */
public class ToolsTest {

    public ToolsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getRandomMember method, of class Tools.
     */
    @Test
    public void testGetRandomMember_StringArr() {
        System.out.println("getRandomMember");
//        String[] keys = null;
//        String expResult = "";
//        String result = Tools.getRandomMember(keys);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getRandomMember method, of class Tools.
     */
    @Test
    public void testGetRandomMember_intArr() {
        System.out.println("getRandomMember");
//        int[] keys = null;
//        int expResult = 0;
//        int result = Tools.getRandomMember(keys);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of bindUrlwithParameters method, of class Tools.
     */
    @Test
    public void testBindUrlwithParameters_1() {
        System.out.println("bindUrlwithParameters");
        String url = "http://solxiom.com/";
        String parameters = "?s=tt&x=24";
        String bind = "/";
        String expResult = "http://solxiom.com/?s=tt&x=24";

        String result = Tools.bindUrlwithParameters(url, parameters, bind);
        assertEquals(expResult, result);

    }

    /**
     * Test of bindUrlwithParameters method, of class Tools.
     */
    @Test
    public void testBindUrlwithParameters_2() {
        System.out.println("bindUrlwithParameters");
        String url = "http://solxiom.com/";
        String parameters = "?s=tt&x=24";
        String bind = null;
        String expResult = "http://solxiom.com/?s=tt&x=24";

        String result = Tools.bindUrlwithParameters(url, parameters, bind);
        assertEquals(expResult, result);

    }

    /**
     * Test of bindUrlwithParameters method, of class Tools.
     */
    @Test
    public void testBindUrlwithParameters_3() {
        System.out.println("bindUrlwithParameters");
        String url = "http://solxiom.com/";
        String parameters = "/?s=tt&x=24";
        String bind = "/";
        String expResult = "http://solxiom.com/?s=tt&x=24";

        String result = Tools.bindUrlwithParameters(url, parameters, bind);
        assertEquals(expResult, result);

    }

    /**
     * Test of bindUrlwithParameters method, of class Tools.
     */
    @Test
    public void testBindUrlwithParameters_4() {
        System.out.println("bindUrlwithParameters");
        String url = "http://solxiom.com";
        String parameters = "/?s=tt&x=24";
        String bind = "/";
        String expResult = "http://solxiom.com/?s=tt&x=24";

        String result = Tools.bindUrlwithParameters(url, parameters, bind);
        assertEquals(expResult, result);

    }
}
