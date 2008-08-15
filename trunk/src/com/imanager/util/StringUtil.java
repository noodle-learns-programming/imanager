package com.imanager.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author Yang Qiang
 * @since 2008-05-01
 *
 */
public class StringUtil {
	
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }
	
	public static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str);
    }

}
