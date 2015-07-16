/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.comment.model;

import hwcore.modules.java.src.library.database.TableData;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public HandlerCommentsQuery() {
        super(EntityModelComments.WithRel.I());
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
        qb = updateCommentsNum(qb, false);

        return qb;
    }

    @Override
    public QueryBuilder buildInsertQuery(TableData tableData, String[] tablesToUpdate) {
        QueryBuilder qb = super.buildInsertQuery(tableData, tablesToUpdate);
        qb = updateCommentsNum(qb, true);

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

    public PreparedStatement insertComment(String comment, String idReport, int idUser, Date date) {
        String query = "INSERT INTO comment (publication_date, message, id_user, id_report)"
                + "VALUES(?,?,?,?)";

        PreparedStatement ps = this.getStatement(query);

        try {
            ps.setDate(1, date);
            ps.setString(2, comment);
            ps.setInt(3, idUser);
            ps.setInt(4, Integer.parseInt(idReport));
        } catch (SQLException ex) {
            Logger.getLogger(HandlerCommentsQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.executeStatement(ps);

    }
    
    public PreparedStatement deleteComment(int id) {
        String query = "DELETE FROM comment WHERE id_comment="+id;

        PreparedStatement ps = this.getStatement(query);

        return this.executeStatement(ps);
    }
}
