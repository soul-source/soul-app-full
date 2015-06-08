/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.notifiche.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;
import system.utente.model.EntityModelUtente;

public class EntityModelNotifiche extends MyEntityModel {

    public FieldModel ID,
            MESSAGGIO,
            DATA,
            LETTA,
            ID_UTENTE;

    protected EntityModelNotifiche() {
        super("notification");

        this.createFields(
                ID = new HiddenField("id_notification", this),
                MESSAGGIO = new VisualName("message", "Messaggio", this),
                DATA = new VisualName("receiving_date", "Data Ricezione", this),
                LETTA = new VisualName("read", "Letta", this),
                ID_UTENTE = new RelField("id_user", this, EntityModelUtente.I().ID_UTENTE)
        );
    }

    public static EntityModelNotifiche I() {
        return I(EntityModelNotifiche.class);
    }
}
