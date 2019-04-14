package zhangjie.util;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class BeanUtil extends BeanUtils {
	// 实体对象转化为Map时，需要过滤的一些字段
	private static final Set<String> COMMON_IGNORE_FIELDS = new HashSet<String>();
	static {
		// 需要过滤的公共字段
		COMMON_IGNORE_FIELDS.add("class");
	}

	static {
		ConvertUtils.register(new Converter() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Object convert(Class clazz, Object value) {
				if (value == null) {
					return null;
				} else {
					if (clazz == java.lang.String.class) {
						if (value instanceof java.util.Date) {
							return value != null ? DateUtil.dateToStr19((Date) value) : "";
						} else {
							return value.toString();
						}
					} else {
						throw new RuntimeException("could not " + value + "to" + clazz.getName());
					}
				}
			}
		}, java.lang.String.class);
	}

	public static Map<String, String> desc(Object o, Set<String> ignoredFields) {
		try {
			@SuppressWarnings("rawtypes")
			Map m = BeanUtils.describe(o);
			if (m != null && !m.isEmpty()) {
				Map<String, String> result = new HashMap<String, String>();
				for (Object k : m.keySet()) {
					String field = String.valueOf(o);
					if (COMMON_IGNORE_FIELDS.contains(field)) {
						continue;
					}
					if (ignoredFields != null && ignoredFields.size() > 0 && ignoredFields.contains(field)) {
						continue;
					}
					result.put(field, String.valueOf(m.get(k)));
				}
				return result;
			}
			return Collections.emptyMap();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("转换对象失败", e);
		}
	}
}
