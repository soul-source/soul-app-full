package system.emergencynumber.model;

import hwcore.modules.java.src.library.database.FieldModel;
import hwcore.modules.java.src.library.database.fielddecorators.VisualName;
import system.common.MyEntityModel;

/**
 *
 *
 */
public class EntityModelNumbers extends MyEntityModel {

    public FieldModel NUMERO,
            TITOLO;

    protected EntityModelNumbers() {
        super("emergency_number");

        this.createFields(
                NUMERO = new VisualName("number", "Numero", this),
                TITOLO = new VisualName("title", "Titolo", this)
        );
    }

    public static EntityModelNumbers I() {
        return I(EntityModelNumbers.class);
    }
}
