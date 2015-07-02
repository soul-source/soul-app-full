package system.report.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import hwcore.modules.java.src.library.database.fielddecorators.RestrictedField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;
import system.common.PermissionsTypes;
import system.emergency.model.EntityModelSubtype;
import system.user.model.EntityModelUserProfile;
import system.user.model.EntityModelUserReportRel;

public class EntityModelReport extends MyEntityModel {

    public static class WithRel extends EntityModelReport {

        public WithRel() {
            super();
        }

        public static WithRel I() {
            return (WithRel) I(WithRel.class).mergeFields(
                    EntityModelSubtype.WithRel.I()
            );
        }
    }
    
    public static class WithUserInfo extends EntityModelReport {

        public WithUserInfo() {
            super();
        }

        public static WithUserInfo I() {
            return (WithUserInfo) I(WithUserInfo.class).mergeFields(
                    EntityModelUserProfile.WithReport.I()
            );
        }
    }

    public static class WithOnlyEmergency extends EntityModelReport {

        public WithOnlyEmergency() {
            super();
        }

        public static WithOnlyEmergency I() {
            return (WithOnlyEmergency) I(WithOnlyEmergency.class).mergeFields(
                    EntityModelSubtype.I()
            );
        }
    }

    public FieldModel ID_SEGNALAZIONE,
            COORDINATE,
            DESCRIZIONE,
            NUMERO_COMMENTI,
            DATA_PUBBLICAZIONE,
            LUOGO,
            ID_SOTTOTIPO;

    protected EntityModelReport() {
        super("report"); // table

        this.createFields(ID_SEGNALAZIONE = new HiddenField("id_report", this),
                DESCRIZIONE = new VisualName("description", "Descrizione", this),
                NUMERO_COMMENTI = new RestrictedField("comments_number", "Numero Commenti", this, PermissionsTypes.readOnly()),
                DATA_PUBBLICAZIONE = new RestrictedField("publication_date", "Data pubblicazione", this, PermissionsTypes.readOnly()),
                LUOGO = new VisualName("place", "Luogo", this),
                COORDINATE = new VisualName("coordinates", "Coordinate", this),
                ID_SOTTOTIPO = new RelField("id_subtype", this, EntityModelSubtype.WithRel.I().ID_SOTTOTIPO)
        );
    }

    public static EntityModelReport I() {
        return I(EntityModelReport.class);
    }
}
