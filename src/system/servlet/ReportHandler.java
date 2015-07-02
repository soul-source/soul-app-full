package system.servlet;

import hwcore.modules.java.src.library.database.RecordSet;
import java.io.IOException;
import java.sql.PreparedStatement;
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
import static system.servlet.UserHandler.getValidSession;
import system.user.model.EntityModelUser;
import system.user.model.HandlerUserQuery;

public class ReportHandler {

    public static void handleReportPost(HttpServletRequest req, HttpServletResponse resp, String type)
            throws IOException {
        String address = req.getParameter("address");
        String reportType = req.getParameter("reportType");
        String reportDescription = req.getParameter("reportDescription");
        String picture = req.getParameter("picture");
        String geoloc = req.getParameter("addressLat") + "-" + req.getParameter("addressLong");
        Date dt = new Date();
        java.sql.Date sDate = new java.sql.Date(dt.getTime());

        HandlerReportsQuery handle = new HandlerReportsQuery(EntityModelReport.WithRel.I());
        PreparedStatement ps = handle.insertReport(address, reportType, reportDescription, picture, geoloc, sDate);
        if (ps != null) {
            try {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {

                    UserHandler.UserSession us = getValidSession(req);
                    if (us != null) {
                        int pId = us.getUserId();
                        HandlerUserQuery handleUser = new HandlerUserQuery(null);
                        handleUser.insertReportRel(pId, rs.getInt(1));
                    }

                    resp.getWriter().write(String.valueOf(rs.getInt(1)));
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReportHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        resp.getWriter().write("false");

    }

    public static ArrayList<RecordSet> handleReportGet(HttpServletRequest req, HttpServletResponse resp, String type)
            throws IOException {
        HandlerReportsQuery handle;

        switch (type) {
            case "list":
                handle = new HandlerReportsQuery(EntityModelReport.WithOnlyEmergency.I());

                return new ArrayList<>(handle.loadData("", "").getRecords());
            case "single":
                handle = new HandlerReportsQuery(EntityModelReport.WithRel.I());

                String id = req.getParameter("id");

                return new ArrayList<>(handle.selectReport(id));
        }

        return null;
    }

}
