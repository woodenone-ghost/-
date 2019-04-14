package zhangjie.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台给页面返回的Ajax应答数据
 * code=00 表示后台处理成功，可以解析data
 * code!=00 表示后台处理失败，失败原因见errorMessage
 * 
 * @author 60569
 *
 */
public class AjaxResult {
	private String code;
	private String errorMessage;
	private Map<String, Object> data = new HashMap<String, Object>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void addData(String key, Object value) {
		data.put(key, value);
	}

}
