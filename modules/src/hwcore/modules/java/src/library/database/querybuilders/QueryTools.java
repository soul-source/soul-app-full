/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.database.querybuilders;

import java.util.List;

public class QueryTools {
    /**
     * Da utilizzare per creare i parametri di una query su tabelle relazionali
     *
     * @param sql query di inserimento da completare
     * @param list lista che contiene i dati da inserire
     */
    public static <T> String prepareSqlRelationParameters(String sql, List<? super T> list) {
        int i = 0;
        for (i = 0; i < list.size(); i++) {
            // crea la lista di parametri per lo statement
            sql = sql.concat(" ( ? , ? )");
            // inserisce la virgola fino al penultimo loop
            if (i + 1 < list.size()) {
                sql = sql.concat(",");
            }
        }

        return sql;
    }
}
