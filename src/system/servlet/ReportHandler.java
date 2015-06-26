
package system.servlet;

import hwcore.modules.java.src.library.database.RecordSet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.emergency.model.HandlerEmergencyQuery;
import system.report.model.EntityModelReport;
import system.report.model.HandlerReportsQuery;


public class ReportHandler {
    
    public static void handleReportPost(HttpServletRequest req, HttpServletResponse resp, String type) 
            throws IOException{
        String address = req.getParameter("address");
        String reportType = req.getParameter("reportType");
        String reportDescription = req.getParameter("reportDescription");
        String picture =req.getParameter("picture");
        String geoloc = req.getParameter("(addressLat"+"-"+"addressLong)");
        Date dt = new Date();
        java.sql.Date sDate = new java.sql.Date(dt.getTime());
        //Date d = new ;
        
        HandlerReportsQuery handle = new HandlerReportsQuery();
        handle.insertReport(address,reportType,reportDescription,picture, geoloc, sDate);
        
    }
    
    public static ArrayList<RecordSet> handleReportGet(HttpServletRequest req, HttpServletResponse resp, String type) 
            throws IOException{
        HandlerEmergencyQuery handle = new HandlerEmergencyQuery();
        
        return new ArrayList<>(handle.selectHealthEmergency());
    }
    
}
