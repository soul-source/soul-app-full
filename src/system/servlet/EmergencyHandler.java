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

public class EmergencyHandler {

    public static ArrayList<RecordSet> handleEmergencyGet(HttpServletRequest req, HttpServletResponse resp, String type)
            throws IOException {
        HandlerEmergencyQuery handle = new HandlerEmergencyQuery();

        return new ArrayList<>(handle.selectHealthEmergency());
    }

}
