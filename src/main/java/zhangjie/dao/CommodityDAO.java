package zhangjie.dao;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import zhangjie.mapper.CommodityMapper;
import zhangjie.model.CommodityExample.Criteria;
import zhangjie.model.Commodity;
import zhangjie.model.CommodityExample;
import zhangjie.util.AssertUtil;

@Repository("commodityDAO")
public class CommodityDAO extends BaseDAO<Commodity, CommodityExample, CommodityMapper> {

	/**
	 * 增加一条记录
	 * 
	 * @param entity
	 */
	public void add(Commodity entity) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		AssertUtil.strIsNotBlank(entity.getIcon(), "icon is null");
		AssertUtil.strIsNotBlank(entity.getName(), "name is null");
		AssertUtil.strIsNotBlank(entity.getPrice(), "price is null");
		AssertUtil.strIsNotBlank(entity.getCategory(), "category is null");
		AssertUtil.argIsNotNull(entity.getBusinessName(), "businessName is null");
		AssertUtil.argIsNotNull(entity.getCharacteristic(), "characteristic is null");
		entity.setSalesVolume(0);
		entity.setEvaluationPrice("0");
		entity.setEvaluationService("0");
		this.getMapper().insert(entity);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param id
	 */
	public void delete(Integer id) {
		AssertUtil.argIsNotNull(id, "id is null");
		this.getMapper().deleteByPrimaryKey(id);
	}

	/**
	 * 更新一条记录
	 * 
	 * @param entity
	 */
	public void update(Commodity entity) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		AssertUtil.argIsNotNull(entity.getId(), "id is null");
		AssertUtil.strIsNotBlank(entity.getIcon(), "icon is null");
		AssertUtil.strIsNotBlank(entity.getName(), "name is null");
		AssertUtil.strIsNotBlank(entity.getPrice(), "price is null");
		AssertUtil.strIsNotBlank(entity.getCategory(), "category is null");
		AssertUtil.argIsNotNull(entity.getBusinessName(), "businessName is null");
		AssertUtil.argIsNotNull(entity.getCharacteristic(), "characteristic is null");
		AssertUtil.argIsNotNull(entity.getSalesVolume(), "salesVolume is null");
		AssertUtil.strIsNotBlank(entity.getEvaluationPrice(), "evaluationPrice is null");
		AssertUtil.strIsNotBlank(entity.getEvaluationService(), "evaluationService is null");
		this.getMapper().updateByPrimaryKeySelective(entity);
	}

	/**
	 * 根据主键选择一条记录
	 * 
	 * @param id
	 * @return
	 */
	public Commodity selectByPrimaryKey(Integer id) {
		AssertUtil.argIsNotNull(id, "id is null");
		return this.getMapper().selectByPrimaryKey(id);
	}

	@Override
	protected CommodityMapper getMapper() {
		// TODO Auto-generated method stub
		return super.findMapper(CommodityMapper.class);
	}

	@Override
	/**
	 * 构筑搜索的条件
	 */
	protected CommodityExample buildExample(Map<String, String> qryParams) {
		// TODO Auto-generated method stub
		CommodityExample example=new CommodityExample();
		Criteria criteria=example.createCriteria();
		String id=qryParams.get("id");
		if(StringUtils.isNotBlank(id)) {
			criteria.andIdEqualTo(Integer.valueOf(id));
		}
		String name=qryParams.get("name");
		if(StringUtils.isNotBlank(name)) {
			criteria.andNameLike("%"+name+"%");
		}
		String category=qryParams.get("category");
		if(StringUtils.isNotBlank(category)) {
			criteria.andCategoryLike("%"+category+"%");
		}
		String businessName=qryParams.get("businessName");
		if(StringUtils.isNotBlank(businessName)) {
			criteria.andBusinessNameLike("%"+businessName+"%");
		}
		return example;
	}
}
