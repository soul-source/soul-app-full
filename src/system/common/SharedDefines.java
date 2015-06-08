/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package system.common;

/**
 *
 * @author giuseppe
 */
public abstract class SharedDefines {

    public enum UserLevel {

        GUEST(-1),
        REGISTERED(0),
        EDITOR(1),
        OPERATOR(2),
        ADMINISTRATOR(3);

        private final int lvl;

        private UserLevel(int lvl) {
            this.lvl = lvl;
        }
        
        public int getLvl() {
            return lvl;
        }
    }
}
