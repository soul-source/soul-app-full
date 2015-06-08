/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.utente.model;

import static hwcore.modules.java.src.library.common.Singleton.I;
import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;

public class EntityModelIncarico extends MyEntityModel {

    public FieldModel ID_INCARICO,
            TIPO;

    protected EntityModelIncarico() {
        super("Incarico");

        this.createFields(
            ID_INCARICO = new HiddenField("ID.Incarico", this),
            TIPO = new VisualName("Tipo", "Tipo Incarico", this)
        );
    }
    
    public static EntityModelIncarico I() {
        return I(EntityModelIncarico.class);
    }
}
