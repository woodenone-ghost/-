package zhangjie.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil extends DateUtils {
	private static final String message="date is null";
	public static final String DATE_FORMAT_19 = "yyyy-MM-dd HH:mm:ss";

	public static String dateToStr19(Date date) {
		AssertUtil.argIsNotNull(date, message);
		return formatDate(date,DATE_FORMAT_19);
	}
	
	public static String formatDate(Date date,String format) {
		AssertUtil.argIsNotNull(date, message);
		return new SimpleDateFormat(format).format(date);
	}
}
