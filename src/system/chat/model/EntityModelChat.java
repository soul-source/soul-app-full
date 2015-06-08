/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.chat.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;

public class EntityModelChat extends MyEntityModel {

    public FieldModel ID_UTENTE,
            ID_CHAT,
            TESTO,
            DATA_INVIO,
            NUMERO_PARTECIPANTI;

    protected EntityModelChat() {
        super("Chat"); // table

        this.createFields(
                ID_UTENTE = EntityModelMessaggio.I().ID_UTENTE,
                ID_CHAT = new HiddenField("idChat", this),
                DATA_INVIO = EntityModelMessaggio.I().DATA_INVIO,
                TESTO = EntityModelMessaggio.I().TESTO,
                NUMERO_PARTECIPANTI = new VisualName("Numero_partecipanti", "Numero Partecipanti", this)
        );
    }

    public static EntityModelChat I() {
        return I(EntityModelChat.class);
    }
}
