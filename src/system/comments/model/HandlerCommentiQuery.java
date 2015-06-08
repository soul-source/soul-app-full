/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.comments.model;

import hwcore.modules.java.src.library.database.TableData;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import system.comments.controller.ControllerJFrameComments;
import system.common.MyQueryHandler;
import system.segnalazioni.model.EntityModelSegnalazione;

public class HandlerCommentiQuery extends MyQueryHandler {

    private int idSegnalazione;
    private ControllerJFrameComments ctrl;

    public HandlerCommentiQuery(int idSegnalazione, ControllerJFrameComments ctrl) {
        super(EntityModelCommenti.I());
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
            EntityModelSegnalazione report = EntityModelSegnalazione.I();
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
