package zhangjie.util;

import org.apache.commons.lang3.StringUtils;

import zhangjie.exception.MyException;

public class AssertUtil {

	/**
	 * 检验参数是否为null，不是null则报错。可自定义错误信息
	 * 
	 * @param o
	 * @param message
	 */
	public static void argIsNull(Object o, String message) {
		if (o != null) {
			throw new MyException(message);
		}
	}

	/**
	 * 检验参数是否为null，是null则报错。可自定义错误信息
	 * 
	 * @param o
	 * @param message
	 */
	public static void argIsNotNull(Object o, String message) {
		if (o == null) {
			throw new MyException(message);
		}
	}

	/**
	 * 检验参数字符串是否为空，为空则报错。
	 * 
	 * @param str
	 * @param errorMessage
	 */
	public static void strIsNotBlank(String str, String errorMessage) {
		if (StringUtils.isBlank(str)) {
			if (StringUtils.isBlank(errorMessage)) {
				errorMessage = "字符串为空" + str;
			}
			throw new MyException(errorMessage);
		}
	}

	/**
	 * 检验两个字符串是否相等
	 * 
	 * @param str1
	 * @param str2
	 * @param errorMessage
	 */
	public static void strEqual(String str1, String str2, String errorMessage) {
		strIsNotBlank(str1, "str1 is blank");
		strIsNotBlank(str2, "str2 is blank");
		if (!str1.equals(str2)) {
			if (StringUtils.isBlank(errorMessage)) {
				throw new MyException("str1 is not equal to str2");
			} else {
				throw new MyException(errorMessage);
			}
		}
	}
}
