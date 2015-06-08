/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

import java.util.Map;

public class Entry<K, V> implements Map.Entry {

    private final K key;
    private V value;

    public Entry(K key) {
        this(key, null);
    }

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Object setValue(Object value) {
        Object old = this.value;
        this.value = (V) value;
        return old;
    }

}
