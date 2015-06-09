/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package system.main.controller;

import system.common.MyController;
import system.common.MyJFrameController;
import system.main.view.JFrameMain;
import system.notification.controller.ControllerJFrameNotifiche;

public class ControllerJFrameMain extends ControllerMain {

    private JFrameMain frame = null;

    @Override
    protected void init() {
        super.init();

        // start login screen
        frame = new JFrameMain();

        this.setCtrl(new ControllerJFrameNotifiche());

        frame.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ControllerJFrameMain.I().init();
            }
        });
    }

    public JFrameMain getMainFrame() {
        return frame;
    }

    /**
     * Return the singleton instance
     *
     * @return
     */
    public static ControllerJFrameMain I() {
        return I(ControllerJFrameMain.class);
    }

    @Override
    public MyJFrameController getCtrl(String name) {
        return (MyJFrameController) super.getCtrl(name);
    }

    @Override
    public MyJFrameController getCtrl(Class c) {
        return (MyJFrameController) controllers.get(c.getName());
    }

    public void setCtrl(MyJFrameController ctrl) {
        controllers.put(ctrl.getClass().getName(), ctrl);
    }

    public void setCtrl(String key, MyJFrameController ctrl) {
        controllers.put(key, ctrl);
    }

    public final void refreshAll() {
        for (MyController controller : controllers.values()) {
            ((MyJFrameController)controller).refreshList();
        }
    }
}
