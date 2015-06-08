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
        super("Utente");

        this.createFields(
                ID_UTENTE = new HiddenField("idUtente", this),
                EMAIL = new VisualName("email", "Email", this),
                DATA_DI_NASCITA = new VisualName("data_di_nascita", "Data di nascita", this),
                NOME = new VisualName("nome", "Nome", this),
                COGNOME = new VisualName("cognome", "Cognome", this),
                COD_FISCALE = new VisualName("codice_fiscale", "Cod. Fiscale", this),
                VIA = new VisualName("via", "Via", this),
                CITTA = new VisualName("citta", "Città", this),
                CAP = new VisualName("cap", "Cap", this),
                STATO = new VisualName("stato", "Stato", this),
                PASSWORD = new VisualName("password", "Password", this)
        );
    }

    public static EntityModelUtenteProfile I() {
        return I(EntityModelUtenteProfile.class);
    }
}
