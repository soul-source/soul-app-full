package system.user.controller;

import system.common.MyController;
import system.user.model.EntityModelUserProfile;
import system.user.model.HandlerUserQuery;
import system.user.model.TableModelUser;

public class ControllerUser extends MyController {

    public ControllerUser(boolean regMode) {
        super(createTable(regMode));
    }

    public static TableModelUser createTable(boolean regMode) {
        return new TableModelUser(
                new HandlerUserQuery(regMode ? EntityModelUserProfile.I() : EntityModelUserProfile.WithIncarico.I()), regMode, new UserListPermissions(regMode)
        );
    }
}
