/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

/**
 * Custom object class for Canonical methods
 *
 */
public abstract class MyObject extends Singleton<MyObject> {

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass();
    }
}
