/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.report.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.CalculatedField;
import hwcore.modules.java.src.library.database.fielddecorators.HiddenField;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import hwcore.modules.java.src.library.database.fielddecorators.RestrictedField;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;
import system.common.PermissionsTypes;
import system.emergency.model.EntityModelSottotipo;

public class EntityModelSegnalazione extends MyEntityModel {

    public static class WithRel extends EntityModelSegnalazione {

        public FieldModel NUMERI;

        public WithRel() {
            super();

            this.createFields(
                    // TODO QueryBuilder should be used instead of raw sql
                    NUMERI = new CalculatedField(
                            "number",
                            "Numeri d'emergenza",
                            this,
                            "GROUP_CONCAT(`emergency_number_rel`.`number` SEPARATOR ', ') AS number"
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
        super("report"); // table

        this.createFields(
                ID_SEGNALAZIONE = new HiddenField("id_report", this),
                DESCRIZIONE = new VisualName("description", "Descrizione", this),
                NUMERO_COMMENTI = new RestrictedField("comments_number", "Numero Commenti", this, PermissionsTypes.readOnly()),
                DATA_PUBBLICAZIONE = new RestrictedField("publication_date", "Data pubblicazione", this, PermissionsTypes.readOnly()),
                LUOGO = new VisualName("place", "Luogo", this),
                COORDINATE = new VisualName("coordinates", "Coordinate", this),
                ID_SOTTOTIPO = new RelField("id_subtype", this, EntityModelSottotipo.WithRel.I().ID_SOTTOTIPO)
        );
    }

    public static EntityModelSegnalazione I() {
        return I(EntityModelSegnalazione.class);
    }
}
