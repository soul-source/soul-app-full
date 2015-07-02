package system.user.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.*;
import system.common.MyEntityModel;
import system.report.model.EntityModelReport;

public class EntityModelUserProfile extends MyEntityModel {

    public static class WithIncarico extends EntityModelUserProfile {

        public FieldModel TIPO;

        public WithIncarico() {
            super();

            this.createFields(
                    TIPO = EntityModelRole.I().TIPO
            );
        }

        public static WithIncarico I() {
            return (WithIncarico) I(WithIncarico.class).mergeFields(EntityModelRoleRel.I());
        }

    }

    public static class WithReport extends EntityModelUserProfile {

        public FieldModel TIPO;

        public WithReport() {
            super();
        }

        public static WithReport I() {
            return (WithReport) I(WithReport.class).mergeFields(EntityModelUserReportRel.I());
        }

    }

    public FieldModel ID_UTENTE,
            EMAIL,
            DATA_DI_NASCITA,
            NOME,
            COGNOME,
            COD_FISCALE,
            VIA,
            CITTA,
            CAP,
            STATO,
            PASSWORD,
            SESSION_TOKEN;

    protected EntityModelUserProfile() {
        super("user");

        this.createFields(
                ID_UTENTE = new HiddenField("id_user", this),
                EMAIL = new VisualName("email", "Email", this),
                DATA_DI_NASCITA = new VisualName("birthdate", "Data di nascita", this),
                NOME = new VisualName("name", "Nome", this),
                COGNOME = new VisualName("last_name", "Cognome", this),
                COD_FISCALE = new VisualName("tax_code", "Cod. Fiscale", this),
                VIA = new VisualName("street", "Via", this),
                CITTA = new VisualName("city", "Città", this),
                CAP = new VisualName("cap", "Cap", this),
                STATO = new VisualName("country", "Stato", this),
                PASSWORD = new VisualName("password", "Password", this),
                SESSION_TOKEN = new HiddenField("session_token", this)
        );
    }

    public static EntityModelUserProfile I() {
        return I(EntityModelUserProfile.class);
    }
}
