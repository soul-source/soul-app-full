/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.news.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.RestrictedField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;
import system.common.PermissionsTypes;

public class EntityModelNews extends MyEntityModel {

    public FieldModel ID_NOTIZIA,
            TITOLO,
            ARTICOLO,
            DATA_PUB,
            ID_UTENTE;

    protected EntityModelNews() {
        super("news"); // table

        this.createFields(
                ID_NOTIZIA =  new HiddenField("id_news", this),
                TITOLO = new VisualName("title", "Titolo", this),
                ARTICOLO =  new VisualName("article", "Articolo", this),
                DATA_PUB = new RestrictedField("publication_date", "Data Pubblicazione", this, PermissionsTypes.readOnly()),
                ID_UTENTE = new HiddenField("id_user", this)
        );
    }

    public static EntityModelNews I() {
        return I(EntityModelNews.class);
    }
}
