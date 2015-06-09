/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */

package system.comment.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import hwcore.modules.java.src.library.database.fielddecorators.RestrictedField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;
import system.common.PermissionsTypes;
import system.user.model.EntityModelUtente;

/**
 *
 *
 */
public class EntityModelCommenti extends MyEntityModel {

    public FieldModel ID_COMMENTO,
            DATA_INVIO,
            MESSAGGIO,
            ID_UTENTE,
            ID_SEGNALAZIONE;

    protected EntityModelCommenti() {
        super("comment");

        this.createFields(
                ID_COMMENTO = new HiddenField("id_comment", this),
                DATA_INVIO = new RestrictedField("publication_date", "Data", this, PermissionsTypes.readOnly()),
                MESSAGGIO = new VisualName("message", "Messaggio",this),
                ID_UTENTE = new RelField("id_user", this,EntityModelUtente.I().ID_UTENTE),
                ID_SEGNALAZIONE = new RelField("id_report", this,EntityModelUtente.I().ID_UTENTE)
        );
    }

    public static EntityModelCommenti I() {
        return I(EntityModelCommenti.class);
    }
}
