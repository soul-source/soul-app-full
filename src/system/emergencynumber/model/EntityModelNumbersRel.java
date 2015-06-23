/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.emergencynumber.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import system.common.MyEntityModel;
import system.emergency.model.EntityModelSubtype;

public class EntityModelNumbersRel extends MyEntityModel {

    public FieldModel NUMERO,
            ID_UTENTE;

    protected EntityModelNumbersRel() {
        super("emergency_number_rel");

        this.createFields(NUMERO = new RelField("number", this, EntityModelNumbers.I().NUMERO),
            ID_UTENTE = new RelField("subtype", this, EntityModelSubtype.I().ID_SOTTOTIPO)
        );
    }
    
    public static EntityModelNumbersRel I() {
        return I(EntityModelNumbersRel.class);
    }
}
