/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.comment.model;

import hwcore.modules.java.src.library.database.TableData;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import system.comment.controller.ControllerJFrameComments;
import system.common.MyQueryHandler;
import system.report.model.EntityModelReport;

public class HandlerCommentsQuery extends MyQueryHandler {

    private int idSegnalazione;
    private ControllerJFrameComments ctrl;

    public HandlerCommentsQuery(int idSegnalazione, ControllerJFrameComments ctrl) {
        super(EntityModelComments.I());
        this.idSegnalazione = idSegnalazione;
        this.ctrl = ctrl;
    }

    @Override
    public void updateData(TableData tableData, String[] tablesToUpdate) {
        super.updateData(tableData, tablesToUpdate);
        this.ctrl.getOwner().refreshList();
        this.ctrl.getOwner().getOwner().refreshList();
    }

    @Override
    public QueryBuilder buildDeleteQuery(TableData tableData, String[] tablesToUpdate) {
        QueryBuilder qb = super.buildDeleteQuery(tableData, tablesToUpdate);
        qb=updateCommentsNum(qb,false);

        return qb;
    }

    @Override
    public QueryBuilder buildInsertQuery(TableData tableData, String[] tablesToUpdate) {
        QueryBuilder qb = super.buildInsertQuery(tableData, tablesToUpdate);
        qb=updateCommentsNum(qb,true);

        return qb;
    }

    private QueryBuilder updateCommentsNum(QueryBuilder qb, boolean add) {
        if (qb != null) {
            EntityModelReport report = EntityModelReport.I();
            qb.update()
                    .qbBuildName(report.getPath())
                    .set()
                    .qbBuildName(report.NUMERO_COMMENTI.getPath())
                    .qbAssign()
                    .qbBuildName(report.NUMERO_COMMENTI.getPath())
                    .qbAdd(add ? "+1" : "-1")
                    .where()
                    .qbBuildName(report.ID_SEGNALAZIONE.getPath())
                    .qbCompare(idSegnalazione)
                    .qbCloseQuery();
        }
        
        return qb;
    }
}
