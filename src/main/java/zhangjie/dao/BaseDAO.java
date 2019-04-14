package zhangjie.dao;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import zhangjie.entity.Pager;
import zhangjie.exception.MyException;
import zhangjie.model.BaseExample;
import zhangjie.util.AssertUtil;

public abstract class BaseDAO<E, EX, MAPPER> {
	public static final Logger logger = Logger.getLogger(BaseDAO.class);

	@Autowired
	private SqlSession session;

	@SuppressWarnings("unchecked")
	public List<E> selectByList(Map<String, String> qryParams) {
		try {
			EX example = this.buildExample(qryParams);
			return (List<E>) this.invokeMapperMethod("selectByExample", new Class<?>[] { example.getClass() },
					new Object[] { example });
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询失败", e);
			throw new MyException("查询失败");
		}
	}

	@SuppressWarnings("unchecked")
	public Pager<E> selectByPage(int pageNum, int pageSize, Map<String, String> qryParams) {
		try {
			Pager<E> p = new Pager<E>(pageNum, pageSize);
			EX example = this.buildExample(qryParams);
			if (example instanceof BaseExample) {
				((BaseExample) example).setStartNumber(p.getStartNum());
				((BaseExample) example).setPageSize(p.getPageSize());
			} else {
				throw new MyException("不支持分页查询");
			}

			p.setTotal((Long) invokeMapperMethod("countByExample", new Class<?>[] { example.getClass() },
					new Object[] { example }));
			p.setRows((List<E>) invokeMapperMethod("selectByExample", new Class<?>[] { example.getClass() },
					new Object[] { example }));
			return p;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("分页查询失败", e);
			throw new MyException("查询失败");
		}
	}

	protected abstract MAPPER getMapper();

	protected abstract EX buildExample(Map<String, String> qryParams);

	protected <M> M findMapper(Class<M> clazz) {
		M mapper = session.getMapper(clazz);
		AssertUtil.argIsNotNull(mapper, "未找到指定的mapper" + clazz.getName());
		return mapper;
	}

	private Object invokeMapperMethod(String name, Class<?>[] parameterTypes, Object[] args) throws Exception {
		MAPPER mapper = this.getMapper();
		Class<?> cls = mapper.getClass();
		Method m = cls.getMethod(name, parameterTypes);
		return m.invoke(mapper, args);
	}
}
