/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.user.model;

import hwcore.modules.java.src.library.database.DbVar;
import hwcore.modules.java.src.library.database.DbId;
import hwcore.modules.java.src.library.database.RecordSet;
import hwcore.modules.java.src.library.database.TableData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class UserRS extends RecordSet {

    private boolean guest = false;

    public UserRS(ResultSet rs, TableData tableData) throws SQLException {
        super(rs, tableData);
    }

    public UserRS(LinkedHashMap<DbId, DbVar> values) {
        super(values);
    }

    // create a guest session
    public UserRS() {
        super(new LinkedHashMap<DbId, DbVar>());

        guest = true;
    }

    /*
     SHORTCUTS
     */
    public int getLevel() {
        return !guest
                ? (Integer) getValue(EntityModelUser.I().LIVELLO_UTENTE.getId())
                : -1;
    }

    public int getId() {
        return !guest ? (Integer) getValue(EntityModelUser.I().ID_UTENTE.getId())
                : -1;
    }

    public String getSessionToken() {
        Object o = getValue(EntityModelUser.I().SESSION_TOKEN.getId());
        if (o == null) {
            return null;
        }

        return o.toString();
    }

    public String getFullName() {
        return !guest
                ? getValue(EntityModelUser.I().NOME.getId())
                + " " + getValue(EntityModelUser.I().COGNOME.getId())
                : "Guest " + this.hashCode();
    }
}
