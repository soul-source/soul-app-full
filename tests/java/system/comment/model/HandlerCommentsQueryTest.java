/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.comment.model;

import hwcore.modules.java.src.library.database.RecordSet;
import hwcore.modules.java.src.library.database.TableData;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class HandlerCommentsQueryTest {

    public HandlerCommentsQueryTest() {
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
     * Test of insertComment/deleteComment method, of class
     * HandlerCommentsQuery.
     */
    @Test
    public void testInsertDeleteComment() {
        System.out.println("insertComment");
        String comment = "ciao come stai?";
        String idReport = "1";
        int idUser = 1;
        Date date = new Date();

        HandlerCommentsQuery instance = new HandlerCommentsQuery();
        PreparedStatement result = instance.insertComment(comment, idReport, idUser, new java.sql.Date(date.getTime()));
        assertNotNull(result);

        try {
            ResultSet rs = result.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("deleteComment");

                HandlerCommentsQuery i2 = new HandlerCommentsQuery();
                PreparedStatement res = i2.deleteComment(id);
                assertNotNull(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HandlerCommentsQueryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of loadComment method, of class HandlerCommentsQuery.
     */
    @Test
    public void testLoadComment() {
        System.out.println("loadComment");
        HandlerCommentsQuery handle = new HandlerCommentsQuery();

        String id = "1";
        ArrayList<RecordSet> res = new ArrayList<>(handle.loadData("`comment`.`id_report`=" + id).getRecords());
        assertTrue(!res.isEmpty());
    }

}
