/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.emergency.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;
import system.emergencynumber.model.EntityModelNumbersRel;

public class EntityModelSubtype extends MyEntityModel {
    
    public static class WithRel extends EntityModelSubtype {

        public FieldModel ENTI,
                NUMERO;

        public WithRel() {
            super();
            
            this.createFields(
                    ENTI = EntityModelType.I().ENTI_COINVOLTE
            );
        }

        public static WithRel I() {
            return (WithRel) I(WithRel.class).mergeFields(EntityModelNumbersRel.I());
        }

    }

    public FieldModel ID_SOTTOTIPO,
            NAME,
            DESCRIZIONE,
            LIVELLO_PRIORITA,
            ID_TIPO;

    protected EntityModelSubtype() {
        super("emergency_subtype"); // table

        this.createFields(ID_SOTTOTIPO = new HiddenField("id_subtype", this),
                NAME = new VisualName("name", "Cause", this),
                DESCRIZIONE = new VisualName("description", "Descrizione", this),
                LIVELLO_PRIORITA = new VisualName("priority_level", "Livello Priorità", this),
                ID_TIPO = new RelField("id_type", this, EntityModelType.I().ID_TIPO)
        );
    }

    public static EntityModelSubtype I() {
        return I(EntityModelSubtype.class);
    }
}
