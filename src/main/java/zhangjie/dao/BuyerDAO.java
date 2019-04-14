package zhangjie.dao;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import zhangjie.mapper.BuyerMapper;
import zhangjie.model.Buyer;
import zhangjie.model.BuyerExample;
import zhangjie.model.BuyerExample.Criteria;
import zhangjie.util.AssertUtil;

@Repository("buyerDAO")
public class BuyerDAO extends BaseDAO<Buyer, BuyerExample, BuyerMapper> {

	/**
	 * 增加一条记录
	 * 
	 * @param entity
	 */
	public void add(Buyer entity) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		AssertUtil.argIsNotNull(entity.getIdBuyer(), "idBuyer is null");
		AssertUtil.strIsNotBlank(entity.getNameBuyer(), "name is null");
		AssertUtil.strIsNotBlank(entity.getSexBuyer(), "sex is null");
		AssertUtil.strIsNotBlank(entity.getAgeBuyer(), "age is null");
		AssertUtil.strIsNotBlank(entity.getAddressBuyer(), "address is null");
		this.getMapper().insert(entity);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param idBuyer
	 */
	public void delete(Integer idBuyer) {
		AssertUtil.argIsNotNull(idBuyer, "idBuyer is null");
		this.getMapper().deleteByPrimaryKey(idBuyer);
	}

	/**
	 * 更新一条记录
	 * 
	 * @param entity
	 */
	public void update(Buyer entity) {
		AssertUtil.argIsNotNull(entity, "entity is null");
		AssertUtil.argIsNotNull(entity.getIdBuyer(), "id is null");
		AssertUtil.strIsNotBlank(entity.getNameBuyer(), "name is null");
		AssertUtil.strIsNotBlank(entity.getSexBuyer(), "sex is null");
		AssertUtil.strIsNotBlank(entity.getAgeBuyer(), "age is null");
		AssertUtil.strIsNotBlank(entity.getAddressBuyer(), "address is null");
		this.getMapper().updateByPrimaryKeySelective(entity);
	}

	/**
	 * 根据主键选择一条记录
	 * 
	 * @param idBuyer
	 * @return
	 */
	public Buyer selectByPrimaryKey(Integer idBuyer) {
		AssertUtil.argIsNotNull(idBuyer, "idBuyer is null");
		return this.getMapper().selectByPrimaryKey(idBuyer);
	}

	@Override
	protected BuyerMapper getMapper() {
		// TODO Auto-generated method stub
		return super.findMapper(BuyerMapper.class);
	}

	@Override
	/**
	 * 构筑搜索的条件
	 */
	protected BuyerExample buildExample(Map<String, String> qryParams) {
		// TODO Auto-generated method stub
		BuyerExample example=new BuyerExample();
		Criteria criteria=example.createCriteria();
		String idBuyer=qryParams.get("idBuyer");
		if(StringUtils.isNotBlank(idBuyer)) {
			criteria.andIdBuyerEqualTo(Integer.valueOf(idBuyer));
		}
		String nameBuyer=qryParams.get("nameBuyer");
		if(StringUtils.isNotBlank(nameBuyer)) {
			criteria.andNameBuyerLike("%"+nameBuyer+"%");
		}
		String ageBuyer=qryParams.get("ageBuyer");
		if(StringUtils.isNotBlank(ageBuyer)) {
			criteria.andAgeBuyerEqualTo(ageBuyer);
		}
		String childNameBuyer=qryParams.get("childNameBuyer");
		if(StringUtils.isNotBlank(childNameBuyer)) {
			criteria.andChildNameBuyerLike("%"+childNameBuyer+"%");
		}
		return example;
	}
}
