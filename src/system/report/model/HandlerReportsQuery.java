/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.report.model;

import hwcore.modules.java.src.library.database.TableData;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import hwcore.modules.java.src.library.database.querybuilders.SqlQueryBuilder;
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
}
