/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.utente.model;

import static hwcore.modules.java.src.library.common.Singleton.I;
import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import system.common.MyEntityModel;

public class EntityModelIncaricoRel extends MyEntityModel {

    public FieldModel ID_INCARICO,
            ID_UTENTE;

    protected EntityModelIncaricoRel() {
        super("role_rel");

        this.createFields(
            ID_INCARICO = new RelField("task_id", this, EntityModelIncarico.I().ID_INCARICO),
            ID_UTENTE = new RelField("user_id_user", this, EntityModelUtenteProfile.I().ID_UTENTE)       
        );
    }
    
    public static EntityModelIncaricoRel I() {
        return I(EntityModelIncaricoRel.class);
    }
}
