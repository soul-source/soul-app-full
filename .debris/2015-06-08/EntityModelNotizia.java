/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.notizia.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.RestrictedField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;
import system.common.PermissionsTypes;

public class EntityModelNotizia extends MyEntityModel {

    public FieldModel ID_NOTIZIA,
            TITOLO,
            ARTICOLO,
            DATA_PUB,
            ID_UTENTE;

    protected EntityModelNotizia() {
        super("Notizia"); // table

        this.createFields(
                ID_NOTIZIA =  new HiddenField("idNotizia", this),
                TITOLO = new VisualName("titolo", "Titolo", this),
                ARTICOLO =  new VisualName("Articolo", "Articolo", this),
                DATA_PUB = new RestrictedField("Data_pubblicazione", "Data Pubblicazione", this, PermissionsTypes.readOnly()),
                ID_UTENTE = new HiddenField("idUtente", this)
        );
    }

    public static EntityModelNotizia I() {
        return I(EntityModelNotizia.class);
    }
}
