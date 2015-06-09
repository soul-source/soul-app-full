/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.main.controller;

import hwcore.modules.java.src.library.common.MyObject;
import java.util.HashMap;
import java.util.Map;
import system.common.MyController;
import system.common.SessionHandler;
import system.user.model.UserRS;

public class ControllerMain extends MyObject {
    protected Map<String, MyController> controllers;

    protected void init() {
        // create a guest session
        SessionHandler.I().setUser(new UserRS());
        controllers = new HashMap<>();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ControllerMain.I();
            }
        });
    }

    /**
     * Return the singleton instance
     *
     * @return
     */
    public static ControllerMain I() {
        ControllerMain ctrl= I(ControllerMain.class);
        ctrl.init();
        return ctrl;
    }

    public MyController getCtrl(String name) {
        return controllers.get(name);
    }

    public MyController getCtrl(Class c) {
        return controllers.get(c.getName());
    }

    public void setCtrl(MyController ctrl) {
        controllers.put(ctrl.getClass().getName(), ctrl);
    }

    public void setCtrl(String key, MyController ctrl) {
        controllers.put(key, ctrl);
    }
}
