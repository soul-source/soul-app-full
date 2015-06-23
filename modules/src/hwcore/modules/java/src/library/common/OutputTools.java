/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hwcore.modules.java.src.library.common;

public class OutputTools {

    /**
     * Prints the var using newline
     *
     * @param value the value
     * @param varName the var name
     * @param className the name of class to print, can be null or empty to
     * avoid classname prefix
     * @param raw the raw
     * @return the string
     */
    public static String printVar(Object value, String varName, String className, boolean raw) {
        return printVar(value, varName, className, raw, true);
    }

    /**
     * Prints the var.
     *
     * @param value the value
     * @param varName the var name
     * @param className the name of class to print, can be null or empty to
     * avoid classname prefix
     * @param raw the raw
     * @param newLine the new line
     * @return the string
     */
    public static String printVar(Object value, String varName, String className, boolean raw, boolean newLine) {
        return printVars(className, raw, newLine, new Var(value, varName));
    }

    /**
     * Prints the vars.
     *
     * @param className the name of class to print, can be null or empty to
     * avoid classname prefix
     * @param raw the raw
     * @param newLine the new line
     * @param varList the var list
     * @return the string
     */
    public static String printVars(String className, boolean raw, boolean newLine, Var... varList) {
        String res = className == null ? "" : className + (newLine ? "\n" : " ");

        for (Var v : varList) {
            res += raw ? "" : "[";
            if (v.name != null && !raw) {
                res += v.name + ": ";
            }

            res += v.value + (raw ? " " : "] ") + (newLine ? "\n" : "");
        }

        return res;
    }
}
