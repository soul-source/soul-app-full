/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.report.model;

import hwcore.modules.java.src.library.database.RecordSet;
import hwcore.modules.java.src.library.database.TableData;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import hwcore.modules.java.src.library.database.querybuilders.SqlQueryBuilder;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.common.MyQueryHandler;

public class HandlerReportsQuery extends MyQueryHandler {

    public HandlerReportsQuery() {
        super(EntityModelReport.WithRel.I());
    }

    @Override
    public TableData loadData(String location) {

        QueryBuilder search = MyQueryHandler.getQb();
        if (!location.isEmpty()) {
            search = MyQueryHandler.getQb();
            search.qbBuildName(EntityModelReport.I().LUOGO.getPath())
                    .qbCompare(location);
        }

        QueryBuilder additional = MyQueryHandler.getQb().groupBy()
                .qbBuildName(EntityModelReport.I().ID_SEGNALAZIONE.getPath())
                .orderBy()
                .qbBuildName(EntityModelReport.I().DATA_PUBBLICAZIONE.getPath())
                .qbOrderType(SqlQueryBuilder.SqlOrderType.DESC);

        return super.loadData(additional.toString(), search.toString());
    }

    public PreparedStatement insertReport(String address, String reportType, String reportDescription, String picture,
            String geoloc, Date date) {

        String query = "INSERT INTO report (coordinates, description, publication_date, place, id_subtype)"
                + "VALUES(?,?,?,?,?)";

        PreparedStatement ps = this.getStatement(query);
        try {
            ps.setString(1, geoloc);
            ps.setString(2, reportDescription);
            ps.setDate(3, date);
            ps.setString(4, address);
            ps.setInt(5, Integer.parseInt(reportType));
        } catch (SQLException ex) {
            Logger.getLogger(HandlerReportsQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return this.executeStatement(ps);
    }

    public List<RecordSet> selectReport(String id) {
        String fName = MyQueryHandler.getQb().qbBuildName(EntityModelReport.I().ID_SEGNALAZIONE.getPath()).toString();

        return this.loadData("", fName + "=" + id).getRecords();
    }
}
