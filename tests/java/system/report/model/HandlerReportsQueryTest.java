/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.report.model;

import hwcore.modules.java.src.library.database.RecordSet;
import hwcore.modules.java.src.library.database.TableData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
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
public class HandlerReportsQueryTest {
    
    public HandlerReportsQueryTest() {
        ControllerMain.I();
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
     * Test of loadData method, of class HandlerReportsQuery.
     */
    @Test
    public void testLoadData() {
        System.out.println("loadData");
        HandlerReportsQuery instance = new HandlerReportsQuery(EntityModelReport.WithOnlyEmergency.I());
        TableData result = instance.loadData("", "");
        assertTrue(result.getRecords().size()>0);
    }

    /**
     * Test of insertReport method, of class HandlerReportsQuery.
     */
    @Test
    public void testInsertReport() {
        /*System.out.println("insertReport");
        String address = "";
        String reportType = "";
        String reportDescription = "";
        String picture = "";
        String geoloc = "";
        Date date = null;
        HandlerReportsQuery instance = null;
        PreparedStatement expResult = null;
        PreparedStatement result = instance.insertReport(address, reportType, reportDescription, picture, geoloc, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of selectReport method, of class HandlerReportsQuery.
     */
    @Test
    public void testSelectReport() {
        System.out.println("selectReport");
        String id = "1";
        HandlerReportsQuery instance = new HandlerReportsQuery(EntityModelReport.WithRel.I());
        List<RecordSet> result = instance.selectReport(id);
        assertTrue(result.size()==1);
    }
    
}
