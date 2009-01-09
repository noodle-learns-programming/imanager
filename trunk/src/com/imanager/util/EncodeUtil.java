package com.imanager.util;

public class EncodeUtil {
	
	public static final String appEncoding = "GBK";
	public static final String dbEncoding = "GBK";
	
	
	/**
	 * 对特殊字符<>"'&等进行预处理，防止HTML解析出错。
	 * 当输出input、area等元素的value属性时，必须预处理。例如：
	 * <input name="foo" value="<%= EncodeUtil.html(value) %>">
	 */
	public static String html(Object obj) {
		if (obj == null) {
			return "";
		}
		
		return obj.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("\'", "&apos;");
	}
	
}
