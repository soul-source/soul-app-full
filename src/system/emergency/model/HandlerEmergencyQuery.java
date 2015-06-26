
package system.emergency.model;

import hwcore.modules.java.src.library.database.RecordSet;
import java.util.List;
import system.common.MyQueryHandler;
import system.report.model.EntityModelReport;

public class HandlerEmergencyQuery extends MyQueryHandler  {
    
    public HandlerEmergencyQuery() {
        super(EntityModelSubtype.I());
    } 
    
    public List<RecordSet> selectHealthEmergency() {
        return this.loadData("", "`emergency_subtype`.`id_type`=3").getRecords();
    }
}
