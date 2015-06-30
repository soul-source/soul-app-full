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
import java.util.ArrayList;
import java.util.List;
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
                + "VALUES('" 
                + geoloc + "','" 
                + reportDescription + "','" 
                + date + "','" 
                + address + "',"
                + reportType+")";
        return this.executeStatement(query);
    }
    
    public List<RecordSet> selectReport(String id) {
        String fName=MyQueryHandler.getQb().qbBuildName(EntityModelReport.I().ID_SEGNALAZIONE.getPath()).toString();
        
        return this.loadData("", fName+"="+id).getRecords();
    }
}
