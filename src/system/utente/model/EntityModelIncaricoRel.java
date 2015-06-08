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
        super("Puo avere");

        this.createFields(
            ID_INCARICO = new RelField("Incarico_id", this, EntityModelIncarico.I().ID_INCARICO),
            ID_UTENTE = new RelField("Utente_idUtente", this, EntityModelUtenteProfile.I().ID_UTENTE)
        );
    }
    
    public static EntityModelIncaricoRel I() {
        return I(EntityModelIncaricoRel.class);
    }
}
