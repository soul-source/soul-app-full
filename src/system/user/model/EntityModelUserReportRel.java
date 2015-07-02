package system.user.model;

import static hwcore.modules.java.src.library.common.Singleton.I;
import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import system.common.MyEntityModel;
import system.report.model.EntityModelReport;

public class EntityModelUserReportRel extends MyEntityModel {

    public FieldModel ID_USER,
            ID_REPORT;

    protected EntityModelUserReportRel() {
        super("user_report_rel");

        this.createFields(
                ID_USER = new RelField("user_id_user", this, EntityModelUserProfile.I().ID_UTENTE),
                ID_REPORT = new RelField("report_id_report", this, EntityModelReport.I().ID_SEGNALAZIONE)
        );
    }

    public static EntityModelUserReportRel I() {
        return I(EntityModelUserReportRel.class);
    }
}
