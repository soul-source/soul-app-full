/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.emergency.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;

public class EntityModelType extends MyEntityModel {

    public FieldModel ID_TIPO,
            ENTI_COINVOLTE;

    protected EntityModelType() {
        super("emergency_type"); // table

        this.createFields(
                ID_TIPO = new HiddenField("id_type", this),
                ENTI_COINVOLTE = new VisualName("authorities_involved", "Enti Coinvolte", this)
        );
    }

    public static EntityModelType I() {
        return I(EntityModelType.class);
    }
}
