/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.user.model;

import hwcore.modules.java.src.library.database.EntityModel;
import system.common.MyQueryHandler;

public class HandlerUtenteQuery extends MyQueryHandler {

    public HandlerUtenteQuery(EntityModel model) {
        super(model, UserRS.class);
    }

    public UserRS loadUtente(int id) {
        return (UserRS) this.loadData(
                getQb().qbBuildName(EntityModelUtente.I().ID_UTENTE.getPath()).qbCompare(id).toString()
        ).getRecords().get(0);
    }

    public UserRS loadUtente(String email, String password) {
        return (UserRS) this.loadData(
                null,
                getQb().qbBuildName(EntityModelUtente.I().EMAIL.getPath())
                .qbCompare(email).toString(),
                getQb().qbBuildName(EntityModelUtente.I().PASSWORD.getPath())
                .qbCompare(password).toString()
        ).getRecords().get(0);
    }
}
