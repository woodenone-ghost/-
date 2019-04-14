package zhangjie.dao.mybatis.interceptor;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import zhangjie.model.BaseExample;

@Intercepts({
		@Signature(args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class,
				BoundSql.class }, method = "query", type = Executor.class),
		@Signature(args = { MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class }, method = "query", type = Executor.class) })
public class PagerInterceptor implements Interceptor {
	@SuppressWarnings("rawtypes")
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement statement = (MappedStatement) args[0];
		//只拦截selectByExample方法，查看是否需要分页查询
		if (statement.getId().endsWith("selectByExample")) {
			Object parameter = args[1];
			if (parameter != null && parameter instanceof BaseExample) {
				BaseExample example = (BaseExample) parameter;
				if (example.isPagination()) {
					Executor executor = (Executor) invocation.getTarget();
					RowBounds rowBounds = (RowBounds) args[2];
					ResultHandler resultHandler = (ResultHandler) args[3];
					CacheKey cacheKey;
					BoundSql boundSql;
					// 4个参数
					if (args.length == 4) {
						boundSql = statement.getBoundSql(parameter);
						cacheKey = executor.createCacheKey(statement, parameter, rowBounds, boundSql);
						// 6个参数
					} else {
						cacheKey = (CacheKey) args[4];
						boundSql = (BoundSql) args[5];
					}
					// 拼接SQL语句
					String pageSql = boundSql.getSql() + " limit " + example.getStartNumber() + " , "
							+ example.getPageSize();
					BoundSql pageBoundSql = new BoundSql(statement.getConfiguration(), pageSql,
							boundSql.getParameterMappings(), parameter);
					List<ParameterMapping> lst = boundSql.getParameterMappings();
					if (lst != null && lst.size() > 0) {
						for (ParameterMapping pm : lst) {
							String nm = pm.getProperty();
							if (boundSql.hasAdditionalParameter(nm)) {
								pageBoundSql.setAdditionalParameter(nm, boundSql.getAdditionalParameter(nm));
							}
						}
					}

					return executor.query(statement, parameter, RowBounds.DEFAULT, resultHandler, cacheKey,
							pageBoundSql);
				}
			}

		}
		return invocation.proceed();
	}

	public Object plugin(Object o) {
		if (o instanceof Executor) {
			return Plugin.wrap(o, this);
		}
		return o;
	}

	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	}
}
