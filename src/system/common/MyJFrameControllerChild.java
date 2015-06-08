/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */

package system.common;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyJFrameControllerChild extends MyJFrameController {

    protected MyJFrameController owner;

    public MyJFrameControllerChild(MyJFrameController owner,MyTableModel tableModel) {
        super(tableModel);
        this.owner = owner;
    }

    public MyJFrameControllerChild(MyJFrameController owner) {
        this(owner,null);
    }

    public MyJFrameController getOwner() {
        return owner;
    }

    @Override
    public void save() {
        super.save();
        owner.refreshList();
    }

    public void dispose() {
        super.dispose();
        if (owner instanceof HasChild)
            ((HasChild)owner).closeChild();
        else
            Logger.getLogger(MyJFrameControllerChild.class.getName()).log(Level.WARNING, "Strange behaviour disposing a child controller");
    }
}
