/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.chat.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.RestrictedField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;
import system.common.PermissionsTypes;

/**
 *
 *
 */
public class EntityModelMessaggio extends MyEntityModel {

    public FieldModel ID_UTENTE,
            ID_CHAT,
            DATA_INVIO,
            NOME_UTENTE,
            TESTO;

    protected EntityModelMessaggio() {
        super("Messaggio");

        this.createFields(
                ID_UTENTE = new HiddenField("idUtente", this),
                ID_CHAT = new HiddenField("idChat", this),
                DATA_INVIO = new RestrictedField("Data_invio", "Data messaggio", this, PermissionsTypes.readOnly()),
                NOME_UTENTE = new RestrictedField("Nome_utente", "Nome Utente", this, PermissionsTypes.readOnly()),
                TESTO = new VisualName("testo", "Messaggio", this)
        );
    }

    public static EntityModelMessaggio I() {
        return I(EntityModelMessaggio.class);
    }
}
