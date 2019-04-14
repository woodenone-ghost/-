package zhangjie.cache;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import zhangjie.entity.EntityConfig;
import zhangjie.entity.EntityFieldConfig;
import zhangjie.exception.MyException;
import zhangjie.util.JsonUtil;

public class EntityConfigCache implements MyCache {
	private static final Logger logger = Logger.getLogger(EntityConfigCache.class);

	private static final EntityConfigCache INSTANCE = new EntityConfigCache();

	private Map<String, EntityConfig> cache = new HashMap<String, EntityConfig>();

	public static EntityConfigCache getInstance() {
		return INSTANCE;
	}

	public static EntityConfig get(String entityId) {
		if (StringUtils.isBlank(entityId)) {
			return null;
		}
		return INSTANCE.cache.get(entityId);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public synchronized void init() {
		// TODO Auto-generated method stub
		logger.info("实体信息配置缓存初始化开始");
		ClassLoader classLoader = this.getClass().getClassLoader();
		URL url = classLoader.getResource("entity_conf");
		String dir = url.getFile();
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File confDir = new File(dir);
		String[] entityConfigFiles = confDir.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".json");
			}
		});
		if (entityConfigFiles != null) {
			Map<String, EntityConfig> tempCache = new HashMap<String, EntityConfig>();
			for (String fileName : entityConfigFiles) {
				try {
					Map confMap = JsonUtil.fromFile(new File(dir + fileName), Map.class);
					EntityConfig entityConfig=new EntityConfig();
					entityConfig.setEntityId(fileName.substring(0, fileName.indexOf(".json")));
					entityConfig.setAbbr(String.valueOf(confMap.get("abbr")).trim());
					entityConfig.setEntityName(String.valueOf(confMap.get("entityName")));
					List<Map> fieldList =(List) confMap.get("fields");
					if(!fieldList.isEmpty()) {
						for (Map fieldConfMap : fieldList) {
							EntityFieldConfig fieldConf=new EntityFieldConfig();
							fieldConf.setfId(String.valueOf(fieldConfMap.get("fId")).trim());
							fieldConf.setfName(String.valueOf(fieldConfMap.get("fNm")).trim());
							entityConfig.addField(fieldConf);
						}
					}
					tempCache.put(entityConfig.getEntityId(), entityConfig);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("解析实体类配置异常");
					throw new MyException("解析实体类配置异常");
				}
			}
			cache=tempCache;
			logger.info(JsonUtil.toJson(cache,true));
			logger.info("实体信息配置缓存初始化完成");
		}
	}

	public void refresh() {
		// TODO Auto-generated method stub
		this.init();
	}

}
