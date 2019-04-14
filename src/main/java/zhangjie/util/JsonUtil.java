package zhangjie.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;
import org.aspectj.util.FileUtil;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Json工具类
 * 
 * @author 60569
 *
 */
public class JsonUtil {
	private static final Logger logger = Logger.getLogger(JsonUtil.class);

	private static final ObjectMapper mapper = new ObjectMapper();
	private static final ObjectMapper formattedMapper = new ObjectMapper();
	static {
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
		
		formattedMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		formattedMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		formattedMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		formattedMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
	}

	public static String toJson(Object object,boolean formatOutput) {
		try {
			if(formatOutput) {
				return formattedMapper.writeValueAsString(object);
			}else {
				return mapper.writeValueAsString(object);
			}
		}catch (Exception e) {
			logger.warn(e);
			// TODO: handle exception
		}
		return "{}";
	}

	public static <T> T fromJson(String json, Class<T> t) {
		if (json == null) {
			return null;
		}
		try {
			return mapper.readValue(json, t);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Cannot parse json:<" + json + ">,Object class:<" + t.getName() + ">", e);
		}
		return null;
	}

	public static <T> T fromFile(File f, Class<T> t) throws IOException {
		Validate.notNull(f);
		if (!f.exists() || !f.isFile()) {
			logger.warn("File [" + f + "] dose not exist");
			return null;
		}
		return fromJson(FileUtil.readAsString(f), t);
	}
}
