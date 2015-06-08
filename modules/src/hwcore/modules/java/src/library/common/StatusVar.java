/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

public class StatusVar extends VarAccessors {

    private boolean changed = false;

    public StatusVar(Object val) {
        super(val);
    }

    @Override
    public void setValue(Object val) {
        if (!val.equals(this.getValue())) {
            changed = true;
        }

        super.setValue(val);
    }

    public boolean isChanged() {
        return changed;
    }
}
