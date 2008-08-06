package com.imanager.common;

import java.security.MessageDigest;

public class Md5Encode {

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			//System.out.println(md.toString());
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				//System.out.println("i: " + i + ", byte0: " + byte0);
				str[k++] = hexDigits[byte0 >>> 4 & 0xF];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		// MD5_Test aa = new MD5_Test();

		System.out.println("YANGQIANG:" + Md5Encode.MD5("YANGQIANG"));
		System.out.println("yangqiang:" + Md5Encode.MD5("yangqiang"));
	}

}
