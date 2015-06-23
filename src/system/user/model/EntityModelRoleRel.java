/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.user.model;

import static hwcore.modules.java.src.library.common.Singleton.I;
import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import system.common.MyEntityModel;

public class EntityModelRoleRel extends MyEntityModel {

    public FieldModel ID_INCARICO,
            ID_UTENTE;

    protected EntityModelRoleRel() {
        super("role_rel");

        this.createFields(
            ID_INCARICO = new RelField("task_id", this, EntityModelRole.I().ID_INCARICO),
            ID_UTENTE = new RelField("user_id_user", this, EntityModelUserProfile.I().ID_UTENTE)       
        );
    }
    
    public static EntityModelRoleRel I() {
        return I(EntityModelRoleRel.class);
    }
}
