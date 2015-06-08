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
        super("emergency_number_rel");

        this.createFields(
            NUMERO = new RelField("number", this, EntityModelNumeri.I().NUMERO),
            ID_UTENTE = new RelField("subtype", this, EntityModelSottotipo.I().ID_SOTTOTIPO)
        );
    }
    
    public static EntityModelNumeriRel I() {
        return I(EntityModelNumeriRel.class);
    }
}
