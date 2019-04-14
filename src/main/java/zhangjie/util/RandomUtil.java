package zhangjie.util;

import java.util.Random;

public class RandomUtil {

	/**
	 * 返回随机性别，男或女
	 * 
	 * @return
	 */
	public static String randomSex() {
		Random random = new Random();
		int i = random.nextInt(2);
		if (i == 0) {
			return "男";
		} else {
			return "女";
		}
	}

	/**
	 * 返回孩子身份，儿子或女儿
	 * 
	 * @return
	 */
	public static String randomChildIdentity() {
		Random random = new Random();
		int i = random.nextInt(2);
		if (i == 0) {
			return "儿子";
		} else {
			return "女儿";
		}
	}

	/**
	 * 返回随机年龄
	 * 
	 * @param startAge 随机年龄的最小值
	 * @param endAge 随机年龄的最大值ֵ
	 * @return
	 */
	public static String randomAge(int startAge, int endAge) {
		Random random = new Random();
		int i = random.nextInt(endAge - startAge) + startAge;
		return Integer.toString(i);
	}
	
	
}
