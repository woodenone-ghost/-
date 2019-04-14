package zhangjie.entity;

import java.util.HashMap;
import java.util.Map;

public class EntityConfig {

	private String entityId;
	private String abbr;
	private String entityName;
	private Map<String, EntityFieldConfig> fields = new HashMap<String, EntityFieldConfig>();

	public void addField(EntityFieldConfig field) {
		fields.putIfAbsent(field.getfId(), field);
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Map<String, EntityFieldConfig> getFields() {
		return fields;
	}

	public void setFields(Map<String, EntityFieldConfig> fields) {
		this.fields = fields;
	}

}
