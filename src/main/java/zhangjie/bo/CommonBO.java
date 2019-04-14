package zhangjie.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import zhangjie.constants.Constants;
import zhangjie.entity.AjaxResult;
import zhangjie.entity.Pager;
import zhangjie.exception.MyException;
import zhangjie.util.AssertUtil;
import zhangjie.util.BeanUtil;

@Service
public class CommonBO {

	/**
	 * 将实体对象转换为Map，便于页面展示
	 * 
	 * @param entity
	 * @param tf
	 * @param ignoredFields
	 * @return
	 */
	public <T> Map<String, String> transform(T entity, EntityTransformer tf, Set<String> ignoredFields) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		// 转换实体对象
		Map<String, String> m = BeanUtil.desc(entity, ignoredFields);
		if (tf != null) {
			tf.transform(m);
		}
		return m;
	}

	/**
	 * 将一个对象列表转换为Map对象列表，用于页面展示
	 * 
	 * @param objectList
	 * @param tf
	 * @param ignoredFields
	 * @return
	 */
	public <T> List<Map<String, String>> transform(List<T> objectList, EntityTransformer tf,
			Set<String> ignoredFields) {
		AssertUtil.argIsNotNull(objectList, "objectList is null");

		// 将对象逐个转换为Map
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for (T object : objectList) {
			Map<String, String> m = BeanUtil.desc(object, ignoredFields);
			if (tf != null) {
				tf.transform(m);
			}
			mapList.add(m);
		}
		return mapList;
	}

	/**
	 * 将对象分页结果转换为Map分页结果，用于页面展示
	 * 
	 * @param pager
	 * @param tf
	 * @param ignoredFields
	 * @return
	 */
	public <T> Pager<Map<String, String>> transform(Pager<T> pager, EntityTransformer tf, Set<String> ignoredFields) {
		AssertUtil.argIsNotNull(pager, "pager is null");
		// 初始化结果对象
		Pager<Map<String, String>> resultPager = new Pager<Map<String, String>>(pager.getPageNum(), pager.getPageSize(),
				pager.getTotal());
		// 逐个转换
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for (T object : pager.getRows()) {
			Map<String, String> m = BeanUtil.desc(object, ignoredFields);
			if (tf != null) {
				tf.transform(m);
			}
			mapList.add(m);
		}
		// 设置转换结果
		resultPager.setRows(mapList);
		return resultPager;
	}

	/**
	 * 构造Ajax成功应答
	 * 
	 * @return
	 */
	public AjaxResult buildSuccessResult() {
		AjaxResult result = new AjaxResult();
		result.setCode(Constants.AJAX_RESULT_SUCCESS_CODE);
		return result;
	}

	/**
	 * 构造Ajax成功应答，放入数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public AjaxResult buildSuccessResult(String key, Object value) {
		AjaxResult result = new AjaxResult();
		result.setCode(Constants.AJAX_RESULT_SUCCESS_CODE);
		result.addData(key, value);
		return result;
	}

	/**
	 * 构造Ajax失败应答
	 * 
	 * @param errorMessage
	 * @return
	 */
	public AjaxResult buildFailResult(String errorMessage) {
		AjaxResult result = new AjaxResult();
		result.setCode(Constants.AJAX_RESULT_FAIL_CODE);
		result.setErrorMessage(errorMessage.trim());
		return result;
	}

	/**
	 * 获取Request范围内指定的对象
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getRequestAttr(String key) {
		Object o = this.getAttr(key, RequestAttributes.SCOPE_REQUEST);
		return o == null ? null : (T) o;
	}

	/**
	 * 在Request范围内设置对象
	 * 
	 * @param key
	 * @param value
	 */
	public void setRequestAttr(String key, Object value) {
		this.setAttr(key, value, RequestAttributes.SCOPE_REQUEST);
	}

	/**
	 * 移除Request范围内指定的对象
	 * 
	 * @param key
	 */
	public void removeRequestAttr(String key) {
		this.removeAttr(key, RequestAttributes.SCOPE_REQUEST);
	}

	/**
	 * 获取Session范围内指定的对象
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getSessionAttr(String key) {
		Object o = this.getAttr(key, RequestAttributes.SCOPE_SESSION);
		return o == null ? null : (T) o;
	}

	/**
	 * 在Session范围内设置对象
	 * 
	 * @param key
	 * @param value
	 */
	public void setSessionAttr(String key, Object value) {
		this.setAttr(key, value, RequestAttributes.SCOPE_SESSION);
	}

	/**
	 * 移除Session范围内指定的对象
	 * 
	 * @param key
	 */
	public void removeSessionAttr(String key) {
		this.removeAttr(key, RequestAttributes.SCOPE_SESSION);

	}

	private Object getAttr(String key, int scope) {
		AssertUtil.strIsNotBlank(key, "key is blank");
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		switch (scope) {
		case RequestAttributes.SCOPE_REQUEST:
			return req.getAttribute(key);
		case RequestAttributes.SCOPE_SESSION:
			return req.getSession().getAttribute(key);
		default:
			throw new MyException("Illegal scope" + scope);
		}
	}

	private void setAttr(String key, Object value, int scope) {
		AssertUtil.strIsNotBlank(key, "key is blank");
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		switch (scope) {
		case RequestAttributes.SCOPE_REQUEST:
			req.setAttribute(key, value);
			break;
		case RequestAttributes.SCOPE_SESSION:
			req.getSession().setAttribute(key, value);
			break;
		default:
			throw new MyException("Illegal scope" + scope);
		}
	}

	private void removeAttr(String key, int scope) {
		AssertUtil.strIsNotBlank(key, "key is blank");
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		switch (scope) {
		case RequestAttributes.SCOPE_REQUEST:
			req.removeAttribute(key);
			break;
		case RequestAttributes.SCOPE_SESSION:
			req.getSession().removeAttribute(key);
			break;
		default:
			throw new MyException("Illegal scope" + scope);
		}
	}
}
