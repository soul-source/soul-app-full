/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.utente.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.*;
import system.common.MyEntityModel;

public class EntityModelUtenteProfile extends MyEntityModel {

    public static class WithIncarico extends EntityModelUtenteProfile {

        public FieldModel TIPO;

        public WithIncarico() {
            super();

            this.createFields(
                    TIPO = EntityModelIncarico.I().TIPO
            );
        }

        public static WithIncarico I() {
            return (WithIncarico) I(WithIncarico.class).mergeFields(EntityModelIncaricoRel.I());
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
            PASSWORD;

    protected EntityModelUtenteProfile() {
        super("user");

        this.createFields(
                ID_UTENTE = new HiddenField("id_user", this),
                EMAIL = new VisualName("email", "Email", this),
                DATA_DI_NASCITA = new VisualName("born_date", "Data di nascita", this),
                NOME = new VisualName("name", "Nome", this),
                COGNOME = new VisualName("surname", "Cognome", this),
                COD_FISCALE = new VisualName("tax_code", "Cod. Fiscale", this),
                VIA = new VisualName("street", "Via", this),
                CITTA = new VisualName("city", "Città", this),
                CAP = new VisualName("cap", "Cap", this),
                STATO = new VisualName("country", "Stato", this),
                PASSWORD = new VisualName("password", "Password", this)
        );
    }

    public static EntityModelUtenteProfile I() {
        return I(EntityModelUtenteProfile.class);
    }
}
