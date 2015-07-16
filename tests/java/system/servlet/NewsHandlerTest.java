/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.servlet;

import hwcore.modules.java.src.library.database.RecordSet;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import system.main.controller.ControllerMain;

/**
 *
 * @author giuseppe
 */
public class NewsHandlerTest {
    
    public NewsHandlerTest() {
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
     * Test of handleNewsGet method, of class NewsHandler.
     */
    @Test
    public void testHandleNewsGet() throws Exception {
        System.out.println("handleNewsGet");
        ControllerMain.I();
        
        ArrayList<RecordSet> result = NewsHandler.handleNewsGet(null, null, "");
        assertTrue(!result.isEmpty());
    }
    
}
