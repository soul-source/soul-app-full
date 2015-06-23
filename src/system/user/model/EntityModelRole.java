/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.user.model;

import static hwcore.modules.java.src.library.common.Singleton.I;
import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;

public class EntityModelRole extends MyEntityModel {

    public FieldModel ID_INCARICO,
            TIPO;

    protected EntityModelRole() {
        super("role");

        this.createFields(
            ID_INCARICO = new HiddenField("id_task", this),
            TIPO = new VisualName("type", "Tipo Incarico", this)
        );
    }
    
    public static EntityModelRole I() {
        return I(EntityModelRole.class);
    }
}
