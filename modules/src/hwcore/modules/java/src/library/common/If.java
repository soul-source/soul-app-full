/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

/**
 * Prototype of programmatic conditions
 */
public class If {

    public Conditions _if(Object... _this) {
        return new Conditions().and(_this);
    }

    public static Conditions condition(Object... _this) {
        return new If()._if(_this);
    }

    public class Conditions {

        private boolean condition = true;
        private Object result = this;

        private Conditions() {
        }

        public Conditions or(Object... _that) {
            for (Object o : _that) {
                if (condition == false) {
                    condition = isTrue(o);
                }
            }

            return this;
        }

        public Conditions and(Object... _that) {
            for (Object o : _that) {
                condition = condition == true && isTrue(o);
            }

            return this;
        }

        public Other then(Object _that) {
            if (condition) {
                result = _that;
            }

            return new Other();
        }

        private boolean isTrue(Object o) {
            if (o == null) {
                return false;
            }

            if (o instanceof Boolean) {
                return (Boolean) o;
            } else if (o instanceof String) {
                return !((String) o).isEmpty();
            }

            return false;
        }

        public class Other {

            private Other() {
            }

            public End other(Object _that) {
                if (!condition) {
                    result = _that;
                }

                return new End();
            }

            public Object end() {
                return result;
            }

            public class End {

                private End() {
                }

                public Object end() {
                    return result;
                }
            }
        }
    }
}
