/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

/**
 * deprecated , Entry is preferred
 */
public class Var {

    /**
     * The value.
     */
    public Object value;

    /**
     * The name.
     */
    public String name;

    public Var(Object value, String name) {
        this.value = value;
        this.name = name;
    }

    public Var(Object value) {
        this.value = value;
    }
}
