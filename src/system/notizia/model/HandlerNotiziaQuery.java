/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.notizia.model;

import hwcore.modules.java.src.library.common.DateTools;
import hwcore.modules.java.src.library.database.TableData;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.common.MyQueryHandler;

public class HandlerNotiziaQuery extends MyQueryHandler {

    public HandlerNotiziaQuery() {
        super(EntityModelNotizia.I());
    }

    public TableData loadData(Date start, Date end) {
        QueryBuilder qb = getQb();
        
        DateTools dt=new DateTools("yyyy-mm-dd");
        
        try {
            qb.qbBuildName(EntityModelNotizia.I().DATA_PUB.getPath())
                    .between(
                            dt.fromDate(start).toString(dt.getFormat()), 
                            dt.fromDate(end).toString(dt.getFormat())
                    );
        } catch (Exception ex) {
            Logger.getLogger(HandlerNotiziaQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return super.loadData(qb.toString());
    }
}
