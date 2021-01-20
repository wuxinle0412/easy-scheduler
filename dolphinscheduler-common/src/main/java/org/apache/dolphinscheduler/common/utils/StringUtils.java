package org.apache.dolphinscheduler.common.utils;

/**
 * @author wuxinle
 * @version 1.0
 * @date 2021/1/20 20:32
 */
public class StringUtils {
    public static final String EMPTY = "";

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(String s){
        if (isEmpty(s)) {
            return true;
        }
        return s.trim().length() == 0;
    }

    public static boolean isNotBlank(String s){
        return !isBlank(s);
    }
}
