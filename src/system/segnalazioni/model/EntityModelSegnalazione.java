/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.segnalazioni.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.CalculatedField;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import hwcore.modules.java.src.library.database.fielddecorators.RestrictedField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;
import system.common.PermissionsTypes;
import system.emergenze.model.EntityModelSottotipo;

public class EntityModelSegnalazione extends MyEntityModel {

    public static class WithRel extends EntityModelSegnalazione {

        public FieldModel NUMERI;

        public WithRel() {
            super();

            this.createFields(
                    // TODO QueryBuilder should be used instead of raw sql
                    NUMERI = new CalculatedField(
                            "numeri",
                            "Numeri d'emergenza",
                            this,
                            "GROUP_CONCAT(`Relativo`.`numero` SEPARATOR ', ') AS numeri"
                    )
            );
        }

        public static WithRel I() {
            return (WithRel) I(WithRel.class).mergeFields(EntityModelSottotipo.WithRel.I());
        }

    }

    public FieldModel ID_SEGNALAZIONE,
            COORDINATE,
            DESCRIZIONE,
            NUMERO_COMMENTI,
            DATA_PUBBLICAZIONE,
            LUOGO,
            ID_SOTTOTIPO;

    protected EntityModelSegnalazione() {
        super("Segnalazione"); // table

        this.createFields(
                ID_SEGNALAZIONE = new HiddenField("idSegnalazione", this),
                DESCRIZIONE = new VisualName("Descrizione", "Descrizione", this),
                NUMERO_COMMENTI = new RestrictedField("Numero_commenti", "Numero Commenti", this, PermissionsTypes.readOnly()),
                DATA_PUBBLICAZIONE = new RestrictedField("Data_pubblicazione", "Data pubblicazione", this, PermissionsTypes.readOnly()),
                LUOGO = new VisualName("Luogo", "Luogo", this),
                COORDINATE = new VisualName("Coordinate", "Coordinate", this),
                ID_SOTTOTIPO = new RelField("idSottotipo", this, EntityModelSottotipo.WithRel.I().ID_SOTTOTIPO)
        );
    }

    public static EntityModelSegnalazione I() {
        return I(EntityModelSegnalazione.class);
    }
}
