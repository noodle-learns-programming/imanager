package com.imanager.util;

public class EncodeUtil {
	
	public static final String appEncoding = "GBK";
	public static final String dbEncoding = "GBK";
	
	
	/**
	 * �������ַ�<>"'&�Ƚ���Ԥ������ֹHTML��������
	 * �����input��area��Ԫ�ص�value����ʱ������Ԥ�������磺
	 * <input name="foo" value="<%= EncodeUtil.html(value) %>">
	 */
	public static String html(Object obj) {
		if (obj == null) {
			return "";
		}
		
		return obj.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("\'", "&apos;");
	}
	
}
