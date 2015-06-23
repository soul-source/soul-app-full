/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hwcore.modules.java.src.library.common;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

/**
 * Various methods to implement generic features .
 *
 * Ronca
 */
public class StringTools {

    /**
     * Formatted mask.
     *
     * @param strMask the str mask
     * @return the mask formatter
     */
    public static MaskFormatter formattedMask(String strMask) {
        MaskFormatter mask = null;
        try {
            //
            // Create a MaskFormatter for accepting phone number, the # symbol accept
            // only a number. We can also set the empty value with a place holder
            // character.
            //
            mask = new MaskFormatter(strMask);
            mask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mask;
    }

    /**
     * Similar to StringUtils method, but it avoid empty/null values
     *
     * @param sep
     * @param argStrings
     * @return
     */
    public static <T> String join(String sep, T... argStrings) {
        if (argStrings == null) {
            return "";
        }
        String ret = "";

        for (int i = 0; i < argStrings.length - 1; i++) {
            T v1 = argStrings[i];
            if (v1 != null) {
                String v2 = v1.toString();
                if (!v2.isEmpty()) {
                    ret += v2 + sep;
                }
            }
        }

        T v1 = argStrings[argStrings.length - 1];
        if (v1 != null) {
            String v2 = v1.toString();
            if (!v2.isEmpty()) {
                ret += v2;
            }
        }

        return ret;
    }
}
