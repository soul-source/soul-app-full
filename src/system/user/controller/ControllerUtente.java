package system.user.controller;

import system.common.MyController;
import system.user.model.EntityModelUtenteProfile;
import system.user.model.HandlerUtenteQuery;
import system.user.model.TableModelUtente;

public class ControllerUtente extends MyController {

    public ControllerUtente(boolean regMode) {
        super(createTable(regMode));
    }

    public static TableModelUtente createTable(boolean regMode) {
        return new TableModelUtente(
                new HandlerUtenteQuery(regMode ? EntityModelUtenteProfile.I() : EntityModelUtenteProfile.WithIncarico.I()), regMode, new UserListPermissions(regMode)
        );
    }
}
