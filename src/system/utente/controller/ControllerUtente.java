package system.utente.controller;

import system.common.MyController;
import system.utente.model.EntityModelUtenteProfile;
import system.utente.model.HandlerUtenteQuery;
import system.utente.model.TableModelUtente;

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
