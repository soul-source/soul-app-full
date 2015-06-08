/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.emergenze.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;
import system.numeriemergenza.model.EntityModelNumeriRel;

public class EntityModelSottotipo extends MyEntityModel {
    
    public static class WithRel extends EntityModelSottotipo {

        public FieldModel ENTI,
                NUMERO;

        public WithRel() {
            super();
            
            this.createFields(
                    ENTI = EntityModelTipo.I().ENTI_COINVOLTE
            );
        }

        public static WithRel I() {
            return (WithRel) I(WithRel.class).mergeFields(EntityModelNumeriRel.I());
        }

    }

    public FieldModel ID_SOTTOTIPO,
            CAUSE,
            DESCRIZIONE,
            LIVELLO_PRIORITA,
            ID_TIPO;

    protected EntityModelSottotipo() {
        super("emergency_subtype"); // table

        this.createFields(ID_SOTTOTIPO = new HiddenField("id_subtype", this),
                CAUSE = new VisualName("cause", "Cause", this),
                DESCRIZIONE = new VisualName("description", "Descrizione", this),
                LIVELLO_PRIORITA = new VisualName("priority_level", "Livello Priorità", this),
                ID_TIPO = new RelField("id_type", this, EntityModelTipo.I().ID_TIPO)
        );
    }

    public static EntityModelSottotipo I() {
        return I(EntityModelSottotipo.class);
    }
}
