/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

public class VarAccessors<T> {

    protected T val;

    public VarAccessors(T val) {
        this.val = val;
    }

    public T getValue() {
        return val;
    }

    public void setValue(T val) {
        this.val = val;
    }

    public boolean isTrue() {
        return this.val instanceof Boolean ? (Boolean) val == true : val != null;
    }
}
