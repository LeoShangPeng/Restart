package com.pengshang.restart.util;

public class a {
	public static String l[] = new String[67];

	/**
	 * 字符库初始化方法
	 */
	public static void initLetter() {
		// 大写字母
		for (int i = 0; i < 26; i++) {
			l[i] = String.valueOf((char) (65 + i));
		}
		// 小写字母
		for (int i = 0; i < 26; i++) {
			l[26 + i] = String.valueOf((char) (97 + i));
		}
		// 数字
		for (int i = 0; i < 10; i++) {
			l[57 + i] = String.valueOf((char) (48 + i));
		}
		l[52] = String.valueOf((char) 45);// "-"
		l[53] = String.valueOf((char) 46);// "."
		l[54] = String.valueOf((char) 47);// "/"
		l[55] = String.valueOf((char) 58);// ":"
		l[56] = String.valueOf((char) 95);// "_"
	}

	/**
	 * 数组转化成字符串
	 */
	public static String a(int... params) {
		if (l == null) {
			l = new String[67];
			initLetter();
		}
		if (l.length > 0) {
			if (l[0] == null) {
				initLetter();
			}
		}

		String result = "";
		for (int i = 0; i < params.length; i++) {
			result += l[params[i]];
		}
		return result;
	}

	/**
	 * 查找字符在数组中的位置(工具)
	 */
	public static int findPosition(String ch) {

		for (int i = 0; i < l.length; i++) {
			if (ch.equals(l[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 字符串转化成数组（工具）
	 */
	public static String toNumber(String name) {
		String str = "";
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			int index = findPosition(String.valueOf(ch));
			if (i == 0) {
				str += "a.a(" + index;
				continue;
			}
			if (i == name.length() - 1) {
				str += "," + index + ")";
				continue;
			}
			str += "," + index;
		}
		System.out.println("转化后的字符串\n" + str);
		return str;
	}
}
