/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.numeriemergenza.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import system.common.MyEntityModel;
import system.emergenze.model.EntityModelSottotipo;

public class EntityModelNumeriRel extends MyEntityModel {

    public FieldModel NUMERO,
            ID_UTENTE;

    protected EntityModelNumeriRel() {
        super("Relativo");

        this.createFields(
            NUMERO = new RelField("numero", this, EntityModelNumeri.I().NUMERO),
            ID_UTENTE = new RelField("sottotipo", this, EntityModelSottotipo.I().ID_SOTTOTIPO)
        );
    }
    
    public static EntityModelNumeriRel I() {
        return I(EntityModelNumeriRel.class);
    }
}
