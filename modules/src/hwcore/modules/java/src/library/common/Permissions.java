package hwcore.modules.java.src.library.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Permissions {

    public static class Value {

        public final int own;
        public final int other;

        public Value(int own, int other) {
            this.own = own;
            this.other = other;
        }

        public Value(PList[] own, PList[] other) {
            this(createPerms(own), createPerms(other));
        }

        /**
         *
         * @param own permissions on own data
         * @param other extends own permissions to other
         */
        public Value(PList[] own, boolean other) {
            this(own, other ? own : new PList[]{});
        }

        public Value(PList[] own) {
            this(own, false);
        }

    }

    public enum PList {

        DENIED, // 0
        READ, // 1
        ADD, // 2
        UPDATE, // 4
        DELETE, // 8
        ALL; // 16

        private final int val;

        private PList() {
            this.val = (int) Math.pow(2, this.ordinal());
        }

        public int getVal() {
            return val;
        }
    }

    protected Map<Integer, Value> perms; // level - mask
    protected int level;

    public Permissions(int level, Map<Integer, Value> perms) {
        this.perms = perms;
        this.level = level;
    }

    public Permissions() {
        this(0, new HashMap<Integer, Value>());
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    protected void setPerms(Map<Integer, Value> perms) {
        this.perms = perms;
    }

    public Map<Integer, Value> getPerms() {
        return perms;
    }

    public static int createPerms(PList... perms) {
        int res = 0;
        for (PList p : perms) {
            res += p.getVal();
        }

        return res;
    }

    /**
     * This method allow to specify an arbitrary level not related to class
     * instance
     *
     * @param level
     * @param other
     * @param perms
     * @return
     */
    public boolean can(int level, boolean other, PList... perms) {
        // acquire permissions of the user level
        Value v = this.perms.get(level);
        // if no permission specified for user level, then deny everything
        if (v == null) {
            return false;
        }

        ArrayList<Integer> pList = DigitTools.bitMask(other ? v.other : v.own);
        // if there are no permissions or is explicity DENIED, then return false
        if (pList.isEmpty() || pList.contains(PList.DENIED)) {
            return false;
        }

        // if contains ALL, then we can do everything
        if (pList.contains(PList.ALL)) {
            return true;
        }

        // convert passed list to integer list 
        ArrayList<Integer> passedList = new ArrayList<>();
        for (PList val : Arrays.asList(perms)) {
            passedList.add(val.getVal());
        }
        // check if user lvl has all passed permissions
        for (Integer p : passedList) {
            if (!pList.contains(p)) {
                return false;
            }
        }

        return true;
    }

    public boolean can(boolean other, PList... perms) {
        return this.can(this.level, other, perms);
    }

    /**
     * Check in both own and other permissions
     *
     * @param level
     * @param perms
     * @return
     */
    public boolean can(PList... perms) {
        return this.can(true, perms) && this.can(false, perms);
    }
}
