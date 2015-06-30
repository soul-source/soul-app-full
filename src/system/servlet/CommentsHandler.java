package system.servlet;

import hwcore.modules.java.src.library.database.RecordSet;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
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
import system.comment.model.HandlerCommentsQuery;
import system.emergency.model.HandlerEmergencyQuery;
import system.report.model.HandlerReportsQuery;
import static system.servlet.UserHandler.getValidSession;

public class CommentsHandler {

    public static ArrayList<RecordSet> handleCommentsGet(HttpServletRequest req, HttpServletResponse resp, String type)
            throws IOException {
        HandlerCommentsQuery handle = new HandlerCommentsQuery();

        switch (type) {
            case "list":
                String id = req.getParameter("id");
                return new ArrayList<>(handle.loadData("`comment`.`id_report`=" + id).getRecords());
        }

        return null;
    }

    static void handlePostComment(HttpServletRequest req, HttpServletResponse resp, String type) throws IOException {
        UserHandler.UserSession us = getValidSession(req);
        if (us == null) {
            resp.getWriter().write("false");
            return;
        }

        int pId = us.getUserId();

        HandlerCommentsQuery handle = new HandlerCommentsQuery();

        String comment = req.getParameter("comment");
        String id = req.getParameter("idReport");
        Date dt = new Date();
        java.sql.Date sDate = new java.sql.Date(dt.getTime());
        if (handle.insertComment(comment, id, pId, sDate) != null) {
            resp.getWriter().write("true");
        }

        resp.getWriter().write("false");
    }
}
