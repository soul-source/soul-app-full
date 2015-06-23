/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.news.model;

import hwcore.modules.java.src.library.common.DateTools;
import hwcore.modules.java.src.library.database.TableData;
import hwcore.modules.java.src.library.database.querybuilders.QueryBuilder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import system.common.MyQueryHandler;

public class HandlerNewsQuery extends MyQueryHandler {

    public HandlerNewsQuery() {
        super(EntityModelNews.I());
    }

    public TableData loadData(Date start, Date end) {
        QueryBuilder qb = getQb();
        
        DateTools dt=new DateTools("yyyy-mm-dd");
        
        try {
            qb.qbBuildName(EntityModelNews.I().DATA_PUB.getPath())
                    .between(
                            dt.fromDate(start).toString(dt.getFormat()), 
                            dt.fromDate(end).toString(dt.getFormat())
                    );
        } catch (Exception ex) {
            Logger.getLogger(HandlerNewsQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

        return super.loadData(qb.toString());
    }
}
