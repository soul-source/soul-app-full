package system.emergencynumber.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.CalculatedField;
import hwcore.modules.java.src.library.database.fielddecorators.RelField;
import system.common.MyEntityModel;
import system.emergency.model.EntityModelSubtype;

public class EntityModelNumbersRel extends MyEntityModel {

    public static class WithRel extends EntityModelNumbersRel {

        public FieldModel NUMERI;

        public WithRel() {
            super();

            this.createFields(
                    // TODO QueryBuilder should be used instead of raw sql
                    NUMERI = new CalculatedField(
                            "emergency_numbers",
                            "Numeri d'emergenza",
                            this,
                            "GROUP_CONCAT(CONCAT(`emergency_number`.`number`,'-',`emergency_number`.`title` ) SEPARATOR ', ') AS number"
                    )
            );
        }

        public static WithRel I() {
            return (WithRel) I(WithRel.class).mergeFields(EntityModelNumbers.I());
        }

    }

    public FieldModel NUMERO,
            ID_SUBTYPE;

    protected EntityModelNumbersRel() {
        super("emergency_number_rel");

        this.createFields(
                NUMERO = new RelField("number", this, EntityModelNumbers.I().NUMERO),
                ID_SUBTYPE = new RelField("subtype", this, EntityModelSubtype.I().ID_SOTTOTIPO)
        );
    }

    public static EntityModelNumbersRel I() {
        return I(EntityModelNumbersRel.class);
    }
}
