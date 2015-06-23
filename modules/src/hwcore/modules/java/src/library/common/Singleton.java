/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Singleton<T> {

    private static HashMap<String, Object> instances = new HashMap<>();

    public static <T> T I() {
        throw new IllegalStateException(
                SharedDefines.NOT_IMPLEMENTED_STATIC_METHOD);
    }

    protected static <T> T I(Class<T> c) {
        Object instance = null;
        instance = instances.get(c.getName());
        if (instance == null) {
            try {
                // get default constructor
                Constructor<?> ct[] = c.getDeclaredConstructors();
                ct[0].setAccessible(true);
                instance = ct[0].newInstance();
                instances.put(c.getName(), instance);
            } catch (Exception ex) {
                Logger.getLogger(Singleton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return (T) instance;
    }

    /**
     * Destructor for singleton
     *
     * @param <T>
     * @param c
     */
    protected static <T> void D(Class<T> c) {
        instances.remove(c.getName());
    }

}
